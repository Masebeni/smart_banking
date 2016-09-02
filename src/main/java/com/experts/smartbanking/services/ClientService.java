package com.experts.smartbanking.services;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Client;

import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */

public interface ClientService {
    List<Client> getAllClients();
    List<Account> getAccounts(final Integer id);

    Client getClient(final Integer id);
    Client addClient(final Client client);
    Client updateClient(final Client client);
    Client removeClient(final Client client);
    Client removeClient(final Integer id);
}
