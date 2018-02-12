package Presentation.DocUI.PaymentDoc.view;

import BusinessLogic.AccountBL.Account;
import Presentation.CustomerUI.CustomerMainUI;
import Presentation.CustomerUI.info.CusListLayoutController;
import Presentation.CustomerUI.info.CustomerSettable;
import Presentation.DocUI.CheckingDoc.view.CheckingDocLayoutController;
import Presentation.DocUI.GoodDoc.GoodDocMainUI;
import Presentation.DocUI.GoodDoc.model.GoodDoc;
import Presentation.DocUI.GoodDoc.view.GoodItemPreviewController;
import Presentation.DocUI.PaymentDoc.PaymentDocDataHelper;
import Presentation.DocUI.PaymentDoc.PaymentDocMainUI;
import Presentation.DocUI.PaymentDoc.model.PaymentDoc;
import Util.DocType;
import Util.ResultMessage;
import VO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class PaymentDocDetailController implements CustomerSettable{

    @FXML
    private ListView<AccountForDocVO> AccountList;

    @FXML
    private Button editButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancleButton;

    @FXML
    private Button passButton;

    @FXML
    private Button backButton;

    @FXML
    private Button submitButton;

    @FXML
    private ComboBox<String> AccountForChoosing;

    @FXML
    private TextArea Amount;

    @FXML
    private Button AddAccountButton;

    @FXML
    private Label DocTitleName;

    @FXML
    private Label TypeText;

    @FXML
    private TextArea CusName;

    @FXML
    private Button AddCusButton;

    @FXML
    private Label DocTitlePrKey;

    @FXML
    private TextArea Total;

    @FXML
    private AnchorPane DetailLayout;


    private PaymentDoc currentDoc;
    private PaymentDocLayoutController LayoutController;
    private CheckingDocLayoutController CheckController;
    private Stage stage;

    private ArrayList<AccountForDocVO> AccountForDocs=new ArrayList<>();
    private CustomerForDocVO currentCus;
    private double total=0;


    @FXML
    private void initialize(){
        AccountList.setCellFactory((ListView<AccountForDocVO> goods)->new GoodPreview());

    }

    public void setAccountList(ObservableList<AccountForDocVO> accountList) {
        AccountList.setItems(accountList);
    }

    public void setDocTitleName(String docTitleName) {
        DocTitleName .setText(docTitleName);
    }

    public void setLayoutController(PaymentDocLayoutController controller){
        LayoutController=controller;
    }

    public void setStage(Stage primaryStage){
        stage=primaryStage;
    }


    public void setTypeText(DocType docType){
        if(docType==DocType.PAYING){
            TypeText.setText("付款金额");
            DocTitleName.setText("付款单");
        }
        else{
            TypeText.setText("收款金额");
            DocTitleName.setText("收款单");
        }
    }

    public double getAmount(){return Double.parseDouble(Amount.getText());}

    public void setCusName(String Cus){CusName.setText(Cus);}

    public void setPrKey(String key){
        DocTitlePrKey.setText(key);
    }

    public void setTotal(double total){Total.setText(String.valueOf(total));}

    /**
     *
     * @param Cus
     */
    @Override
    public void setCustomer(CustomerForDocVO Cus) {
        currentCus=Cus;
    }


    private class GoodPreview extends ListCell<AccountForDocVO> {
        @Override
        protected  void updateItem(AccountForDocVO item,boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(PaymentDocMainUI.class.getResource("view/AccountItemPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    AccountItemPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setAccountID(item.getCardNumber());
                    controller.setAccountName(item.getCardName());
                    controller.setMoney(item.getAmount());
                    controller.setComment(item.getComment());

                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 展示具体信息
     * @param Doc 库存单据
     */
    public void showDetail(PaymentDoc Doc){
        if(LayoutController!=null){
            AccountForChoosing.setItems(FXCollections.observableArrayList(LayoutController.getDataHelper().getAccounts()));
        }

        if(Doc.getAcounts().size()!=0){
            AccountForChoosing.setValue(Doc.getAccount().getCardNumber());
        }

        if(Doc.getType()== DocType.PAYING){
            setDocTitleName("付款单");
        }
        else{
            setDocTitleName("收款单");
        }

        setTypeText(Doc.getType());
        setPrKey(Doc.getPrKey());

        if(Doc.getCurrentCustomer()!=null){
            setCusName(Doc.getCurrentCustomer().getName());
        }
        if(Doc.getAcounts().size()!=0){
            AccountForDocs=Doc.getAcounts();

            for(AccountForDocVO Acc:AccountForDocs){
                total+=Acc.getAmount();
            }
            setTotal(total);
        }

        currentDoc=Doc;
        setReadable();
    }

    /**
     * 点击新增按钮增加商品
     */
    @FXML
    private void AddAccount(){
        String CardNumber=AccountForChoosing.getSelectionModel().getSelectedItem().toString();
        AccountForDocVO newAccount=new AccountForDocVO(CardNumber,"");

        double amount=Double.parseDouble(Amount.getText());

        for(AccountVO accountVO:LayoutController.getDataHelper().getAccountList()){
            if(CardNumber.equals(accountVO.getCardNumber())){
                newAccount=new AccountForDocVO(accountVO.getCardNumber(),accountVO.getCardName(),amount,accountVO.getNotes());
                break;
            }
        }


        //refresh
        AccountForDocs.add(newAccount);
        total+=newAccount.getAmount();
        setTotal(total);
        Amount.setText("");

        AccountList.setItems(FXCollections.observableArrayList(AccountForDocs));
        AccountList.setCellFactory((ListView<AccountForDocVO> goods)->new GoodPreview());

    }

    /**
     *
     * @return
     */
        @FXML
        private void AddCus(){
            try{
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(CustomerMainUI.class.getResource("info/CusListLayout.fxml"));

                AnchorPane CusListLayout=loader.load();

                Stage stage=new Stage();
                Scene scene=new Scene(CusListLayout);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(this.stage);
                stage.setScene(scene);
                stage.centerOnScreen();

                CusListLayoutController controller=loader.getController();

                controller.setStage(stage);
                controller.setController(this);

                stage.showAndWait();
                CusName.setEditable(true);
                CusName.setText(currentCus.getName());
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }


    /**
     * 保存更改
     */
    @FXML
    private void Save(){
        /**
         * 若是新增的保存
         */
        if(currentDoc.getAcounts().size()==0){

            ResultMessage isSuccess=LayoutController.getDataHelper().addPaymentDoc(currentDoc.getPrKey(),currentDoc.getCurrentOperator(),LayoutController.getAddType(),
                    AccountForDocs,currentCus);

            if(isSuccess==ResultMessage.SUCCESS){
                //新增客户成功，刷新列表，并检查
                LayoutController.refresh();
                LayoutController.selectLast();
            }

        }

        /**
         * 若是编辑的保存，进行比较
         */

        else{
            PaymentDoc savedDoc=SavedDoc(currentDoc);

            if(LayoutController.getDataHelper().modifyPaymentDoc(savedDoc)==ResultMessage.SUCCESS){
                LayoutController.refresh();
            }
        }

        setReadable();
    }

    //比对找出改动
    private PaymentDoc SavedDoc(PaymentDoc doc){

        PaymentDoc changedDoc=new PaymentDoc();
        changedDoc.setPrKey(doc.getPrKey());


        if(currentCus!=null){
            changedDoc.setCurrentCustomer(currentCus);
        }

        if(AccountForDocs!=null){
            changedDoc.setAcounts(AccountForDocs);
        }

        return changedDoc;
    }

    /**
     * 取消更改
     */
    @FXML
    private void Cancle(){
        setReadable();
    }



    /**
     * 编辑信息
     */
    @FXML
    private void Edit(){
        setWritable();
    }


    /**
     * 只读模式
     */
    private void setReadable(){

        //保存、取消按钮设置为不可见
        saveButton.setVisible(false);
        cancleButton.setVisible(false);
        passButton.setVisible(false);
        backButton.setVisible(false);
        submitButton.setVisible(true);

        //编辑按钮设置为可见
        editButton.setVisible(true);

        //新增客户和商品的按钮设置为不可见
        AddAccountButton.setVisible(false);
        AddCusButton.setVisible(false);
        AccountForChoosing.setVisible(false);
        Amount.setVisible(false);

    }


    /**
     * 可写模式
     */
    public void setWritable(){

        //显示保存、取消按钮,不显示编辑按钮
        saveButton.setVisible(true);
        cancleButton.setVisible(true);
        editButton.setVisible(false);
        passButton.setVisible(false);
        backButton.setVisible(false);
        submitButton.setVisible(false);

        AddAccountButton.setVisible(true);
        AddCusButton.setVisible(true);
        AccountForChoosing.setVisible(true);
        Amount.setVisible(true);
    }

    /**
     * 审批状态
     */
    public void setCheckMode(){
        setReadable();
        saveButton.setVisible(false);
        cancleButton.setVisible(false);
        editButton.setVisible(false);
        submitButton.setVisible(false);

        passButton.setVisible(true);
        backButton.setVisible(true);
    }

    @FXML
    private void Pass(){
        CheckController.passDoc(this.ConverterToVO(currentDoc));

    }

    @FXML
    private void Back(){
        CheckController.rejectDoc(this.ConverterToVO(currentDoc));
    }

    @FXML
    private void Submit(){
        ResultMessage isSuccess=LayoutController.getDataHelper().submitDoc(currentDoc,LayoutController.getIntegerList());

        if(isSuccess==ResultMessage.SUCCESS){
            DetailLayout.setVisible(false);
            LayoutController.refresh();
        }
    }

    private PaymentDocVO ConverterToVO(PaymentDoc doc){
        return new PaymentDocVO(doc.getPrKey(),doc.getID(),doc.getCurrentOperator(),doc.getType(),doc.getCurrentCustomer(),doc.getAcounts());
    }
}
