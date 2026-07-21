package org.dsa.services.accountsservice.mapper;

import org.dsa.services.accountsservice.entity.Account;
import org.dsa.services.accountsservice.request.CreateAccountRequest;
import org.dsa.services.accountsservice.response.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

  public Account toEntity(CreateAccountRequest dto) {
    return Account.builder()
        .username(dto.username())
        .firstName(dto.firstName())
        .lastName(dto.lastName())
        .email(dto.email())
        .password(dto.password())
        .role(dto.role())
        .build();
  }

  public AccountResponse toDto(Account account) {
    return AccountResponse.builder()
        .id(account.getId())
        .username(account.getUsername())
        .firstName(account.getFirstName())
        .lastName(account.getLastName())
        .email(account.getEmail())
        .role(account.getRole())
        .build();
  }
}
