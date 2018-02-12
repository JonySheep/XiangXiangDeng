package Presentation.StrategyUI.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * 每一条赠品信息的界面控制器
 */
public class giftPreviewController {

    @FXML
    private Text GoodName;

    @FXML
    private Text GoodID;

    @FXML
    private Text Number;

    //setter
    public void setGoodName(String goodName) {
        GoodName.setText(goodName);
    }

    public void setGoodID(String goodID) {
        GoodID.setText(goodID);
    }

    public void setNumber(int number) {
        Number.setText(String.valueOf(number));
    }

    public int getNumber(){return Integer.parseInt(Number.getText());}
}
