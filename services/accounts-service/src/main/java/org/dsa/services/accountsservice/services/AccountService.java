package org.dsa.services.accountsservice.services;

import org.dsa.services.accountsservice.common.dtos.AccountDto;
import org.dsa.services.accountsservice.common.dtos.CreateAccountDto;
import org.dsa.services.accountsservice.common.dtos.UpdateAccountDto;
import org.dsa.services.core.servicesstarter.common.operations.BaseService;

public interface AccountService extends BaseService<Long, AccountDto, CreateAccountDto, UpdateAccountDto> {
}
