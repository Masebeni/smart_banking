package com.experts.smartbanking.services.implementation;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Bank;
import com.experts.smartbanking.repo.BankRepo;
import com.experts.smartbanking.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */

@Service
public class BankServiceImpl implements BankService{
    @Autowired
    private BankRepo bankRepo;

    @Override
    public List<Bank> getAllBanks() {
        List<Bank> allBanksList = new ArrayList<>();

        Iterable<Bank> foundBankList = bankRepo.findAll();
        for(Bank currentBank : foundBankList){
            allBanksList.add(currentBank);
        }
        return allBanksList;
    }

    @Override
    public Bank create(Bank bank) {
        return bankRepo.save(bank);
    }

    @Override
    public Bank read(Integer id) {
        return bankRepo.findOne(id);
    }

    @Override
    public Bank update(Bank bank) {
        return bankRepo.save(bank);
    }

    @Override
    public Bank delete(Integer id) {
        bankRepo.delete(id);
        return bankRepo.findOne(id);
    }

    @Override
    public List<Account> getAccounts(Integer id) {
        return bankRepo.findOne(id).getAccounts();
    }
}
