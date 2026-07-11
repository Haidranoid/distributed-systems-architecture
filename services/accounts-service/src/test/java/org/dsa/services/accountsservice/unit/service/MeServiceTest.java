package org.dsa.services.accountsservice.unit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.dsa.core.sharedstarter.common.constants.Role;
import org.dsa.core.sharedstarter.common.exceptions.AccountNotFoundException;
import org.dsa.core.sharedstarter.common.exceptions.UnauthorizedException;
import org.dsa.core.sharedstarter.utils.SessionService;
import org.dsa.services.accountsservice.common.fixtures.AccountDtoFixtures;
import org.dsa.services.accountsservice.common.fixtures.AccountFixtures;
import org.dsa.services.accountsservice.mapper.AccountMapper;
import org.dsa.services.accountsservice.repository.AccountRepository;
import org.dsa.services.accountsservice.service.MeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MeServiceTest {

  @Mock private AccountRepository accountRepository;
  @Mock private AccountMapper accountsMapper;
  @Mock private SessionService sessionService;

  @InjectMocks private MeService meService;

  @Test
  void me_whenThereIsASession_shouldReturnAuthAccountDto() {
    var username = "ronald";
    var currentSessionDto = AccountDtoFixtures.currentSessionDto(1L);
    var currentSession = AccountFixtures.currentSessionAccount(1L);

    when(sessionService.getCurrentUsername()).thenReturn(username);
    when(accountRepository.findByUsername(username)).thenReturn(Optional.of(currentSession));
    when(accountsMapper.toDto(currentSession)).thenReturn(currentSessionDto);

    var session = meService.me();

    assertNotNull(session);
    assertEquals(1L, session.id());
    assertEquals("ronald", session.username());
    assertEquals("ronald@email.com", session.email());
    assertEquals(Role.USER, session.role());
  }

  @Test
  void me_whenThereIsNotASession_shouldThrowException() {
    when(sessionService.getCurrentUsername()).thenReturn(null);

    var exception = assertThrows(UnauthorizedException.class, () -> meService.me());

    assertEquals(UnauthorizedException.class, exception.getClass());
  }

  @Test
  void me_whenThereIsASessionButIsNotFoundInDB_shouldThrowException() {
    var username = "ronald";

    when(sessionService.getCurrentUsername()).thenReturn(username);
    when(accountRepository.findByUsername(username)).thenReturn(Optional.empty());

    var exception = assertThrows(AccountNotFoundException.class, () -> meService.me());

    assertEquals(AccountNotFoundException.class, exception.getClass());
  }
}
