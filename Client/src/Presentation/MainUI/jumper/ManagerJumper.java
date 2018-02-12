package Presentation.MainUI.jumper;

import Presentation.DocUI.CheckingDoc.CheckingDocMainUI;
import Presentation.StrategyUI.StrategyMainUI;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ManagerJumper implements ManagerJumpable{

    private Stage primaryStage = null;
    private BorderPane rootLayout = null;

    private CheckingDocMainUI checkingDocMainUI=new CheckingDocMainUI();
    private StrategyMainUI strategyMainUI=new StrategyMainUI();


    /**
     * 设置导航
     * @param primaryStage
     * @param rootLayout
     */
    public ManagerJumper(Stage primaryStage, BorderPane rootLayout) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("香香灯");
        this.rootLayout = rootLayout;

        strategyMainUI.setPrimaryStage(primaryStage);

        Scene scene = new Scene(rootLayout);
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();

        JumpCheckingDoc();
    }

    @Override
    public void JumpCheckingDoc() {
        rootLayout.setCenter(checkingDocMainUI.getCheckingDoc());
    }

    @Override
    public void JumpStrategy() {
        rootLayout.setCenter(strategyMainUI.getStrategy());
    }
}
