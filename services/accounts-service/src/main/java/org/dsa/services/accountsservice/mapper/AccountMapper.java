package org.dsa.services.accountsservice.mapper;

import org.dsa.services.accountsservice.dto.AccountDto;
import org.dsa.services.accountsservice.dto.CreateAccountDto;
import org.dsa.services.accountsservice.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

  public Account toEntity(CreateAccountDto dto) {
    return Account.builder()
        .username(dto.username())
        .firstName(dto.firstName())
        .lastName(dto.lastName())
        .email(dto.email())
        .password(dto.password())
        .role(dto.role())
        .build();
  }

  public AccountDto toDto(Account account) {
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
