package Presentation.DocUI.GoodDoc.view;

import Presentation.DocUI.GoodDoc.GoodDocDataHelper;
import Presentation.DocUI.GoodDoc.GoodDocMainUI;
import Presentation.DocUI.GoodDoc.model.GoodDoc;
import Presentation.DocUI.PurchaseSaleDoc.model.PurchaseSaleDoc;
import Util.DocType;
import Util.ResultMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * 进货、进货退货单
 */
public class GoodDocLayoutController {

    @FXML
    private ComboBox<String> DocKind;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<GoodDoc> DocList;

    @FXML
    private BorderPane DocLayout;

    @FXML
    private BorderPane DetailLayout;

    private GoodDocDataHelper dataHelper=new GoodDocDataHelper();
    private GoodDocDetailController detailController;
    private DocType currentType=DocType.GOOD_OVERFLOW; //choose type 时改变

    private Stage primaryStage;

    @FXML
    /**
     *初始化方法
     */
    private void initialize(){
        DocList.setItems(dataHelper.gerCurrentDocList());

        DocList.setCellFactory((ListView<GoodDoc> Docs)->new DocPreview());
        DocList.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showDetail(newValue)));

        DocKind.setItems(FXCollections.observableArrayList("库存报溢单","库存报损单"));
        DocKind.setValue("库存报溢单");
    }


    private void showDetail(GoodDoc doc){
        try{
            FXMLLoader DetailLoader=new FXMLLoader();

            DetailLoader.setLocation(GoodDocMainUI.class.getResource("view/GoodDocDetail.fxml"));
            AnchorPane DocDetailsLayout=DetailLoader.load();

            DocLayout.setCenter(DocDetailsLayout);

            GoodDocDetailController controller=DetailLoader.getController();
            controller.setLayoutController(this);
            controller.setGoodList(FXCollections.observableArrayList(doc.getGoods()));
            controller.showDetail(doc);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private class DocPreview extends ListCell<GoodDoc>{
        @Override
        protected  void updateItem(GoodDoc item,boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(GoodDocMainUI.class.getResource("view/GoodDocPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    GoodDocPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setCusName(item.getCurrentOperator().getName());
                    controller.setComment(item.getComment());
                    controller.setDocType(item.getType());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    @FXML
    private void chooseType(){
        if(DocKind.getSelectionModel().getSelectedItem().toString().equals("库存报溢单")){
            currentType=DocType.GOOD_OVERFLOW;
        }
        else{
            currentType=DocType.GOOD_LOSS;
        }
    }

    @FXML
    private void Add(){

        /**
         * 显示具体信息界面供用户填写
         */
        try{
            FXMLLoader DetailLoader=new FXMLLoader();

            DetailLoader.setLocation(GoodDocMainUI.class.getResource("view/GoodDocDetail.fxml"));
            AnchorPane detailLayout=DetailLoader.load();

            DocLayout.setCenter(detailLayout);

            detailController=DetailLoader.getController();

            String prKey=dataHelper.getUser().getUserID()+ "-"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            GoodDoc newDoc=new GoodDoc(currentType);
            newDoc.setPrKey(prKey);
            detailController.setLayoutController(this);
            detailController.showDetail(newDoc);
            detailController.setStage(primaryStage);
            detailController.setWritable();


        }catch (IOException e){
            e.printStackTrace();
        }


    }


    @FXML
    private void Delete(){
        Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION);
        delAlert.setTitle("删除客户");
        delAlert.setHeaderText(null);
        delAlert.setContentText("确定删除？");

        Optional<ButtonType> result = delAlert.showAndWait();

        //确认单据类型

        if (result.get() == ButtonType.OK){
            //用户确定删除
            ObservableList<Integer> list=  DocList.getSelectionModel().getSelectedIndices();
            if(dataHelper.delGoodDoc(list) == ResultMessage.SUCCESS){
                //服务器删除成功
                refresh(); //刷新ListView

                if(dataHelper.gerCurrentDocList().size()==0){
                    DetailLayout.setVisible(false);
                }

            }
            else{
                //服务器删除失败
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("删除失败");
                alert.setHeaderText(null);
                alert.setContentText("尝试与服务器同步qwq");

                alert.showAndWait();

                refresh();
                selectFirst();
            }
        }
    }


    /**
     * 强行刷新，不刷新不好使
     */
    public void refresh(){

        DocList.setItems(dataHelper.gerCurrentDocList());
        DocList.setCellFactory((ListView<GoodDoc> CellData) -> new DocPreview());
        if(dataHelper.gerCurrentDocList().size()!=0){
            selectFirst();
        }
    }


    /**
     * 默认选中开头
     */
    public void selectFirst(){
        DocList.getSelectionModel().selectFirst();
    }



    /**
     * 选中末尾
     */
    public void selectLast(){
        DocList.getSelectionModel().selectLast();
    }


    /**
     * 选中某个客户
     * @param obj
     */
    public void select(GoodDoc obj){
        DocList.getSelectionModel().select(obj);
    }


    public GoodDocDataHelper getDataHelper(){return dataHelper;}

    public ObservableList<Integer> getIntegerList(){return DocList.getSelectionModel().getSelectedIndices();}

    public void setPrimaryStage(Stage stage){
        this.primaryStage=stage;
    }


    public DocType getAddType(){
        return currentType;
    }
}
