package Presentation.DocUI.PaymentDoc.view;

import Presentation.CustomerUI.CustomerMainUI;
import Presentation.CustomerUI.info.CusListLayoutController;
import Presentation.CustomerUI.info.CustomerSettable;
import Presentation.DocUI.CheckingDoc.view.CheckingDocLayoutController;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class CashDocDetailController implements CustomerSettable{

    @FXML
    private ListView<CashItemVO> CashList;

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
    private TextArea ItemName;

    @FXML
    private TextArea Amount;

    @FXML
    private TextArea Comment;

    @FXML
    private Button AddCashButton;

    @FXML
    private Label DocTitleName;

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

    private ArrayList<CashItemVO> CashItem=new ArrayList<>();
    private CustomerForDocVO currentCus;
    private double total=0;


    @FXML
    private void initialize(){
       CashList.setCellFactory((ListView<CashItemVO> goods)->new GoodPreview());

    }

    public void setCashList(ObservableList<CashItemVO> cashList) {
        CashList.setItems(cashList);
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

    public double getAmount(){return Double.parseDouble(Amount.getText());}


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


    private class GoodPreview extends ListCell<CashItemVO> {
        @Override
        protected  void updateItem(CashItemVO item,boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(PaymentDocMainUI.class.getResource("view/CashItemPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    CashItemPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setItemName(item.getName());
                    controller.setAmount(String.valueOf(item.getAmount()));
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
        AccountForChoosing.setItems(FXCollections.observableArrayList(LayoutController.getDataHelper().getAccounts()));


        setDocTitleName("现金费用单");
        setPrKey(Doc.getPrKey());

        if(Doc.getCashItems().size()!=0){
            CashItem=Doc.getCashItems();

            for(CashItemVO cash:CashItem){
                total+=cash.getAmount();
            }
            setTotal(total);
            AccountForChoosing.setValue(Doc.getAccount().getCardNumber());
        }

        currentDoc=Doc;
        setReadable();
    }

    /**
     * 点击新增按钮增加商品
     */
    @FXML
    private void AddCash(){

        String item=ItemName.getText();
        double amount=Double.parseDouble(Amount.getText());
        String cmt=Comment.getText();

        CashItemVO newCashItem=new CashItemVO(item,amount,cmt);


        //refresh
        CashItem.add(newCashItem);
        total+=newCashItem.getAmount();
        setTotal(total);
        Amount.setText("");

        CashList.setItems(FXCollections.observableArrayList(CashItem));
        CashList.setCellFactory((ListView<CashItemVO> goods)->new GoodPreview());

        ItemName.setText("");
        Amount.setText("");
        Comment.setText("");
    }


    /**
     * 保存更改
     */
    @FXML
    private void Save(){
        /**
         * 若是新增的保存
         */
        if(currentDoc.getCashItems().size()==0){

            String selectedCardNum=AccountForChoosing.getSelectionModel().getSelectedItem().toString();
            AccountForDocVO currentAccount=new AccountForDocVO("","");
            for(AccountVO accountVO:LayoutController.getDataHelper().getAccountList()){
                if(accountVO.getCardNumber().equals(selectedCardNum)){
                    currentAccount=new AccountForDocVO(accountVO.getCardNumber(),accountVO.getCardName(),accountVO.getBalance(),accountVO.getNotes());
                    break;
                }
            }

            ResultMessage isSuccess=LayoutController.getDataHelper().addCashDoc(currentDoc.getPrKey(),currentDoc.getCurrentOperator(),LayoutController.getAddType()
                    ,CashItem,currentAccount);

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

        String newAccount=AccountForChoosing.getSelectionModel().getSelectedItem().toString();

        if(!doc.getAccount().getCardNumber().equals(newAccount)){
            AccountForDocVO currentAccount=new AccountForDocVO("","");
            for(AccountVO accountVO:LayoutController.getDataHelper().getAccountList()){
                if(accountVO.getCardNumber().equals(newAccount)){
                    currentAccount=new AccountForDocVO(accountVO.getCardNumber(),accountVO.getCardName(),accountVO.getBalance(),accountVO.getNotes());
                    break;
                }
            }
            changedDoc.setAccount(currentAccount);
        }

        if(CashItem!=null){
            changedDoc.setCashItems(CashItem);
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
        AddCashButton.setVisible(false);

        AccountForChoosing.setEditable(false);
        ItemName.setEditable(false);
        Amount.setEditable(false);
        Comment.setEditable(false);

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

        AddCashButton.setVisible(true);

        AccountForChoosing.setEditable(true);
        ItemName.setEditable(true);
        Amount.setEditable(true);
        Comment.setEditable(true);
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
        CheckController.passDoc(this.ConvererToCashVO(currentDoc));

    }

    @FXML
    private void Back(){

    }

    @FXML
    private void Submit(){
        ResultMessage isSuccess=LayoutController.getDataHelper().submitDoc(currentDoc,LayoutController.getIntegerList());

        if(isSuccess==ResultMessage.SUCCESS){
            DetailLayout.setVisible(false);
            LayoutController.refresh();
        }
    }

    private CashDocVO ConvererToCashVO(PaymentDoc doc){
        return new CashDocVO(doc.getPrKey(),doc.getCurrentOperator(),doc.getType(),doc.getAccount(),doc.getCashItems());
    }
}
