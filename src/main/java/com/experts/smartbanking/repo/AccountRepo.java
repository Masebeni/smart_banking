package com.experts.smartbanking.repo;

import com.experts.smartbanking.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AXE on 2016/08/20.
 */
@Repository
public interface AccountRepo extends CrudRepository<Account, Integer> {
    Account findBynumber(final String number);
}
