package Presentation.DocUI.PurchaseSaleDoc.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GiftItemPreviewController {

    @FXML
    private Text name;

    @FXML
    private Text number;

    public void setName(String name) {
        this.name .setText(name);
    }

    public void setNumber(int number) {
        this.number.setText(String.valueOf(number));
    }
}
