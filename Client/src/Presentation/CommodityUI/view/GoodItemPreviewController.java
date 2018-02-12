package Presentation.CommodityUI.view;

import Util.GoodSelectItem;
import VO.GoodItemForPurchaseSaleDocVO;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class GoodItemPreviewController {
    public Text name;
    public Text amount;
    public TextField amountField;
    private GoodSelectItem item;

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setAmount(int amount) {
        this.amount.setText(""+amount);
    }

    public void edit(){
        amountField.setText(""+amount.getText());
        amountField.setVisible(true);
        amountField.requestFocus();
    }

    public void saveOrCancel(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            try{
                int newAmount = Integer.parseInt(amountField.getText());
                setAmount(newAmount);
                item.setNumber(newAmount);
                amountField.setVisible(false);
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("输入的数据有误");
                alert.setHeaderText(null);
                alert.setContentText("请重新填写要添加的商品数量");

                alert.showAndWait();

                return;
            }
        }else if(keyEvent.getCode() == KeyCode.ESCAPE){
            amountField.setVisible(false);
        }
    }

    public void setItem(GoodSelectItem item) {
        this.item = item;
    }
}
