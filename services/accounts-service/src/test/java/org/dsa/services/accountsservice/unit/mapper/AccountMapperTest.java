package org.dsa.services.accountsservice.unit.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.core.sharedstarter.common.constants.Role;
import org.dsa.services.accountsservice.common.fixtures.AccountDtoFixtures;
import org.dsa.services.accountsservice.common.fixtures.AccountFixtures;
import org.dsa.services.accountsservice.dto.AccountDto;
import org.dsa.services.accountsservice.dto.CreateAccountDto;
import org.dsa.services.accountsservice.entity.Account;
import org.dsa.services.accountsservice.mapper.AccountMapper;
import org.junit.jupiter.api.Test;

public class AccountMapperTest {

  private final AccountMapper mapper = new AccountMapper();

  @Test
  void toEntity_shouldMapAllFields() {
    CreateAccountDto createAccountDto = AccountDtoFixtures.createAdminAccountDto();

    Account account = mapper.toEntity(createAccountDto);

    assertThat(account.getUsername()).isEqualTo("admin");
    assertThat(account.getFirstName()).isEqualTo("Steve");
    assertThat(account.getLastName()).isEqualTo("Rogers");
    assertThat(account.getEmail()).isEqualTo("admin@email.com");
    assertThat(account.getPassword()).isEqualTo("<PASSWORD>");
    assertThat(account.getRole()).isEqualTo(Role.ADMIN);
  }

  @Test
  void toDto_shouldMapAllFields() {
    Account account = AccountFixtures.adminAccountPersisted(1L);

    AccountDto dto = mapper.toDto(account);

    assertThat(dto.id()).isEqualTo(1L);
    assertThat(dto.username()).isEqualTo("admin");
    assertThat(dto.firstName()).isEqualTo("Steve");
    assertThat(dto.lastName()).isEqualTo("Rogers");
    assertThat(dto.email()).isEqualTo("admin@email.com");
    assertThat(dto.role()).isEqualTo(Role.ADMIN);
  }
}
