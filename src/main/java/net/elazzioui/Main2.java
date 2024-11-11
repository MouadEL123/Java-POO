package net.elazzioui;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.elazzioui.Data.Mapper;
import net.elazzioui.exception.BankAccountNotFoundException;
import net.elazzioui.model.BankAccount;
import net.elazzioui.model.CurrentAccount;
import net.elazzioui.service.BankAccountService;
import net.elazzioui.service.BankAccountServiceImpl;

import java.util.List;

public class Main2 {
    public static void main(String[] args){
        BankAccountService bankAccountService = new BankAccountServiceImpl();
        Mapper mapper = new Mapper();
        List<BankAccount> bankAccountList;

        // 1) add random data

        bankAccountService.addRandomData(10);
        bankAccountService.addBankAccount(new CurrentAccount(300000 , 9000));
        bankAccountList = bankAccountService.getAccounts();

        bankAccountList.forEach(bankAccount -> {
            try {
                System.out.println(mapper.toJson(bankAccount));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("--------------------------------------------------------------");

        String accountId = bankAccountList.get(0).getId();
        try{
            System.out.println(bankAccountService.getAccount(accountId));
        } catch (BankAccountNotFoundException ex){
            System.out.println(ex.getMessage());
        }



    }
}
