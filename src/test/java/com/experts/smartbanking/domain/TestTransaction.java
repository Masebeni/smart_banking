package com.experts.smartbanking.domain;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * Created by AXE on 2016/08/20.
 */
public class TestTransaction {

    //@Test
    public void testTransactionCreate() throws Exception {
        Transaction transaction = new Transaction.Builder(new Date())
                .amount(250.00)
                .type(Transaction.DEPOSIT)
                .build();

        assertEquals(new Double("250.00"), transaction.getAmount());
    }

    //@Test
    public void testTransactionUpdate() throws Exception {
        Transaction transaction = new Transaction.Builder(new Date())
                .amount(250.00)
                .type(Transaction.DEPOSIT)
                .build();

        Transaction newTransaction = new Transaction.Builder(new Date())
                .copy(transaction)
                .amount(600.00)
                .build();

        assertNotSame(transaction.getAmount(), newTransaction.getAmount());
    }
}
