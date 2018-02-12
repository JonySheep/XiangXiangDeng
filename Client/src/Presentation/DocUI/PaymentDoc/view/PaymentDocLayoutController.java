package Presentation.DocUI.PaymentDoc.view;

import Presentation.DocUI.GoodDoc.GoodDocDataHelper;
import Presentation.DocUI.GoodDoc.GoodDocMainUI;
import Presentation.DocUI.GoodDoc.model.GoodDoc;
import Presentation.DocUI.PaymentDoc.PaymentDocMainUI;
import Presentation.DocUI.PaymentDoc.model.PaymentDoc;
import Presentation.DocUI.PaymentDoc.PaymentDocDataHelper;
import Util.DocType;
import Util.ResultMessage;
import VO.UserForDocVO;
import VO.UserVO;
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
public class PaymentDocLayoutController {

    @FXML
    private ComboBox<String> DocKind;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<PaymentDoc> DocList;

    @FXML
    private BorderPane DocLayout;

    @FXML
    private BorderPane DetailLayout;

    private PaymentDocDataHelper dataHelper=new PaymentDocDataHelper();
    private PaymentDocDetailController detailController;
    private DocType currentType=DocType.RECEIVING; //choose type 时改变

    private Stage primaryStage;
    private UserVO User;

    @FXML
    /**
     *初始化方法
     */
    private void initialize(){
        User=dataHelper.getUser();
        dataHelper.setRecievingDocList();
        DocList.setItems(dataHelper.gerCurrentDocList());

        DocList.setCellFactory((ListView<PaymentDoc> Docs)->new DocPreview());
        DocList.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showDetail(newValue)));

        DocKind.setItems(FXCollections.observableArrayList("收款单","付款单","现金费用单"));
        DocKind.setValue("收款单");
    }


    /**
     * 选择单据类型
     */
    @FXML
    private void chooseType(){
        refresh();
    }


    private void showDetail(PaymentDoc doc){
        if(doc==null) return;

        try{
            FXMLLoader DetailLoader=new FXMLLoader();

            if(doc.getType()==DocType.PAYING||doc.getType()==DocType.RECEIVING){
                DetailLoader.setLocation(PaymentDocMainUI.class.getResource("view/PaymentDocDetail.fxml"));

                AnchorPane DocDetailsLayout=DetailLoader.load();

                DocLayout.setCenter(DocDetailsLayout);

                PaymentDocDetailController controller=DetailLoader.getController();
                controller.setAccountList(FXCollections.observableArrayList(doc.getAcounts()));
                controller.setLayoutController(this);
                controller.showDetail(doc);
            }


            else {
                DetailLoader.setLocation(PaymentDocMainUI.class.getResource("view/CashDocDetail.fxml"));

                AnchorPane DocDetailsLayout=DetailLoader.load();

                DocLayout.setCenter(DocDetailsLayout);

                CashDocDetailController controller=DetailLoader.getController();
                controller.setCashList(FXCollections.observableArrayList(doc.getCashItems()));
                controller.setLayoutController(this);
                controller.showDetail(doc);
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private class DocPreview extends ListCell<PaymentDoc>{
        @Override
        protected  void updateItem(PaymentDoc item,boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(PaymentDocMainUI.class.getResource("view/PaymentDocPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    PaymentDocPreviewController controller=loader.getController();

                    setGraphic(preview);
                    if(item.getType()==DocType.CASH){
                        controller.setCusName(item.getCurrentOperator().getName());
                        controller.setCardNumber(item.getAccount().getCardNumber());
                    }
                    else{
                        controller.setCusName(item.getCurrentCustomer().getName());
                    }
                    controller.setComment("_(:з」∠)_少吃点");
                    controller.setDocType(item.getType());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void Add(){

        /**
         * 显示具体信息界面供用户填写
         */
        try{
            FXMLLoader DetailLoader=new FXMLLoader();

            if(currentType==DocType.CASH){
                DetailLoader.setLocation(PaymentDocMainUI.class.getResource("view/CashDocDetail.fxml"));
                AnchorPane detailLayout=DetailLoader.load();

                DocLayout.setCenter(detailLayout);

                CashDocDetailController controller=DetailLoader.getController();

                PaymentDoc newDoc=new PaymentDoc(currentType);
                newDoc.setCurrentOperator(new UserForDocVO(User.getUserID(),User.getName()));

                String prKey=dataHelper.getUser().getUserID()+ "-"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                newDoc.setPrKey(prKey);

                controller.setLayoutController(this);
                controller.showDetail(newDoc);
                controller.setStage(primaryStage);
                controller.setWritable();
            }
            else{
                DetailLoader.setLocation(PaymentDocMainUI.class.getResource("view/PaymentDocDetail.fxml"));
                AnchorPane detailLayout=DetailLoader.load();

                DocLayout.setCenter(detailLayout);

                detailController=DetailLoader.getController();

                PaymentDoc newDoc=new PaymentDoc(currentType);
                newDoc.setCurrentOperator(new UserForDocVO(User.getUserID(),User.getName()));

                String prKey=dataHelper.getUser().getUserID()+ "-"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                newDoc.setPrKey(prKey);

                detailController.setLayoutController(this);
                detailController.showDetail(newDoc);
                detailController.setStage(primaryStage);
                detailController.setWritable();
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

        if(DocKind.getSelectionModel().getSelectedItem().toString().equals("收款单")){
            currentType=DocType.RECEIVING;
            dataHelper.setRecievingDocList();
        }
        else if(DocKind.getSelectionModel().getSelectedItem().toString().equals("付款单")){
            currentType=DocType.PAYING;
            dataHelper.setPayingDocList();
        }
        else{
            currentType=DocType.CASH;
            dataHelper.setCashDocList();
        }

        DocList.setItems(dataHelper.gerCurrentDocList());
        DocList.setCellFactory((ListView<PaymentDoc> CellData) -> new DocPreview());
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


    public PaymentDocDataHelper getDataHelper(){return dataHelper;}

    public void setPrimaryStage(Stage stage){
        this.primaryStage=stage;
    }


    public DocType getAddType(){
        return currentType;
    }

    public ObservableList<Integer> getIntegerList(){return DocList.getSelectionModel().getSelectedIndices();}
}
