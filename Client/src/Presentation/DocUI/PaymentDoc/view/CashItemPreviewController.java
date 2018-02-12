package Presentation.DocUI.PaymentDoc.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class CashItemPreviewController {
    @FXML
    private Text itemName;

    @FXML
    private Text Amount;

    @FXML
    private Text comment;

    public void setItemName(String itemName) {
        this.itemName.setText(itemName);
    }

    public void setAmount(String amount) {
        Amount.setText(amount);
    }

    public void setComment(String comment) {
        this.comment.setText(comment);
    }
}
