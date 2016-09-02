package com.experts.smartbanking.api;

import com.experts.smartbanking.domain.Account;
import com.experts.smartbanking.domain.Transaction;
import com.experts.smartbanking.services.AccountService;
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
 * Created by AXE on 2016/08/21.
 */
@Controller
@RequestMapping(value = "/accounts")
public class AccountPage {
    @Autowired
    private AccountService service;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> readAllAccounts(){
        List<Account> accounts = service.getAllAccounts();
        if(accounts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> create(@RequestBody final Account account){
        Account savedAccount = service.create(account);
        if(savedAccount.getId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(savedAccount, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> read(@PathVariable("id") final Integer id){
        Account account = service.read(id);
        if(account == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> update(@RequestBody final Account account){
        Account updatedAccount = service.update(account);
        if(updatedAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/delete/", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") final Integer id){
        Account deletedAccount = service.delete(id);
        if(deletedAccount == null){
            return new ResponseEntity<>("Deleted Account!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}/transactions/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> readAccountTransactions(@PathVariable("id") final Integer id){
        List<Transaction> transactionList = service.getTransactions(id);
        if(transactionList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
        }
    }
}
