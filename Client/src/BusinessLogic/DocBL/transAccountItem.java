package BusinessLogic.DocBL;

public class transAccountItem {

    String account;
    double amount;
    String comment;

    public transAccountItem(String currentAccount, double currentAmount, String currentComment){
        account=currentAccount;
        amount=currentAmount;
        comment=currentComment;
    }

    public String getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }
}
