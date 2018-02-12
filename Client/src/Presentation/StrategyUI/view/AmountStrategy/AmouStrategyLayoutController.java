package Presentation.StrategyUI.view.AmountStrategy;

import Presentation.StrategyUI.StrategyMainUI;
import VO.AmountStrategyVO;
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


public class AmouStrategyLayoutController {


    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Text Type;


    @FXML
    private ListView<AmountStrategyVO> StrategyList;


    /**
     * 初始化StrategyLayout界面
     */
    @FXML
    private void initialize(){
        StrategyList.setCellFactory((ListView<AmountStrategyVO> Strategys)->new AmouStraPreview());
        StrategyList.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->showCusDetail(newValue));
    }


    private class AmouStraPreview extends ListCell<AmountStrategyVO>{
        @Override
        protected  void updateItem(AmountStrategyVO item,boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(StrategyMainUI.class.getResource("view/AmountStrategy/AmouStrategyPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    AmouStrategyPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setAmount(item.getAmount());
                    controller.setStartTime(item.getBegin().format(DateTimeFormatter.ISO_LOCAL_DATE));
                    controller.setEndTime(item.getEnd().format(DateTimeFormatter.ISO_LOCAL_DATE));

                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    private void showCusDetail(AmountStrategyVO CusStrategy){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/AmountStrategy/AmouStrategyDetail.fxml"));

            AnchorPane detail=loader.load();

            AmouStrategyDetailController controller=loader.getController();
            controller.setBeginTime(CusStrategy.getBegin());
            controller.setEndTime(CusStrategy.getEnd());
            controller.setList(CusStrategy.getGift());
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
     * 加入总额赠送策略数据方法
     * @param strategys
     */
    public void setAmouStrategy(ObservableList<AmountStrategyVO> strategys){
        StrategyList.setItems(strategys);
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
            loader.setLocation(StrategyMainUI.class.getResource("view/AmouStrategy/NewAmouStrategyDetailLayout.fxml"));

            BorderPane newCusStrategyLayout=loader.load();
            NewAmouStrategyDetailLayoutController controller=loader.getController();
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
