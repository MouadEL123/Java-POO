package net.elazzioui.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.elazzioui.AccountStatus;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class BankAccount {
    private String id;
    private Date createdAt;
    private double balance;
    private String currency;
    private AccountStatus accountStatus;

    public BankAccount(){
        this.id = UUID.randomUUID().toString();
        this.currency = "$";
        this.createdAt = new Date();
        this.accountStatus = AccountStatus.CREATED;
    }

    public BankAccount(double balance){
        this();
        this.balance = balance;
    }


    @Override
    public String toString(){
        return "Id : "+id+";Created at : "+createdAt+";Balance : "+balance+";Currency : "+currency;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || this.getClass() != this.getClass()) return false;
        BankAccount bankAccount = (BankAccount) obj;
        return id.equals(bankAccount.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public String getId() {
        return id;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
