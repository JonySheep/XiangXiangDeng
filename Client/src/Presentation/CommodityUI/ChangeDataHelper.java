package Presentation.CommodityUI;

import BusinessLogic.CommodityBL.CommodityController;
import BusinessLogicService.CommodityBLService;
import Presentation.CommodityUI.model.Commodity;
import Util.ResultMessage;
import VO.CommodityVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class ChangeDataHelper {
    private static ChangeDataHelper ourInstance = new ChangeDataHelper();

    public static ChangeDataHelper getInstance() {
        return ourInstance;
    }

    private ObservableList<Commodity> list = FXCollections.observableArrayList();

    private CommodityBLService commodityBLService = CommodityController.getInstance();

    private ChangeDataHelper() {
        
    }


    public ResultMessage searchCommodity(LocalDate start, LocalDate end){
        ArrayList<CommodityVO> l = commodityBLService.getCommodity(start, end);
        if(l!=null){
            updateList(l);
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAIL;
        }
    }

    public ObservableList<Commodity> getCommodity() {
        return list;
    }

    private void updateList(ArrayList<CommodityVO> voList){
        list.clear();
        for(CommodityVO vo:voList){
            list.add(
                    new Commodity(
                            vo.getGoodId(),
                            vo.getGoodName(),
                            vo.getGoodType(),
                            vo.getTotalInbound(),
                            vo.getTotalOutbound(),
                            vo.getTotalSalesVolume(),
                            vo.getTotalPurchaseVolume()
                    )
            );
        }
    }
}
