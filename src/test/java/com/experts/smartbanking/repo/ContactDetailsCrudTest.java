package com.experts.smartbanking.repo;

import com.experts.smartbanking.Server;
import com.experts.smartbanking.domain.ContactDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;


/**
 * Created by AXE on 2016/08/20.
 */

@SpringApplicationConfiguration(classes= Server.class)
@WebAppConfiguration
public class ContactDetailsCrudTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private ContactDetailsRepo contactDetailsRepo;

    //@Test
    public void testCreateContactDetails() throws Exception {
        ContactDetails contactDetails = new ContactDetails.Builder("0612435467")
                .email("AXEpaulus@gmail.com")
                .build();

        contactDetailsRepo.save(contactDetails);
        Integer id = contactDetails.getId();
        assertNotNull(id);
    }

    //@Test
    public void testReadContactDetails() throws Exception {
        ContactDetails contactDetails = contactDetailsRepo.findOne(1);
        assertNotNull(contactDetails);
    }

    //@Test
    public void testUpdateContactDetails() throws Exception {
        ContactDetails contactDetails = contactDetailsRepo.findOne(1);
        ContactDetails newContactDetails = new ContactDetails.Builder("")
                .copy(contactDetails)
                .email("paulus@yahoo.com")
                .build();

        contactDetailsRepo.save(newContactDetails);
        ContactDetails readUpdatedContactDetails = contactDetailsRepo.findOne(1);

        assertEquals(newContactDetails.getEmailAddress(), readUpdatedContactDetails.getEmailAddress());
    }

    //@Test
    public void testContactDetailsSearchByCellNumber() throws Exception {
        ContactDetails contactDetails = contactDetailsRepo.findBycellNumber("0611092633");
        assertNotNull(contactDetails);
    }

    //@Test
    public void testDeleteContactDetails() throws Exception {
        //contactDetailsRepo.delete("");
        //ContactDetails
    }
}
