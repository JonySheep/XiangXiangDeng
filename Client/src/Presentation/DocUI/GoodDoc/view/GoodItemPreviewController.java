package Presentation.DocUI.GoodDoc.view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GoodItemPreviewController {

    @FXML
    private Text goodName;

    @FXML
    private Text goodID;

    @FXML
    private Text goodType;

    @FXML
    private Text goodCommodity;

    @FXML
    private Text goodWarning;

    //setter
    public void setGoodName(String goodName) {
        this.goodName.setText(goodName);
    }

    public void setGoodID(String goodID) {
        this.goodID.setText(goodID);
    }

    public void setGoodType(String goodType) {
        this.goodType .setText(goodType);
    }

    public void setGoodCommodity(int goodCommodity) {
        this.goodCommodity.setText(String.valueOf(goodCommodity));
    }

    public void setGoodWarning(int goodWarning) {
        this.goodWarning .setText(String.valueOf(goodWarning));
    }
}
