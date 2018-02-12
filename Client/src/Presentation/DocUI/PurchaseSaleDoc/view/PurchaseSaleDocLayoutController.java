package Presentation.DocUI.PurchaseSaleDoc.view;

import Presentation.CustomerUI.CustomerMainUI;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocDataHelper;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocMainUI;
import Presentation.DocUI.PurchaseSaleDoc.model.PurchaseSaleDoc;
import Util.DocType;
import Util.ResultMessage;
import Util.UserRole;
import VO.*;
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
public class PurchaseSaleDocLayoutController {

    @FXML
    private ComboBox<String> DocKind;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<PurchaseSaleDoc> DocList;

    @FXML
    private BorderPane DocLayout;

    @FXML
    private BorderPane DetailLayout;

    private PurchaseSaleDocDataHelper dataHelper=new PurchaseSaleDocDataHelper();
    private PurchaseDocDetailLayoutController PdetailLayoutController;
    private SaleDocDetailLayoutController SdetailLayoutController;

    private Stage primaryStage;
    private UserVO user;

    @FXML
    /**
     *初始化方法
     */
    private void initialize(){
        user=dataHelper.getUser();

        dataHelper.setPurchaseDocList();
        DocList.setItems(dataHelper.getCurrentPurchaseSaleList());

        DocList.setCellFactory((ListView<PurchaseSaleDoc> Docs)->new DocPreview());
        DocList.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showDetail(newValue)));

        if(user.getRole()==UserRole.SALES){
            DocKind.setItems(FXCollections.observableArrayList("销售单","销售退货单"));
            DocKind.setValue("销售单");

        }
        else{
            DocKind.setItems(FXCollections.observableArrayList("进货单","进货退货单"));
            DocKind.setValue("进货单");
        }
    }


    /**
     * 选择单据类型
     */
    @FXML
    private void chooseType(){
        refresh();
    }


    private void showDetail(PurchaseSaleDoc doc){
        try{
            FXMLLoader DetailLoader=new FXMLLoader();

            DetailLoader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/PurchaseDocDetailLayout.fxml"));
            //DetailLoader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/SaleDocDetailLayout.fxml"));
            BorderPane DocDetailsLayout=DetailLoader.load();

            DocLayout.setCenter(DocDetailsLayout);

            PurchaseDocDetailLayoutController controller=DetailLoader.getController();
            controller.setGoodItemList(FXCollections.observableArrayList(doc.getGoodList()));
            controller.setLayoutController(this);
            //controller.setGiftItemList(FXCollections.observableArrayList(doc.getGifts()));
            controller.showDetail(doc);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private class DocPreview extends ListCell<PurchaseSaleDoc>{
        @Override
        protected  void updateItem(PurchaseSaleDoc item,boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/PurchaseSaleDocPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    PurchaseSaleDocPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setDocID(item.getDocPrKey());
                    controller.setCusName(item.getCus().getName());
                    controller.setAmount(String.valueOf(item.getAmount())+"   RMB");
                    controller.setComment(item.getComment());
                    controller.setType(item.getType());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    @FXML
    private void Add(){

        String chosenType=DocKind.getSelectionModel().getSelectedItem().toString(); //区别进货退货
        DocType type=DocType.PURCHASE;

        if(chosenType.equals("进货单")){
            type=DocType.PURCHASE;
        }
        else if(chosenType.equals("进货退货单")){
            type=DocType.PURCHASE_RETURN;
        }
        else if(chosenType.equals("销售单")){
            type=DocType.SALE;
        }
        else if(chosenType.equals("销售退货单")){
            type=DocType.SALE_RETURN;
        }


        /**
         * 显示具体信息界面供用户填写
         */
        try{
            FXMLLoader DetailLoader=new FXMLLoader();

            if(type==DocType.PURCHASE||type==DocType.PURCHASE_RETURN){
                DetailLoader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/PurchaseDocDetailLayout.fxml"));
                DetailLayout=DetailLoader.load();

                DocLayout.setCenter(DetailLayout);

                PdetailLayoutController=DetailLoader.getController();

                PurchaseSaleDoc newDoc=new PurchaseSaleDoc(type);

                String prKey=user.getUserID()+ "-"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                newDoc.setClerk(new UserForDocVO(user.getUserID(),user.getName()));
                newDoc.setDocPrKey(prKey);

                PdetailLayoutController.setLayoutController(this);
                PdetailLayoutController.showDetail(newDoc);
                PdetailLayoutController.setPrimaryStage(primaryStage);
                PdetailLayoutController.setWritable();
            }
            else{
                DetailLoader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/SaleDocDetailLayout.fxml"));
                DetailLayout=DetailLoader.load();

                DocLayout.setCenter(DetailLayout);

                SdetailLayoutController=DetailLoader.getController();

                PurchaseSaleDoc newDoc=new PurchaseSaleDoc(type);
                SdetailLayoutController.setLayoutController(this);
                SdetailLayoutController.showDetail(newDoc);
                SdetailLayoutController.setPrimaryStage(primaryStage);
                SdetailLayoutController.setWritable();
            }


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
        if(user.getRole()==UserRole.IMPORT){
            dataHelper.setPurchaseDocList();
        }
        else{
            dataHelper.setSaleDocList();
        }

        if (result.get() == ButtonType.OK){
            //用户确定删除
            ObservableList<Integer> list=  DocList.getSelectionModel().getSelectedIndices();
            if(dataHelper.delPurchaseSaleDoc(list) == ResultMessage.SUCCESS){
                //服务器删除成功
                refresh(); //刷新ListView

                if(dataHelper.getCurrentPurchaseSaleList().size()==0){
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

        //若选择进货单
        if(user.getRole()== UserRole.IMPORT){
            dataHelper.setPurchaseDocList();
        }
        //若选择进货退货单
        else if(user.getRole()==UserRole.SALES||user.getRole()==UserRole.SALESMANAGER){
            dataHelper.setSaleDocList();
        }

        DocList.setItems(dataHelper.getCurrentPurchaseSaleList());
        DocList.setCellFactory((ListView<PurchaseSaleDoc> CellData) -> new DocPreview());
        selectFirst();

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
    public void select(PurchaseSaleDoc obj){
        DocList.getSelectionModel().select(obj);
    }


    public PurchaseSaleDocDataHelper getDataHelper(){
        return dataHelper;
    }

    public void setPrimaryStage(Stage stage){
        this.primaryStage=stage;
    }


    public DocType getAddType(){
        if(DocKind.getSelectionModel().getSelectedItem().toString().equals("进货单")){
            return DocType.PURCHASE;
        }
        else if(DocKind.getSelectionModel().getSelectedItem().toString().equals("进货退货单")){
            return DocType.PURCHASE_RETURN;
        }
        else if(DocKind.getSelectionModel().getSelectedItem().toString().equals("销售单")){
            return DocType.SALE;
        }
        else if(DocKind.getSelectionModel().getSelectedItem().toString().equals("销售退货单")){
            return DocType.SALE_RETURN;
        }

        return DocType.PURCHASE;
    }

    public ObservableList<Integer> getIntegerList(){
        return DocList.getSelectionModel().getSelectedIndices();
    }

}
