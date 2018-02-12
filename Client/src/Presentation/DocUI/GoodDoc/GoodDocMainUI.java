package Presentation.DocUI.GoodDoc;

import Presentation.CommodityUI.CommodityUI;
import Presentation.DocUI.GoodDoc.view.GoodDocLayoutController;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocMainUI;
import Presentation.DocUI.PurchaseSaleDoc.model.PurchaseSaleDoc;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GoodDocMainUI {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private BorderPane GoodDocLayout;


    public Pane getGoodDoc(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(GoodDocMainUI.class.getResource("view/GoodDocLayout.fxml"));

            GoodDocLayout=loader.load();

            GoodDocLayoutController controller=loader.getController();
            controller.setPrimaryStage(primaryStage);

            return GoodDocLayout;
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
