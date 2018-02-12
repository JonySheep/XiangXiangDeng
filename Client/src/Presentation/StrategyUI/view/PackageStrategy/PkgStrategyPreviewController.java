package Presentation.StrategyUI.view.PackageStrategy;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class PkgStrategyPreviewController {

    @FXML
    private Text begin;

    @FXML
    private Text end;

    @FXML
    private Text PkgName;

    @FXML
    private Text PkgPrize;


    //setter

    public void setBegin(String begin) {
        this.begin.setText(begin);
    }

    public void setEnd(String end) {
        this.end.setText(end);
    }

    public void setPkgName(String pkgName) {
        PkgName.setText(pkgName);
    }

    public void setPkgPrize(double pkgPrize) {
        PkgPrize.setText(String.valueOf(pkgPrize));
    }
}
