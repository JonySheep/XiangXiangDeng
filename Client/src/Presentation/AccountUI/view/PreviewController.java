package Presentation.AccountUI.view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PreviewController {
    @FXML
    private Label cardName;

    @FXML
    private Label cardNumber;

    @FXML
    private Label remark;

    public String getCardName() {
        return cardName.getText();
    }

    public void setCardName(String cardName) {
        this.cardName.setText(cardName);
    }

    public String getCardNumber() {
        return cardNumber.getText();
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.setText(cardNumber);
    }

    public String getRemark() {
        return remark.getText();
    }

    public void setRemark(String remark) {
        this.remark.setText(remark);
    }
}
