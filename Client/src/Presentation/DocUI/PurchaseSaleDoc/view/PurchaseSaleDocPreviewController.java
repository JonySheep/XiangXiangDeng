package Presentation.DocUI.PurchaseSaleDoc.view;

import Util.DocType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import javax.print.Doc;

/**
 * 左边栏单据列表的
 */
public class PurchaseSaleDocPreviewController {

    @FXML
    private Label DocID;

    @FXML
    private Label CusName;

    @FXML
    private Label Amount;

    @FXML
    private Label comment;

    @FXML
    private Text DocType;

    //setter

    public void setDocID(String docID) {
        DocID.setText(docID);
    }

    public void setCusName(String cusName) {
        CusName.setText(cusName);
    }

    public void setAmount(String amount) {
        Amount.setText(amount);
    }

    public void setComment(String comment) {
        this.comment.setText(comment);
    }

    public void setType(Util.DocType type){
        if(type == Util.DocType.PURCHASE ){
            DocType.setText("进");
        }
        else if(type== Util.DocType.PURCHASE_RETURN|| type==Util.DocType.SALE_RETURN){
            DocType.setText("退");
        }
        else if(type== Util.DocType.SALE){
            DocType.setText("销");
        }
    }

}
