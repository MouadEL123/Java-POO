package net.elazzioui.model;

public class SavingAccount extends BankAccount {
    private double interestRate;

    public SavingAccount(double balance,double interestRate){
        super(balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public String getTye(){
        return "SAVING_ACCOUNT";
    }

    @Override
    public String toString(){
        return super.toString() + ";Interest rate : " + this.interestRate;
    }
}
