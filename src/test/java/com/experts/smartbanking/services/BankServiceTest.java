package com.experts.smartbanking.services;

import com.experts.smartbanking.Server;
import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/**
 * Created by AXE on 2016/08/22.
 */

@SpringApplicationConfiguration(classes= Server.class)
@WebAppConfiguration
public class BankServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private BankService service;
    @Autowired
    private AccountService accountService;

    //@Test
    public void testBankCreate() throws Exception {
        Bank bank = new Bank
                .Builder("Capitec")
                .build();

        Integer id = service.create(bank).getId();
        assertNotNull(id);
    }

    //@Test
    public void testBankRead() throws Exception {
        Bank bank = service.read(1);
        assertNotNull(bank);
    }

    //@Test
    public void testBankUpdate() throws Exception {
        //Check the database to see if the bank account updates - it now belongs to this bank
        Account account = accountService.read(2);
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);

        Bank bank = service.read(1);
        Bank newBank = new Bank
                .Builder("")
                .copy(bank)
                .name("Bank of America")
                .accounts(accountList)
                .build();

        service.update(newBank);
        assertEquals(new Integer("1"), newBank.getId());
    }

    //@Test
    public void testBankDelete() throws Exception {
        Bank deletedBank = service.delete(2);
        assertNull(deletedBank);
    }
}
