package com.experts.smartbanking.domain;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;

/**
 * Created by AXE on 2016/08/20.
 */
public class TestAccount {

    //@Test
    public void testAccountCreate() throws Exception {
        Account savings = new Account.Builder("1445")
                .balance(2500.00)
                .build();

        assertEquals(2500.00D, savings.getBalance());
    }

    //@Test
    public void testAccountUpdate() throws Exception {
        Account account = new Account.Builder("1445")
                .balance(2500.00)
                .build();

        Account updatedAccount = new Account.Builder("")
                .copy(account)
                .balance(9000.00)
                .build();

        assertEquals(account.getNumber(), updatedAccount.getNumber());
        assertNotSame(account.getBalance(), updatedAccount.getBalance());
    }
}
