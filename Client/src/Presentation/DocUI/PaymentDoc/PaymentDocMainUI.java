package Presentation.DocUI.PaymentDoc;

import Presentation.DocUI.PaymentDoc.model.PaymentDoc;
import Presentation.DocUI.PaymentDoc.view.PaymentDocLayoutController;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocMainUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentDocMainUI {
    private Stage primaryStage;
    private BorderPane PaymentDocLayout;


    public Pane getFinanceDoc(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(PaymentDocMainUI.class.getResource("view/PaymentDocLayout.fxml"));

            PaymentDocLayout=loader.load();

            PaymentDocLayoutController controller=loader.getController();
            controller.setPrimaryStage(primaryStage);

            return PaymentDocLayout;
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
