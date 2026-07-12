package org.dsa.services.accountsservice.fixture;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.dsa.core.sharedstarter.constants.Role;
import org.dsa.services.accountsservice.entity.Account;

public class AccountFixtures {

  public static Account currentSessionAccount(Long id) {
    return Account.builder()
        .id(id)
        .username("ronald")
        .firstName("Ronald")
        .lastName("Flag")
        .email("ronald@email.com")
        .password("<PASSWORD>")
        .role(Role.USER)
        .build();
  }

  public static List<Account> emptyAccountsList() {
    return new ArrayList<>();
  }

  public static Account adminAccount() {
    return Account.builder()
        .username("admin")
        .firstName("Steve")
        .lastName("Rogers")
        .email("admin@email.com")
        .password("<PASSWORD>")
        .role(Role.ADMIN)
        .build();
  }

  public static Account managerAccount() {
    return Account.builder()
        .username("manager")
        .firstName("Black")
        .lastName("Widow")
        .email("manager@email.com")
        .password("<PASSWORD>")
        .role(Role.MANAGER)
        .build();
  }

  public static Account adminAccountPersisted(Long id) {
    return Account.builder()
        .id(id)
        .username("admin")
        .firstName("Steve")
        .lastName("Rogers")
        .email("admin@email.com")
        .password("<PASSWORD>")
        .role(Role.ADMIN)
        .enabled(true)
        .build();
  }

  public static Account managerAccountPersisted(Long id) {
    return Account.builder()
        .id(id)
        .username("manager")
        .firstName("Black")
        .lastName("Widow")
        .email("manager@email.com")
        .password("<PASSWORD>")
        .role(Role.MANAGER)
        .enabled(true)
        .build();
  }

  public static Optional<Account> accountOptional() {
    return Optional.of(
        Account.builder()
            .username("admin")
            .email("admin@email.com")
            .password("<PASSWORD>")
            .firstName("Steve")
            .lastName("Rogers")
            .role(Role.ADMIN)
            .build());
  }

  public static List<Account> twoAccountsPersisted() {
    List<Account> accounts = new ArrayList<>();

    accounts.add(AccountFixtures.adminAccountPersisted(1L));
    accounts.add(AccountFixtures.managerAccountPersisted(2L));

    return accounts;
  }
}
