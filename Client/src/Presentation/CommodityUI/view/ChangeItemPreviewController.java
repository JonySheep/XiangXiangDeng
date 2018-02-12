package Presentation.CommodityUI.view;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ChangeItemPreviewController {
    public Label name;
    public Label id;
    public Label type;
    public Label out;
    public Label in;
    public Label change;
    public Label sales;
    public Label purchase;
    public Label money;

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setOut(Integer out) {
        this.out.setText(""+out);
    }

    public void setIn(Integer in) {
        this.in.setText(""+in);
    }

    public void setChange(Integer change) {
        this.change.setText(""+change);
    }

    public void setSales(Double sales) {
        this.sales.setText(""+sales);
    }

    public void setPurchase(Double purchase) {
        this.purchase.setText(""+purchase);
    }

    public void setMoney(Double money) {
        if(money>=0){
            this.money.setText("+"+money);
            this.money.setTextFill(Color.RED);
        }else{
            this.money.setText(""+money);
            this.money.setTextFill(Color.GREEN);
        }
    }
}
