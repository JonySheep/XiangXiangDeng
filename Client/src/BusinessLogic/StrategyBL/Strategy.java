package BusinessLogic.StrategyBL;

import DataService.CommodityDataService;
import DataService.StrategyDataService;
import PO.*;
import Rmi.RemoteHelper;
import Util.*;
import VO.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.*;

public class Strategy {

    private static StrategyDataService stgData= RemoteHelper.getInstance().getStrategyDataService();
    private static CommodityDataService comData=RemoteHelper.getInstance().getCommodityDataService();
    private ResultMessage message=ResultMessage.SUCCESS;
    private static LocalDate currentDate=LocalDate.now();
    private static VOPOConverter converter=new VOPOConverter();

    /**
     *新增客户回馈策略
     * @param CusStgVO
     * @return ResultMessage
     */
    ResultMessage addCusStrategy(CustomerStrategyVO CusStgVO){

        try{
            CustomerStrategyPO CusStgPO=converter.CusvoTOpo(CusStgVO);
            message=stgData.insertCusStrategy(CusStgPO);
            return message;
        }
        catch (RemoteException e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    /**
     *新增组合降价策略
     * @param PkgStraVO
     * @return ResultMessage
     */
    ResultMessage addPkgStrategy(PackageStrategyVO PkgStraVO){
        try{
            PackageStrategyPO PkgStgPO=converter.PkgvoTOpo(PkgStraVO);
            message=stgData.insertPkgStrategy(PkgStgPO);
            return message;
        }
        catch (RemoteException e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }

    /**
     * 新增总额赠送策略
     * @param AmouStraVO
     * @return ResultMessage
     */
    ResultMessage addAmouStrategy(AmountStrategyVO AmouStraVO){
        try{
            AmountStrategyPO AmouStgPO=converter.AmouvoTOpo(AmouStraVO);
            message=stgData.insertAmouStrategy(AmouStgPO);
            return message;
        }
        catch (RemoteException e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }


    CustomerStrategyVO getCustomerStrategy(int level, LocalDate now) {
        try {
            CustomerStrategyPO customerStrategyPO = stgData.getCurrentCusStrategy(now,level);
            CustomerStrategyVO customerStrategyVO = converter.CuspoTOvo(customerStrategyPO);
            return customerStrategyVO;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    AmountStrategyVO getAmountStrategy(double sum, LocalDate now) {

        try {
            AmountStrategyPO amountStrategyPO = stgData.getCurrentAmouStrategy(now,sum);
            return converter.AmoupoTOvo(amountStrategyPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除促销策略
     * @param strategyvo
     * @return ResultMessage
     */
    ResultMessage deleteStrategy(StrategyVO strategyvo){
        try{
            message=stgData.delete(converter.StgvoTOpo(strategyvo));
            return message;
        }
        catch (RemoteException e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }


    ResultMessage updateCusStrategy(CustomerStrategyVO customerStrategyVO){
        CustomerStrategyPO customerStrategyPO = converter.CusvoTOpo(customerStrategyVO);
        try {
            return stgData.updateCusStrategy(customerStrategyPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    ResultMessage updatePkgStrategy(PackageStrategyVO packageStrategyVO){
        PackageStrategyPO packageStrategyPO = converter.PkgvoTOpo(packageStrategyVO);
        try {
            return stgData.updatePkgStrategy(packageStrategyPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    ResultMessage updateAmouStrategy(AmountStrategyVO amountStrategyVO){
        AmountStrategyPO amountStrategyPO = converter.AmouvoTOpo(amountStrategyVO);
        try {
            return stgData.updateAmouStrategy(amountStrategyPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    /**
     * 得到促销策略对象
     * @param id
     * @return StrategyVO
     */
    StrategyVO getStrategy(String id){
        try {
            StrategyPO strategyPO = stgData.getStrategy(id);
            if (strategyPO.getType() == StrategyType.CUSTOMER){
                CustomerStrategyPO customerStrategyPO = (CustomerStrategyPO)strategyPO;
                return converter.CuspoTOvo(customerStrategyPO);
            }
            else if(strategyPO.getType() == StrategyType.PACKAGE){
                PackageStrategyPO packageStrategyPO = (PackageStrategyPO)strategyPO;
                return converter.PkgpoTOvo(packageStrategyPO);
            }
            else{
                AmountStrategyPO amountStrategyPO = (AmountStrategyPO)strategyPO;
                return converter.AmoupoTOvo(amountStrategyPO);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得组合促销后价格
     * @param goods
     * @return finalAmount
     */
    double getAmount(ArrayList<SelectedGoodItem> goods, ArrayList<PackageStrategyVO> SelectedStgs){

        double sum=0;

        //遍历所有组合促销包，将组合促销包中的商品从已买商品列表中删除
        for(PackageStrategyVO pkg:SelectedStgs){
            ArrayList<PackageGoodItem> PkgGoods=pkg.getPkgGood();

            for(PackageGoodItem g:PkgGoods){
                String goodName=g.getGoodID();
                int PkgGoodNum=g.getGoodNumber();

                for(SelectedGoodItem good:goods){
                    if(goodName.equals(good.getName())){
                        int tempNum=good.getNumber();
                        good.setNumber(tempNum-PkgGoodNum);

                        if(good.getNumber()==0){
                            goods.remove(good);       //若商品全为组合包中商品，则将其从已买商品列表中删除
                        }

                        break;
                    }
                }
            }

            //组合包价格计入总价
            sum+=pkg.getPrice();
        }

        //商品列表处理结束后，开始计算其他商品总价
        for(SelectedGoodItem leftGood:goods){
            sum+=leftGood.getPrice();
        }

        return sum;
    }


    /**
     *根据销售单中的商品来检查可用的组合包
     * 在界面层用户可以递归的调用checkValid来检查
     * @param goods
     * @return validPkg
     */
    ArrayList<PackageStrategyVO> checkValidPkg(ArrayList<SelectedGoodItem> goods){

        ArrayList<PackageStrategyVO> validPkg=new ArrayList<>();
        try{
            ArrayList<PackageStrategyPO> currentPkgs=stgData.getCurrentPkgStrategy(currentDate);

            //遍历所有当前进行的组合促销策略
            for(PackageStrategyPO pkg:currentPkgs){

                ArrayList<PackageGoodItem> PkgGoods=pkg.getPkgGood();

                //记录满足组合降价包的数据
                int count=0;

                //遍历ArrayList
                for(PackageGoodItem g:PkgGoods){
                    String goodName=g.getGoodID();
                    int goodNum=g.getGoodNumber();

                    boolean isValid=false;

                    //如果满足所有组合包中商品及数量
                    if(count==PkgGoods.size()){
                        validPkg.add(converter.PkgpoTOvo(pkg));
                        break;
                    }

                    for(SelectedGoodItem good:goods){
                        if(good.getName().equals(goodName) && good.getNumber()>=goodNum) {
                            isValid = true;
                            count++;

                            break;            //如果该数量的商品满足此Map的keyString，则跳出已买商品的循环，进行下一个循环
                        }
                    }

                    if(!isValid){
                        break;
                    }
                }
            }
            return validPkg;
        }
        catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 从数据层得到所有客户回馈策略
     * @return
     */
    ArrayList<CustomerStrategyVO> getCusStrategy(){
        ArrayList<CustomerStrategyVO> CusStrategys=new ArrayList<>();
        try{
            for(CustomerStrategyPO po:stgData.getCusStrategy()){
                CusStrategys.add(converter.CuspoTOvo(po));
            }

        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return CusStrategys;
    }


    /**
     * 从数据层得到所有组合促销策略
     * @return
     */
    ArrayList<PackageStrategyVO> getPkgStrategy(){
        ArrayList<PackageStrategyVO> PkgStrategy=new ArrayList<>();
        try{
            for(PackageStrategyPO po:stgData.getPkgStrategy()){
                PkgStrategy.add(converter.PkgpoTOvo(po));
            }
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return PkgStrategy;
    }



    /**
     * 从数据层得到所有总额赠送策略
     * @return
     */
    ArrayList<AmountStrategyVO> getAmouStrategy(){
        ArrayList<AmountStrategyVO> AmouStrategy=new ArrayList<>();
        try{
            for(AmountStrategyPO po:stgData.getAmouStrategy()){
                AmouStrategy.add(converter.AmoupoTOvo(po));
            }
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        return AmouStrategy;
    }

}