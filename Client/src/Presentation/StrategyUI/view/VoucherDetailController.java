package Presentation.StrategyUI.view;

import Util.Voucher;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.time.LocalDate;

public class VoucherDetailController {

    @FXML
    private TextArea Amount;

    //getter


    public int getAmount(){return Integer.parseInt(Amount.getText());}


    /**
     * 将所有代金券数据包装到代金券对象中传递出去
     * @return
     */
    public Voucher getVoucher(){
        Voucher voucher=new Voucher(this.getAmount());
        return voucher;
    }
}
