package Presentation.DocUI.PurchaseSaleDoc.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GoodItemController {

    @FXML
    private Text GoodName;

    @FXML
    private Text GoodPrize;

    @FXML
    private Text GoodNumber;

    @FXML
    private Text Comment;

    //setter
    public void setGoodName(String goodName) {
        GoodName.setText(goodName);
    }

    public void setGoodPrize(double goodPrize) {
        GoodPrize.setText(String.valueOf(goodPrize));
    }

    public void setGoodNumber(int goodNumber) {
        GoodNumber.setText(String.valueOf(goodNumber));
    }

    public void setComment(String comment) {
        Comment.setText(comment);
    }
}
