package org.dsa.services.accountsservice.slice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.core.sharedstarter.testing.integration.DataJpaIntegrationTest;
import org.dsa.services.accountsservice.fixture.AccountFixtures;
import org.dsa.services.accountsservice.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountRepositoryIT extends DataJpaIntegrationTest {

  @Autowired AccountRepository accountRepository;

  @Test
  void save_shouldPersistOneAccount() {
    var accountEntity = AccountFixtures.adminAccount();

    accountRepository.save(accountEntity);
    var accounts = accountRepository.findAll();

    assertThat(accounts).hasSize(1);
  }

  @Test
  void delete_shouldPersistAndRemoveOneAccount() {
    var accountEntity = AccountFixtures.adminAccount();

    var accountSaved = accountRepository.save(accountEntity);
    var accounts = accountRepository.findAll();

    assertThat(accounts).hasSize(1);

    accountRepository.delete(accountSaved);

    accounts = accountRepository.findAll();

    assertThat(accounts).hasSize(0);
  }

  @Test
  void findById_shouldReturnAccount() {
    var accountEntity = AccountFixtures.adminAccount();

    var accountSaved = accountRepository.save(accountEntity);
    var account = accountRepository.findById(accountSaved.getId());

    assertThat(account).isPresent();
  }

  @Test
  void findByUsername_shouldReturnAccount() {
    var accountEntity = AccountFixtures.adminAccount();

    accountRepository.save(accountEntity);
    var account = accountRepository.findByUsername(accountEntity.getUsername());

    assertThat(account).isPresent();
  }

  @Test
  void findByEmail_shouldReturnAccount() {
    var accountEntity = AccountFixtures.adminAccount();

    accountRepository.save(accountEntity);
    var account = accountRepository.findByEmail(accountEntity.getEmail());

    assertThat(account).isPresent();
  }

  @Test
  void findByAll_shouldReturnTwoAccounts() {
    var accountEntity = AccountFixtures.adminAccount();
    var managerAccountFixture = AccountFixtures.managerAccount();

    accountRepository.save(accountEntity);
    accountRepository.save(managerAccountFixture);

    var accounts = accountRepository.findAll();

    assertThat(accounts).hasSize(2);
  }

  @Test
  void findAccountByEmail_shouldReturnAccount() {
    var adminAccountFixture = AccountFixtures.adminAccount();
    accountRepository.save(adminAccountFixture);

    var accountFound = accountRepository.findByEmail(adminAccountFixture.getEmail());

    assertThat(accountFound).isPresent();
  }

  @Test
  void save_shouldPersistEntity() {
    var account = AccountFixtures.adminAccount();
    accountRepository.save(account);

    var accounts = accountRepository.findAll();

    assertThat(accounts).hasSize(1);
  }
}
