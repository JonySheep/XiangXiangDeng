package Presentation.CustomerUI.info;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class CusListPreviewController {

    @FXML
    private Text CusName;

    @FXML
    private Text CusID;

    @FXML
    private Text CusLevel;

    //setter
    public void setCusName(String cusName) {
        CusName.setText(cusName);
    }

    public void setCusID(String cusID) {
        CusID.setText(cusID);
    }

    public void setCusLevel(String cusLevel) {
        CusLevel.setText(cusLevel);
    }
}
