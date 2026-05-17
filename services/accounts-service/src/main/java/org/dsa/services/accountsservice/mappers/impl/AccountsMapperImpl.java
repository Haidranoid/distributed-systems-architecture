package org.dsa.services.accountsservice.mappers.impl;

import org.dsa.services.accountsservice.common.dtos.AccountDto;
import org.dsa.services.accountsservice.common.dtos.CreateAccountDto;
import org.dsa.services.accountsservice.common.entities.AccountEntity;
import org.dsa.services.accountsservice.mappers.AccountsMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountsMapperImpl implements AccountsMapper {

    @Override
    public AccountEntity toEntity(CreateAccountDto dto) {
        return AccountEntity.builder()
                .username(dto.username())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(dto.password())
                .role(dto.role())
                .build();
    }

    @Override
    public AccountDto toDto(AccountEntity account) {
        return AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .role(account.getRole())
                .build();
    }
}
