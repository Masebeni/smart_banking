package com.experts.smartbanking.services.implementation;

import com.experts.smartbanking.domain.Transaction;
import com.experts.smartbanking.repo.TransactionRepo;
import com.experts.smartbanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();

        Iterable<Transaction> foundTransactions = transactionRepo.findAll();
        for(Transaction currentTransaction : foundTransactions){
            allTransactions.add(currentTransaction);
        }

        return allTransactions;
    }

    @Override
    public Transaction create(final Transaction transaction) {
        return transactionRepo.save(transaction);
    }


    @Override
    public Transaction read(final Integer id) {
        return transactionRepo.findOne(id);
    }

    @Override
    public Transaction update(final Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    @Override
    public Transaction delete(final Transaction transaction) {
        transactionRepo.delete(transaction);
        return transactionRepo.findOne(transaction.getId());
    }

    @Override
    public Transaction delete(final Integer id) {
        transactionRepo.delete(id);
        return transactionRepo.findOne(id);
    }
}
