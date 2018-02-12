package Presentation.UserUI;

import Presentation.UserUI.view.UserLayoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserUI {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private BorderPane userLayout;

    /**
     * 用MainUI的primaryStage构造UserUI
     * @param primaryStage
     */
    public UserUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("香香灯");

        initRootLayout();
        showUserLayout();
    }

    private void initRootLayout() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserUI.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane)loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.hide();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showUserLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserUI.class.getResource("view/UserLayout.fxml"));
            userLayout = (BorderPane)loader.load();

            rootLayout.setCenter(userLayout);

            UserLayoutController controller = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getUserLayout() {
        return userLayout;
    }
}
