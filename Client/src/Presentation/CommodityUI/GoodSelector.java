package Presentation.CommodityUI;

import Presentation.CommodityUI.view.SelectorPreviewController;
import Util.GoodSelectItem;
import VO.GoodVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GoodSelector {

    private Stage primaryStage;
    private BorderPane selectorLayout;

    /**
     * 选择商品的窗口
     * @param primaryStage 点击确定或取消时要关闭窗口
     * @param controller 需要被设置商品列表的窗口的控制器
     * @param arr 当前已经添加的商品列表（置空不置null）
     */
    public GoodSelector(Stage primaryStage, GoodListSettable controller,
                        ArrayList<GoodSelectItem> arr) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("请选择商品");

        initSelectorLayout(primaryStage ,controller, arr);
    }

    private void initSelectorLayout(Stage primaryStage, GoodListSettable ctrl, ArrayList<GoodSelectItem> arr) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Presentation.CommodityUI.CommodityUI.class.getResource("view/SelectorPreview.fxml"));
            selectorLayout = (BorderPane) loader.load();
            SelectorPreviewController controller = loader.getController();

            controller.setController(ctrl);
            controller.setStage(primaryStage);
            controller.setGoodList(arr);

            Scene scene = new Scene(selectorLayout);
            primaryStage.hide();
            primaryStage.setScene(scene);
            primaryStage.show();
            //primaryStage.setResizable(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
