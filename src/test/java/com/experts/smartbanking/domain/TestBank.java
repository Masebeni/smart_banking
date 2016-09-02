package com.experts.smartbanking.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by AXE on 2016/08/20.
 */
public class TestBank {

    //@Test
    public void testBankCreate() throws Exception {
        Bank bank = new Bank.Builder("Capitec")
                .build();

        assertEquals("Capitec", bank.getName());
    }

    //@Test
    public void testBankUpdate() throws Exception {
        Bank bank = new Bank.Builder("Capitec")
                .id(123)
                .build();

        Bank newBank = new Bank.Builder("")
                .copy(bank)
                .build();

        assertEquals("Capitec", newBank.getName());
        assertEquals(bank.getId(), newBank.getId());
    }
}
