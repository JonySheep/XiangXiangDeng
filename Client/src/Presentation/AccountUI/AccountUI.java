package Presentation.AccountUI;

import Presentation.AccountUI.view.AccountLayoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountUI {
    private BorderPane accountLayout;


    public Pane getAccountUI(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AccountUI.class.getResource("view/AccountLayout.fxml"));
            accountLayout = (BorderPane)loader.load();

            AccountLayoutController controller = loader.getController();

            return accountLayout;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
