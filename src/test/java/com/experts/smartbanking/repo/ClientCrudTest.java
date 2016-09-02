package com.experts.smartbanking.repo;

import com.experts.smartbanking.Server;
import com.experts.smartbanking.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;


/**
 * Created by AXE on 2016/08/20.
 */

@SpringApplicationConfiguration(classes= Server.class)
@WebAppConfiguration
public class ClientCrudTest extends AbstractTestNGSpringContextTests {
    private Integer id;
    @Autowired
    private ClientRepo clientRepo;

    //@Test
    public void testCreateClient() throws Exception {
        Client client = new Client.Builder("Smith")
                .firstName("Will")
                .age(49)
                .build();

        clientRepo.save(client);
        id = client.getId();

        assertNotNull(id);
    }

    //@Test
    public void testReadClient() throws Exception {
        Client client = clientRepo.findOne(1);
        assertNotNull(client);
        //assertEquals("Kevin Hart", client.getFirstName() + " " + client.getLastName());
    }

    //@Test
    public void testUpdateClient() throws Exception {
        Client client = clientRepo.findOne(1);
        Client updatedClient = new Client.Builder("")
                .copy(client)
                .age(35)
                .build();

        clientRepo.save(updatedClient);
        Client readUpdatedClient = clientRepo.findOne(1);

        Assert.assertEquals(new Integer("35"), readUpdatedClient.getAge());
    }

    //@Test
    public void testDeleteClient() throws Exception {
        clientRepo.delete(3);
        Client client = clientRepo.findOne(3);

        assertNull(client);
    }
}
