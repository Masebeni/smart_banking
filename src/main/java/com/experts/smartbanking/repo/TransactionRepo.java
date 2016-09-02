package com.experts.smartbanking.repo;

import com.experts.smartbanking.domain.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AXE on 2016/08/20.
 */
@Repository
public interface TransactionRepo extends CrudRepository<Transaction, Integer> {
}
