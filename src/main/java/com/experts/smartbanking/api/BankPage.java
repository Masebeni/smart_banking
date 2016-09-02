package com.experts.smartbanking.api;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Bank;
import com.experts.smartbanking.services.BankService;
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
@RequestMapping(value = "/bank")
public class BankPage {
    @Autowired
    private BankService service;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bank>> readAllBanks(){
        List<Bank> banks = service.getAllBanks();
        if(banks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(banks, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bank> read(@PathVariable("id") final Integer id){
        Bank bank = service.read(id);
        if(bank == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(bank, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bank> create(@RequestBody final Bank bank){
        Bank createdBank = service.create(bank);
        if(createdBank.getId() == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(createdBank, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/{id}/update/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bank> update(@PathVariable("id") final Integer id, @RequestBody final Bank bank){
        Bank found = service.read(id);
        if(found == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            if(Objects.equals(bank.getId(), id)){
                Bank newBank = new Bank
                        .Builder(bank.getName())
                        .copy(bank)
                        .build();

                service.update(newBank);
                return new ResponseEntity<>(newBank, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
    }

    @RequestMapping(value = "/{id}/delete/", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") final Integer id){
        Bank bank = service.read(id);
        if(bank == null){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }else{
            Bank deletedBank = service.delete(id);
            if(deletedBank != null){
                return new ResponseEntity<>("Conflict", HttpStatus.CONFLICT);
            }else{
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            }
        }
    }

    @RequestMapping(value = "/{id}/accounts/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> readAllAccounts(@PathVariable("id")final Integer id){
        List<Account> bankAccountList = service.read(id).getAccounts();
        if(bankAccountList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(bankAccountList, HttpStatus.OK);
        }
    }

}
