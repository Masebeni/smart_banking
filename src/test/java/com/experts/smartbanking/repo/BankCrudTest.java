package com.experts.smartbanking.repo;

import com.experts.smartbanking.Server;
import com.experts.smartbanking.domain.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by AXE on 2016/08/20.
 */

@SpringApplicationConfiguration(classes= Server.class)
@WebAppConfiguration
public class BankCrudTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private BankRepo repo;

    //@Test
    public void testCreateBank() throws Exception {
        Bank bank = new Bank.Builder("First National Bank")
                .id(1)
                .build();

        repo.save(bank);
        Integer id = bank.getId();

        assertNotNull(id);
    }

    //@Test
    public void testReadBank() throws Exception {
        Bank bank = repo.findOne(1);

        assertEquals("First National Bank", bank.getName());
    }

    //@Test
    public void testUpdateBank() throws Exception {
        Bank bank = repo.findOne(1);

        Bank newBank = new Bank.Builder("")
                .copy(bank)
                .name(bank.getName() + " - ltd")
                .build();

        repo.save(newBank);
        Bank readUpdatedBank = repo.findOne(1);

        assertEquals(bank.getName() + " - ltd", readUpdatedBank.getName());
    }

    //@Test
    public void testDeleteBank() throws Exception {
        repo.delete(2);
        Bank bank = repo.findOne(2);

        assertNull(bank);
    }
}
