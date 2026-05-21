package org.dsa.services.accountsservice.common.fixtures;

import org.dsa.core.sharedstarter.common.constants.Role;
import org.dsa.services.accountsservice.common.entities.AccountEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountEntityFixtures {

    public static AccountEntity currentSessionAccount(Long id) {
        return AccountEntity.builder()
                .id(id)
                .username("ronald")
                .firstName("Ronald")
                .lastName("Flag")
                .email("ronald@email.com")
                .password("<PASSWORD>")
                .role(Role.USER)
                .build();
    }

    public static List<AccountEntity> emptyAccountsList() {
        return new ArrayList<>();
    }

    public static AccountEntity adminAccount() {
        return AccountEntity.builder()
                .username("admin")
                .firstName("Steve")
                .lastName("Rogers")
                .email("admin@email.com")
                .password("<PASSWORD>")
                .role(Role.ADMIN)
                .build();
    }

    public static AccountEntity managerAccount() {
        return AccountEntity.builder()
                .username("manager")
                .firstName("Black")
                .lastName("Widow")
                .email("manager@email.com")
                .password("<PASSWORD>")
                .role(Role.MANAGER)
                .build();
    }

    public static AccountEntity adminAccountPersisted(Long id) {
        return AccountEntity.builder()
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

    public static AccountEntity managerAccountPersisted(Long id) {
        return AccountEntity.builder()
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

    public static Optional<AccountEntity> accountOptional() {
        return Optional.of(AccountEntity.builder()
                .username("admin")
                .email("admin@email.com")
                .password("<PASSWORD>")
                .firstName("Steve")
                .lastName("Rogers")
                .role(Role.ADMIN)
                .build()
        );
    }

    public static List<AccountEntity> twoAccountsPersisted() {
        List<AccountEntity> accounts = new ArrayList<>();

        accounts.add(AccountEntityFixtures.adminAccountPersisted(1L));
        accounts.add(AccountEntityFixtures.managerAccountPersisted(2L));

        return accounts;
    }
}
