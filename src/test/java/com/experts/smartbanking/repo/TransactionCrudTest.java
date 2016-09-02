package com.experts.smartbanking.repo;

import com.experts.smartbanking.Server;
import com.experts.smartbanking.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

/**
 * Created by AXE on 2016/08/20.
 */

@SpringApplicationConfiguration(classes= Server.class)
@WebAppConfiguration
public class TransactionCrudTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private TransactionRepo transactionRepo;

    //@Test
    public void testCreateTransaction() throws Exception {
        Transaction transaction = new Transaction.Builder(new Date())
                .amount(250.00)
                .type(Transaction.DEPOSIT)
                .build();

        transactionRepo.save(transaction);
        Integer id = transaction.getId();

        assertNotNull(id);
    }

    //@Test
    public void testReadTransaction() throws Exception {
        Transaction transaction = transactionRepo.findOne(1);
        assertEquals(250.00, transaction.getAmount());
    }

    //@Test
    public void testUpdateTransaction() throws Exception {
        Transaction transaction = transactionRepo.findOne(1);
        Transaction newTransaction = new Transaction.Builder(null)
                .copy(transaction)
                .amount(500.00)
                .build();

        transactionRepo.save(newTransaction);
        Transaction readUpdatedTransaction = transactionRepo.findOne(1);

        assertEquals(newTransaction.getAmount(), readUpdatedTransaction.getAmount());
    }

    //@Test
    public void testDeleteTransaction() throws Exception {
        transactionRepo.delete(2);
        Transaction deletedTransaction = transactionRepo.findOne(2);

        assertNull(deletedTransaction);
    }
}
