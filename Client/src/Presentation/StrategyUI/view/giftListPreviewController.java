package Presentation.StrategyUI.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class giftListPreviewController {

    @FXML
    private Text name;

    @FXML
    private Text number;


    //setter
    public void setName(String name){
        this.name.setText(name);
    }

    public void setNumber(int n){
        this.number.setText(String.valueOf(n));
    }
}
