package Presentation.CustomerUI.view;

import Presentation.CustomerUI.CustomerMainUI;
import Presentation.DocUI.PurchaseSaleDoc.view.PurchaseSaleDocLayoutController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


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
            loader.setLocation(CustomerMainUI.class.getResource("view/PurchaseSaleDocLayout.fxml"));

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
