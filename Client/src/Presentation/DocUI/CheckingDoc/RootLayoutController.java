package Presentation.DocUI.CheckingDoc;

import Presentation.StrategyUI.StrategyDataHelper;
import Presentation.StrategyUI.StrategyMainUI;
import Presentation.StrategyUI.view.AmountStrategy.AmouStrategyLayoutController;
import Presentation.StrategyUI.view.CustomerStrategy.CusStrategyLayoutController;
import Presentation.StrategyUI.view.PackageStrategy.PkgStrategyLayoutController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

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
    private BorderPane rootLayout;


    /**
     * 初始化界面
     */
    @FXML
    private void initialize(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(CheckingDocMainUI.class.getResource("view/CheckingDocLayout.fxml"));

            BorderPane Layout=loader.load();
            rootLayout.setCenter(Layout);


        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
