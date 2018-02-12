package Presentation.CommodityUI;

import Presentation.CommodityUI.view.CommodityLayoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CommodityUI{

    public Pane getCommodityManagement(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Presentation.CommodityUI.CommodityUI.class.getResource("view/CommodityLayout.fxml"));
            BorderPane commodityLayout = (BorderPane)loader.load();
            CommodityLayoutController controller = loader.getController();
            controller.initializeManager();

            return commodityLayout;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pane getCommodityChange(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Presentation.CommodityUI.CommodityUI.class.getResource("view/ChangeLayout.fxml"));
            BorderPane changeLayout = (BorderPane)loader.load();

            return changeLayout;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pane jumpCommodityStat() {
        return null;
    }

    public Pane jumpCommodityDoc() {
        return null;
    }
}
