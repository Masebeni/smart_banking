package com.experts.smartbanking.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AXE on 2016/08/18.
 */
@Entity
public class Account implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String number;
    private Double balance;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private List<Transaction> transactionList;

    public Account() {
    }

    public Account(final Builder builder) {
        this.id = builder.id;
        this.balance = builder.balance;
        this.number = builder.number;
        this.transactionList = builder.transactionList;
        processTransactions();
    }

    public static class Builder{
        private Integer id;
        private String number;
        private Double balance;
        private List<Transaction> transactionList;

        public Builder(final String number){
            this.number = number;
        }

        public Builder id(final Integer id){
            this.id = id;
            return this;
        }

        public Builder balance(final Double amt){
            this.balance = amt;
            return this;
        }

        public Builder transaction(final List<Transaction> transactions){
            this.transactionList = transactions;
            return this;
        }

        public Builder copy(final Account account){
            this.balance = account.getBalance();
            this.id = account.getId();
            this.number = account.getNumber();
            this.transactionList = account.getTransactionList();

            return this;
        }

        public Account build(){
            return new Account(this);
        }
    }

    private void processTransactions(){
        if(this.transactionList != null){
            for(Transaction currentTransaction: this.transactionList){
                if(currentTransaction.getTransactionType().equals("DEPOSIT") && currentTransaction.getAmount() > 0){
                    this.balance += currentTransaction.getAmount();
                }else if(currentTransaction.getTransactionType().equals("WITHDRAWAL") && currentTransaction.getAmount() > 0){
                    if(this.balance > currentTransaction.getAmount()){
                        this.balance -= currentTransaction.getAmount();
                    }
                }
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!id.equals(account.id)) return false;
        return number.equals(account.number);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + number.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", transactionList=" + transactionList +
                '}';
    }
}
