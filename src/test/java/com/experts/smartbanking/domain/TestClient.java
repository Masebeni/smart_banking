package com.experts.smartbanking.domain;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by AXE on 2016/08/20.
 */
public class TestClient {

    //@Test
    public void testCreateClient() throws Exception {
        Client client = new Client.Builder("Paulus")
                .age(22)
                .firstName("AXE")
                .build();

        assertEquals("AXE Paulus 22", client.getFirstName() + " " + client.getLastName() + " " + client.getAge().toString());
    }

    //@Test
    public void testUpdateClient() throws Exception {
        Client client = new Client.Builder("Zuma")
                .firstName("Johnathan")
                .age(88)
                .build();

        Client updatedClient = new Client.Builder("")
                .copy(client)
                .build();

        assertEquals(client.getFirstName(), updatedClient.getFirstName());
    }
}
