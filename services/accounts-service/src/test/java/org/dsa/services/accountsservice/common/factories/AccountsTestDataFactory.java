package org.dsa.services.accountsservice.common.factories;

import org.dsa.services.core.servicesstarter.common.constants.Role;
import org.dsa.services.accountsservice.common.entities.AccountEntity;
import net.datafaker.Faker;

public class AccountsTestDataFactory {

    private static final Faker faker = new Faker();

    public static AccountEntity randomAccount() {
        return AccountEntity.builder()
                .id(faker.number().randomNumber())
                .username(faker.credentials().username())
                .email(faker.internet().emailAddress())
                .password(faker.credentials().password())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .role(Role.USER)
                .build();
    }
}
