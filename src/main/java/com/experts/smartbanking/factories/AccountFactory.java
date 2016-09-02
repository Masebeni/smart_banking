package com.experts.smartbanking.factories;

import com.experts.smartbanking.domain.Account;

import java.util.Map;

/**
 * Created by AXE on 2016/08/20.
 */
public class AccountFactory {
    public static Account createAccount(final Map<String, String> values){
        Account account = new Account
                .Builder(values.get("number"))
                .balance(Double.parseDouble(values.get("balance")))
                .id(Integer.parseInt(values.get("id")))
                .build();

        return account;
    }
}
