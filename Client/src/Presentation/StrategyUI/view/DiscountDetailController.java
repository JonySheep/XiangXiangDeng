package Presentation.StrategyUI.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class DiscountDetailController {

    @FXML
    private TextArea discount;

    public double getDiscount(){
        return Double.parseDouble(discount.getText());
    }

    public void setDiscount(double dis){
        discount.setText(String.valueOf(dis));
    }
}
