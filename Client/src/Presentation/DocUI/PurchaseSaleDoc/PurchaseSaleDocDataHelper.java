package Presentation.DocUI.PurchaseSaleDoc;

import BusinessLogic.DocBL.DocController;
import BusinessLogic.UserBL.UserController;
import BusinessLogicService.DocBLService;
import Presentation.DocUI.DataChecker;
import Presentation.DocUI.PurchaseSaleDoc.model.PurchaseSaleDoc;
import Util.*;
import VO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.print.Doc;
import java.util.ArrayList;

public class PurchaseSaleDocDataHelper {

    private ObservableList<PurchaseSaleDoc> PurchaseDocList= FXCollections.observableArrayList();
    private ObservableList<PurchaseSaleDoc> SaleDocList=FXCollections.observableArrayList();

    private ObservableList<PurchaseSaleDoc> currentPurchaseSaleList=FXCollections.observableArrayList();

    private ArrayList<GoodItemForPurchaseSaleDocVO> GoodList=new ArrayList<>();
    private ArrayList<GiftItem> GiftList=new ArrayList<>();

    private DataChecker checker=new DataChecker();

    private DocBLService blService=new DocController();
    private UserVO currentUser= new UserController().getCurrentUser();
    //private UserVO currentUser=new UserVO("000000001","2333","YangYang", UserLevel.GENERAL,UserRole.IMPORT);

    //private DocBLService blService=new DocController();

    public PurchaseSaleDocDataHelper(){
       /* GoodList.add(new GoodItemForPurchaseSaleDocVO("0000001","超级香香灯","香灯",4,5,200,""));
        GoodList.add(new GoodItemForPurchaseSaleDocVO("0000006","臭臭羊灯","臭死你",4,10,300,"hhhhh"));

        GiftList.add(new GiftItem("超级香香灯",10));
        GiftList.add(new GiftItem("撒贝宁明灯",3));

        PurchaseDocList.add(new PurchaseSaleDoc("000000001-20180116094600",DocType.PURCHASE,new CustomerForDocVO("GYS000001","李泽言",5),"羊",GoodList,4000.0,"李总裁真好看"));
        PurchaseDocList.add(new PurchaseSaleDoc("000000001-20180116122203",DocType.PURCHASE_RETURN,new CustomerForDocVO("GYS000002","林俊杰",4),"羊",GoodList,4000.0,"爱羊不解释"));

        SaleDocList.add(new PurchaseSaleDoc("",DocType.SALE,new CustomerForDocVO("GYS000002","林俊杰",4),"羊",GoodList,4000.0,"随便写写好累啊",50.0,100,GiftList));
    */}


    /**
     * 得到所有进货单据
     * @return 进货单列表
     */


    /**
     * 得到所有销售单据
     * @return 销售单列表
     */
    public ObservableList<PurchaseSaleDoc> getSaleDocList(){

        /*
         * 链接BL
         */
        /*
        ArrayList<SaleDocVO> DocList=blService.getSaleDraftList(userId)
        //VO转模型类
        ObservableList<PurchaseSaleDoc> Sale=FXCollections.observableArrayList();
        for(SaleDocVO vo:DocList){
            Purchases.add(this.toModel(vo));
        }

        return Sale;
        */
        return SaleDocList;
    }



    /**
     * 新增进货单、进货退货单
     * @param prkey 唯一标识符
     * @param type 单据类型
     * @param Cus 客户
     * @param goods 商品列表
     * @param Amount 总额
     * @param comment 备注
     * @return
     */
    public ResultMessage addPurchaseDoc(String prkey,DocType type,CustomerForDocVO Cus,ArrayList<GoodItemForPurchaseSaleDocVO> goods
            ,double Amount,String comment){

        /*
        链接BL
         */

        PurchaseDocVO newPurchaseDoc=new PurchaseDocVO(prkey,new UserForDocVO(currentUser.getUserID(),currentUser.getName()),type,Cus,goods,comment);
        ResultMessage isSuccess=blService.addDocDraft(newPurchaseDoc);


        PurchaseDocList.add(this.toModel(newPurchaseDoc));

        return ResultMessage.SUCCESS;
    }


    /**
     * 新增销售、销售退货单
     * @param prKey 唯一标识符
     * @param type 单据类型
     * @param Cus 客户名
     * @param Clerk 操作员
     * @param goods 商品列表
     * @param Amount 总金额
     * @param Comment 备注
     * @param gifts 赠品列表
     * @param Discount 折让
     * @param Voucher 优惠券
     * @return
     */
    public ResultMessage addSaleDoc(String prKey,DocType type,CustomerForDocVO Cus, UserForDocVO Clerk, ArrayList<GoodItemForPurchaseSaleDocVO> goods, double Amount, String Comment,
                                    ArrayList<GiftItem> gifts,double Discount,int Voucher){

        /*
        链接BL
         */
        /*
        PurchaseDocVO newPurchaseDoc=new PurchaseDocVO(CusName,Clerk,goods,Amount,comment);
        ResultMessage isSuccess=blService.addDocDraft(newPurchaseDoc);
        */

        PurchaseSaleDoc newSaleDoc=new PurchaseSaleDoc(prKey,type,Cus,Clerk,GoodList,Amount,Comment,Discount,Voucher,gifts);

        SaleDocList.add(newSaleDoc);

        return ResultMessage.SUCCESS;
    }


    /**
     * 新增销售退货单
     * @param prKey 唯一标识符
     * @param type 单据类型
     * @param Cus 客户
     * @param Clerk 操作员
     * @param goods 商品列表
     * @param Amount 总额
     * @param Comment 备注
     * @return
     */
    public ResultMessage addSaleReturnDoc(String prKey,DocType type,CustomerForDocVO Cus, UserForDocVO Clerk, ArrayList<GoodItemForPurchaseSaleDocVO> goods, double Amount, String Comment) {

        /*
        链接BL
         */
        /*
        PurchaseDocVO newPurchaseDoc=new PurchaseDocVO(CusName,Clerk,goods,Amount,comment);
        ResultMessage isSuccess=blService.addDocDraft(newPurchaseDoc);
        */

        PurchaseSaleDoc newSaleDoc = new PurchaseSaleDoc(prKey, type, Cus, Clerk, GoodList, Amount, Comment);

        SaleDocList.add(newSaleDoc);

        return ResultMessage.SUCCESS;
    }


    /**
     * 删除进货、退货单据
     * @param list
     * @return
     */
    public ResultMessage delPurchaseSaleDoc(ObservableList<Integer> list){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        PurchaseSaleDoc delCus=new PurchaseSaleDoc();

        try{
            for(Integer e:list){
                delCus=currentPurchaseSaleList.get(e);
                isSuccess=blService.delDocDraft(this.toPurVO(delCus));

                if(isSuccess==ResultMessage.SUCCESS){
                    currentPurchaseSaleList.remove(e,e+1);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return ResultMessage.SUCCESS;
    }


    public ResultMessage modifyPurchaseDoc(PurchaseSaleDoc PurchaseDoc){
        ResultMessage isSuccess=ResultMessage.SUCCESS;

        try{
            isSuccess=blService.editDocDraft(this.toPurVO(PurchaseDoc));
        }
        catch (Exception e){
            e.printStackTrace();
        }


        //check data
        for(PurchaseSaleDoc oldDoc:currentPurchaseSaleList){

            if(oldDoc.getDocPrKey().equals(PurchaseDoc.getDocPrKey())){
                //找到相应改动的单据后进行比对
                if(PurchaseDoc.getCus()!=null){
                    oldDoc.setCus(PurchaseDoc.getCus());
                }
                if(PurchaseDoc.getGoodList().size()!=0){
                    oldDoc.setGoodList(PurchaseDoc.getGoodList());
                }
                if(!PurchaseDoc.getComment().equals(EmptyValue.getString())){
                    oldDoc.setComment(PurchaseDoc.getComment());
                }
                if(PurchaseDoc.getAmount()!=EmptyValue.getDouble()){
                    oldDoc.setAmount(PurchaseDoc.getAmount());
                }
                break;
            }

        }


        return isSuccess;
    }



    /**
     * 提交单据
     * @param doc 选中的单据
     * @param list 选中的单据列表
     * @return
     */
    public ResultMessage submitDoc(PurchaseSaleDoc doc,ObservableList<Integer> list){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        try{
            isSuccess=blService.submitDocDraft(this.toPurVO(doc));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(isSuccess==ResultMessage.SUCCESS){
            for(Integer e:list){
                currentPurchaseSaleList.remove(e,e+1);
            }
        }

        return isSuccess;
    }



    /**
     * VO转模型类
     * @param vo 单据VO
     * @return PurchaseSaleDoc
     */
    private PurchaseSaleDoc toModel(PurchaseDocVO vo){
        return new PurchaseSaleDoc(vo.getPrKey(),vo.getType(),vo.getCustomer(),vo.getOperator(),vo.getItemList(),
                vo.getTotal(),vo.getComment());
    }

    private PurchaseSaleDoc toModel(SaleDocVO vo){
        return new PurchaseSaleDoc(vo.getPrKey(),vo.getType(),vo.getCustomer(),vo.getOperator(),vo.getItemList(),
                vo.getTotal(),vo.getComment(),vo.getRebate(),vo.getVoucher(),vo.getGiftList());
    }

    private PurchaseDocVO toPurVO(PurchaseSaleDoc doc){
        return new PurchaseDocVO(doc.getDocPrKey(),new UserForDocVO(currentUser.getUserID(),currentUser.getName()),doc.getType(),doc.getCus(),doc.getGoodList(),doc.getComment());
    }

    public void setPurchaseDocList() {
           /*
        链接BL
         */
        ObservableList<PurchaseSaleDoc> Purchase=FXCollections.observableArrayList();
        ArrayList<PurchaseDocVO> DocList=blService.getPurchaseDraftList(currentUser.getUserID());
        //VO转模型类

        for(PurchaseDocVO vo:DocList){
            Purchase.add(this.toModel(vo));
        }

        PurchaseDocList=Purchase;
        currentPurchaseSaleList=PurchaseDocList;
    }

    public void setSaleDocList() {
          /*
        链接BL
         */
        ObservableList<PurchaseSaleDoc> Purchase=FXCollections.observableArrayList();
        ArrayList<SaleDocVO> DocList=blService.getSaleDraftList(currentUser.getUserID());
        //VO转模型类

        for(SaleDocVO vo:DocList){
            Purchase.add(this.toModel(vo));
        }

        SaleDocList=Purchase;
        currentPurchaseSaleList=SaleDocList;
    }

    public ObservableList<PurchaseSaleDoc> getCurrentPurchaseSaleList() {
        return currentPurchaseSaleList;
    }

    public UserVO getUser(){return currentUser;}

}
