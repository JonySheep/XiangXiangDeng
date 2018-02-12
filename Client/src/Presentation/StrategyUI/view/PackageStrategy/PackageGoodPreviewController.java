package Presentation.StrategyUI.view.PackageStrategy;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PackageGoodPreviewController {

    @FXML
    private Text goodName;

    @FXML
    private Text goodNumber;


    //setter
    public void setGoodName(String name){
        this.goodName.setText(name);
    }

    public void setGoodNumber(int n){
        this.goodNumber.setText(String.valueOf(n));
    }
}
