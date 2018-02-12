package Presentation.CustomerUI;

import Presentation.CustomerUI.model.Customer;
import Presentation.CustomerUI.view.CustomerLayOutController;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocMainUI;
import Presentation.DocUI.PurchaseSaleDoc.view.PurchaseSaleDocLayoutController;
import VO.SaleDocVO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerMainUI  {


    private Stage primaryStage;
    private BorderPane CusLayout;


    public Pane getCusManagement(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(CustomerMainUI.class.getResource("view/CustomerLayout.fxml"));
            CusLayout=(BorderPane)loader.load();

            CustomerLayOutController controller=loader.getController();
            controller.setCustomer();

            return CusLayout;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public Pane getPurchaseDoc(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/PurchaseSaleDocLayout.fxml"));
            CusLayout=(BorderPane)loader.load();

            PurchaseSaleDocLayoutController controller=loader.getController();
            controller.setPrimaryStage(primaryStage);

            return CusLayout;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void setPrimaryStage(Stage stage){
        primaryStage=stage;
    }

}
