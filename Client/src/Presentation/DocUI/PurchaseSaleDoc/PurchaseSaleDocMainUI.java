package Presentation.DocUI.PurchaseSaleDoc;

import Presentation.DocUI.PurchaseSaleDoc.model.PurchaseSaleDoc;
import Presentation.DocUI.PurchaseSaleDoc.view.PurchaseSaleDocLayoutController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchaseSaleDocMainUI {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private BorderPane SaleDocLayout;
    private PurchaseSaleDocDataHelper dataHelper=new PurchaseSaleDocDataHelper();

    public PurchaseSaleDocMainUI(Stage stage){
        this.primaryStage=stage;
        primaryStage.setTitle("香香灯");

        initRootLayout();
        showSaleDocUI();
    }


    /**
     * 初始化StrategyUI主界面
     * 默认为客户回馈策略界面
     */
    void initRootLayout(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/RootLayout.fxml"));

            rootLayout=loader.load();
            Scene scene=new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    void showSaleDocUI(){

        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/PurchaseSaleDocLayout.fxml"));

            SaleDocLayout=loader.load();


            PurchaseSaleDocLayoutController controller=loader.getController();
            controller.setPrimaryStage(primaryStage);


            rootLayout.setCenter(SaleDocLayout);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
