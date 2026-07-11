package org.dsa.services.accountsservice.mappers;

import org.dsa.services.accountsservice.dtos.AccountDto;
import org.dsa.services.accountsservice.dtos.CreateAccountDto;
import org.dsa.services.accountsservice.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

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
