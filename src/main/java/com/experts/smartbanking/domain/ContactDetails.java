package com.experts.smartbanking.domain;

import com.experts.smartbanking.utils.PasswordEncryptor;

import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by AXE on 2016/08/18.
 */
@Entity
public class ContactDetails implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String cellNumber;
    private String emailAddress;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="contact_id")
    private List<Client> client;


    public ContactDetails() {
    }

    public ContactDetails(Builder builder) {
        this.id = builder.id;
        this.emailAddress = builder.emailAddress;
        this.cellNumber = builder.cellNumber;
        this.client = builder.client;
        this.password = builder.password;
    }

    public static class Builder{
        private Integer id;
        private String cellNumber;
        private String emailAddress;
        private String password;
        private List<Client> client;

        public Builder( final String newCellNumber){
            this.cellNumber = newCellNumber;
        }

        public Builder email(final String newMailAddress){
            this.emailAddress = newMailAddress;
            return this;
        }

        public Builder id(final Integer id){
            this.id = id;
            return this;
        }

        public Builder client(final List<Client> newClient){
            this.client = newClient;
            return this;
        }

        public Builder password(final String newPassword) throws NoSuchAlgorithmException {
            this.password = PasswordEncryptor.convertPasswordToMD5(newPassword);
            return this;
        }

        public Builder copy(final ContactDetails contactDetails){
            this.id = contactDetails.getId();
            this.client = contactDetails.getClient();
            this.emailAddress = contactDetails.getEmailAddress();
            this.cellNumber = contactDetails.getCellNumber();

            return this;
        }

        public ContactDetails build(){
            return new ContactDetails(this);
        }

    }

    public String getCellNumber() {
        return cellNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<Client> getClient() {
        return client;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactDetails that = (ContactDetails) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "ContactDetails{" +
                "id=" + id +
                ", cellNumber='" + cellNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", client=" + client +
                '}';
    }
}
