package net.elazzioui.service;

import net.elazzioui.AccountStatus;
import net.elazzioui.exception.BalanceNotSufficientException;
import net.elazzioui.exception.BankAccountNotFoundException;
import net.elazzioui.model.BankAccount;
import net.elazzioui.model.Condition;
import net.elazzioui.model.CurrentAccount;
import net.elazzioui.model.SavingAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankAccountServiceImpl implements BankAccountService{

    private List<BankAccount> bankAccountList = new ArrayList<>();
    @Override
    public BankAccount addBankAccount(BankAccount bankAccount) {
        this.bankAccountList.add(bankAccount);
        return bankAccount;
    }

    @Override
    public List<BankAccount> getAccounts() {
        return this.bankAccountList;
    }

    @Override
    public BankAccount getAccount(String accountId) throws BankAccountNotFoundException {

        // 1)
        /*
        for(int i = 0 ; i < this.bankAccountList.size() ; i++){
            if(bankAccountList.get(i).getId().equals(accountId))
                return bankAccountList.get(i);
        }

        throw  new BankAccountNotFoundException("account not found");
         */

        // 2)

        return bankAccountList
                .stream()
                .filter(account -> account.getId().equals(accountId))
                .findFirst()
                .orElseThrow(()->new BankAccountNotFoundException("account not found"));


    }

    @Override
    public void addRandomData(int size) {
        AccountStatus[] accountStatuses = AccountStatus.values();
        Random random = new Random();
        for(int i= 0 ; i < size ; i++){
            if(Math.random() > 0.5){
                CurrentAccount currentAccount = new CurrentAccount(Math.random() * 1666666 , 4000);
                currentAccount.setAccountStatus(accountStatuses[random.nextInt(accountStatuses.length)]);
                bankAccountList.add(currentAccount);
            } else {
                SavingAccount savingAccount = new SavingAccount(Math.random() * 1666666 , Math.random());
                savingAccount.setAccountStatus(accountStatuses[random.nextInt(accountStatuses.length)]);
                bankAccountList.add(savingAccount);
            }
        }
    }

    @Override
    public void credit(String accountId, double amount) throws BankAccountNotFoundException {
            BankAccount bankAccount = getAccount(accountId);
            bankAccount.setBalance(bankAccount.getBalance() + amount);
    }

    @Override
    public void debit(String accountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
            BankAccount bankAccount = getAccount(accountId);
            if(bankAccount.getBalance() < amount) throw new BalanceNotSufficientException("balance not sufficient");
            bankAccount.setBalance(bankAccount.getBalance() - amount);

    }

    @Override
    public void transfer(String accountSourceId, String accountDestinationId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
            debit(accountSourceId,amount);
            credit(accountDestinationId,amount);
    }

    @Override
    public List<CurrentAccount> getCurrentAccounts() {

        return bankAccountList
                .stream()
                .filter(bankAccount -> bankAccount instanceof CurrentAccount)
                .map(currentAccount -> (CurrentAccount)currentAccount)
                .toList();
    }

    @Override
    public List<SavingAccount> getSavingAccounts() {
        return bankAccountList
                .stream()
                .filter(bankAccount -> bankAccount instanceof SavingAccount)
                .map(savingAccount -> (SavingAccount)savingAccount)
                .toList();
    }

    @Override
    public Double getTotalBalance() {
        /*
        double sum = 0;
        for(BankAccount bankAccount : bankAccountList){
            sum+=bankAccount.getBalance();
        }
        return sum;
         */

        return bankAccountList.stream()
                .map(bankAccount -> bankAccount.getBalance())
                .reduce(0.0,(a,b)->a+b);
    }

    @Override
    public List<BankAccount> search(Condition<BankAccount> condition) {
        return bankAccountList.stream()
                .filter(bankAccount -> condition.test(bankAccount))
                .toList();
    }

}
