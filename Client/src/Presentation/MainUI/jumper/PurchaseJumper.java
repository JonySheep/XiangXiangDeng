package Presentation.MainUI.jumper;

import Presentation.CustomerUI.CustomerMainUI;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PurchaseJumper implements PurchaseJumpable{

    private Stage primaryStage = null;
    private BorderPane rootLayout = null;

    private CustomerMainUI customerMainUI=new CustomerMainUI();


    public PurchaseJumper(Stage primaryStage,BorderPane rootLayout){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("香香灯");
        this.rootLayout = rootLayout;

        customerMainUI.setPrimaryStage(primaryStage);

        Scene scene = new Scene(rootLayout);
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();

        JumpCusManagement();
    }



    @Override
    public void JumpCusManagement() {
        rootLayout.setCenter(customerMainUI.getCusManagement());
    }

    @Override
    public void JumpPurchaseDoc() {
        rootLayout.setCenter(customerMainUI.getPurchaseDoc());
    }
}
