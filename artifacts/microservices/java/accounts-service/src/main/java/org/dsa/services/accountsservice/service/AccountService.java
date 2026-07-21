package org.dsa.services.accountsservice.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.contracts.BaseService;
import org.dsa.core.sharedstarter.exception.AccountNotFoundException;
import org.dsa.services.accountsservice.mapper.AccountMapper;
import org.dsa.services.accountsservice.repository.AccountRepository;
import org.dsa.services.accountsservice.request.CreateAccountRequest;
import org.dsa.services.accountsservice.request.UpdateAccountRequest;
import org.dsa.services.accountsservice.request.VerifyAccountCredentialsRequest;
import org.dsa.services.accountsservice.response.AccountResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService
    implements BaseService<Long, AccountResponse, CreateAccountRequest, UpdateAccountRequest> {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  public AccountResponse create(CreateAccountRequest createAccountRequest) {
    // TODO: handle the case when the account already exist
    var accountEntity = accountMapper.toEntity(createAccountRequest);
    var accountSaved = accountRepository.save(accountEntity);

    return accountMapper.toDto(accountSaved);
  }

  public AccountResponse findById(Long userId) {
    var account =
        accountRepository
            .findById(userId)
            .orElseThrow(() -> new AccountNotFoundException("id " + userId));

    return accountMapper.toDto(account);
  }

  @Transactional(readOnly = true)
  public List<AccountResponse> findAll() {
    var accountsList = accountRepository.findAll().stream().map(accountMapper::toDto).toList();

    return accountsList;
  }

  public AccountResponse update(Long userId, UpdateAccountRequest updateAccountRequest) {
    var account =
        accountRepository
            .findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("Account not found"));

    // applyUpdates(accountDB, accountDto);

    var accountUpdated = accountRepository.save(account);

    return accountMapper.toDto(accountUpdated);
  }

  public void delete(Long userId) {
    var account =
        accountRepository
            .findById(userId)
            .orElseThrow(() -> new AccountNotFoundException("id " + userId));

    accountRepository.delete(account);
  }

  public AccountResponse verifyCredentials(
      VerifyAccountCredentialsRequest verifyAccountCredentialsRequest) {
    var account =
        accountRepository
            .findByUsername(verifyAccountCredentialsRequest.username())
            .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    return accountMapper.toDto(account);
  }
}
