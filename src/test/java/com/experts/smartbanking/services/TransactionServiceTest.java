package com.experts.smartbanking.services;

import com.experts.smartbanking.Server;
import com.experts.smartbanking.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by AXE on 2016/08/22.
 */

@SpringApplicationConfiguration(classes= Server.class)
@WebAppConfiguration
public class TransactionServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private TransactionService service;

    //@Test
    public void testTransactionServiceCreate() throws Exception {
        Transaction transaction = new Transaction
                .Builder(new Date())
                .amount(300000.00)
                .type(Transaction.DEPOSIT)
                .build();

        assertNotNull(service.create(transaction).getId());
    }

    //@Test
    public void testTransactionServiceRead() throws Exception {
        Transaction transaction = service.read(2);
        assertEquals(new Double("300000.00"), transaction.getAmount());
    }

    //@Test
    public void testTransactionServiceUpdate() throws Exception {
        Transaction transaction = service.read(2);
        Transaction updatedTransaction = new Transaction
                .Builder(transaction.getDate())
                .copy(transaction)
                .amount(390000.00)
                .build();

        assertEquals(transaction.getId(), service.update(updatedTransaction).getId());
        assertNotSame(transaction.getAmount(), updatedTransaction.getAmount());
    }

    //@Test
    public void testTransactionServiceDelete() throws Exception {
        assertNull(service.delete(3));
    }
}
