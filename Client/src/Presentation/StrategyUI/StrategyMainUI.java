package Presentation.StrategyUI;

import Presentation.StrategyUI.view.CustomerStrategy.CusStrategyLayoutController;
import VO.CustomerStrategyVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class StrategyMainUI {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private BorderPane StrategyLayout;
    private StrategyDataHelper dataHelper=new StrategyDataHelper();

    public Pane getStrategy(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/CustomerStrategy/CusStrategyLayout.fxml"));

            StrategyLayout=loader.load();

            CusStrategyLayoutController controller=loader.getController();

            ObservableList<CustomerStrategyVO> CusStrList=dataHelper.getCusStrategyList();
            controller.setCusStrategy(CusStrList);

            return StrategyLayout;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 初始化StrategyUI主界面
     * 默认为客户回馈策略界面
     */
    void initRootLayout(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/RootLayout.fxml"));

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

    void showStrategyUI(){

        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/CustomerStrategy/CusStrategyLayout.fxml"));

            StrategyLayout=loader.load();
            rootLayout.setCenter(StrategyLayout);

            CusStrategyLayoutController controller=loader.getController();

            ObservableList<CustomerStrategyVO> CusStrList=dataHelper.getCusStrategyList();
            controller.setCusStrategy(CusStrList);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage stage){
        primaryStage=stage;
    }
}
