package com.experts.smartbanking.services;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Bank;

import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */
public interface BankService {
    List<Bank> getAllBanks();
    Bank create(final Bank bank);
    Bank read(final Integer id);
    Bank update(final Bank bank);
    Bank delete(final Integer id);
    List<Account> getAccounts(final Integer id);
}
