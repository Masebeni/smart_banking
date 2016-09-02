package com.experts.smartbanking.services;

import com.experts.smartbanking.Server;
import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/**
 * Created by AXE on 2016/08/22.
 */

@SpringApplicationConfiguration(classes= Server.class)
@WebAppConfiguration
public class AccountServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AccountService service;

    //@Test
    public void testAccountCreate() throws Exception {
        Account account = new Account
                .Builder("12342")
                .balance(9000.00)
                .build();

        service.create(account);
        Integer id = account.getId();

        assertNotNull(id);
    }

    //@Test
    public void testAccountRead() throws Exception {
        Account account = service.read(2);
        assertEquals(new Double("9000.00"), account.getBalance());
    }

    //@Test
    public void testAccountReadByNumber() throws Exception {
        Account account = service.read("12342");
        assertNotNull(account);
    }

    //@Test
    public void testAccountUpdate() throws Exception {
        Transaction deposit = new Transaction
                .Builder(new Date())
                .amount(250000.00)
                .type(Transaction.DEPOSIT)
                .build();

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(deposit);

        Account account = service.read(2);
        Account newAccount = new Account
                .Builder(account.getNumber())
                .copy(account)
                .transaction(transactionList)
                .build();

        Account updatedAccount = service.update(newAccount);
        assertEquals(new Double("259000.00"), updatedAccount.getBalance());
    }

    //@Test
    public void testAccountDelete() throws Exception {
        Account deletedAccount = service.delete(1);
        assertNull(deletedAccount);
    }
    
    
}
