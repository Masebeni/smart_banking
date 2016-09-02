package com.experts.smartbanking.api;

import com.experts.smartbanking.domain.Transaction;
import com.experts.smartbanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by AXE on 2016/08/21.
 */

@Controller
@RequestMapping(value = "/transactions")
public class TransactionPage {
    @Autowired
    private TransactionService service;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> readAllTransactions(){
        List<Transaction> transactionList = service.getAllTransactions();
        if(transactionList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> read(@PathVariable("id") final Integer id){
        Transaction transaction = service.read(id);
        if(transaction == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> create(@RequestBody final Transaction transaction){
        Transaction savedTransaction = service.create(transaction);
        if(savedTransaction.getId() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(savedTransaction, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/update/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> update(@PathVariable("id") final Integer id, @RequestBody final Transaction transaction){
        Transaction found = service.read(id);
        if(found == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            if(Objects.equals(id, transaction.getId())){
                Transaction updatedTransaction = new Transaction
                        .Builder(new Date())
                        .copy(transaction)
                        .build();

                service.update(updatedTransaction);
                return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
    }

    @RequestMapping(value = "/{id}/delete/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> delete(@PathVariable("id") final Integer id){
        Transaction transaction = service.delete(id);
        if(transaction == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(transaction, HttpStatus.BAD_REQUEST);
        }
    }
}
