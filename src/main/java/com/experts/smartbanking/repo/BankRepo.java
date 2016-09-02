package com.experts.smartbanking.repo;

import com.experts.smartbanking.domain.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AXE on 2016/08/20.
 */
@Repository
public interface BankRepo extends CrudRepository<Bank, Integer> {
}
