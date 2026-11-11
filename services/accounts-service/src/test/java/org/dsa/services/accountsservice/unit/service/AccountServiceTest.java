package org.dsa.services.accountsservice.unit.service;

import org.dsa.services.accountsservice.entity.Account;
import org.dsa.services.accountsservice.fixture.AccountDtoFixtures;
import org.dsa.services.accountsservice.fixture.AccountFixtures;
import org.dsa.services.accountsservice.mapper.AccountMapper;
import org.dsa.services.accountsservice.repository.AccountRepository;
import org.dsa.services.accountsservice.service.AccountService;
import org.dsa.shared.core.exception.AccountNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

  @Mock
  private AccountRepository accountRepository;
  @Mock
  private AccountMapper accountsMapper;

  @InjectMocks
  private AccountService accountService;

  @Test
  void create_whenAccountIsCreated_shouldReturnAccountResponseDto() {
    var createAccountDto = AccountDtoFixtures.createAdminAccountDto();
    var accountEntity = AccountFixtures.adminAccount();
    var accountPersisted = AccountFixtures.adminAccountPersisted(1L);
    var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);

    Mockito.when(accountsMapper.mapCreateAccountRequestToEntity(createAccountDto)).thenReturn(accountEntity);
    Mockito.when(accountRepository.save(accountEntity)).thenReturn(accountPersisted);
    Mockito.when(accountsMapper.mapToAccountResponse(accountPersisted)).thenReturn(accountDto);

    var accountCreated = accountService.create(createAccountDto);

    Assertions.assertNotNull(accountCreated);

    Mockito.verify(accountsMapper).mapCreateAccountRequestToEntity(createAccountDto);
    Mockito.verify(accountRepository).save(accountEntity);
    Mockito.verify(accountsMapper).mapToAccountResponse(accountPersisted);
  }

  @Test
  void findById_whenAccountExists_shouldReturnAccountResponseDto() {
    var accountPersisted = AccountFixtures.adminAccountPersisted(1L);
    var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);

    Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountPersisted));
    Mockito.when(accountsMapper.mapToAccountResponse(accountPersisted)).thenReturn(accountDto);

    var accountFound = accountService.findById(1L);

    Assertions.assertEquals(1L, accountFound.id());
    Assertions.assertEquals("admin", accountFound.username());
    Assertions.assertEquals(accountDto, accountFound);

    Mockito.verify(accountRepository).findById(1L);
    Mockito.verify(accountsMapper).mapToAccountResponse(accountPersisted);
  }

  @Test
  void findById_whenAccountDoesNotExist_shouldThrowException() {
    Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.empty());

    var exception =
        Assertions.assertThrows(AccountNotFoundException.class, () -> accountService.findById(1L));

    Assertions.assertEquals("Account not found: id " + 1L, exception.getMessage());

    Mockito.verify(accountRepository).findById(1L);
    Mockito.verify(accountsMapper, Mockito.never()).mapToAccountResponse(Mockito.any());
  }

  @Test
  void findAll_whenNoAccounts_shouldReturnAnEmptyList() {
    // Arrange
    var emptyAccountList = AccountFixtures.emptyAccountsList();

    Mockito.when(accountRepository.findAll()).thenReturn(emptyAccountList);

    // Act
    var accounts = accountService.findAll();

    // assertions
    Assertions.assertNotNull(accounts);
    Assertions.assertEquals(0, accounts.size());

    Mockito.verify(accountRepository, Mockito.times(1)).findAll();
  }

  @Test
  void findAll_whenAccountsAreEqualToTwo_shouldReturnTwoAccountResponseDto() {
    var accountsPersisted = AccountFixtures.twoAccountsPersisted();

    Mockito.when(accountRepository.findAll()).thenReturn(accountsPersisted);
    Mockito.when(accountsMapper.mapToAccountResponse(accountsPersisted.get(0)))
        .thenReturn(AccountDtoFixtures.adminAccountResponseDto(1L));
    Mockito.when(accountsMapper.mapToAccountResponse(accountsPersisted.get(1)))
        .thenReturn(AccountDtoFixtures.adminAccountResponseDto(2L));
    /*
    mockedAccounts.forEach(account -> {
        Mockito.when(accountsMapper.mapToAccountResponse(account))
                .thenReturn(AccountDtoFixtures.adminAccountResponseDto(1L));
    });
    */

    var accounts = accountService.findAll();

    Assertions.assertEquals(2, accounts.size());
    Assertions.assertEquals(1L, accounts.get(0).id());
    Assertions.assertEquals(2L, accounts.get(1).id());

    Mockito.verify(accountRepository, Mockito.times(1)).findAll();
  }

  @Test
  void update_whenAccountExists_shouldBeUpdatedAndReturnAccountResponseDto() {
    var updateAccountDto = AccountDtoFixtures.updateAccountDtoOne();
    var accountPersisted = AccountFixtures.adminAccountPersisted(1L);
    var accountUpdated = AccountFixtures.adminAccountPersisted(1L);

    Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountPersisted));
    Mockito.when(accountRepository.save(accountPersisted)).thenReturn(accountUpdated);
    Mockito.when(accountsMapper.mapToAccountResponse(accountUpdated))
        .thenReturn(AccountDtoFixtures.adminAccountResponseDto(1L));

    var account = accountService.update(1L, updateAccountDto);

    Assertions.assertEquals(1L, account.id());
    Assertions.assertEquals("admin", account.username());

    Mockito.verify(accountRepository).findById(1L);
    Mockito.verify(accountsMapper).mapToAccountResponse(accountUpdated);
  }

  @Test
  void update_whenAccountDoesNotExist_shouldThrowException() {
    var updateAccountDto = AccountDtoFixtures.updateAccountDtoOne();

    Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.empty());

    var exception =
        Assertions.assertThrows(
            UsernameNotFoundException.class, () -> accountService.update(1L, updateAccountDto));

    Assertions.assertEquals("Account not found", exception.getMessage());

    Mockito.verify(accountRepository, Mockito.never()).save(Mockito.any());
    Mockito.verify(accountsMapper, Mockito.never()).mapToAccountResponse(Mockito.any());
  }

  @Test
  void delete_whenAccountExists_shouldReturnNull() {
    InOrder inOrder = Mockito.inOrder(accountRepository);

    var accountPersisted = AccountFixtures.adminAccountPersisted(1L);

    Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(accountPersisted));

    accountService.delete(1L);

    inOrder.verify(accountRepository).findById(1L);
    inOrder.verify(accountRepository, Mockito.times(1)).delete(accountPersisted);
  }

  @Test
  void delete_whenAccountDoesNotExist_shouldThrowException() {
    Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.empty());

    // TODO: modify service to use custom exception
    var exception =
        Assertions.assertThrows(RuntimeException.class, () -> accountService.delete(1L));

    Assertions.assertEquals("Account not found: id " + 1L, exception.getMessage());

    Mockito.verify(accountRepository, Mockito.atMostOnce()).findById(1L);
    Mockito.verify(accountRepository, Mockito.never()).delete(Mockito.any(Account.class));
  }

  @Test
  void verifyCredentials_whenAccountExists_shouldReturnAccountResponseDto() {
    var authenticateAccountDto = AccountDtoFixtures.authenticateAdminAccountDto();
    var accountPersisted = AccountFixtures.adminAccountPersisted(1L);
    var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);

    Mockito.when(accountRepository.findByUsername(authenticateAccountDto.username()))
        .thenReturn(Optional.of(accountPersisted));
    Mockito.when(accountsMapper.mapToAccountResponse(accountPersisted)).thenReturn(accountDto);

    var accountFound = accountService.verifyCredentials(authenticateAccountDto);

    Assertions.assertEquals(1L, accountFound.id());
    Assertions.assertEquals("admin", accountFound.username());
    Assertions.assertEquals(accountDto, accountFound);

    Mockito.verify(accountRepository).findByUsername("admin");
    Mockito.verify(accountsMapper).mapToAccountResponse(accountPersisted);
  }

  @Test
  void verifyCredentials_whenAccountDoesNotExist_shouldThrowException() {
    var authenticateAccountDto = AccountDtoFixtures.authenticateAdminAccountDto();

    Mockito.when(accountRepository.findByUsername(authenticateAccountDto.username()))
        .thenReturn(Optional.empty());

    var exception =
        Assertions.assertThrows(
            UsernameNotFoundException.class,
            () -> accountService.verifyCredentials(authenticateAccountDto));

    Assertions.assertEquals("Username not found", exception.getMessage());

    Mockito.verify(accountRepository, Mockito.atMostOnce())
        .findByUsername(authenticateAccountDto.username());
    Mockito.verify(accountsMapper, Mockito.never()).mapToAccountResponse(Mockito.any(Account.class));
  }
}
