package com.ironhack.proyect.finalproyect.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.proyect.finalproyect.model.accounts.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn
public class AccountHolder extends User {

    private Date dateOfBirth;
    @Embedded
    private Address primaryAddress;
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress", column = @Column(name = "mailing_address")),
            @AttributeOverride(name = "city", column = @Column(name = "mailing_city")),
            @AttributeOverride(name = "state", column = @Column(name = "mailing_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "mailing_postal_code"))

    })

    private Address mailingAddress;

    @NotNull
    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> primaryOwnerAccount = new ArrayList<>();
    @NotNull
    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> secondaryOwnerAccount = new ArrayList<>();


    public AccountHolder() {
    }

    public AccountHolder(String name, String password, Date dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
