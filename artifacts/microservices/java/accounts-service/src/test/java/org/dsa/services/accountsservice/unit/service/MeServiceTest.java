package org.dsa.services.accountsservice.unit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.dsa.core.sharedstarter.constants.Role;
import org.dsa.core.sharedstarter.exception.AccountNotFoundException;
import org.dsa.core.sharedstarter.exception.UnauthorizedException;
import org.dsa.core.sharedstarter.utils.CurrentSession;
import org.dsa.services.accountsservice.fixture.AccountDtoFixtures;
import org.dsa.services.accountsservice.fixture.AccountFixtures;
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
  @Mock private CurrentSession currentSession;

  @InjectMocks private MeService meService;

  @Test
  void me_whenThereIsASession_shouldReturnAuthAccountDto() {
    var username = "ronald";
    var currentSessionDto = AccountDtoFixtures.currentSessionDto(1L);
    var currentSessionAccount = AccountFixtures.currentSessionAccount(1L);

    when(currentSession.getCurrentUsername()).thenReturn(username);
    when(accountRepository.findByUsername(username)).thenReturn(Optional.of(currentSessionAccount));
    when(accountsMapper.toDto(currentSessionAccount)).thenReturn(currentSessionDto);

    var session = meService.me();

    assertNotNull(session);
    assertEquals(1L, session.id());
    assertEquals("ronald", session.username());
    assertEquals("ronald@email.com", session.email());
    assertEquals(Role.USER, session.role());
  }

  @Test
  void me_whenThereIsNotASession_shouldThrowException() {
    when(currentSession.getCurrentUsername()).thenReturn(null);

    var exception = assertThrows(UnauthorizedException.class, () -> meService.me());

    assertEquals(UnauthorizedException.class, exception.getClass());
  }

  @Test
  void me_whenThereIsASessionButIsNotFoundInDB_shouldThrowException() {
    var username = "ronald";

    when(currentSession.getCurrentUsername()).thenReturn(username);
    when(accountRepository.findByUsername(username)).thenReturn(Optional.empty());

    var exception = assertThrows(AccountNotFoundException.class, () -> meService.me());

    assertEquals(AccountNotFoundException.class, exception.getClass());
  }
}
