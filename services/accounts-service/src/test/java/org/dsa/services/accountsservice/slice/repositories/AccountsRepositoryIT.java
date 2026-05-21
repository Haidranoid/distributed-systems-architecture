package org.dsa.services.accountsservice.slice.repositories;

import org.dsa.services.accountsservice.common.fixtures.AccountEntityFixtures;

import org.dsa.services.accountsservice.repositories.AccountsRepository;
import org.dsa.core.sharedstarter.testing.integration.DataJpaIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountsRepositoryIT extends DataJpaIntegrationTest {

    @Autowired
    AccountsRepository accountsRepository;

    @Test
    void save_shouldPersistOneAccount() {
        var accountEntity = AccountEntityFixtures.adminAccount();

        accountsRepository.save(accountEntity);
        var accounts = accountsRepository.findAll();

        assertThat(accounts).hasSize(1);
    }

    @Test
    void delete_shouldPersistAndRemoveOneAccount() {
        var accountEntity = AccountEntityFixtures.adminAccount();

        var accountSaved = accountsRepository.save(accountEntity);
        var accounts = accountsRepository.findAll();

        assertThat(accounts).hasSize(1);

        accountsRepository.delete(accountSaved);

        accounts = accountsRepository.findAll();

        assertThat(accounts).hasSize(0);
    }

    @Test
    void findById_shouldReturnAccount() {
        var accountEntity = AccountEntityFixtures.adminAccount();

        var accountSaved = accountsRepository.save(accountEntity);
        var account = accountsRepository.findById(accountSaved.getId());

        assertThat(account).isPresent();
    }

    @Test
    void findByUsername_shouldReturnAccount() {
        var accountEntity = AccountEntityFixtures.adminAccount();

        accountsRepository.save(accountEntity);
        var account = accountsRepository.findByUsername(accountEntity.getUsername());

        assertThat(account).isPresent();
    }

    @Test
    void findByEmail_shouldReturnAccount() {
        var accountEntity = AccountEntityFixtures.adminAccount();

        accountsRepository.save(accountEntity);
        var account = accountsRepository.findByEmail(accountEntity.getEmail());

        assertThat(account).isPresent();
    }

    @Test
    void findByAll_shouldReturnTwoAccounts() {
        var accountEntity = AccountEntityFixtures.adminAccount();
        var managerAccountFixture = AccountEntityFixtures.managerAccount();

        accountsRepository.save(accountEntity);
        accountsRepository.save(managerAccountFixture);

        var accounts = accountsRepository.findAll();

        assertThat(accounts).hasSize(2);
    }

    @Test
    void findAccountByEmail_shouldReturnAccount(){
        var adminAccountFixture = AccountEntityFixtures.adminAccount();
        accountsRepository.save(adminAccountFixture);

        var accountFound = accountsRepository.findByEmail(adminAccountFixture.getEmail());

        assertThat(accountFound).isPresent();
    }

    @Test
    void save_shouldPersistEntity(){
        var account = AccountEntityFixtures.adminAccount();
        accountsRepository.save(account);

        var accounts = accountsRepository.findAll();

        assertThat(accounts).hasSize(1);
    }

}
