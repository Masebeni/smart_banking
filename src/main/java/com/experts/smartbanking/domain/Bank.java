package com.experts.smartbanking.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AXE on 2016/08/20.
 */
@Entity
public class Bank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="bank_id")
    private List<Account> accounts;          // Client's are linked to accounts...A bank cannot have client's without accounts.

    public Bank() {
    }

    public Bank(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.accounts = builder.accounts;
    }

    public static class Builder{
        private Integer id;
        private String name;
        private List<Account> accounts;

        public Builder(final String bankName){
            this.name = bankName;
        }

        public Builder id(final Integer id){
            this.id = id;
            return this;
        }

        public Builder accounts(final List<Account> accounts){
            this.accounts = accounts;
            return this;
        }

        public Builder name(final String name){
            this.name = name;
            return this;
        }

        public Builder copy(final Bank bank){
            this.id = bank.getId();
            this.name = bank.getName();

            return this;
        }

        public Bank build(){
            return new Bank(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        return id.equals(bank.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
