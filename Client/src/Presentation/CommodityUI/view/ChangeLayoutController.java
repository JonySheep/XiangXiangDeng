package Presentation.CommodityUI.view;

import Presentation.CommodityUI.CommodityUI;
import Presentation.CommodityUI.ChangeDataHelper;
import Presentation.CommodityUI.model.Commodity;
import Util.ResultMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;

public class ChangeLayoutController {

    @FXML
    private Label totalSalesVolume;
    @FXML
    private Label totalPurchaseVolume;
    @FXML
    private Label totalGain;
    @FXML
    private ListView<Commodity> changeList;
    @FXML
    private DatePicker startPicker;
    @FXML
    private DatePicker endPicker;


    private void setTextAndColor(Label l,double d){
        if(d>=0){
            l.setText("+"+String.format("%.2f",d));
            l.setTextFill(Color.RED);
        }else{
            l.setText(""+String.format("%.2f",d));
            l.setTextFill(Color.GREEN);
        }
    }


    private ChangeDataHelper changeDataHelper = ChangeDataHelper.getInstance();
    @FXML
    public void initialize(){

        changeList.setCellFactory(new Callback<ListView<Commodity>, ListCell<Commodity>>() {
            @Override
            public ListCell<Commodity> call(ListView<Commodity> param) {
                return new ListCell<Commodity>(){
                    @Override
                    protected void updateItem(Commodity item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty && item != null) {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(CommodityUI.class.getResource("view/ChangeItemPreview.fxml"));

                            try {
                                Pane preview = loader.load();
                                ChangeItemPreviewController controller = loader.getController();
                                controller.setName(item.getGoodName());
                                controller.setId(item.getGoodId());
                                controller.setType(item.getGoodType());
                                controller.setOut(item.getTotalOutbound());
                                controller.setIn(item.getTotalInbound());
                                controller.setChange(item.getTotalInbound()-item.getTotalOutbound());
                                controller.setSales(item.getTotalSalesVolume());
                                controller.setPurchase(item.getTotalPurchaseVolume());
                                controller.setMoney(item.getTotalSalesVolume()-item.getTotalPurchaseVolume());
                                setGraphic(preview);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
            }
        });
        changeList.setItems(changeDataHelper.getCommodity());

        double ts = 0.0,tp = 0.0 ,tg =0.0;
        for(Commodity e:changeList.getItems()){
            ts += e.getTotalSalesVolume();
            tp -= e.getTotalPurchaseVolume();
        }
        tg = ts+tp;

        setTextAndColor(totalSalesVolume,ts);
        setTextAndColor(totalPurchaseVolume,tp);
        setTextAndColor(totalGain,tg);
    }

    /**
     * 按开始和结束时间获取库存变动
     * @param actionEvent
     */
    public void searchCommodity(ActionEvent actionEvent) {
        LocalDate start = startPicker.getValue();
        LocalDate end = endPicker.getValue();

        ResultMessage rm = changeDataHelper.searchCommodity(start,end);
        if(rm == ResultMessage.SUCCESS){
            initialize();
        }

    }
}
