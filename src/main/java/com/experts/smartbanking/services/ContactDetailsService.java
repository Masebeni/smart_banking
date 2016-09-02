package com.experts.smartbanking.services;

import com.experts.smartbanking.domain.Client;
import com.experts.smartbanking.domain.ContactDetails;

import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */
public interface ContactDetailsService {
    List<ContactDetails> readAllContactDetails();
    ContactDetails create(final ContactDetails contactDetails);
    ContactDetails read(final Integer id);
    ContactDetails update(final ContactDetails contactDetails);
    List<Client> client(final Integer id);
    ContactDetails delete(final Integer id);
    ContactDetails search(final String cellNumber);
}
