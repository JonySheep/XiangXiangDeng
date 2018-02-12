package Presentation.MainUI.jumper;

import Presentation.AccountUI.AccountUI;
import Presentation.DocUI.PaymentDoc.PaymentDocMainUI;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FinanceJumper implements FinanceJumpable{

    private Stage primaryStage = null;
    private BorderPane rootLayout = null;

    private AccountUI accountUI=new AccountUI();
    private PaymentDocMainUI paymentDocMainUI=new PaymentDocMainUI();

    /**
     * 设置导航
     * @param primaryStage
     * @param rootLayout
     */
    public FinanceJumper(Stage primaryStage, BorderPane rootLayout) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("香香灯");
        this.rootLayout = rootLayout;

        paymentDocMainUI.setPrimaryStage(primaryStage);

        Scene scene = new Scene(rootLayout);
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();

        JumpAccountManagement();
    }

    @Override
    public void JumpAccountManagement() {
        rootLayout.setCenter(accountUI.getAccountUI());
    }

    @Override
    public void JumpFinanceDoc() {
        rootLayout.setCenter(paymentDocMainUI.getFinanceDoc());
    }
}
