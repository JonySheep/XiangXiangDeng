package Presentation.MainUI.view;

import BusinessLogic.UserBL.UserController;
import BusinessLogicService.UserLoginService;
import Presentation.AccountUI.AccountUI;
import Presentation.CustomerUI.CustomerMainUI;
import Presentation.DocUI.CheckingDoc.CheckingDocMainUI;
import Presentation.DocUI.GoodDoc.GoodDocMainUI;
import Presentation.DocUI.PaymentDoc.PaymentDocMainUI;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocMainUI;
import Presentation.MainUI.jumper.FinanceJumper;
import Presentation.MainUI.jumper.InventoryJumper;
import Presentation.MainUI.MainUI;
import Presentation.MainUI.jumper.ManagerJumper;
import Presentation.MainUI.jumper.PurchaseJumper;
import Presentation.StrategyUI.StrategyMainUI;
import Presentation.UserUI.UserUI;
import Util.ResultMessage;
import Util.UserRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;


    private Stage primaryStage;

    private UserLoginService loginService = new UserController();

    private MainUI mainUI;

    /**
     * 点击登录
     * @param actionEvent
     */
    public void login(ActionEvent actionEvent) {

        ResultMessage rm = ResultMessage.FAILED;
        String name = username.getText();
        String pass = password.getText();
        if(name.equals("") || pass.equals("")){
            rm = ResultMessage.FAILED;
        }else {
            rm = loginService.login(
                    username.getText(),
                    password.getText()
            );
        }


        if (rm == ResultMessage.SUCCESS) {
            UserRole userRole = loginService.getCurrentUser().getRole();
            BorderPane rootLayout = null;
            switch (userRole) {
                case FINANCIAL:
                    try{
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Presentation.MainUI.MainUI.class.getResource("view/FinanceRootLayout.fxml"));
                        rootLayout = (BorderPane)loader.load();
                        FinanceRootLayoutController controller = loader.getController();
                        controller.setLoginController(this);

                        FinanceJumper jumper=new FinanceJumper(primaryStage, rootLayout);
                        controller.setFinanceJumper(jumper);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case GENERALMANAGER:
                    try{
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Presentation.MainUI.MainUI.class.getResource("view/ManagerRootLayout.fxml"));
                        rootLayout = (BorderPane)loader.load();
                        ManagerRootLayoutController controller = loader.getController();
                        controller.setLoginController(this);

                        ManagerJumper jumper=new ManagerJumper(primaryStage, rootLayout);
                        controller.setManagerJumper(jumper);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } break;
                case SALESMANAGER:
                case SALES:
                case IMPORT:
                    try{
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Presentation.MainUI.MainUI.class.getResource("view/PurchaseSaleRootLayout.fxml"));
                        rootLayout = (BorderPane)loader.load();
                        PurchaseSaleRootLayoutController controller = loader.getController();
                        controller.setLoginController(this);

                        PurchaseJumper jumper=new PurchaseJumper(primaryStage, rootLayout);
                        controller.setPurchaseJumper(jumper);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case INVENTORY:
                    try{
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Presentation.MainUI.MainUI.class.getResource("view/InventoryRootLayout.fxml"));
                        rootLayout = (BorderPane)loader.load();
                        InventoryRootLayoutController controller = loader.getController();
                        controller.setLoginController(this);

                        InventoryJumper jumper = new InventoryJumper(primaryStage, rootLayout);
                        controller.setInventoryJumper(jumper);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case ADMIN:
                    UserUI userUI = new UserUI(primaryStage);
                    break;

                default:
                    break;
            }

        }else if(rm == ResultMessage.FAILED){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("登录失败");
            alert.setHeaderText(null);
            alert.setContentText("用户名或密码错误");
            alert.showAndWait();
        }else if(rm == ResultMessage.REPEATEDLOGIN){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("登录失败");
            alert.setHeaderText(null);
            alert.setContentText("该用户已经登录");
            alert.showAndWait();
        }



        //UserUI userUI = new UserUI(primaryStage);
        //CommodityUI commodityUI = new CommodityUI(primaryStage);
        //StrategyMainUI strategyMainUI=new StrategyMainUI(primaryStage);


        //PaymentDocMainUI paymentDocMainUI=new PaymentDocMainUI(primaryStage);
        //userController.login(username.getText(),password.getText());



    }

    /**
     * 退出登录并重新加载登录界面
     */
    public void logout(){
        loginService.logout();
        primaryStage.hide();
        //FIXME 编码太硬..不过暂时就这样
        primaryStage.setX(660);
        primaryStage.setY(300);

        mainUI.start(primaryStage);
    }

    /**
     * 获得主场景，用于控制界面跳转
     * @param stage
     */
    public void setPrimaryStage(Stage stage){
        primaryStage = stage;

        primaryStage.setOnCloseRequest(
                event -> {
                    loginService.logout();
                    //FIXME 暂时这样吧你关掉吧..我也很绝望
                    System.exit(0);
                }
        );
    }

    /**
     * 获得MainUI，用于登出
     * @param mainUI
     */
    public void setMainUI(MainUI mainUI) {
        this.mainUI = mainUI;
    }
}
