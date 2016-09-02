package com.experts.smartbanking.factories;

import com.experts.smartbanking.domain.Client;
import com.experts.smartbanking.domain.ContactDetails;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * Created by AXE on 2016/08/20.
 */
public class ContactDetailsFactory {
    public static ContactDetails createContactDetails(final Map<String, String> values, final List<Client> client) throws NoSuchAlgorithmException {
        ContactDetails contactDetails = new ContactDetails
                .Builder(values.get("cell"))
                .email(values.get("email"))
                .password(values.get("password"))
                .client(client)
                .id(Integer.parseInt(values.get("id")))
                .build();

        return contactDetails;
    }
}
