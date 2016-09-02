package com.experts.smartbanking.api;

import com.experts.smartbanking.domain.Client;
import com.experts.smartbanking.domain.ContactDetails;
import com.experts.smartbanking.services.ContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Objects;

/**
 * Created by AXE on 2016/08/21.
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactDetailsPage {
    @Autowired
    protected ContactDetailsService service;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<ContactDetails>> readAllContactDetails(){
        List<ContactDetails> contactDetailsList = service.readAllContactDetails();
        if(contactDetailsList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(contactDetailsList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactDetails> create(@RequestBody final ContactDetails contactDetails){
        ContactDetails savedContactDetails = service.create(contactDetails);
        if(savedContactDetails.getId() == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(savedContactDetails, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactDetails> read(@PathVariable("id") final Integer id){
        ContactDetails contactDetails = service.read(id);
        if(contactDetails == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(contactDetails, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/search/{cell}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactDetails> read(@PathVariable("cell") final String cellNumber){
        ContactDetails contactDetails = service.search(cellNumber);
        if(contactDetails == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(contactDetails, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/update/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactDetails> update(@PathVariable final Integer id, @RequestBody final ContactDetails contactDetails){
        ContactDetails found = service.read(id);
        if(found == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            if(Objects.equals(id, contactDetails.getId())){
                ContactDetails updatedContactDetails = new ContactDetails
                        .Builder(contactDetails.getCellNumber())
                        .copy(contactDetails)
                        .build();

                service.update(updatedContactDetails);
                return new ResponseEntity<>(updatedContactDetails, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
    }

    @RequestMapping(value = "/{id}/delete/", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") final Integer id){
        ContactDetails found = service.read(id);
        if (found == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            ContactDetails deletedContact = service.delete(id);
            if(deletedContact == null){
                return new ResponseEntity<>("Deleted!", HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
    }

    @RequestMapping(value = "/{id}/client/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getContactClient(@PathVariable("id") final Integer id){
        List<Client> clients = service.client(id);
        if(clients.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
    }
}
