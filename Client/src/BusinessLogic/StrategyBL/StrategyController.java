package BusinessLogic.StrategyBL;

import BusinessLogicService.StrategyBLService;
import Util.GiftItem;
import Util.GoodSelectItem;
import Util.ResultMessage;
import Util.SelectedGoodItem;
import VO.*;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StrategyController implements StrategyBLInfo, StrategyBLService{

    private Strategy strategy = new Strategy();

    @Override
    public CustomerStrategyVO getCustomerStrategy(int level, LocalDate now) {
        return strategy.getCustomerStrategy(level,now);
    }

    @Override
    public AmountStrategyVO getAmountStrategy(double sum, LocalDate now) {
        return strategy.getAmountStrategy(sum,now);
    }

    @Override
    public ArrayList<GoodSelectItem> recalcPrice(ArrayList<GoodSelectItem> goodSelectItems, LocalDate now) {
        return null;
    }

    @Override
    public ResultMessage addCusStrategy(CustomerStrategyVO CusStrategyVO) {
        return strategy.addCusStrategy(CusStrategyVO);
    }

    @Override
    public ResultMessage addPkgStrategy(PackageStrategyVO PkgStrategyVO) {
        return strategy.addPkgStrategy(PkgStrategyVO);
    }

    @Override
    public ResultMessage addAmouStrategy(AmountStrategyVO AmouStrategyVO) {
        return strategy.addAmouStrategy(AmouStrategyVO);
    }

    @Override
    public ResultMessage removeCusStrategy(CustomerStrategyVO CusStrategyVO) {
        return strategy.deleteStrategy(CusStrategyVO);
    }

    @Override
    public ResultMessage removePkgStrategy(PackageStrategyVO PkgStrategyVO) {
        return strategy.deleteStrategy(PkgStrategyVO);
    }

    @Override
    public ResultMessage removeAmouStrategy(AmountStrategyVO AmouStrategyVO) {
        return strategy.deleteStrategy(AmouStrategyVO);
    }

    @Override
    public ResultMessage updateCusStrategy(CustomerStrategyVO CusStrategyVO) {
        return strategy.updateCusStrategy(CusStrategyVO);
    }

    @Override
    public ResultMessage updatePkgStrategy(PackageStrategyVO PkgStrategyVO) {
        return strategy.updatePkgStrategy(PkgStrategyVO);
    }

    @Override
    public ResultMessage updateAmouStrategy(AmountStrategyVO AmouStrategyVO) {

        return strategy.updateAmouStrategy(AmouStrategyVO);
    }

    @Override
    public StrategyVO getStrategy(String id) {
        return strategy.getStrategy(id);
    }

    @Override
    public ArrayList<CustomerStrategyVO> getCusStrategies() {
        return strategy.getCusStrategy();
    }

    @Override
    public ArrayList<PackageStrategyVO> getPkgStrategies() {
        return strategy.getPkgStrategy();
    }

    @Override
    public ArrayList<AmountStrategyVO> getAmouStrategies() {
        return strategy.getAmouStrategy();
    }
}