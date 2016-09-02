package com.experts.smartbanking.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertNotSame;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by AXE on 2016/08/20.
 */
public class TestContactDetails {

    //@Test
    public void testContactDetailsCreate() throws Exception {
        ContactDetails contact = new ContactDetails.Builder("0612345678")
                .email("AXEpaulus@gmail.com")
                .build();

        assertEquals("AXEpaulus@gmail.com", contact.getEmailAddress());
    }

    //@Test
    public void testContactDetailsUpdate() throws Exception {
        ContactDetails contact = new ContactDetails.Builder("0612345678")
                .email("AXEpaulus@gmail.com")
                .build();

        ContactDetails updatedContactDetails = new ContactDetails.Builder("")
                .copy(contact)
                .email("paulus@yahoo.com")
                .build();

        assertNotSame(contact.getEmailAddress(), updatedContactDetails.getEmailAddress());
    }
}
