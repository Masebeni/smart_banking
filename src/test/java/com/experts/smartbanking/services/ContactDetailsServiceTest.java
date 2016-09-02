package com.experts.smartbanking.services;

import com.experts.smartbanking.Server;
import com.experts.smartbanking.domain.Client;
import com.experts.smartbanking.domain.ContactDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by AXE on 2016/08/22.
 */

@SpringApplicationConfiguration(classes= Server.class)
@WebAppConfiguration
public class ContactDetailsServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private ContactDetailsService service;
    @Autowired
    private ClientService clientService;

    //@Test
    public void testContactDetailsCreate() throws Exception {
        ContactDetails contactDetails = new ContactDetails
                .Builder("0611092633")
                .email("AXEpaulus@gmail.com")
                .build();

        Integer id = service.create(contactDetails).getId();
        assertNotNull(id);
    }

    //@Test
    public void testContactDetailsRead() throws Exception {
        ContactDetails contactDetails = service.read(1);
        assertNotNull(contactDetails);
    }

    //@Test
    public void testContactDetailsUpdate() throws Exception {
        Client client = clientService.getClient(1);            //Check if the client cell number updates in the db
        List<Client> clientList= new ArrayList<>();
        clientList.add(client);

        ContactDetails contactDetails = service.read(1);
        ContactDetails newContactDetails = new ContactDetails
                .Builder("")
                .copy(contactDetails)
                .client(clientList)
                .build();

        ContactDetails updatedContactDetails = service.update(newContactDetails);
        assertEquals("AXE", updatedContactDetails.getClient().get(0).getFirstName());
    }

    //@Test
    public void testContactDetailsCreateWithNewClient() throws Exception {
        Client client = new Client
                .Builder("Pompies")
                .firstName("Piet")
                .age(22)
                .build();

        List<Client> clients = new ArrayList<>();
        clients.add(client);

        ContactDetails contactDetails = new ContactDetails
                .Builder("0821234567")
                .email("AXE@gmail.com")
                .password("peita")
                .client(clients)
                .build();

        service.create(contactDetails);
        assertNotNull(contactDetails.getId());
        assertNotNull(contactDetails.getClient().get(0).getId());
    }

    //@Test
    public void testContactDetailsDelete() throws Exception {
        ContactDetails deletedContactDetails = service.delete(1);
        assertNull(deletedContactDetails);
    }
}
