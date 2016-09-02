package com.experts.smartbanking.services.implementation;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Transaction;
import com.experts.smartbanking.repo.AccountRepo;
import com.experts.smartbanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<Account> getAllAccounts() {
        List<Account> allAccountsList = new ArrayList<>();

        Iterable<Account> foundAccountList = accountRepo.findAll();
        for(Account currentAccount : foundAccountList){
            allAccountsList.add(currentAccount);
        }

        return allAccountsList;
    }

    @Override
    public Account create(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Account read(Integer id) {
        return accountRepo.findOne(id);
    }

    @Override
    public Account update(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Account delete(Integer id) {
        accountRepo.delete(id);
        return accountRepo.findOne(id);
    }

    @Override
    public Account read(String number) {
        return accountRepo.findBynumber(number);
    }

    @Override
    public List<Transaction> getTransactions(Integer id) {
        return accountRepo.findOne(id).getTransactionList();
    }
}
