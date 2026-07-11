package org.dsa.services.accountsservice.slice.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.core.sharedstarter.testing.integration.DataJpaIntegrationTest;
import org.dsa.services.accountsservice.common.fixtures.AccountEntityFixtures;
import org.dsa.services.accountsservice.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountRepositoryIT extends DataJpaIntegrationTest {

  @Autowired
  AccountRepository accountRepository;

  @Test
  void save_shouldPersistOneAccount() {
    var accountEntity = AccountEntityFixtures.adminAccount();

    accountRepository.save(accountEntity);
    var accounts = accountRepository.findAll();

    assertThat(accounts).hasSize(1);
  }

  @Test
  void delete_shouldPersistAndRemoveOneAccount() {
    var accountEntity = AccountEntityFixtures.adminAccount();

    var accountSaved = accountRepository.save(accountEntity);
    var accounts = accountRepository.findAll();

    assertThat(accounts).hasSize(1);

    accountRepository.delete(accountSaved);

    accounts = accountRepository.findAll();

    assertThat(accounts).hasSize(0);
  }

  @Test
  void findById_shouldReturnAccount() {
    var accountEntity = AccountEntityFixtures.adminAccount();

    var accountSaved = accountRepository.save(accountEntity);
    var account = accountRepository.findById(accountSaved.getId());

    assertThat(account).isPresent();
  }

  @Test
  void findByUsername_shouldReturnAccount() {
    var accountEntity = AccountEntityFixtures.adminAccount();

    accountRepository.save(accountEntity);
    var account = accountRepository.findByUsername(accountEntity.getUsername());

    assertThat(account).isPresent();
  }

  @Test
  void findByEmail_shouldReturnAccount() {
    var accountEntity = AccountEntityFixtures.adminAccount();

    accountRepository.save(accountEntity);
    var account = accountRepository.findByEmail(accountEntity.getEmail());

    assertThat(account).isPresent();
  }

  @Test
  void findByAll_shouldReturnTwoAccounts() {
    var accountEntity = AccountEntityFixtures.adminAccount();
    var managerAccountFixture = AccountEntityFixtures.managerAccount();

    accountRepository.save(accountEntity);
    accountRepository.save(managerAccountFixture);

    var accounts = accountRepository.findAll();

    assertThat(accounts).hasSize(2);
  }

  @Test
  void findAccountByEmail_shouldReturnAccount() {
    var adminAccountFixture = AccountEntityFixtures.adminAccount();
    accountRepository.save(adminAccountFixture);

    var accountFound = accountRepository.findByEmail(adminAccountFixture.getEmail());

    assertThat(accountFound).isPresent();
  }

  @Test
  void save_shouldPersistEntity() {
    var account = AccountEntityFixtures.adminAccount();
    accountRepository.save(account);

    var accounts = accountRepository.findAll();

    assertThat(accounts).hasSize(1);
  }
}
