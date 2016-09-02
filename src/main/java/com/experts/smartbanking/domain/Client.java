package com.experts.smartbanking.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AXE on 2016/08/18.
 */

@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="client_id")
    private List<Account> accounts;

    public Client(){

    }


    public Client(final Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.accounts = builder.accounts;
        this.age = builder.age;
        this.id = builder.id;
    }

    public static class Builder{
        private Integer id;
        private String firstName;
        private String lastName;
        private Integer age;
        private List<Account> accounts;

        public Builder(final String lastName){
            this.lastName = lastName;
        }

        public Builder id(final Integer id){
            this.id = id;
            return this;
        }

        public Builder firstName(final String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder age(final Integer age){
            this.age = age;
            return this;
        }

        public Builder accounts(final List<Account> accounts){
            this.accounts = accounts;
            return this;
        }

        public Builder copy(final Client client){
            this.id = client.getId();
            this.lastName = client.getLastName();
            this.firstName = client.getFirstName();
            this.accounts = client.getAccounts();
            this.age = client.getAge();

            return this;
        }

        public Client build(){
            return new Client(this);
        }

    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!id.equals(client.id)) return false;
        return lastName.equals(client.lastName);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", accounts=" + accounts +
                '}';
    }
}
