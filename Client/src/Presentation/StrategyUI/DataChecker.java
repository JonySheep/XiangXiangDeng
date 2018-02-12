package Presentation.StrategyUI;

import Util.ResultMessage;
import com.sun.org.apache.regexp.internal.RE;
import javafx.scene.control.Alert;

import javax.xml.transform.Result;
import java.time.LocalDate;

public class DataChecker {

    LocalDate today=LocalDate.now();


    /**
     *若起始日期在当天日期前，则提示错误
     */
    public boolean checkBegin(LocalDate begin){
        if (begin.isBefore(today)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("日期设置错误");
            alert.setHeaderText(null);
            alert.setContentText("您设置的起始日期早于当日，请重新设置");
            alert.showAndWait();

            return false;
        }
        return true;
    }


    /**
     * 若结束日期在起始日期前，则提示错误
     * @param begin
     * @param end
     * @return
     */
    public boolean checkEnd(LocalDate begin,LocalDate end){
        if(end.isBefore(begin)){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("日期设置错误");
            alert.setHeaderText(null);
            alert.setContentText("您设置的结束日期早于起始日期，请重新设置");
            alert.showAndWait();

            return false;
        }
        return true;
    }


    /**
     * 若金额为负，则提示错误
     * @param money
     * @return
     */
    public boolean checkMoney(double money){
        if(money<0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入金额错误");
            alert.setHeaderText(null);
            alert.setContentText("您设置的折让金额小于零，请重新设置");
            alert.showAndWait();

            return false;
        }
        return true;
    }
}
