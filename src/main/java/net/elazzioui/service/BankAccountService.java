package net.elazzioui.service;

import net.elazzioui.exception.BalanceNotSufficientException;
import net.elazzioui.exception.BankAccountNotFoundException;
import net.elazzioui.model.BankAccount;
import net.elazzioui.model.Condition;
import net.elazzioui.model.CurrentAccount;
import net.elazzioui.model.SavingAccount;

import java.util.List;

public interface BankAccountService {

    BankAccount addBankAccount(BankAccount bankAccount);
    List<BankAccount> getAccounts();
    BankAccount getAccount(String accountId) throws BankAccountNotFoundException;
    void addRandomData(int size);
    void credit(String accountId,double amount) throws BankAccountNotFoundException;
    void debit(String accountId , double amount) throws BankAccountNotFoundException , BalanceNotSufficientException;
    void transfer(String accountSourceId , String accountDestinationId , double amount)
            throws BankAccountNotFoundException , BalanceNotSufficientException;
    List<CurrentAccount> getCurrentAccounts();
    List<SavingAccount> getSavingAccounts();
    Double getTotalBalance();
    List<BankAccount> search(Condition<BankAccount> condition);




}
