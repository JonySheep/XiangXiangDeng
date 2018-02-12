package BusinessLogic.StrategyBL;

import BusinessLogicService.StrategyBLService;
import Util.*;
import VO.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class StrategyBL_stub implements StrategyBLService,StrategyBLInfo {

    StrategyType type;
    LocalDate begin;
    LocalDate end;
    String id;

    public StrategyBL_stub(StrategyType type, LocalDate begin, LocalDate end,String id) {
        this.type = type;
        this.begin = begin;
        this.end = end;
        this.id=id;
    }

    @Override
    public ResultMessage addCusStrategy(CustomerStrategyVO CusStrategyVO) {
        return null;
    }

    @Override
    public ResultMessage addPkgStrategy(PackageStrategyVO PkgStrategyVO) {
        return null;
    }

    @Override
    public ResultMessage addAmouStrategy(AmountStrategyVO AmouStrategyVO) {
        return null;
    }

    @Override
    public ResultMessage removeCusStrategy(CustomerStrategyVO CusStrategyVO) {
        return null;
    }

    @Override
    public ResultMessage removePkgStrategy(PackageStrategyVO PkgStrategyVO) {
        return null;
    }

    @Override
    public ResultMessage removeAmouStrategy(AmountStrategyVO AmouStrategyVO) {
        return null;
    }

    @Override
    public ResultMessage updateCusStrategy(CustomerStrategyVO CusStrategyVO) {
        return null;
    }

    @Override
    public ResultMessage updatePkgStrategy(PackageStrategyVO PkgStrategyVO) {
        return null;
    }

    @Override
    public ResultMessage updateAmouStrategy(AmountStrategyVO AmouStrategyVO) {
        return null;
    }

    /**
     * 得到促销策略对象
     * @param ID
     * @return 成功信息
     */
    @Override
    public StrategyVO getStrategy(String ID){
        return new StrategyVO(type,begin,end,id);
    }

    @Override
    public ArrayList<CustomerStrategyVO> getCusStrategies() {
        return null;
    }

    @Override
    public ArrayList<PackageStrategyVO> getPkgStrategies() {
        return null;
    }

    @Override
    public ArrayList<AmountStrategyVO> getAmouStrategies() {
        return null;
    }

    /**
     * 得到折让价格
     * @param level
     * @return
     */

    @Override
    public CustomerStrategyVO getCustomerStrategy(int level, LocalDate now) {
        return null;
    }

    @Override
    public AmountStrategyVO getAmountStrategy(double sum, LocalDate now) {
        return null;
    }

    @Override
    public ArrayList<GoodSelectItem> recalcPrice(ArrayList<GoodSelectItem> goodSelectItems, LocalDate now) {
        return null;
    }
}
