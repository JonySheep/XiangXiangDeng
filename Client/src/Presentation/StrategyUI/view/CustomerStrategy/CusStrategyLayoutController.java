package Presentation.StrategyUI.view.CustomerStrategy;

import Presentation.StrategyUI.StrategyMainUI;
import VO.CustomerStrategyVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class CusStrategyLayoutController {


    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Text Type;


    @FXML
    private ListView<CustomerStrategyVO> CusStrategyList;


    /**
     * 初始化StrategyLayout界面
     */
    @FXML
    private void initialize(){
        CusStrategyList.setCellFactory((ListView<CustomerStrategyVO> Strategys)->new CusStraPreview());
        CusStrategyList.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->showCusDetail(newValue));
    }


    private class CusStraPreview extends ListCell<CustomerStrategyVO>{
        @Override
        protected  void updateItem(CustomerStrategyVO item,boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(StrategyMainUI.class.getResource("view/CustomerStrategy/CusStrategyPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    CusStrategyPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setCusLevel(item.getLevel());
                    controller.setStartTime(item.getBegin().format(DateTimeFormatter.ISO_LOCAL_DATE));
                    controller.setEndTime(item.getEnd().format(DateTimeFormatter.ISO_LOCAL_DATE));

                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    private void showCusDetail(CustomerStrategyVO CusStrategy){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/CustomerStrategy/CusStrategyDetail.fxml"));

            AnchorPane detail=loader.load();

            CusStrategyDetailController controller=loader.getController();
            controller.setBeginTime(CusStrategy.getBegin());
            controller.setEndTime(CusStrategy.getEnd());
            controller.setList(CusStrategy.getGift());
            controller.setDiscount(CusStrategy.getDiscount());
            controller.setVoucherAmount(CusStrategy.getVouchers().getAmount());

            Stage detailStage=new Stage();
            Scene scene=new Scene(detail);
            detailStage.setScene(scene);

            controller.setDetailStage(detailStage);

            detailStage.centerOnScreen();
            detailStage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 加入客户回馈策略数据方法
     * @param strategys
     */
    public void setCusStrategy(ObservableList<CustomerStrategyVO> strategys){
        CusStrategyList.setItems(strategys);
    }


    /**
     * 新增促销策略响应方法
     */
    @FXML
    private void add(){
        /**
        新增客户回馈策略
         */
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/CustomerStrategy/NewCusStrategyDetailLayout.fxml"));

            BorderPane newCusStrategyLayout=loader.load();
            NewCusStrategyDetailLayoutController controller=loader.getController();
            Stage addStage=new Stage();

            Scene scene=new Scene(newCusStrategyLayout);
            addStage.setScene(scene);

            controller.setAddStage(addStage);

            addStage.centerOnScreen();
            addStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
