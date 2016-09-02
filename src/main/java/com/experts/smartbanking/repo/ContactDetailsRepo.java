package com.experts.smartbanking.repo;

import com.experts.smartbanking.domain.ContactDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AXE on 2016/08/20.
 */
@Repository
public interface ContactDetailsRepo extends CrudRepository<ContactDetails, Integer> {
    ContactDetails findBycellNumber(final String cellNumber);
    //ContactDetails findByemailAddress(final String emailAddress);
}
