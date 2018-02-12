package Presentation.CustomerUI.view;

import Util.CustomerKinds;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class PreviewController {

    @FXML
    private Label CustomerID;

    @FXML
    private  Label CustomerName;

    @FXML
    private Label CustomerKind;

    @FXML
    private Label CustomerLevel;

    @FXML
    private  Label comment;

    @FXML
    private Text DocType;


    public void setCustomerID(String ID){
        this.CustomerID.setText(ID);
    }

    public void setCustomerName(String Name) {
        this.CustomerName.setText(Name);
    }

    public void setCustomerKind(CustomerKinds Kind) {
        if(Kind==CustomerKinds.SUPPLIER){
            this.CustomerKind.setText("供应商");
        }
        else if(Kind==CustomerKinds.SALER){
            this.CustomerKind.setText("销售商");
        }
    }

    public void setCustomerLevel(int level) {
        this.CustomerLevel.setText("VIP"+String.valueOf(level));
    }

    public void setComment(String comment) {
        this.comment.setText(comment);
    }


}
