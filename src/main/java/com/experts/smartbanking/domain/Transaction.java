package com.experts.smartbanking.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by AXE on 2016/08/20.
 */
@Entity
public class Transaction implements Serializable {
    public static final Integer DEPOSIT = 0;
    public static final Integer WITHDRAWAL = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double amount;
    private Date date;
    private String transactionType;

    public Transaction() {
    }

    public Transaction(final Builder builder){
        this.amount = builder.amount;
        this.date = builder.date;
        this.id = builder.id;
        this.transactionType = builder.transactionType;
    }

    public static class Builder{
        private Integer id;
        private Double amount;
        private Date date;
        private String transactionType;

        public Builder(final Date date){
            this.date = date;
        }

        public Builder type(final Integer transType){
            if(transType == Transaction.DEPOSIT){
                this.transactionType = "DEPOSIT";
            }else if(transType == Transaction.WITHDRAWAL){
                this.transactionType = "WITHDRAWAL";
            }else{
                this.transactionType = "UNKNOWN";
            }
            return this;
        }

        public Builder id(final Integer id){
            this.id = id;
            return this;
        }

        public Builder amount(final Double amt){
            this.amount = amt;
            return this;
        }


        public Builder copy(final Transaction transaction){
            this.id = transaction.getId();
            this.amount = transaction.getAmount();
            this.date = transaction.getDate();
            this.transactionType = transaction.getTransactionType();

            return this;
        }

        public Transaction build(){
            return new Transaction(this);
        }
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Integer getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
