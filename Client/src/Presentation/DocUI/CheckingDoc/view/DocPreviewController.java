package Presentation.DocUI.CheckingDoc.view;

import Util.DocType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import javax.print.Doc;

public class DocPreviewController {
    @FXML
    private Text DocType;

    @FXML
    private Label Name;

    @FXML
    private Label Comment;

    @FXML
    private Label prKey;

    public void setDocType(DocType docType) {
        switch (docType){
            case PURCHASE:DocType.setText("进");break;
            case PURCHASE_RETURN:DocType.setText("退");break;
            case SALE:DocType.setText("销");break;
            case SALE_RETURN:DocType.setText("退");break;
            case GOOD_OVERFLOW:DocType.setText("溢");break;
            case GOOD_LOSS:DocType.setText("损");break;
            case GOOD_GIFT:DocType.setText("赠");break;
            case CASH:DocType.setText("现");break;
            case PAYING:DocType.setText("付");break;
            case RECEIVING:DocType.setText("收");break;
        }
    }

    public void setName(String name) {
        Name.setText(name);
    }

    public void setComment(String comment) {
        Comment.setText(comment);
    }

    public void setPrKey(String pr){
        prKey.setText(pr);
    }
}
