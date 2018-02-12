package Presentation.DocUI.PurchaseSaleDoc.view;

import Presentation.CustomerUI.CustomerMainUI;
import Presentation.CustomerUI.view.CustomerLayOutController;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocMainUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * RootLayout跳转根界面的Controller
 * 主要是界面跳转、消息传递、登出
 */
public class RootLayoutController {

    @FXML
    private Button CusManageButton;

    @FXML
    private Button CusDocButton;

    @FXML
    private Button MessageButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Label UserInfo;

    @FXML
    private BorderPane RootLayout;



    @FXML
    private void showCusDoc(){
        try{
            FXMLLoader loader =new FXMLLoader();
            loader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/PurchaseSaleDocLayout.fxml"));

            BorderPane DocLayout=loader.load();
            RootLayout.setCenter(DocLayout);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void showCusManage(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(CustomerMainUI.class.getResource("view/CustomerLayout.fxml"));

            BorderPane CusLayout=loader.load();
            RootLayout.setCenter(CusLayout);

            CustomerLayOutController CusController=loader.getController();
            CusController.setCustomer();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
