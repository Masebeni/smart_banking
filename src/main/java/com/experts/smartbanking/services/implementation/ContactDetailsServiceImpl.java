package com.experts.smartbanking.services.implementation;

import com.experts.smartbanking.domain.Client;
import com.experts.smartbanking.domain.ContactDetails;
import com.experts.smartbanking.repo.ContactDetailsRepo;
import com.experts.smartbanking.services.ContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */

@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {
    @Autowired
    private ContactDetailsRepo contactDetailsRepo;

    @Override
    public List<ContactDetails> readAllContactDetails() {
        List<ContactDetails> allContactDetailsList = new ArrayList<>();

        Iterable<ContactDetails> foundContactDetails = contactDetailsRepo.findAll();
        for(ContactDetails currentContactDetails : foundContactDetails){
            allContactDetailsList.add(currentContactDetails);
        }

        return allContactDetailsList;
    }

    @Override
    public ContactDetails create(final ContactDetails contactDetails) {
        return contactDetailsRepo.save(contactDetails);
    }

    @Override
    public ContactDetails read(final Integer id) {
        return contactDetailsRepo.findOne(id);
    }

    @Override
    public ContactDetails update(final ContactDetails contactDetails) {
        return contactDetailsRepo.save(contactDetails);
    }

    @Override
    public List<Client> client(final Integer id) {
        return contactDetailsRepo.findOne(id).getClient();
    }

    @Override
    public ContactDetails delete(final Integer id) {
        contactDetailsRepo.delete(id);
        return contactDetailsRepo.findOne(id);
    }

    @Override
    public ContactDetails search(final String cellNumber) {
        return contactDetailsRepo.findBycellNumber(cellNumber);
    }
}
