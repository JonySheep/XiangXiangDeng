package BusinessLogic.StrategyBL;

import PO.AmountStrategyPO;
import PO.CustomerStrategyPO;
import PO.PackageStrategyPO;
import PO.StrategyPO;
import VO.AmountStrategyVO;
import VO.CustomerStrategyVO;
import VO.PackageStrategyVO;
import VO.StrategyVO;

public class VOPOConverter {


    /**
     * 将CustomerStrategyVO转为PO传入数据层
     * @param CusStgVO
     * @return CustomerStrategyPO
     */
    CustomerStrategyPO CusvoTOpo(CustomerStrategyVO CusStgVO){
        CustomerStrategyPO CusStgPO=new CustomerStrategyPO(CusStgVO.getType(),CusStgVO.getBegin(),CusStgVO.getEnd(),CusStgVO.getId(),
                CusStgVO.getLevel(),CusStgVO.getGift(),CusStgVO.getVouchers(),CusStgVO.getDiscount());
        return CusStgPO;
    }


    /**
     * 将CustomerStrategyPO转为VO传回展示层
     * @param CusStgPO
     * @return
     */
    CustomerStrategyVO CuspoTOvo(CustomerStrategyPO CusStgPO){
        CustomerStrategyVO CusStgVO=new CustomerStrategyVO(CusStgPO.getType(),CusStgPO.getBegin(),CusStgPO.getEnd(),CusStgPO.getId(),
                CusStgPO.getLevel(),CusStgPO.getGift(),CusStgPO.getVouchers(),CusStgPO.getDiscount());
        return CusStgVO;
    }


    /**
     * 将PackageStrategyVO转为PO传入数据层
     * @param PkgStgVO
     * @return PackageStrategyPO
     */
    PackageStrategyPO PkgvoTOpo(PackageStrategyVO PkgStgVO){
        PackageStrategyPO PkgStgPO=new PackageStrategyPO(PkgStgVO.getType(),PkgStgVO.getBegin(),PkgStgVO.getEnd(),PkgStgVO.getId(),
                PkgStgVO.getPkgName(),PkgStgVO.getPrice(),PkgStgVO.getPkgGood());
        return PkgStgPO;
    }


    /**
     * 将PackageStrategyPO转为VO传回界面层
     * @param PkgStgPO
     * @return PackageStrategyVO
     */
    PackageStrategyVO PkgpoTOvo(PackageStrategyPO PkgStgPO){
        PackageStrategyVO PkgStgVO=new PackageStrategyVO(PkgStgPO.getType(),PkgStgPO.getBegin(),PkgStgPO.getEnd(),PkgStgPO.getId(),
                PkgStgPO.getPkgName(),PkgStgPO.getPrice(),PkgStgPO.getPkgGood());
        return PkgStgVO;
    }



    /**
     * 将AmountStrategyVO转为PO传入数据层
     * @param AmouStgVO
     * @return AmountStrategyPO
     */
    AmountStrategyPO AmouvoTOpo(AmountStrategyVO AmouStgVO){
        AmountStrategyPO AmouStgPO=new AmountStrategyPO(AmouStgVO.getType(),AmouStgVO.getBegin(),AmouStgVO.getEnd(),AmouStgVO.getId(),
                AmouStgVO.getAmount(),AmouStgVO.getGift(),AmouStgVO.getVouchers());
        return AmouStgPO;
    }


    /**
     * 将AmountStrategyPO转为VO传回界面层
     * @param AmouStgPO
     * @return
     */
    AmountStrategyVO AmoupoTOvo(AmountStrategyPO AmouStgPO){
        AmountStrategyVO AmouStgVO=new AmountStrategyVO(AmouStgPO.getType(),AmouStgPO.getBegin(),AmouStgPO.getEnd(),AmouStgPO.getId(),
                AmouStgPO.getAmount(),AmouStgPO.getGift(),AmouStgPO.getVouchers());
        return AmouStgVO;
    }


    /**
     * 将StrategyVO转为PO传入数据层
     * @param stgVO
     * @return StrategyPO
     */
    StrategyPO StgvoTOpo(StrategyVO stgVO){
        StrategyPO stgPO=new StrategyPO(stgVO.getType(),stgVO.getBegin(),stgVO.getEnd(),stgVO.getId());
        return stgPO;
    }


    /**
     * 将StrategyPO转为VO传回界面层
     * @param StgPO
     * @return StrategyVO
     */
    StrategyVO StgpoTOvo(StrategyPO StgPO){
        StrategyVO StgVO=new StrategyVO(StgPO.getType(),StgPO.getBegin(),StgPO.getEnd(),StgPO.getId());
        return StgVO;
    }
}
