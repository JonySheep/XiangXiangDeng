package Presentation.DocUI.GoodDoc;

import BusinessLogic.DocBL.DocController;
import BusinessLogic.UserBL.UserController;
import BusinessLogicService.DocBLService;
import Presentation.DocUI.GoodDoc.model.GoodDoc;
import Util.*;
import VO.*;
import com.sun.org.apache.regexp.internal.RE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GoodDocDataHelper {

    private ObservableList<GoodDoc> currentGoodDocList= FXCollections.observableArrayList();

    private UserVO currentUser=new UserController().getCurrentUser();
    //private UserVO currentUser=new UserVO("00000001","YangYang", "YangYang",UserLevel.GENERAL, UserRole.INVENTORY);
    private DocBLService blService=new DocController();

    public GoodDocDataHelper(){
        /*ArrayList<GoodItemForGoodDocVO> goods=new ArrayList<>();

        goods.add(new GoodItemForGoodDocVO("0000001","超级香香灯","xxxxxx",100,23));
        goods.add(new GoodItemForGoodDocVO("0000002","撒撒芳心纵火犯明灯","ssssss",200,30));
        currentGoodDocList.add(new GoodDoc("",new UserForDocVO("0000001","羊"),DocType.GOOD_OVERFLOW,goods,"倒计时十小时"));
        currentGoodDocList.add(new GoodDoc("",new UserForDocVO("0000001","羊"),DocType.GOOD_OVERFLOW,goods,"倒计时十小时"));

        currentGoodDocList.add(new GoodDoc("",new UserForDocVO("0000001","羊"),DocType.GOOD_LOSS,goods,"倒计时十小时"));
        currentGoodDocList.add(new GoodDoc("",new UserForDocVO("0000001","羊"),DocType.GOOD_LOSS,goods,"倒计时十小时"));
*/

    }


    public ResultMessage addGoodDoc(String prKey, UserForDocVO operator, DocType type, ArrayList<GoodItemForGoodDocVO> itemList, String comment){
        ResultMessage isSuccess=ResultMessage.SUCCESS;

        GoodDoc newDoc=new GoodDoc(prKey,operator,type,itemList,comment);

        try{
            isSuccess=blService.addDocDraft(this.convererToVO(newDoc));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(isSuccess==ResultMessage.SUCCESS){
            currentGoodDocList.add(newDoc);
        }
        return isSuccess;
    }


    /**
     * 删除单据
     * @param list
     * @return
     */
    public ResultMessage delGoodDoc(ObservableList<Integer> list){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        GoodDoc delCus=new GoodDoc();

        try{

            for(Integer e:list){
                delCus=currentGoodDocList.get(e);

                isSuccess=blService.delDocDraft(this.convererToVO(delCus));
                if(isSuccess==ResultMessage.SUCCESS){
                    currentGoodDocList.remove(e,e+1);
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
    public ResultMessage modifyGoodDoc(GoodDoc Doc){
        ResultMessage isSuccess=ResultMessage.SUCCESS;

        try{
            isSuccess=blService.editDocDraft(this.convererToVO(Doc));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //check data
        for(GoodDoc oldDoc:currentGoodDocList){

            if(oldDoc.getPrKey().equals(Doc.getPrKey())){
                //找到相应改动的单据后进行比对
                if(Doc.getGoods()!=null&&Doc.getGoods().size()!=0){
                    oldDoc.setGoods(Doc.getGoods());
                }
                if(!Doc.getComment().equals(EmptyValue.getString())){
                    oldDoc.setComment(Doc.getComment());
                }
            }
            break;
        }

        return isSuccess;
    }


    public ObservableList<GoodDoc> gerCurrentDocList(){

        /*
        链接bl
         */
        ObservableList<GoodDoc> Goods=FXCollections.observableArrayList();
        try{

            ArrayList<GoodDocVO> GoodVOs=blService.getGoodOverflowDraftList(currentUser.getUserID());
            ArrayList<GoodDocVO> GoodLossVO=blService.getGoodLossDraftList(currentUser.getUserID());
            for(GoodDocVO vo:GoodVOs){
                Goods.add(this.converterToModel(vo));
            }
            for(GoodDocVO vo:GoodLossVO){
                Goods.add(this.converterToModel(vo));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        currentGoodDocList=Goods;
        return currentGoodDocList;
    }


    /**
     * 提交单据
     * @param doc 选中的单据
     * @param list 选中的单据列表
     * @return
     */
    public ResultMessage submitDoc(GoodDoc doc,ObservableList<Integer> list){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        try{
            isSuccess=blService.submitDocDraft(this.convererToVO(doc));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(isSuccess==ResultMessage.SUCCESS){
            for(Integer e:list){
                currentGoodDocList.remove(e,e+1);
            }
        }

        return isSuccess;
    }



    public GoodDoc converterToModel (GoodDocVO vo){
        return new GoodDoc(vo.getPrKey(),vo.getOperator(),vo.getType(),vo.getItemList(),vo.getComment());
    }

    public GoodDocVO convererToVO(GoodDoc doc){
        return new GoodDocVO(doc.getPrKey(),doc.getCurrentOperator(),doc.getType(),doc.getGoods(),doc.getComment());
    }

    public UserVO getUser(){return currentUser;}
}
