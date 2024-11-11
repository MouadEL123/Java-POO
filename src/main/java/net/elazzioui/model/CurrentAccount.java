package net.elazzioui.model;

public class CurrentAccount extends BankAccount{
    private double overDraft;

    public CurrentAccount(double balance,double overDraft){
        super(balance);
        this.overDraft = overDraft;
    }
    @Override
    public String toString(){
        return super.toString()+";Over Draft : " + overDraft;
    }

    public String getType(){
        return "CURRENT_ACCOUNT";
    }

    public double getOverDraft() {
        return overDraft;
    }
}
