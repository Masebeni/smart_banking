package com.experts.smartbanking.factories;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Client;

import java.util.List;
import java.util.Map;

/**
 * Created by AXE on 2016/08/20.
 */
public class ClientFactory {
    public static Client createClient(final Map<String, String> values, final List<Account> accounts){
        Client client = new Client
                .Builder(values.get("lastName"))
                .firstName(values.get("firstName"))
                .age(Integer.parseInt(values.get("age")))
                .accounts(accounts)
                .id(Integer.parseInt(values.get("id")))
                .build();

        return client;
    }
}
