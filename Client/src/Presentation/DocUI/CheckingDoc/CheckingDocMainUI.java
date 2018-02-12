package Presentation.DocUI.CheckingDoc;

import Presentation.DocUI.CheckingDoc.view.CheckingDocLayoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckingDocMainUI {

    public Pane getCheckingDoc(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(CheckingDocMainUI.class.getResource("view/CheckingDocLayout.fxml"));

            BorderPane Layout=loader.load();

            return Layout;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
