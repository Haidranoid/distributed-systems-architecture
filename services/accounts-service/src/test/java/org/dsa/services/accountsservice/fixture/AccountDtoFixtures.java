package org.dsa.services.accountsservice.fixture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.dsa.core.sharedstarter.common.constants.Role;
import org.dsa.services.accountsservice.dto.*;

public class AccountDtoFixtures {

  public static AccountDto meAccountResponseDto(Long id) {
    return AccountDto.builder()
        .id(id)
        .username("ronald")
        .email("ronald@email.com")
        .firstName("Ronald")
        .lastName("Flag")
        .role(Role.USER)
        .build();
  }

  public static AccountDto currentSessionDto(Long id) {
    return AccountDto.builder()
        .id(id)
        .username("ronald")
        .email("ronald@email.com")
        .firstName("Ronald")
        .lastName("Flag")
        .role(Role.USER)
        .build();
  }

  public static List<AccountDto> emptyAccountResponseDtoList() {
    return Collections.emptyList();
  }

  public static AccountDto adminAccountResponseDto(Long id) {
    return AccountDto.builder()
        .id(id)
        .username("admin")
        .email("admin@email.com")
        .firstName("Steve")
        .lastName("Rogers")
        .role(Role.ADMIN)
        .build();
  }

  public static CreateAccountDto createAdminAccountDto() {
    return CreateAccountDto.builder()
        .username("admin")
        .firstName("Steve")
        .lastName("Rogers")
        .email("admin@email.com")
        .password("<PASSWORD>")
        .role(Role.ADMIN)
        .build();
  }

  public static VerifyAccountCredentialsDto authenticateAccountDto() {
    return VerifyAccountCredentialsDto.builder().username("admin").password("<PASSWORD>").build();
  }

  public static CreateAccountDto createManagerAccountDto() {
    return CreateAccountDto.builder()
        .username("manager")
        .firstName("Black")
        .lastName("Widow")
        .email("manager@email.com")
        .password("<PASSWORD>")
        .role(Role.MANAGER)
        .build();
  }

  public static UpdateAccountDto updateAccountDtoOne() {
    return UpdateAccountDto.builder()
        .firstName("Steve")
        .lastName("Rogers")
        .email("manager@email.com")
        // .password("<PASSWORD>")
        .role(Role.ADMIN)
        .build();
  }

  public static AccountDto updatedAccountDtoOne(Long id) {
    return AccountDto.builder()
        .id(id)
        .username("updatedAccount")
        .firstName("Steve")
        .lastName("Rogers")
        .email("manager@email.com")
        .role(Role.ADMIN)
        .build();
  }

  public static UpdateAccountPasswordDto changePasswordAccountDto() {
    return UpdateAccountPasswordDto.builder()
        .currentPassword("<PASSWORD>")
        .confirmationPassword("<PASSWORD>")
        .newPassword("<NEW-PASSWORD>")
        .build();
  }

  public static VerifyAccountCredentialsDto authenticateAdminAccountDto() {
    return VerifyAccountCredentialsDto.builder().username("admin").password("<PASSWORD>").build();
  }

  public static List<AccountDto> twoAccountResponseDto() {
    List<AccountDto> accountsDto = new ArrayList<>();

    accountsDto.add(AccountDtoFixtures.adminAccountResponseDto(1L));
    accountsDto.add(AccountDtoFixtures.adminAccountResponseDto(2L));

    return accountsDto;
  }
}
