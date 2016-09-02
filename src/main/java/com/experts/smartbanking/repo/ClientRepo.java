package com.experts.smartbanking.repo;

import com.experts.smartbanking.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AXE on 2016/08/20.
 */
@Repository
public interface ClientRepo extends CrudRepository<Client, Integer> {
}
