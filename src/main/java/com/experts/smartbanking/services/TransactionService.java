package com.experts.smartbanking.services;

import com.experts.smartbanking.domain.Transaction;

import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */
public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction create(final Transaction transaction);
    Transaction read(final Integer id);
    Transaction update(final Transaction transaction);
    Transaction delete(final Transaction transaction);
    Transaction delete(final Integer id);
}
