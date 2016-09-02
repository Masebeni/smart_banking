package com.experts.smartbanking.services.implementation;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Client;
import com.experts.smartbanking.repo.ClientRepo;
import com.experts.smartbanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */

@Service
public class ClientServiceImp implements ClientService {
    @Autowired
    private ClientRepo clientRepo;

    @Override
    public List<Client> getAllClients() {
        List<Client> clientList = new ArrayList<>();

        Iterable<Client> clients = clientRepo.findAll();
        for(Client currentClient : clients){
            clientList.add(currentClient);
        }

        return clientList;
    }

    @Override
    public List<Account> getAccounts(final Integer id) {
        return clientRepo.findOne(id).getAccounts();
    }

    @Override
    public Client getClient(final Integer id) {
        return clientRepo.findOne(id);
    }

    @Override
    public Client addClient(final Client client) {
        return clientRepo.save(client);
    }

    @Override
    public Client updateClient(final Client client) {
        return clientRepo.save(client);
    }

    @Override
    public Client removeClient(final Client client) {
        clientRepo.delete(client);
        return clientRepo.findOne(client.getId());
    }

    @Override
    public Client removeClient(final Integer id) {
        clientRepo.delete(id);
        Client deletedClient = clientRepo.findOne(id);

        return deletedClient;
    }
}
