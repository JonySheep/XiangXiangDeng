package Presentation.DocUI.GoodDoc.view;

import Presentation.CommodityUI.GoodListSettable;
import Presentation.CommodityUI.GoodSelector;
import Presentation.DocUI.CheckingDoc.CheckingDocDataHelper;
import Presentation.DocUI.CheckingDoc.CheckingDocMainUI;
import Presentation.DocUI.CheckingDoc.view.CheckingDocLayoutController;
import Presentation.DocUI.GoodDoc.GoodDocMainUI;
import Presentation.DocUI.GoodDoc.model.GoodDoc;
import Util.DocType;
import Util.GoodSelectItem;
import Util.ResultMessage;
import VO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class GoodDocDetailController implements GoodListSettable{

    @FXML
    private ListView<GoodItemForGoodDocVO> GoodList;

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
    private TextField Comment;

    @FXML
    private Button AddGoodButton;

    @FXML
    private Label DocTitleName;

    @FXML
    private Label TypeText;

    @FXML
    private Label DocTitleID;

    @FXML
    private AnchorPane DetailLayout;

    private GoodDoc currentDoc;
    private GoodDocLayoutController LayoutController;
    private CheckingDocLayoutController CheckController;
    private Stage stage;

    /** 用于构造ListView的列表 */
    private ArrayList<GoodItemForGoodDocVO> Goods = new ArrayList<>();
    /** GoodSelector实际的列表 */
    private ArrayList<GoodSelectItem> goodSelectItems = new ArrayList<>();


    @FXML
    private void initialize(){
        GoodList.setCellFactory((ListView<GoodItemForGoodDocVO> goods)->new GoodPreview());
    }

    public void setGoodList(ObservableList<GoodItemForGoodDocVO> goodList) {
        GoodList.setItems(goodList);
    }

    public void setComment(String comment) {
        Comment.setText(comment);
    }

    public void setDocTitleName(String docTitleName) {
        DocTitleName .setText(docTitleName);
    }

    public void setLayoutController(GoodDocLayoutController controller){
        LayoutController=controller;
    }

    public void setStage(Stage primaryStage){
        stage=primaryStage;
    }

    public void setTypeText(DocType docType){
        if(docType==DocType.GOOD_OVERFLOW){
            TypeText.setText("报溢数量");
        }
        else{
            TypeText.setText("报损数量");
        }
    }

    public void setDocTitleID(String docTitleID) {
        DocTitleID .setText(docTitleID);
    }

    @Override
    public void setGoodList(ArrayList<GoodSelectItem> list) {
        //refresh
        goodSelectItems = list;
        Goods=new ArrayList<>();
        for(GoodSelectItem good:list){
            Goods.add(new GoodItemForGoodDocVO(good.getId(),good.getName(),good.getType(),good.getNowAmount(),good.getNumber()));
        }

        GoodList.setItems(FXCollections.observableArrayList(Goods));
    }


    private class GoodPreview extends ListCell<GoodItemForGoodDocVO> {
        @Override
        protected  void updateItem(GoodItemForGoodDocVO item,boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(GoodDocMainUI.class.getResource("view/GoodItemPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    GoodItemPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setGoodName(item.getName());
                    controller.setGoodID(item.getId());
                    controller.setGoodType(item.getType());
                    controller.setGoodCommodity(item.getExpected());
                    controller.setGoodWarning(item.getChange());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 展示具体信息
     * @param goodDoc 库存单据
     */
    public void showDetail(GoodDoc goodDoc){
        if(goodDoc.getType()== DocType.GOOD_OVERFLOW){
            setDocTitleName("库存报溢单");
        }
        else{
            setDocTitleName("库存报损单");
        }

        setDocTitleID(goodDoc.getPrKey());
        if(goodDoc.getCurrentOperator()!=null){
            setComment(goodDoc.getComment());
        }

        setTypeText(goodDoc.getType());

        currentDoc=goodDoc;
        setReadable();
    }

    /**
     * 点击新增按钮增加商品
     */
    @FXML
    private void AddGood(){

        Stage stage=new Stage();
        //FIXME
        GoodSelector selector=new GoodSelector(stage,this, goodSelectItems);

    }


    /**
     * 保存更改
     */
    @FXML
    private void Save(){
        /**
         * 若是新增的保存
         */
        if(currentDoc.getCurrentOperator()==null){


            String prKey=LayoutController.getDataHelper().getUser().getUserID()+ "-"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            ResultMessage isSuccess=LayoutController.getDataHelper().addGoodDoc(prKey,new UserForDocVO(LayoutController.getDataHelper().getUser().getUserID(),
                            LayoutController.getDataHelper().getUser().getName()), LayoutController.getAddType(),
                    this.ObToArray(GoodList.getItems()),Comment.getText());

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
            GoodDoc savedDoc=SavedDoc(currentDoc);

            if(LayoutController.getDataHelper().modifyGoodDoc(savedDoc)==ResultMessage.SUCCESS){
                LayoutController.refresh();
            }
        }

        setReadable();
    }

    //比对找出改动
    private GoodDoc SavedDoc(GoodDoc doc){

        GoodDoc changedDoc=new GoodDoc();

        changedDoc.setPrKey(doc.getPrKey());
        String comment=this.Comment.getText();

        if(!doc.getComment().equals(comment)){
            changedDoc.setComment(comment);
        }

        if(Goods!=null){
            changedDoc.setGoods(Goods);
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
        submitButton.setVisible(true);
        saveButton.setVisible(false);
        cancleButton.setVisible(false);
        passButton.setVisible(false);
        backButton.setVisible(false);

        //编辑按钮设置为可见
        editButton.setVisible(true);

        //新增客户和商品的按钮设置为不可见
        AddGoodButton.setVisible(false);

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

        AddGoodButton.setVisible(true);
        Comment.setEditable(true);
    }

    /**
     *
     * @param docs ObservableList
     * @return ArrayList
     */
    private ArrayList<GoodItemForGoodDocVO> ObToArray(ObservableList<GoodItemForGoodDocVO> docs){
        ArrayList<GoodItemForGoodDocVO> arrayList=new ArrayList<>();
        for(GoodItemForGoodDocVO doc:docs){
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
        submitButton.setVisible(false);

        passButton.setVisible(true);
        backButton.setVisible(true);
    }

    @FXML
    private void Pass(){
        CheckController.passDoc(this.convererToVO(currentDoc));

    }

    @FXML
    private void Back(){
        CheckController.rejectDoc(this.convererToVO(currentDoc));
    }

    @FXML
    private void Submit(){
        ResultMessage isSuccess=LayoutController.getDataHelper().submitDoc(currentDoc,LayoutController.getIntegerList());

        if(isSuccess==ResultMessage.SUCCESS){
            DetailLayout.setVisible(false);
            LayoutController.refresh();
        }
    }

    public void setCheckController(CheckingDocLayoutController controller){
        CheckController=controller;
    }


    public GoodDocVO convererToVO(GoodDoc doc){
        return new GoodDocVO(doc.getPrKey(),doc.getCurrentOperator(),doc.getType(),doc.getGoods(),doc.getComment());
    }
}
