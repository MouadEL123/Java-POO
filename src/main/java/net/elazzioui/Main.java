package net.elazzioui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.elazzioui.model.BankAccount;
import net.elazzioui.model.CurrentAccount;
import net.elazzioui.model.SavingAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        /*
                String s1 = "mouad";
                String s2 = "mouad";
                System.out.println(s1==s2);

         */

        BankAccount [] bankAccounts = new BankAccount[4];
        bankAccounts[0] = new BankAccount(3000);
        bankAccounts[1] = new BankAccount(55000);
        bankAccounts[2] = new BankAccount(30);
        bankAccounts[3] = new BankAccount(90);

        for(int i =0 ; i < bankAccounts.length ; i++){
            System.out.println(bankAccounts[i]);
        }

        System.out.println("----------------------------------------------------------------");
        for(BankAccount bankAccount : bankAccounts){
            System.out.println(bankAccount);
        }
        System.out.println("----------------------------------------------------------------");

        List<BankAccount> accountList = new ArrayList<>();
        accountList.add(new BankAccount(60));
        accountList.add(new BankAccount(90));
        accountList.add(new BankAccount(70));
        accountList.add(new BankAccount(1090));

        for(int i = 0  ; i <accountList.size() ; i++){
            System.out.println(accountList.get(i));
        }

        System.out.println("----------------------------------------------------------------");

        Map<String,BankAccount> accountMap = new HashMap<>();
        accountMap.put("1",new BankAccount(90));
        accountMap.put("2",new BankAccount(89));
        accountMap.put("3",new BankAccount(8999));

        for(String key : accountMap.keySet()){
            System.out.println("-----"+key+"-------");
            System.out.println(accountMap.get(key));
        }

        System.out.println("----------------------------------------------------------------");

        for (BankAccount bankAccount : accountMap.values()){
            System.out.println(bankAccount);
        }

        System.out.println("----------------------------------------------------------------");

        for (BankAccount bankAccount : accountMap.values()){
            System.out.println(mapper(bankAccount));
        }

        System.out.println("----------------------------------------------------------------");

        List<BankAccount> bankAccountList = new ArrayList<>();
        bankAccountList.add(new SavingAccount(89898,0.9));
        bankAccountList.add(new SavingAccount(6777,0.8));
        bankAccountList.add(new CurrentAccount(7888,70));

        bankAccountList.forEach(bankAccount -> {
            try {
                System.out.println(mapper(bankAccount));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        BankAccount bankAccount1 = new SavingAccount(89898,0.9);
        System.out.println(((SavingAccount)bankAccount1).getTye());




    }


    public static String mapper(BankAccount bankAccount) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bankAccount);
    }
}