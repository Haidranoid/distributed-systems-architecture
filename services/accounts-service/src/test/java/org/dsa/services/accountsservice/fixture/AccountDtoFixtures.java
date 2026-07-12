package org.dsa.services.accountsservice.fixture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.dsa.core.sharedstarter.constants.Role;
import org.dsa.services.accountsservice.request.CreateAccountRequest;
import org.dsa.services.accountsservice.request.UpdateAccountPasswordRequest;
import org.dsa.services.accountsservice.request.UpdateAccountRequest;
import org.dsa.services.accountsservice.request.VerifyAccountCredentialsRequest;
import org.dsa.services.accountsservice.response.AccountResponse;

public class AccountDtoFixtures {

  public static AccountResponse meAccountResponseDto(Long id) {
    return AccountResponse.builder()
        .id(id)
        .username("ronald")
        .email("ronald@email.com")
        .firstName("Ronald")
        .lastName("Flag")
        .role(Role.USER)
        .build();
  }

  public static AccountResponse currentSessionDto(Long id) {
    return AccountResponse.builder()
        .id(id)
        .username("ronald")
        .email("ronald@email.com")
        .firstName("Ronald")
        .lastName("Flag")
        .role(Role.USER)
        .build();
  }

  public static List<AccountResponse> emptyAccountResponseDtoList() {
    return Collections.emptyList();
  }

  public static AccountResponse adminAccountResponseDto(Long id) {
    return AccountResponse.builder()
        .id(id)
        .username("admin")
        .email("admin@email.com")
        .firstName("Steve")
        .lastName("Rogers")
        .role(Role.ADMIN)
        .build();
  }

  public static CreateAccountRequest createAdminAccountDto() {
    return CreateAccountRequest.builder()
        .username("admin")
        .firstName("Steve")
        .lastName("Rogers")
        .email("admin@email.com")
        .password("<PASSWORD>")
        .role(Role.ADMIN)
        .build();
  }

  public static VerifyAccountCredentialsRequest authenticateAccountDto() {
    return VerifyAccountCredentialsRequest.builder()
        .username("admin")
        .password("<PASSWORD>")
        .build();
  }

  public static CreateAccountRequest createManagerAccountDto() {
    return CreateAccountRequest.builder()
        .username("manager")
        .firstName("Black")
        .lastName("Widow")
        .email("manager@email.com")
        .password("<PASSWORD>")
        .role(Role.MANAGER)
        .build();
  }

  public static UpdateAccountRequest updateAccountDtoOne() {
    return UpdateAccountRequest.builder()
        .firstName("Steve")
        .lastName("Rogers")
        .email("manager@email.com")
        // .password("<PASSWORD>")
        .role(Role.ADMIN)
        .build();
  }

  public static AccountResponse updatedAccountDtoOne(Long id) {
    return AccountResponse.builder()
        .id(id)
        .username("updatedAccount")
        .firstName("Steve")
        .lastName("Rogers")
        .email("manager@email.com")
        .role(Role.ADMIN)
        .build();
  }

  public static UpdateAccountPasswordRequest changePasswordAccountDto() {
    return UpdateAccountPasswordRequest.builder()
        .currentPassword("<PASSWORD>")
        .confirmationPassword("<PASSWORD>")
        .newPassword("<NEW-PASSWORD>")
        .build();
  }

  public static VerifyAccountCredentialsRequest authenticateAdminAccountDto() {
    return VerifyAccountCredentialsRequest.builder()
        .username("admin")
        .password("<PASSWORD>")
        .build();
  }

  public static List<AccountResponse> twoAccountResponseDto() {
    List<AccountResponse> accountsDto = new ArrayList<>();

    accountsDto.add(AccountDtoFixtures.adminAccountResponseDto(1L));
    accountsDto.add(AccountDtoFixtures.adminAccountResponseDto(2L));

    return accountsDto;
  }
}
