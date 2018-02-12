package Presentation.StrategyUI.view;

import Presentation.StrategyUI.StrategyDataHelper;
import Presentation.StrategyUI.StrategyMainUI;
import Presentation.StrategyUI.view.AmountStrategy.AmouStrategyLayoutController;
import Presentation.StrategyUI.view.CustomerStrategy.CusStrategyLayoutController;
import Presentation.StrategyUI.view.PackageStrategy.PkgStrategyLayoutController;
import Util.EmptyValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class RootLayoutController {
    /*
    上边框跳转界面按钮
     */
    @FXML
    private Button CheckDocButton;

    @FXML
    private Button MakeStrategyButton;

    @FXML
    private Button CheckLogButton;

    @FXML
    private Button SaleConditionButton;

    @FXML
    private Button ManageHistoryButton;

    @FXML
    private Button ManageConditionButton;

    @FXML
    private Button Message;

    @FXML
    private Label User;

    @FXML
    private Button LogoutButton;

    @FXML
    private ComboBox<String> StrategyType;

    @FXML
    private BorderPane rootLayout;

    private StrategyDataHelper dataHelper=new StrategyDataHelper();


    /**
     * 初始化Root界面
     */
    @FXML
    private void initialize(){

        StrategyType.setItems(FXCollections.observableArrayList("客户回馈策略","组合降价策略","总额赠送策略"));
        //默认显示客户回馈策略
        StrategyType.setValue("客户回馈策略");
    }


    /**
     * 根据ComboBox的选择显示不同的促销策略列表
     */
    @FXML
    private void chooseType(){
        /**
         * 显示客户回馈策略界面
         */
        if(StrategyType.getSelectionModel().getSelectedItem().toString().equals("客户回馈策略")){
            try{
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(StrategyMainUI.class.getResource("view/CustomerStrategy/CusStrategyLayout.fxml"));

                BorderPane CusLayout=loader.load();
                rootLayout.setCenter(CusLayout);

                CusStrategyLayoutController controller=loader.getController();
                controller.setCusStrategy(dataHelper.getCusStrategyList());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


        /**
         * 显示组合降价策略
         */
        else if(StrategyType.getSelectionModel().getSelectedItem().toString().equals("组合降价策略")){
            try{
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(StrategyMainUI.class.getResource("view/PackageStrategy/PkgStrategyLayout.fxml"));

                BorderPane PkgLayout=loader.load();
                rootLayout.setCenter(PkgLayout);

                PkgStrategyLayoutController controller=loader.getController();
                //controller.setCusStrategy(dataHelper.getCusStrategyList());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


        else if(StrategyType.getSelectionModel().getSelectedItem().toString().equals("总额赠送策略")){
            try{
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(StrategyMainUI.class.getResource("view/AmountStrategy/AmouStrategyLayout.fxml"));

                BorderPane PkgLayout=loader.load();
                rootLayout.setCenter(PkgLayout);

                AmouStrategyLayoutController controller=loader.getController();
                controller.setAmouStrategy(dataHelper.getAmouStrategyList());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
