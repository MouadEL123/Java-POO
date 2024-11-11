package net.elazzioui;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.elazzioui.Data.Mapper;
import net.elazzioui.exception.BankAccountNotFoundException;
import net.elazzioui.model.BankAccount;
import net.elazzioui.model.CurrentAccount;
import net.elazzioui.model.SavingAccount;
import net.elazzioui.service.BankAccountService;
import net.elazzioui.service.BankAccountServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main3 {
    public static void main(String[] args) throws JsonProcessingException {
        BankAccountService bankAccountService = new BankAccountServiceImpl();
        Mapper mapper = new Mapper();
        List<BankAccount> bankAccountList = new ArrayList<>();
        List<SavingAccount> savingAccounts = new ArrayList<>();
        List<CurrentAccount> currentAccounts = new ArrayList<>();

        // 1) add data
        bankAccountService.addRandomData(7);
        bankAccountList = bankAccountService.getAccounts();
        currentAccounts=bankAccountService.getCurrentAccounts();
        savingAccounts=bankAccountService.getSavingAccounts();

        System.out.println("============ BankAccounts ===============");
        bankAccountList.forEach(bankAccount -> {
            try {
                System.out.println(mapper.toJson(bankAccount));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("============ CurrentAccounts ===============");
        currentAccounts.forEach(currentAccount -> {
            try {
                System.out.println(mapper.toJson(currentAccount));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("============ SavingAccounts ===============");
        savingAccounts.forEach(savingAccount -> {
            try {
                System.out.println(mapper.toJson(savingAccount));
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
            }
        });

        BankAccount bankAccount1 = bankAccountList.get(0);
        BankAccount bankAccount2 = bankAccountList.get(1);

        String accountId1 = bankAccount1.getId();
        String accountId2 = bankAccount2.getId();

        System.out.println("================= Credit ===================== ");
        System.out.println(mapper.toJson(bankAccount1));
        System.out.println(mapper.toJson(bankAccount2));
        try{
            bankAccountService.credit(accountId1,1000);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("================= After-Credit ===================== ");
        System.out.println(mapper.toJson(bankAccount1));
        System.out.println(mapper.toJson(bankAccount2));
        System.out.println("================= Debit ===================== ");
        System.out.println(mapper.toJson(bankAccount1));
        System.out.println(mapper.toJson(bankAccount2));
        try{
            bankAccountService.debit(accountId1,200);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("================= After - Debit ===================== ");
        System.out.println(mapper.toJson(bankAccount1));
        System.out.println(mapper.toJson(bankAccount2));
        System.out.println("================= Transfer ===================== ");
        System.out.println(mapper.toJson(bankAccount1));
        System.out.println(mapper.toJson(bankAccount2));
        try{
            bankAccountService.transfer(accountId1,accountId2,400);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("================= After-Transfer ===================== ");
        System.out.println(mapper.toJson(bankAccount1));
        System.out.println(mapper.toJson(bankAccount2));

        System.out.println("================= Total-Balance ===================== ");
        System.out.println(bankAccountService.getTotalBalance());
        System.out.println("================= Search 1 ===================== ");
        bankAccountService.search(bankAccount -> bankAccount.getBalance() > 900000).forEach(System.out::println);
        System.out.println("================= Search 2 ===================== ");
        bankAccountService.search(bankAccount -> bankAccount.getAccountStatus().equals(AccountStatus.CREATED)).forEach(System.out::println);





    }
}
