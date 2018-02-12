package Presentation.DocUI.CheckingDoc.view;

import Presentation.DocUI.CheckingDoc.CheckingDocDataHelper;
import Presentation.DocUI.CheckingDoc.CheckingDocMainUI;
import Presentation.DocUI.CheckingDoc.ModelConverter;
import Presentation.DocUI.CheckingDoc.Previewable;
import Presentation.DocUI.GoodDoc.GoodDocMainUI;
import Presentation.DocUI.GoodDoc.view.GoodDocDetailController;
import Presentation.DocUI.PaymentDoc.PaymentDocMainUI;
import Presentation.DocUI.PaymentDoc.model.PaymentDoc;
import Presentation.DocUI.PaymentDoc.view.CashDocDetailController;
import Presentation.DocUI.PaymentDoc.view.PaymentDocDetailController;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocDataHelper;
import Presentation.DocUI.PurchaseSaleDoc.PurchaseSaleDocMainUI;
import Presentation.DocUI.PurchaseSaleDoc.view.PurchaseDocDetailLayoutController;
import Presentation.DocUI.PurchaseSaleDoc.view.SaleDocDetailLayoutController;
import Util.DocType;
import Util.ResultMessage;
import VO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.print.Doc;
import java.io.IOException;

public class CheckingDocLayoutController {

    @FXML
    private ComboBox<String> DocKind;

    @FXML
    private ListView<Previewable> DocList;

    @FXML
    private BorderPane DocLayout;

    @FXML
    private BorderPane DetailLayout;

    private CheckingDocDataHelper dataHelper=new CheckingDocDataHelper();
    private Stage primaryStage;
    private ModelConverter Converter=new ModelConverter();

    @FXML
    /**
     *初始化方法
     */
    private void initialize(){

        DocList.setItems(dataHelper.getGoodDocList());
        DocList.setCellFactory((ListView<Previewable> previews)->new Preview());
        DocList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDocDetail(newValue));
        DocKind.setItems(FXCollections.observableArrayList("库存类","进货类","销售类","财务类"));
        DocKind.setValue("库存类");//默认为库存赠送单
    }


    private class Preview extends ListCell<Previewable>{
        @Override
        protected  void updateItem(Previewable item, boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(CheckingDocMainUI.class.getResource("view/DocPreview.fxml"));

                try{
                    AnchorPane PreviewLayout=loader.load();

                    setGraphic(PreviewLayout);
                    DocPreviewController controller=loader.getController();
                    controller.setDocType(item.getDocType());
                    controller.setName(item.getName());
                    controller.setComment(item.getComment());
                    controller.setPrKey(item.getPrKey());

                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    private void showDocDetail(Previewable docPre){
        //判断单据类型，调用不同面板显示
        FXMLLoader loader=new FXMLLoader();

        if(docPre==null) {
            return;
        }

        switch (docPre.getClass().getSimpleName()){

            case "GoodDocVO":{
                try{
                    loader.setLocation(GoodDocMainUI.class.getResource("view/GoodDocDetail.fxml"));
                    AnchorPane GoodDocDetail=loader.load();

                    DocLayout.setCenter(GoodDocDetail);
                    GoodDocDetailController controller=loader.getController();

                    GoodDocVO good=(GoodDocVO)docPre;
                    controller.setCheckController(this);
                    controller.showDetail(Converter.ToGoodModel(good));
                    controller.setGoodList(FXCollections.observableArrayList(good.getItemList()));
                    controller.setCheckMode();

                }
                catch (IOException e){
                    e.printStackTrace();
                }
                break;}

            case "PurchaseDocVO":{

                try{
                    loader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/PurchaseDocDetailLayout.fxml"));
                    BorderPane PurDocDetail=loader.load();

                    DocLayout.setCenter(PurDocDetail);
                    PurchaseDocDetailLayoutController controller=loader.getController();

                    PurchaseDocVO purchaseDocVO=(PurchaseDocVO) docPre;
                    controller.showDetail(Converter.ToPurModel(purchaseDocVO));
                    controller.setGoodItemList(FXCollections.observableArrayList(purchaseDocVO.getItemList()));
                    controller.setCheckMode();

                }
                catch (IOException e){
                    e.printStackTrace();
                }
                break;}


            case "SaleDocVO":{
                DocType type=((PurchaseSaleDocVO)docPre).getType();
                if(type==DocType.SALE){
                    try{
                        loader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/SaleDocDetailLayout.fxml"));
                        BorderPane PurDocDetail=loader.load();

                        DocLayout.setCenter(PurDocDetail);
                        SaleDocDetailLayoutController controller=loader.getController();

                        SaleDocVO saleDocVO=(SaleDocVO) docPre;
                        controller.showDetail(Converter.ToSaleModel(saleDocVO));
                        controller.setGoodItemList(FXCollections.observableArrayList(saleDocVO.getItemList()));
                        controller.setGiftItemList(FXCollections.observableArrayList(saleDocVO.getGiftList()));
                        controller.setCheckMode();

                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else{
                    try{
                        loader.setLocation(PurchaseSaleDocMainUI.class.getResource("view/PurchaseDocDetailLayout.fxml"));
                        BorderPane PurDocDetail=loader.load();

                        DocLayout.setCenter(PurDocDetail);
                        PurchaseDocDetailLayoutController controller=loader.getController();

                        SaleDocVO saleDocVO=(SaleDocVO) docPre;
                        controller.showDetail(Converter.ToSaleModel(saleDocVO));
                        controller.setGoodItemList(FXCollections.observableArrayList(saleDocVO.getItemList()));
                        controller.setCheckMode();

                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }

                }
                break;}

            case "PaymentDocVO":{
                DocType type=((FinanceDocVO)docPre).getType();
                try{
                    loader.setLocation(PaymentDocMainUI.class.getResource("view/PaymentDocDetail.fxml"));
                    AnchorPane PayDocDetail=loader.load();

                    DocLayout.setCenter(PayDocDetail);
                    PaymentDocDetailController controller=loader.getController();

                    controller.showDetail(Converter.ToPaymentModel((PaymentDocVO) docPre));
                    controller.setCheckMode();

                }
                catch (IOException e){
                    e.printStackTrace();
                }
                break;}

            case "CashDocVO":{
                try{
                        loader.setLocation(PaymentDocMainUI.class.getResource("view/CashDocDetail.fxml"));
                        AnchorPane PayDocDetail=loader.load();

                        DocLayout.setCenter(PayDocDetail);
                        CashDocDetailController controller=loader.getController();

                        controller.showDetail(Converter.ToPaymentModel((CashDocVO) docPre));
                        controller.setCheckMode();

                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                break;
            }
        }
    }

    @FXML
    private void chooseType(){
        refresh();
    }


    public void passDoc(GoodDocVO Doc){
        if(dataHelper.PassDoc(Doc)== ResultMessage.SUCCESS){
            refresh();
        }
    }

    public void passDoc(PurchaseDocVO Doc){
        if(dataHelper.PassDoc(Doc)== ResultMessage.SUCCESS){
            refresh();
        }
    }

    public void passDoc(PaymentDocVO Doc){
        if(dataHelper.PassDoc(Doc)== ResultMessage.SUCCESS){
            refresh();
        }
    }

    public void passDoc(CashDocVO Doc){
        if(dataHelper.PassDoc(Doc)== ResultMessage.SUCCESS){
            refresh();
        }
    }

    public void rejectDoc(GoodDocVO Doc){
        if(dataHelper.RejectDoc(Doc)==ResultMessage.SUCCESS){
            refresh();
        }
    }

    public void rejectDoc(PaymentDocVO Doc){
        if(dataHelper.RejectDoc(Doc)==ResultMessage.SUCCESS){
            refresh();
        }
    }

    public void refresh(){
        if(DocKind.getSelectionModel().getSelectedItem().toString().equals("库存类")){
            DocList.setItems(dataHelper.getGoodDocList());
        }
        else if(DocKind.getSelectionModel().getSelectedItem().toString().equals("进货类")){
            DocList.setItems(dataHelper.getPurDocList());
        }
        else if(DocKind.getSelectionModel().getSelectedItem().toString().equals("销售类")){
            DocList.setItems(dataHelper.getSaleDocList());
        }
        else if(DocKind.getSelectionModel().getSelectedItem().toString().equals("财务类")){
            DocList.setItems(dataHelper.getFinDocList());
        }

        DocList.setCellFactory((ListView<Previewable> previews)->new Preview());
        DocList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDocDetail(newValue));
        DocList.getSelectionModel().selectFirst();

    }


    public CheckingDocDataHelper getDataHelper(){return dataHelper;}

}
