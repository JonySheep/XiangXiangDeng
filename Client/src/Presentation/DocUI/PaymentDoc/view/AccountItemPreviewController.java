package Presentation.DocUI.PaymentDoc.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AccountItemPreviewController {

    @FXML
    private Text AccountID;

    @FXML
    private Text AccountName;

    @FXML
    private Text Money;

    @FXML
    private Text Comment;


    public void setAccountID(String accountID) {
        AccountID.setText(accountID);
    }

    public void setAccountName(String accountName) {
        AccountName.setText(accountName);
    }

    public void setMoney(double money) {
        Money.setText(String.valueOf(money));
    }

    public void setComment(String comment) {
        Comment.setText(comment);
    }
}
