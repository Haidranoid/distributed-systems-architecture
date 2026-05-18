package org.dsa.services.accountsservice.unit.services;

import org.dsa.services.core.servicesstarter.common.exceptions.AccountNotFoundException;
import org.dsa.services.core.servicesstarter.common.exceptions.UnauthorizedException;
import org.dsa.services.accountsservice.common.fixtures.AccountDtoFixtures;
import org.dsa.services.accountsservice.common.fixtures.AccountEntityFixtures;
import org.dsa.services.accountsservice.mappers.impl.AccountMapperImpl;
import org.dsa.services.accountsservice.repositories.AccountsRepository;
import org.dsa.services.accountsservice.services.impl.AccountServiceImpl;
import org.dsa.services.core.servicesstarter.common.constants.Role;
import org.dsa.services.core.servicesstarter.utils.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountsRepository accountsRepository;
    @Mock
    private AccountMapperImpl accountsMapper;
    @Mock
    private SessionService sessionService;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void me_whenThereIsASession_shouldReturnAuthAccountDto() {
        var username = "ronald";
        var currentSessionDto = AccountDtoFixtures.currentSessionDto(1L);
        var currentSession = AccountEntityFixtures.currentSessionAccount(1L);

        when(sessionService.getCurrentUsername())
                .thenReturn(username);
        when(accountsRepository.findByUsername(username))
                .thenReturn(Optional.of(currentSession));
        when(accountsMapper.toDto(currentSession))
                .thenReturn(currentSessionDto);

        var session = accountService.me();

        assertNotNull(session);
        assertEquals(1L, session.id());
        assertEquals("ronald", session.username());
        assertEquals("ronald@email.com", session.email());
        assertEquals(Role.USER, session.role());
    }

    @Test
    void me_whenThereIsNotASession_shouldThrowException() {
        when(sessionService.getCurrentUsername())
                .thenReturn(null);

        var exception = assertThrows(UnauthorizedException.class,
                () -> accountService.me());

        assertEquals(UnauthorizedException.class, exception.getClass());
    }

    @Test
    void findAll_whenNoAccounts_shouldReturnAnEmptyList() {
        // Arrange
        var emptyAccountList = AccountEntityFixtures.emptyAccountsList();

        when(accountsRepository.findAll())
                .thenReturn(emptyAccountList);

        // Act
        var accounts = accountService.findAll();

        // Assert
        assertNotNull(accounts);
        assertEquals(0, accounts.size());
    }

    @Test
    void findAll_whenAccountsAreEqualToTwo_shouldReturnTwoAccountResponseDto() {
        var accountsPersisted = AccountEntityFixtures.twoAccountsPersisted();

        when(accountsRepository.findAll())
                .thenReturn(accountsPersisted);
        when(accountsMapper.toDto(accountsPersisted.get(0)))
                .thenReturn(AccountDtoFixtures.adminAccountResponseDto(1L));
        when(accountsMapper.toDto(accountsPersisted.get(1)))
                .thenReturn(AccountDtoFixtures.adminAccountResponseDto(2L));
        /*
        mockedAccounts.forEach(account -> {
            when(accountsMapper.toDto(account))
                    .thenReturn(AccountDtoFixtures.adminAccountResponseDto(1L));
        });
        */

        var accounts = accountService.findAll();

        assertEquals(2, accounts.size());
        assertEquals(1L, accounts.get(0).id());
        assertEquals(2L, accounts.get(1).id());
    }

    @Test
    void findById_whenAccountExists_shouldReturnAccountResponseDto() {
        var accountPersisted = AccountEntityFixtures.adminAccountPersisted(1L);
        var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);

        when(accountsRepository.findById(1L))
                .thenReturn(Optional.of(accountPersisted));
        when(accountsMapper.toDto(accountPersisted))
                .thenReturn(accountDto);

        var accountFound = accountService.findById(1L);

        assertEquals(1L, accountFound.id());
        assertEquals("admin", accountFound.username());
    }

    @Test
    void findById_whenAccountDoesNotExist_shouldThrowException() {
        when(accountsRepository.findById(1L))
                .thenReturn(Optional.empty());

        var exception = assertThrows(AccountNotFoundException.class,
                () -> accountService.findById(1L));

        assertEquals("Account not found: id " + 1L, exception.getMessage());
        verify(accountsMapper,never()).toDto(any());
    }

    @Test
    void create_whenAccountIsCreated_shouldReturnAccountResponseDto() {
        var createAccountDto = AccountDtoFixtures.createAdminAccountDto();
        var accountEntity = AccountEntityFixtures.adminAccount();
        var accountPersisted = AccountEntityFixtures.adminAccountPersisted(1L);
        var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);

        when(accountsMapper.toEntity(createAccountDto))
                .thenReturn(accountEntity);
        when(accountsRepository.save(accountEntity))
                .thenReturn(accountPersisted);
        when(accountsMapper.toDto(accountPersisted))
                .thenReturn(accountDto);

        var accountCreated = accountService.create(createAccountDto);

        assertNotNull(accountCreated);
        verify(accountsMapper).toEntity(createAccountDto);
        verify(accountsRepository).save(accountEntity);
        verify(accountsMapper).toDto(accountPersisted);
    }

    @Test
    void update_whenAccountExists_shouldBeUpdatedAndReturnAccountResponseDto() {
        var updateAccountDto = AccountDtoFixtures.updateAccountDtoOne();
        var accountPersisted = AccountEntityFixtures.adminAccountPersisted(1L);
        var accountUpdated = AccountEntityFixtures.adminAccountPersisted(1L);

        when(accountsRepository.findById(1L))
                .thenReturn(Optional.of(accountPersisted));
        when(accountsRepository.save(accountPersisted))
                .thenReturn(accountUpdated);
        when(accountsMapper.toDto(accountUpdated))
                .thenReturn(AccountDtoFixtures.adminAccountResponseDto(1L));

        var account = accountService.update(1L, updateAccountDto);

        assertEquals(1L, account.id());
        assertEquals("admin", account.username());
    }

    @Test
    void update_whenAccountDoesNotExist_shouldThrowException() {
        var updateAccountDto = AccountDtoFixtures.updateAccountDtoOne();

        when(accountsRepository.findById(1L))
                .thenReturn(Optional.empty());

        var exception = assertThrows(UsernameNotFoundException.class,
                () -> accountService.update(1L, updateAccountDto));

        assertEquals("Account not found", exception.getMessage());
        verify(accountsRepository,never()).save(any());
        verify(accountsMapper,never()).toDto(any());
    }

    @Test
    void delete_whenAccountExists_shouldReturnNull() {
        InOrder inOrder = inOrder(accountsRepository);

        var accountPersisted = AccountEntityFixtures.adminAccountPersisted(1L);

        when(accountsRepository.findById(1L))
                .thenReturn(Optional.of(accountPersisted));

        accountService.delete(1L);

        inOrder.verify(accountsRepository).findById(1L);
        inOrder.verify(accountsRepository, times(1)).delete(accountPersisted);
    }

    @Test
    void delete_whenAccountDoesNotExist_shouldThrowException() {
        when(accountsRepository.findById(1L))
                .thenReturn(Optional.empty());

        //TODO: modify service to use custom exception
        var exception = assertThrows(RuntimeException.class, () -> accountService.delete(1L));

        assertEquals("Account not found: id " + 1L, exception.getMessage());
        verify(accountsRepository, never()).delete(any());
    }

    @Test
    void verifyCredentials_whenAccountExists_shouldReturnAccountResponseDto() {
        var authenticateAccountDto = AccountDtoFixtures.authenticateAdminAccountDto();
        var accountPersisted = AccountEntityFixtures.adminAccountPersisted(1L);
        var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);

        when(accountsRepository.findByUsername(authenticateAccountDto.username()))
                .thenReturn(Optional.of(accountPersisted));
        when(accountsMapper.toDto(accountPersisted))
                .thenReturn(accountDto);

        var accountFound = accountService.verifyCredentials(authenticateAccountDto);

        assertEquals(1L, accountFound.id());
        assertEquals("admin", accountFound.username());
    }

    @Test
    void verifyCredentials_whenAccountDoesNotExist_shouldThrowException() {
        var authenticateAccountDto = AccountDtoFixtures.authenticateAdminAccountDto();

        when(accountsRepository.findByUsername(authenticateAccountDto.username()))
                .thenReturn(Optional.empty());

        var exception = assertThrows(UsernameNotFoundException.class,
                () -> accountService.verifyCredentials(authenticateAccountDto));

        assertEquals("Username not found", exception.getMessage());
        verify(accountsMapper, never()).toDto(any());
    }

}