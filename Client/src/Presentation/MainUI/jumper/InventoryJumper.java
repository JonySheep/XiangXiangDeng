package Presentation.MainUI.jumper;

import Presentation.CommodityUI.CommodityUI;
import Presentation.DocUI.GoodDoc.GoodDocMainUI;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InventoryJumper implements InventoryJumpable {

    private Stage primaryStage = null;
    private BorderPane rootLayout = null;

    private CommodityUI commodityUI = new CommodityUI();
    private GoodDocMainUI goodDocMainUI=new GoodDocMainUI();

    /**
     * 设置导航
     * @param primaryStage
     * @param rootLayout
     */
    public InventoryJumper(Stage primaryStage, BorderPane rootLayout) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("香香灯");
        this.rootLayout = rootLayout;

        Scene scene = new Scene(rootLayout);
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();

        jumpCommodityManagement();
    }


    @Override
    public void jumpCommodityManagement() {
        rootLayout.setCenter(commodityUI.getCommodityManagement());
    }

    @Override
    public void jumpCommodityChange() {
        rootLayout.setCenter(commodityUI.getCommodityChange());
    }

    @Override
    public void jumpCommodityStat() {

    }

    @Override
    public void jumpCommodityDoc() {
        rootLayout.setCenter((goodDocMainUI.getGoodDoc()));
    }
}
