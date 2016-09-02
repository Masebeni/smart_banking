package com.experts.smartbanking.repo;

import com.experts.smartbanking.Server;
import com.experts.smartbanking.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by AXE on 2016/08/20.
 */

@SpringApplicationConfiguration(classes= Server.class)
@WebAppConfiguration
public class AccountCrudTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AccountRepo accountRepo;

    //@Test
    public void testCreateAccount() throws Exception {
        Account account = new Account.Builder("14323")
                .balance(9000.00)
                .build();

        accountRepo.save(account);
        Integer id = account.getId();

        assertNotNull(id);
    }

    //@Test
    public void testReadAccount() throws Exception {
        Account account = accountRepo.findOne(1);
        assertEquals(9000.00, account.getBalance());
    }

    //@Test
    public void testUpdateAccount() throws Exception {
        Account account = accountRepo.findOne(1);
        Account newAccount = new Account.Builder(account.getNumber())
                .copy(account)
                .balance(6000.00)
                .build();

        accountRepo.save(newAccount);
        Account updatedAccount = accountRepo.findOne(1);

        assertEquals(6000.00, updatedAccount.getBalance());
    }

    //@Test
    public void testDeleteAccount() throws Exception {
        accountRepo.delete(2);
        Account account = accountRepo.findOne(2);

        assertNull(account);
    }
}
