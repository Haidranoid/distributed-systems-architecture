package org.dsa.services.accountsservice.unit.services;

import org.dsa.core.sharedstarter.common.constants.Role;
import org.dsa.core.sharedstarter.common.exceptions.AccountNotFoundException;
import org.dsa.core.sharedstarter.common.exceptions.UnauthorizedException;
import org.dsa.core.sharedstarter.utils.SessionService;
import org.dsa.services.accountsservice.common.fixtures.AccountDtoFixtures;
import org.dsa.services.accountsservice.common.fixtures.AccountEntityFixtures;
import org.dsa.services.accountsservice.mappers.impl.AccountMapperImpl;
import org.dsa.services.accountsservice.repositories.AccountsRepository;
import org.dsa.services.accountsservice.services.impl.MeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeServiceImplTest {

    @Mock
    private AccountsRepository accountsRepository;
    @Mock
    private AccountMapperImpl accountsMapper;
    @Mock
    private SessionService sessionService;

    @InjectMocks
    private MeServiceImpl meService;

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

        var session = meService.me();

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
                () -> meService.me());

        assertEquals(UnauthorizedException.class, exception.getClass());
    }

    @Test
    void me_whenThereIsASessionButIsNotFoundInDB_shouldThrowException() {
        var username = "ronald";

        when(sessionService.getCurrentUsername())
                .thenReturn(username);
        when(accountsRepository.findByUsername(username))
                .thenReturn(Optional.empty());

        var exception = assertThrows(AccountNotFoundException.class,
                () -> meService.me());

        assertEquals(AccountNotFoundException.class, exception.getClass());
    }
}