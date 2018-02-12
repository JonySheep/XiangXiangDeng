package Presentation.StrategyUI.view.PackageStrategy;

import Presentation.StrategyUI.StrategyMainUI;
import Presentation.StrategyUI.view.CustomerStrategy.CusStrategyPreviewController;
import VO.CustomerStrategyVO;
import VO.PackageStrategyVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class PkgStrategyLayoutController {

    @FXML
    private ListView<PackageStrategyVO> PkgList;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;


    /**
     * 初始化PkgLayout方法
     */
    @FXML
    private void initialize(){
        PkgList.setCellFactory((ListView<PackageStrategyVO> strategy)->new PkgStrategyPreview());
    }



    private class PkgStrategyPreview extends ListCell<PackageStrategyVO>{
            @Override
            protected  void updateItem(PackageStrategyVO item,boolean empty){
                super.updateItem(item,empty);

                if(!empty&&item!=null){
                    FXMLLoader loader=new FXMLLoader();
                    loader.setLocation(StrategyMainUI.class.getResource("view/PackageStrategy/PkgStrategyPreview.fxml"));

                    try{
                        AnchorPane preview=loader.load();
                        PkgStrategyPreviewController controller=loader.getController();

                        setGraphic(preview);
                        controller.setBegin(item.getBegin().format(DateTimeFormatter.ISO_LOCAL_DATE));
                        controller.setEnd(item.getEnd().format(DateTimeFormatter.ISO_LOCAL_DATE));
                        controller.setPkgName(item.getPkgName());
                        controller.setPkgPrize(item.getPrice());

                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
    }


    /**
     * 新增按钮的响应方法
     */
    @FXML
    private void add(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/PackageStrategy/NewPackageStrategyLayout.fxml"));

            BorderPane newPkgStrategyLayout=loader.load();
            NewPackageStrategyLayoutController controller=loader.getController();
            Stage addStage=new Stage();

            Scene scene=new Scene(newPkgStrategyLayout);
            addStage.setScene(scene);

            controller.setAddStage(addStage);
            addStage.centerOnScreen();
            addStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 删除按钮的响应方法
     */
    @FXML
    private void delete(){

    }
}
