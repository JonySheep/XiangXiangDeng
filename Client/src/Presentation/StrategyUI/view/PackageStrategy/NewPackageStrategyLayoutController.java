package Presentation.StrategyUI.view.PackageStrategy;

import Presentation.StrategyUI.StrategyDataHelper;
import Presentation.StrategyUI.StrategyMainUI;
import Presentation.StrategyUI.view.CustomerStrategy.CusStrategyPreviewController;
import Util.PackageGoodItem;
import Util.StrategyType;
import VO.CustomerStrategyVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class NewPackageStrategyLayoutController {

    @FXML
    private ListView<PackageGoodItem> goodList;

    @FXML
    private DatePicker begin;

    @FXML
    private DatePicker end;

    @FXML
    private TextArea pkgName;

    @FXML
    private TextArea pkgPrize;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancleButton;

    @FXML
    private Button addButton;

    private ArrayList<PackageGoodItem> goods=new ArrayList<>();

    private StrategyDataHelper dataHelper=new StrategyDataHelper();

    private PackageGoodPreviewController controller;

    private Stage addStage;

    @FXML
    private void initialize(){
        //goodList.setCellFactory((ListView<PackageGoodItem> goods)->new GoodPreview());
    }


    private class GoodPreview extends ListCell<PackageGoodItem>{
        @Override
        protected  void updateItem(PackageGoodItem item, boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(StrategyMainUI.class.getResource("view/PackageStrategy/PackageGoodPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    controller=loader.getController();

                    setGraphic(preview);
                    controller.setGoodName(item.getGoodID());
                    controller.setGoodNumber(item.getGoodNumber());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    @FXML
    private void add(){
        //TODO

        /**
         * 选择商品
         * 返回GoodVO和选择的数量，并包装成PackageGoodItem
         */

        //goods.add();
        //goodList.setItems();


        /**
         * 显示goodList
         */
        //goodList.setCellFactory((ListView<PackageGoodItem> goods)->new GoodPreview());
    }

    @FXML
    private void save(){
        dataHelper.addPkgStrategy(StrategyType.PACKAGE,begin.getValue(),end.getValue(),pkgName.getText(),Double.parseDouble(pkgPrize.getText()),
                goods);

        addStage.close();
    }


    @FXML
    private void cancle(){
        addStage.close();
    }


    public void setAddStage(Stage stage){
        this.addStage=stage;
    }
}
