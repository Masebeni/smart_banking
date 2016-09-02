package com.experts.smartbanking.factories;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Bank;

import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */
public class BankFactory {
    public static Bank createBank(final Integer id, final String name, final List<Account> accounts){
        Bank bank = new Bank
                .Builder(name)
                .accounts(accounts)
                .id(id)
                .build();

        return bank;
    }

}
