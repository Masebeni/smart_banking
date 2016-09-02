package com.experts.smartbanking.api;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Client;
import com.experts.smartbanking.services.ClientService;
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

/**
 * Created by AXE on 2016/08/20.
 */
@Controller
@RequestMapping(value = "/clients")
public class ClientPage {
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clientList = clientService.getAllClients();
        if(clientList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> create(@RequestBody final Client client){
        Client savedClient = clientService.addClient(client);
        if(savedClient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(savedClient, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> read(@PathVariable("id") final Integer id){
        Client client = clientService.getClient(id);
        if(client == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/update/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> update(@PathVariable("id") final Integer id, @RequestBody final Client client){
        Client oldClient = clientService.getClient(id);
        if(id == client.getId()){
            if(oldClient == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                Client updatedClient = new Client
                        .Builder(client.getLastName())
                        .copy(client)
                        .build();

                clientService.updateClient(updatedClient);
                return new ResponseEntity<>(updatedClient, HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/delete/{id}/", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") final Integer id) {
        Client deletedClient = clientService.removeClient(id);
        if (deletedClient == null) {
            return new ResponseEntity<>("Client with id " + id + " successfully deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}/accounts/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> readClientAccounts(@PathVariable("id") final Integer id){
        Client client = clientService.getClient(id);
        if(client == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(client.getAccounts(), HttpStatus.OK);
        }
    }

}
