package com.experts.smartbanking.services;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Transaction;

import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */
public interface AccountService {
    List<Account> getAllAccounts();
    Account create(final Account account);
    Account read(final Integer id);
    Account update(final Account account);
    Account delete(final Integer id);
    Account read(final String number);
    List<Transaction> getTransactions(final Integer id);
}
