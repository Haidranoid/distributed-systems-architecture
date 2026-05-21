package org.dsa.services.accountsservice.unit.mappers;

import org.dsa.services.accountsservice.common.dtos.AccountDto;
import org.dsa.services.accountsservice.common.dtos.CreateAccountDto;
import org.dsa.services.accountsservice.common.fixtures.AccountDtoFixtures;
import org.dsa.services.accountsservice.common.fixtures.AccountEntityFixtures;
import org.dsa.services.accountsservice.mappers.AccountMapper;
import org.dsa.services.accountsservice.mappers.impl.AccountMapperImpl;
import org.dsa.core.sharedstarter.common.constants.Role;
import org.dsa.services.accountsservice.common.entities.AccountEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountMapperTest {

    private final AccountMapper mapper = new AccountMapperImpl();

    @Test
    void toEntity_shouldMapAllFields() {
        CreateAccountDto createAccountDto = AccountDtoFixtures.createAdminAccountDto();

        AccountEntity accountEntity = mapper.toEntity(createAccountDto);

        assertThat(accountEntity.getUsername()).isEqualTo("admin");
        assertThat(accountEntity.getFirstName()).isEqualTo("Steve");
        assertThat(accountEntity.getLastName()).isEqualTo("Rogers");
        assertThat(accountEntity.getEmail()).isEqualTo("admin@email.com");
        assertThat(accountEntity.getPassword()).isEqualTo("<PASSWORD>");
        assertThat(accountEntity.getRole()).isEqualTo(Role.ADMIN);
    }

    @Test
    void toDto_shouldMapAllFields() {
        AccountEntity accountEntity = AccountEntityFixtures.adminAccountPersisted(1L);

        AccountDto dto = mapper.toDto(accountEntity);

        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.username()).isEqualTo("admin");
        assertThat(dto.firstName()).isEqualTo("Steve");
        assertThat(dto.lastName()).isEqualTo("Rogers");
        assertThat(dto.email()).isEqualTo("admin@email.com");
        assertThat(dto.role()).isEqualTo(Role.ADMIN);
    }
}
