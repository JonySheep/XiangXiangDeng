package Presentation.MainUI;

import Presentation.MainUI.view.LoginController;
import Rmi.RemoteHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MainUI extends Application {

    private RemoteHelper remoteHelper;
    private BorderPane loginLayout;
    private BorderPane base;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("香香灯");

        linkToServer();
        initLoginLayout();
    }


    /**
     * 链接服务器
     */
    private void linkToServer() {
        try {
            remoteHelper = RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://127.0.0.1:8887/DataRemoteObject"));
            System.out.println("linked");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载登录界面
     */
    private void initLoginLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainUI.class.getResource("view/Login.fxml"));
            loginLayout = (BorderPane)loader.load();

            LoginController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            controller.setMainUI(this);

            Scene loginScene = new Scene(loginLayout);
            primaryStage.setScene(loginScene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param args
     */

    public static void main(String[] args) {
        launch(args);
    }
}
