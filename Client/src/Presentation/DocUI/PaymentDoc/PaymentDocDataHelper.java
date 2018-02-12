package Presentation.DocUI.PaymentDoc;

import BusinessLogic.DocBL.DocController;
import BusinessLogic.UserBL.UserController;
import BusinessLogicService.DocBLService;
import Presentation.DocUI.PaymentDoc.model.PaymentDoc;
import Util.*;
import VO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;

public class PaymentDocDataHelper {
    private ObservableList<PaymentDoc> PayingDocList= FXCollections.observableArrayList();
    private ObservableList<PaymentDoc> RecievingDocList= FXCollections.observableArrayList();
    private ObservableList<PaymentDoc> CashDocList= FXCollections.observableArrayList();
    private ObservableList<PaymentDoc> currentPaymentDocList= FXCollections.observableArrayList();

    private ArrayList<AccountVO> AccountList=new ArrayList<>();

    private DocBLService blService=new DocController();

    private UserVO currentUser = new UserController().getCurrentUser();
    //private UserVO currentUser;

    public PaymentDocDataHelper(){
       /* currentUser= new UserVO("0000001","2333","yangYang", UserLevel.GENERAL, UserRole.FINANCIAL);

        ArrayList<AccountForDocVO> accounts1=new ArrayList<>();
        accounts1.add(new AccountForDocVO("000022220000","李泽言",100000.0,"李总裁～"));
        accounts1.add(new AccountForDocVO("111122223333","许墨",2000.0,"许墨并没有钱"));

        ArrayList<AccountForDocVO> accounts2=new ArrayList<>();
        accounts2.add(new AccountForDocVO("000022220000","李泽言",100000.0,"李总裁～"));
        accounts2.add(new AccountForDocVO("111122223333","许墨",2000.0,"许墨并没有钱"));


        ArrayList<AccountForDocVO> accounts3=new ArrayList<>();
        accounts3.add(new AccountForDocVO("000022220000","李泽言",100000.0,"李总裁～"));
        accounts3.add(new AccountForDocVO("111122223333","许墨",2000.0,"许墨并没有钱"));

        ArrayList<AccountForDocVO> accounts4=new ArrayList<>();
        accounts4.add(new AccountForDocVO("000022220000","李泽言",100000.0,"李总裁～"));
        accounts4.add(new AccountForDocVO("111122223333","许墨",2000.0,"许墨并没有钱"));

        RecievingDocList.add(new PaymentDoc("1",new UserForDocVO("0000001","羊1"), DocType.RECEIVING,new CustomerForDocVO("000001","李泽言",5),accounts1));
        RecievingDocList.add(new PaymentDoc("2",new UserForDocVO("0000001","羊2"), DocType.RECEIVING,new CustomerForDocVO("000002","林俊杰",5),accounts2));

        PayingDocList.add(new PaymentDoc("3",new UserForDocVO("0000001","羊3"), DocType.PAYING,new CustomerForDocVO("000002","林俊杰",5),accounts3));
        PayingDocList.add(new PaymentDoc("4",new UserForDocVO("0000001","羊4"), DocType.PAYING,new CustomerForDocVO("000002","林俊杰",5),accounts4));

        ArrayList<CashItemVO> cashes=new ArrayList<>();
        cashes.add(new CashItemVO("养野男人",10000,"最喜欢养李泽言"));
        cashes.add(new CashItemVO("看林俊杰演唱会",2000,"WOW"));

        CashDocList.add(new PaymentDoc("000001-20180111119032",new UserForDocVO(currentUser.getUserID(),currentUser.getName()),DocType.CASH,new AccountForDocVO("022221222030","李泽言",400000,"有钱哇"),cashes));
*/
    }



    public ResultMessage addPaymentDoc(String prKey, UserForDocVO operator, DocType type, ArrayList<AccountForDocVO> itemList, CustomerForDocVO Cus){
        ResultMessage isSuccess=ResultMessage.SUCCESS;

        PaymentDoc newDoc=new PaymentDoc(prKey,operator,type,Cus,itemList);

        try{
            isSuccess=blService.addDocDraft(this.ConverterToVO(newDoc));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        currentPaymentDocList.add(newDoc);

        return isSuccess;
    }


    public ResultMessage addCashDoc(String prKey, UserForDocVO operator, DocType type,ArrayList<CashItemVO> cashItemVOS,AccountForDocVO account){
        ResultMessage isSuccess=ResultMessage.SUCCESS;

        PaymentDoc newDoc=new PaymentDoc(prKey,operator,type,account,cashItemVOS);

        try{
            isSuccess=blService.addDocDraft(this.ConvererToCashVO(newDoc));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        CashDocList.add(newDoc);
        currentPaymentDocList=CashDocList;

        return isSuccess;
    }

    /**
     * 删除单据
     * @param list
     * @return
     */
    public ResultMessage delGoodDoc(ObservableList<Integer> list){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        PaymentDoc delCus=new PaymentDoc();

        try{
            for(Integer e:list){
                delCus=currentPaymentDocList.get(e);
                isSuccess=blService.delDocDraft(this.ConverterToVO(delCus));

                if(isSuccess==ResultMessage.SUCCESS){
                    currentPaymentDocList.remove(e,e+1);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return ResultMessage.SUCCESS;
    }


    /**
     * 修改库存报警单
     * @param Doc 库存报警单
     * @return
     */
    public ResultMessage modifyPaymentDoc(PaymentDoc Doc){
        ResultMessage isSuccess=ResultMessage.SUCCESS;

        try{
            isSuccess=blService.editDocDraft(this.ConverterToVO(Doc));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //check data
        for(PaymentDoc oldDoc:currentPaymentDocList){

            if(oldDoc.getPrKey().equals(Doc.getPrKey())){
                //Compare
                if(Doc.getCurrentCustomer()!=null){
                    oldDoc.setCurrentCustomer(Doc.getCurrentCustomer());
                }
                //ArrayList直接赋值，不做判断
                if(Doc.getAcounts().size()!=0){
                    oldDoc.setAcounts(Doc.getAcounts());
                }
                break;
            }
        }


        return isSuccess;
    }


    /**
     * 显示付款单
     */
    public void setPayingDocList(){
          /*
        链接BL
         */
        ObservableList<PaymentDoc> ObList=FXCollections.observableArrayList();

        try{
            for(PaymentDocVO VO:blService.getPayingDraftList(currentUser.getUserID())){
                ObList.add(this.converterToModel(VO));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        PayingDocList=ObList;
        currentPaymentDocList=PayingDocList;
    }


    /**
     * 显示收款单
     */
    public void setRecievingDocList(){
          /*
        链接BL
         */
        ObservableList<PaymentDoc> ObList=FXCollections.observableArrayList();
        ArrayList<PaymentDocVO> docVOS=blService.getReceivingDraftList(currentUser.getUserID());
        try{
            for(PaymentDocVO VO:blService.getReceivingDraftList(currentUser.getUserID())){
                ObList.add(this.converterToModel(VO));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        RecievingDocList=ObList;
        currentPaymentDocList=RecievingDocList;

    }


    /**
     * 显示现金费用单
     */
    public void setCashDocList(){
          /*
        链接BL
         */
        ObservableList<PaymentDoc> ObList=FXCollections.observableArrayList();

        try{
            for(CashDocVO VO:blService.getCashDraftList(currentUser.getUserID())){
                ObList.add(this.converterToModel(VO));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        RecievingDocList=ObList;
        currentPaymentDocList=CashDocList;

    }

    /**
     * 提交单据
     * @param doc 选中的单据
     * @param list 选中的单据列表
     * @return
     */
    public ResultMessage submitDoc(PaymentDoc doc,ObservableList<Integer> list){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        try{
            if(doc.getType()==DocType.CASH){
                isSuccess=blService.submitDocDraft(this.ConvererToCashVO(doc));
            }
            else{
                isSuccess=blService.submitDocDraft(this.ConverterToVO(doc));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(isSuccess==ResultMessage.SUCCESS){
            for(Integer e:list){
                currentPaymentDocList.remove(e,e+1);
            }
        }


        return isSuccess;
    }



    public ObservableList<PaymentDoc> gerCurrentDocList(){

        return currentPaymentDocList;
    }


    public ArrayList<String> getAccounts(){
        ArrayList<String> AccountNumbers=new ArrayList<>();
        try{

           ArrayList<AccountVO> accountVOS=blService.getAccountList();
            //VO -> DocVO
            for(AccountVO VO:accountVOS){
                AccountNumbers.add(VO.getCardNumber());
            }
            AccountList=accountVOS;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return AccountNumbers;
    }

    public ArrayList<AccountVO> getAccountList() {
        return AccountList;
    }


    public PaymentDoc converterToModel (PaymentDocVO vo){
        return new PaymentDoc(vo.getPrKey(),vo.getId(),vo.getOperator(),vo.getType(),vo.getCustomer(),vo.getAccountList());
    }

    public PaymentDoc converterToModel(CashDocVO vo){
        return new PaymentDoc(vo.getPrKey(),vo.getOperator(),vo.getType(),vo.getAccount(),vo.getItemList());
    }

    public PaymentDocVO ConverterToVO(PaymentDoc doc){
        return new PaymentDocVO(doc.getPrKey(),doc.getID(),doc.getCurrentOperator(),doc.getType(),doc.getCurrentCustomer(),doc.getAcounts());
    }

    public CashDocVO ConvererToCashVO(PaymentDoc doc){
        return new CashDocVO(doc.getPrKey(),doc.getCurrentOperator(),doc.getType(),doc.getAccount(),doc.getCashItems());
    }

    public UserVO getUser(){return currentUser;}
}
