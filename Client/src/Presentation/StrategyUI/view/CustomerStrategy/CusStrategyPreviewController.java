package Presentation.StrategyUI.view.CustomerStrategy;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.Date;

public class CusStrategyPreviewController {

    @FXML
    private Text CusLevel;

    @FXML
    private Text StartTime;

    @FXML
    private Text EndTime;


    //setter

    public void setCusLevel(int cusLevel) {
        CusLevel.setText(String.valueOf(cusLevel));
    }

    public void setStartTime(String startTime) {
        StartTime .setText(startTime);
    }

    public void setEndTime(String endTime) {
        EndTime.setText(endTime);
    }
}
