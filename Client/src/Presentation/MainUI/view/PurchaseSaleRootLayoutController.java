package Presentation.MainUI.view;

import BusinessLogic.MessageBL.MessageHandler;
import BusinessLogic.UserBL.User;
import Presentation.CustomerUI.CustomerMainUI;
import Presentation.CustomerUI.view.CustomerLayOutController;
import Presentation.MainUI.jumper.InventoryJumpable;
import Presentation.MainUI.jumper.PurchaseJumpable;
import Presentation.MainUI.jumper.PurchaseJumper;
import Presentation.Util.view.MessageBoxLayoutController;
import Util.Message;
import Util.ResultMessage;
import VO.UserVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class PurchaseSaleRootLayoutController {

    @FXML
    private Button CusManageButton;

    @FXML
    private Button CusDocButton;

    @FXML
    private Button logoutButton;

    @FXML
    private BorderPane RootLayout;

    @FXML
    private Label welcome;

    @FXML
    private ImageView messageLogo;

    private MessageHandler handler;

    private UserVO user;

    private LoginController loginController;

    private PurchaseJumper purchaseJumper;




    @FXML
    public void initialize(){
        handler = new MessageHandler();
        handle();
        user = new User().getCurrentUser();
        welcome.setText("欢迎，"+user.getName()+"("+user.getUserID()+")");
    }

    public ResultMessage deleteMessage(Message message) {
        ResultMessage rm = handler.delete(user.getUserID(),message);
        return rm;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setPurchaseJumper(PurchaseJumper jumper){
        purchaseJumper=jumper;
    }


    /**
     * 登出
     * @param actionEvent
     */
    public void logout(ActionEvent actionEvent) {
        loginController.logout();
    }


    /**
     * 跳转至客户管理界面
     * @param actionEvent
     */
    public void jumpCusManagement(ActionEvent actionEvent){purchaseJumper.JumpCusManagement();}


    /**
     * 跳转至制定进货单据界面
     * @param actionEvent
     */
    public void jumpPurchaseDoc(ActionEvent actionEvent){purchaseJumper.JumpPurchaseDoc();}



    public void handle() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(
                new EchoServer(),
                0,
                1,
                TimeUnit.MINUTES);
    }

    private class EchoServer implements Runnable {

        @Override
        public void run() {
            if(handler.hasNewMessage()){
                messageLogo.setImage(new Image("Presentation/CommodityUI/view/pics/MessagesAdd.png"));
            }
        }
    }


    /**
     * 展示接收到的信息
     * @param actionEvent
     */
    public void getMessages(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MessageBoxLayoutController.class.getResource("MessageBoxLayout.fxml"));
            ListView<Message> layout = loader.load();

            MessageBoxLayoutController controller = loader.getController();
            controller.setRootLayoutController(this);

            Scene messageBox = new Scene(layout);
            Stage stage = new Stage();
            stage.setScene(messageBox);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
