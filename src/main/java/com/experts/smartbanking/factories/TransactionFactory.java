package com.experts.smartbanking.factories;

import com.experts.smartbanking.domain.Transaction;

import java.sql.Date;
import java.util.Map;

/**
 * Created by AXE on 2016/08/20.
 */
public class TransactionFactory {
    public static Transaction createTransaction(final Map<String, String> values, final Date date){
        Transaction transaction = new Transaction
                .Builder(date)
                .amount(Double.parseDouble(values.get("amount")))
                .type(Integer.parseInt(values.get("type")))
                .id(Integer.parseInt(values.get("id")))
                .build();

        return transaction;
    }
}
