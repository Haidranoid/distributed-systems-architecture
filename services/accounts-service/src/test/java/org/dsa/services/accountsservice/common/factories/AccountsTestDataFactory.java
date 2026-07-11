package org.dsa.services.accountsservice.common.factories;

import net.datafaker.Faker;
import org.dsa.core.sharedstarter.common.constants.Role;
import org.dsa.services.accountsservice.entity.Account;

public class AccountsTestDataFactory {

  private static final Faker faker = new Faker();

  public static Account randomAccount() {
    return Account.builder()
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
