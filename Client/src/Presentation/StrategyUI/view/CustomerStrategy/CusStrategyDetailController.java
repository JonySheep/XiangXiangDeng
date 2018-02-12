package Presentation.StrategyUI.view.CustomerStrategy;

import Presentation.StrategyUI.StrategyMainUI;
import Presentation.StrategyUI.view.giftListPreviewController;
import Util.GiftItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CusStrategyDetailController {

    @FXML
    private Label beginTime;

    @FXML
    private Label endTime;

    @FXML
    private ListView<GiftItem> giftLists;

    @FXML
    private Label discount;

    @FXML
    private Label voucherAmount;

    @FXML
    private Label voucherNumber;

    @FXML
    private Label voucherBegin;

    @FXML
    private Label voucherEnd;

    @FXML
    private Button okButton;

    private Stage detailStage;


    @FXML
    private void initialize(){
        giftLists.setCellFactory((ListView<GiftItem> CusStrategy)->new CusStrategyPreview());
    }


    private class CusStrategyPreview extends ListCell<GiftItem>{
        @Override
        protected  void updateItem(GiftItem item, boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(StrategyMainUI.class.getResource("view/giftListPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    giftListPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setName(item.getGoodName());
                    controller.setNumber(item.getNumber());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 关闭查看界面
     */
    @FXML
    private void close(){
        detailStage.close();
    }


    public void setBeginTime(LocalDate date){
        beginTime.setText(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public void setEndTime(LocalDate date){
        endTime.setText(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public void setList(ArrayList<GiftItem> gifts){
        ObservableList<GiftItem> giftsList= FXCollections.observableArrayList();
        for(GiftItem g:gifts){
            giftsList.add(g);
        }

        this.giftLists.setItems(giftsList);
    }

    public void setDiscount(double dis){
        discount.setText(String.valueOf(dis));
    }

    public void setVoucherAmount(int amount){
        voucherAmount.setText(String.valueOf(amount));
    }

    public void setVoucherNumber(int n){
        voucherNumber.setText(String.valueOf(n));
    }

    public void setVoucherBegin(LocalDate date){
        voucherBegin.setText(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public void setVoucherEnd(LocalDate date){
        voucherEnd.setText(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public void setDetailStage(Stage stage){
        this.detailStage=stage;
    }
}
