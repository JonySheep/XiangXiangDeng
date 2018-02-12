package BusinessLogic.DocBL;

public class CashItem {

    String name;
    double amount;
    String comment;

    public CashItem(String currentName,double currentAmount,String currentComment){
        name=currentName;
        amount=currentAmount;
        comment=currentComment;
    }

    public double getAmount() {
        return amount;
    }
}
