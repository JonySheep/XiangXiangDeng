package Presentation.DocUI.PurchaseSaleDoc.view;

import Presentation.CommodityUI.GoodListSettable;
import Presentation.CommodityUI.GoodSelector;
import Presentation.CustomerUI.CustomerMainUI;
import Presentation.CustomerUI.info.CusListLayoutController;
import Presentation.CustomerUI.info.CustomerSettable;
import Presentation.DocUI.CheckingDoc.view.CheckingDocLayoutController;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocMainUI;
import Presentation.DocUI.PurchaseSaleDoc.model.PurchaseSaleDoc;
import Util.DocType;
import Util.GoodSelectItem;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PurchaseDocDetailLayoutController implements CustomerSettable,GoodListSettable {

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
    private Button cancleButton;

    @FXML
    private Button passButton;

    @FXML
    private Button backButton;

    @FXML
    private Button submitButton;

    @FXML
    private Label prize;

    @FXML
    private Label number;

    @FXML
    private BorderPane DetailLayout;

    private PurchaseSaleDocLayoutController LayoutController;

    private CheckingDocLayoutController CheckController;

    private PurchaseSaleDoc currentDoc;

    private Stage primaryStage;

    private CustomerForDocVO addCus;

    /** 用于构造ListView的列表 */
    private ArrayList<GoodItemForPurchaseSaleDocVO> Goods = new ArrayList<>();
    /** GoodSelector实际的列表 */
    private ArrayList<GoodSelectItem> goodSelectItems = new ArrayList<>();

    private UserVO User;


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

    /**
     * 初始化界面，加载Preview
     */
    @FXML
    private void initialize(){
        GoodItemList.setCellFactory((ListView<GoodItemForPurchaseSaleDocVO> Goods)->new GoodPreview());
    }


    @Override
    public void setCustomer(CustomerForDocVO Cus) {
        this.addCus=Cus;
    }

    @Override
    public void setGoodList(ArrayList<GoodSelectItem> list) {
        goodSelectItems = list;
        Goods.clear();
        for(GoodSelectItem item: list){
            Goods.add(new GoodItemForPurchaseSaleDocVO(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getNowAmount(),
                item.getNumber(),
                item.getSellingPrice(),
                item.getComment()
            ));
        }

        ObservableList<GoodItemForPurchaseSaleDocVO> temp = FXCollections.observableArrayList(Goods);
        GoodItemList.setItems(temp);
        GoodItemList.setCellFactory((ListView<GoodItemForPurchaseSaleDocVO> Goods)->new GoodPreview());

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



    /**
     *
     * @param doc 进货单据
     */
    public void showDetail(PurchaseSaleDoc doc){


        if(doc.getType()==DocType.PURCHASE){
            setTitleDocName("进货单");
            ListName.setText("丨进货商品列表");
            prize.setText("丨进货单价");
            number.setText("丨进货数量");
        }
        else {
            setTitleDocName("进货退货单");
            ListName.setText("丨退货商品列表");
            prize.setText("丨进货单价");
            number.setText("丨退货数量");
        }

        //set 数据
        if(doc.getCus()!=null){
            setCusName(doc.getCus().getName());
            setStorage("1");//默认仓库1
            setAmount(doc.getAmount());
            setComment(doc.getComment());
        }

        //自动填写的数据
        setClerk(doc.getClerk().getName());
        setTitleDocID(doc.getDocPrKey());

        this.currentDoc=doc;

        setReadable();
    }


    /**
     * 点击新增按钮增加商品
     */
    @FXML
    private void AddGood(){

        Stage stage=new Stage();
        GoodSelector selector=new GoodSelector(stage,this, goodSelectItems);
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

            ResultMessage isSuccess=LayoutController.getDataHelper().addPurchaseDoc(currentDoc.getDocPrKey(),LayoutController.getAddType(),addCus,
                    this.ObToArray(GoodItemList.getItems()),Double.valueOf(Amount.getText()),comment.getText());

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
        changedDoc.setDocPrKey(currentDoc.getDocPrKey()); //用于确定唯一单据

        String comment=this.comment.getText();
        double Amount=Double.parseDouble(this.Amount.getText());

        if(addCus!=null){
            changedDoc.setCus(addCus);
        }

        if(!doc.getComment().equals(comment)){
            changedDoc.setComment(comment);
        }
        if(doc.getAmount()!=Amount){
            changedDoc.setAmount(Amount);
        }

        if(Goods!=null){
            changedDoc.setGoodList(Goods);
        }


        return changedDoc;
    }

    @FXML
    private void calTotal(){
        double total=0;
        for(GoodItemForPurchaseSaleDocVO good:Goods){
            total+= good.getTotalPrice();
        }
        Amount.setText(String.valueOf(total));
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
        LayoutController.selectFirst();
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
        clerk.setEditable(false);
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
    private ArrayList<GoodItemForPurchaseSaleDocVO> ObToArray(ObservableList<GoodItemForPurchaseSaleDocVO> docs){
        ArrayList<GoodItemForPurchaseSaleDocVO> arrayList=new ArrayList<>();
        for(GoodItemForPurchaseSaleDocVO doc:docs){
            arrayList.add(doc);
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
       CheckController.passDoc(this.toPurVO(currentDoc));

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

    public void setCheckController(CheckingDocLayoutController controller){CheckController=controller;}

    private PurchaseDocVO toPurVO(PurchaseSaleDoc doc){
        return new PurchaseDocVO(doc.getDocPrKey(),doc.getClerk(),doc.getType(),doc.getCus(),doc.getGoodList(),doc.getComment());
    }
}
