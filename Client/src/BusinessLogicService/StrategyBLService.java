package BusinessLogicService;

import Util.ResultMessage;
import Util.StrategyType;
import VO.*;

import java.util.ArrayList;

public interface StrategyBLService {
    /**
     * 新增客户回馈策略
     * @param CusStrategyVO
     * @return
     */
    public ResultMessage addCusStrategy(CustomerStrategyVO CusStrategyVO);


    /**
     * 新增组合降价策略
     * @param PkgStrategyVO
     * @return
     */
    public ResultMessage addPkgStrategy(PackageStrategyVO PkgStrategyVO);


    /**
     * 新增总额赠送策略
     * @param AmouStrategyVO
     * @return
     */
    public ResultMessage addAmouStrategy(AmountStrategyVO AmouStrategyVO);


    /**
     * 删除客户回馈策略
     * @param CusStrategyVO
     * @return
     */
    public ResultMessage removeCusStrategy(CustomerStrategyVO CusStrategyVO);


    /**
     * 删除组合降价策略
     * @param PkgStrategyVO
     * @return
     */
    public ResultMessage removePkgStrategy(PackageStrategyVO PkgStrategyVO);


    /**
     * 删除总额赠送策略
     * @param AmouStrategyVO
     * @return
     */
    public ResultMessage removeAmouStrategy(AmountStrategyVO AmouStrategyVO);

    /**
     * 更新客户回馈策略
     * @param CusStrategyVO
     * @return
     */
    public ResultMessage updateCusStrategy(CustomerStrategyVO CusStrategyVO);


    /**
     * 更新组合降价策略
     * @param PkgStrategyVO
     * @return
     */
    public ResultMessage updatePkgStrategy(PackageStrategyVO PkgStrategyVO);


    /**
     * 更新总额赠送策略
     * @param AmouStrategyVO
     * @return
     */
    public ResultMessage updateAmouStrategy(AmountStrategyVO AmouStrategyVO);


    /**
     * 根据策略的ID得到促销策略
     * @param id
     * @return
     */
    public StrategyVO getStrategy(String id);


    /**
     * 得到所有客户回馈策略
     * @return
     */
    public ArrayList<CustomerStrategyVO> getCusStrategies();


    /**
     * 得到所有组合降价策略
     * @return
     */
    public ArrayList<PackageStrategyVO> getPkgStrategies();


    /**
     * 得到所有总额赠送策略
     * @return
     */
    public ArrayList<AmountStrategyVO> getAmouStrategies();

}