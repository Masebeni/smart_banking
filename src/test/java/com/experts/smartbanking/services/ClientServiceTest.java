package com.experts.smartbanking.services;

import com.experts.smartbanking.Server;
import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by AXE on 2016/08/20.
 */

@SpringApplicationConfiguration(classes= Server.class)
@WebAppConfiguration
public class ClientServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;

    //@Test
    public void testCreateClient() throws Exception {
        Client client = new Client.Builder("Smith")
                .firstName("Will")
                .age(49)
                .build();

        clientService.addClient(client);
        Integer id = client.getId();

        assertNotNull(id);
    }

    //@Test
    public void testClientRead() throws Exception {
        Client client = clientService.getClient(1);
        assertNotNull(client);
    }

    //@Test
    public void testClientUpdate() throws Exception {
        Account account = accountService.read(2);            //since this account already exists..
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);

        Client client = clientService.getClient(1);
        Client newClient = new Client
                .Builder("")
                .copy(client)
                .firstName("AXE")
                .age(22)
                .accounts(accountList)
                .build();

        clientService.updateClient(newClient);
        Client readUpdatedClient = clientService.getClient(1);

        assertEquals(newClient.getFirstName(), readUpdatedClient.getFirstName());
    }

    //@Test
    public void testClientDelete() throws Exception {
        clientService.removeClient(2);
        Client client = clientService.getClient(2);

        assertNull(client);
    }
}
