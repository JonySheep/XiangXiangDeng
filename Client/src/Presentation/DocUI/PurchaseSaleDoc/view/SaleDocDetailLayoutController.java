package Presentation.DocUI.PurchaseSaleDoc.view;

import Presentation.CustomerUI.CustomerMainUI;
import Presentation.CustomerUI.info.CusListLayoutController;
import Presentation.CustomerUI.info.CustomerSettable;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocMainUI;
import Presentation.DocUI.PurchaseSaleDoc.model.PurchaseSaleDoc;
import Util.DocType;
import Util.GiftItem;
import Util.ResultMessage;
import Util.Voucher;
import VO.CustomerForDocVO;
import VO.GoodItemForPurchaseSaleDocVO;
import VO.UserForDocVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SaleDocDetailLayoutController implements CustomerSettable{
    @FXML
    private Label titleDocName;

    @FXML
    private Label titleDocID;

    @FXML
    private Label CusKind;

    @FXML
    private TextArea CusName;

    @FXML
    private TextArea clerk;

    @FXML
    private ComboBox<String> storage;

    @FXML
    private Label ListName;

    @FXML
    private ListView<GoodItemForPurchaseSaleDocVO> GoodItemList;

    @FXML
    private TextArea Amount;

    @FXML
    private TextField comment;

    @FXML
    private Button AddCusButton;

    @FXML
    private Button AddGoodButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button editButton;

    @FXML
    private Button passButton;

    @FXML
    private Button backButton;

    @FXML
    private Button cancleButton;

    @FXML
    private BorderPane DetailLayout;

    @FXML
    private TextArea Discount;

    @FXML
    private TextArea Voucher;

    @FXML
    private ListView<GiftItem> Gifts;

    private PurchaseSaleDocLayoutController LayoutController;

    private PurchaseSaleDoc currentDoc;

    private Stage primaryStage;

    private CustomerForDocVO addCus;


    //getter


    public String getTitleDocName() {
        return titleDocName.getText();
    }

    public String getTitleDocID() {
        return titleDocID.getText();
    }

    public String getCusKind() {
        return CusKind.getText();
    }

    public String getCusName() {
        return CusName.getText();
    }

    public String getClerk() {
        return clerk.getText();
    }

    public String getStorage() {
        return storage.getValue();
    }

    public String getListName() {
        return ListName.getText();
    }

    public String getAmount() {
        return Amount.getText();
    }

    public String getComment() {
        return comment.getText();
    }


    //setter
    public void setTitleDocName(String titleDocName) {
        this.titleDocName.setText(titleDocName);
    }

    public void setTitleDocID(String titleDocID) {
        this.titleDocID.setText(titleDocID);
    }

    public void setCusKind(String cusKind) {
        this.CusKind.setText(cusKind);
    }

    public void setCusName(String cusName) {
        this.CusName.setText(cusName);
    }

    public void setClerk(String clerk) {
        this.clerk.setText(clerk);
    }

    public void setStorage(String storage) {
        this.storage.setValue(storage);
    }

    public void setGoodItemList(ObservableList<GoodItemForPurchaseSaleDocVO> goodItemList){
        GoodItemList.setItems(goodItemList);
    }

    public void setGiftItemList(ObservableList<GiftItem> giftItemList){
        Gifts.setItems(giftItemList);
    }
    public void setListName(String listName) {
        this.ListName.setText(listName);
    }

    public void setAmount(double amount) {
        Amount .setText(String.valueOf(amount));
    }

    public void setComment(String comment) {
        this.comment.setText(comment);
    }

    public void setLayoutController(PurchaseSaleDocLayoutController layoutController) {
        LayoutController = layoutController;
    }

    public void setDiscount(double discount){Discount.setText(String.valueOf(discount));}

    public void setVoucher(int voucher){Voucher.setText(String.valueOf(voucher));}

    /**
     * 初始化界面，加载Preview
     */
    @FXML
    private void initialize(){
        GoodItemList.setCellFactory((ListView<GoodItemForPurchaseSaleDocVO> Goods)->new GoodPreview());
        Gifts.setCellFactory((ListView<GiftItem> gifts)->new GiftPreview());
    }

    @Override
    public void setCustomer(CustomerForDocVO Cus) {
        this.addCus=Cus;
    }

    private class GoodPreview extends ListCell<GoodItemForPurchaseSaleDocVO>{
        @Override
        protected  void updateItem(GoodItemForPurchaseSaleDocVO item,boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/GoodItem.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    GoodItemController controller=loader.getController();

                    setGraphic(preview);
                    controller.setGoodName(item.getName());
                    controller.setGoodPrize(item.getUnitPrice());
                    controller.setGoodNumber(item.getNumber());
                    controller.setComment(item.getComment());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private class GiftPreview extends ListCell<GiftItem>{
        @Override
        protected  void updateItem(GiftItem item,boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/GiftItemPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    GiftItemPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setName(item.getGoodName());
                    controller.setNumber(item.getNumber());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param doc 进货单据
     */
    public void showDetail(PurchaseSaleDoc doc){

        setClerk(LayoutController.getDataHelper().getUser().getName());

        if(doc.getType()== DocType.SALE){
            setTitleDocName("销售单");
        }
        else{
            setTitleDocName("销售退货单");
        }

        //set 数据
        if(doc.getCus()!=null){
            setCusName(doc.getCus().getName());
            setStorage("1");//默认仓库1
            setAmount(doc.getAmount());
            setComment(doc.getComment());
            setDiscount(doc.getDiscount());
            setVoucher(doc.getVoucher());
        }

        this.currentDoc=doc;

        setReadable();
    }


    /**
     * 点击新增按钮增加商品
     */
    @FXML
    private void AddGood(){

    }


    /**
     * 点击添加客户
     */
    @FXML
    private CustomerForDocVO AddCus(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(CustomerMainUI.class.getResource("info/CusListLayout.fxml"));

            AnchorPane CusListLayout=loader.load();

            Stage stage=new Stage();
            Scene scene=new Scene(CusListLayout);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.primaryStage);
            stage.setScene(scene);
            stage.setTitle("选择客户");
            stage.centerOnScreen();

            CusListLayoutController controller=loader.getController();

            controller.setStage(stage);
            controller.setController(this);

            stage.showAndWait();
            CusName.setEditable(true);
            CusName.setText(addCus.getName());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return addCus;
    }


    /**
     * 保存更改
     */
    @FXML
    private void Save(){
        /**
         * 若是新增的保存
         */
        if(currentDoc.getCus()==null){

            //prKey
            String prKey="";

            ResultMessage isSuccess=ResultMessage.SUCCESS;

            if(LayoutController.getAddType()==DocType.SALE){
                isSuccess=LayoutController.getDataHelper().addSaleDoc(prKey,LayoutController.getAddType(),addCus,
                        new UserForDocVO(LayoutController.getDataHelper().getUser().getUserID(),LayoutController.getDataHelper().getUser().getName()),
                        this.GoodObToArray(GoodItemList.getItems()),Double.parseDouble(Amount.getText()),comment.getText(),
                        this.GiftObToArray(Gifts.getItems()),Double.parseDouble(Discount.getText()),Integer.parseInt(Voucher.getText()));

            }
            else if(LayoutController.getAddType()==DocType.SALE_RETURN){
                isSuccess=LayoutController.getDataHelper().addSaleReturnDoc(prKey,LayoutController.getAddType(),addCus,
                        new UserForDocVO(LayoutController.getDataHelper().getUser().getUserID(),LayoutController.getDataHelper().getUser().getName()),
                        this.GoodObToArray(GoodItemList.getItems()),Double.parseDouble(Amount.getText()),comment.getText());
            }

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
            PurchaseSaleDoc savedDoc=SavedDoc(currentDoc);

            if(LayoutController.getDataHelper().modifyPurchaseDoc(savedDoc)==ResultMessage.SUCCESS){
                LayoutController.refresh();
            }
        }

        setReadable();
    }



    //比对找出改动
    private PurchaseSaleDoc SavedDoc(PurchaseSaleDoc doc){

        PurchaseSaleDoc changedDoc=new PurchaseSaleDoc();

        String comment=this.comment.getText();
        double Amount=Double.parseDouble(this.Amount.getText());

        if(!doc.getCus().getId().equals(addCus.getId())){
            changedDoc.setCus(addCus);
        }
        if(!doc.getComment().equals(comment)){
            changedDoc.setComment(comment);
        }
        if(doc.getAmount()!=Amount){
            changedDoc.setAmount(Amount);
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

        //编辑按钮设置为可见
        editButton.setVisible(true);

        //新增客户和商品的按钮设置为不可见
        AddCusButton.setVisible(false);
        AddGoodButton.setVisible(false);

        CusName.setEditable(false);
        clerk.setEditable(false);
        Amount.setEditable(false);
        comment.setEditable(false);
        storage.setEditable(false);
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

        AddGoodButton.setVisible(true);
        AddCusButton.setVisible(true);

        CusName.setEditable(true);
        clerk.setEditable(true);
        Amount.setEditable(true);
        comment.setEditable(true);
    }

    public void setPrimaryStage(Stage stage){
        this.primaryStage=stage;
    }

    public void setCus(CustomerForDocVO Cus){
        this.addCus=Cus;
    }

    /**
     *
     * @param docs ObservableList
     * @return ArrayList
     */
    private ArrayList<GoodItemForPurchaseSaleDocVO> GoodObToArray(ObservableList<GoodItemForPurchaseSaleDocVO> docs){
        ArrayList<GoodItemForPurchaseSaleDocVO> arrayList=new ArrayList<>();
        for(GoodItemForPurchaseSaleDocVO doc:docs){
            arrayList.add(doc);
        }
        return arrayList;
    }

    private ArrayList<GiftItem> GiftObToArray(ObservableList<GiftItem> gifts){
        ArrayList<GiftItem> arrayList=new ArrayList<>();
        for(GiftItem gift:gifts){
            arrayList.add(gift);
        }
        return arrayList;
    }


    /**
     * 审批状态
     */
    public void setCheckMode(){
        setReadable();
        saveButton.setVisible(false);
        cancleButton.setVisible(false);
        editButton.setVisible(false);

        passButton.setVisible(true);
        backButton.setVisible(true);
    }

    @FXML
    private void Pass(){

    }

    @FXML
    private void Back(){

    }
}
