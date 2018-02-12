package Presentation.DocUI.GoodDoc.view;

import Util.DocType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * 左边栏单据列表的
 */
public class GoodDocPreviewController {

    @FXML
    private Label CusName;

    @FXML
    private Label comment;

    @FXML
    private Text Type;

    //setter

    public void setCusName(String cusName) {
        CusName.setText(cusName);
    }

    public void setComment(String comment) {
        this.comment.setText(comment);
    }

    public void setDocType(DocType type){
        if(type==DocType.GOOD_OVERFLOW){
            Type.setText("溢");
        }
        else {
            Type.setText("损");
        }
    }
}
