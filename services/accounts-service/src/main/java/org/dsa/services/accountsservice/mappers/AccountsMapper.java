package org.dsa.services.accountsservice.mappers;

import org.dsa.services.accountsservice.common.dtos.AccountDto;
import org.dsa.services.accountsservice.common.dtos.CreateAccountDto;
import org.dsa.services.accountsservice.common.entities.AccountEntity;

public interface AccountsMapper {
    AccountEntity toEntity(CreateAccountDto dto);
    AccountDto toDto(AccountEntity account);
}
