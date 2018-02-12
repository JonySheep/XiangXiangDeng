package Presentation.DocUI.CheckingDoc;

import BusinessLogic.DocBL.DocController;
import BusinessLogic.UserBL.UserController;
import BusinessLogicService.DocBLService;
import Presentation.DocUI.GoodDoc.model.GoodDoc;
import Presentation.DocUI.PaymentDoc.model.PaymentDoc;
import Util.DocType;
import Util.ResultMessage;
import VO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CheckingDocDataHelper {

    private ObservableList<PurchaseDocVO> PurchaseDocList= FXCollections.observableArrayList();
    private ObservableList<SaleDocVO> SaleDocList= FXCollections.observableArrayList();
    private ObservableList<FinanceDocVO> FinanceDocList= FXCollections.observableArrayList();
    private ObservableList<GoodDocVO> GoodDocList= FXCollections.observableArrayList();

    private DocBLService blService=new DocController();
    private UserVO currentUser=new UserController().getCurrentUser();

    public ObservableList<Previewable> getGoodDocList(){
        /*
        链接BL得到所有库存类单据
         */
        ObservableList<Previewable> PreList = FXCollections.observableArrayList();
        try{
            ArrayList<GoodDocVO> GoodDocs=blService.getUncheckedGoodDocList();
            PreList=FXCollections.observableArrayList(GoodDocs);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return PreList;

    }

    public ObservableList<Previewable> getPurDocList(){
        /*
        链接BL得到所有进货类单据
         */
        ObservableList<Previewable> PreList = FXCollections.observableArrayList();
        try{
            ArrayList<PurchaseDocVO> PurDocs=blService.getUncheckedPurchaseDocList();
            PreList=FXCollections.observableArrayList(PurDocs);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return PreList;
    }


    public ObservableList<Previewable> getSaleDocList(){
        /*
        链接BL得到所有销售类单据
         */
        ObservableList<Previewable> PreList = FXCollections.observableArrayList();
        try{
            ArrayList<SaleDocVO> PurDocs=blService.getUncheckedSaleDocList();
            PreList=FXCollections.observableArrayList(PurDocs);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return PreList;
    }

    public ObservableList<Previewable> getFinDocList(){
        /*
        链接BL得到所有财务类单据
         */
        ObservableList<Previewable> PreList = FXCollections.observableArrayList();
        try{
            ArrayList<FinanceDocVO> FinDocs=blService.getUncheckedFinanceDocList();
            PreList=FXCollections.observableArrayList(FinDocs);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return PreList;
    }


    public ResultMessage PassDoc(GoodDocVO doc){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        try{
            isSuccess=blService.approveDoc(doc);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  isSuccess;
    }

    public ResultMessage PassDoc(PurchaseDocVO doc){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        try{
            isSuccess=blService.approveDoc(doc);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  isSuccess;
    }

    public ResultMessage PassDoc(PaymentDocVO doc){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        try{
            isSuccess=blService.approveDoc(doc);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  isSuccess;
    }

    public ResultMessage PassDoc(CashDocVO doc){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        try{
            isSuccess=blService.approveDoc(doc);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  isSuccess;
    }

    public ResultMessage RejectDoc(GoodDocVO doc){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        try{
            isSuccess=blService.rejectDoc(doc);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  isSuccess;
    }

    public ResultMessage RejectDoc(PaymentDocVO doc){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        try{
            isSuccess=blService.rejectDoc(doc);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  isSuccess;
    }
}
