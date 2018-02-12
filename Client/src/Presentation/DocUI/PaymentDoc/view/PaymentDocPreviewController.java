package Presentation.DocUI.PaymentDoc.view;

import Util.DocType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * 左边栏单据列表的
 */
public class PaymentDocPreviewController {

    @FXML
    private Label CusName;

    @FXML
    private Label comment;

    @FXML
    private Text Type;

    @FXML
    private Label CardNumber;

    //setter

    public void setCusName(String cusName) {
        CusName.setText(cusName);
    }

    public void setComment(String comment) {
        this.comment.setText(comment);
    }

    public void setDocType(DocType type){
        if(type==DocType.PAYING){
            Type.setText("付");
        }
        else if(type==DocType.RECEIVING){
            Type.setText("收");
        }
        else{
            Type.setText("现");
        }
    }

    public void setCardNumber(String cardNumber){
        CardNumber.setText(cardNumber);
    }
}
