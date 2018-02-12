package Presentation.StrategyUI.view.AmountStrategy;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AmouStrategyPreviewController {

    @FXML
    private Text Amount;

    @FXML
    private Text StartTime;

    @FXML
    private Text EndTime;


    //setter

    public void setAmount(double amount) {
        Amount.setText(String.valueOf(amount));
    }

    public void setStartTime(String startTime) {
        StartTime .setText(startTime);
    }

    public void setEndTime(String endTime) {
        EndTime.setText(endTime);
    }
}
