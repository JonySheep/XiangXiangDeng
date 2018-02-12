package BusinessLogic.CustomerBL;

import Util.ResultMessage;
import VO.CustomerVO;

import java.util.ArrayList;

public interface CustomerBLInfo {

    ArrayList<CustomerVO> getSupplier();

    ArrayList<CustomerVO> getSaler();

    ResultMessage updateReceivable(String id,double change);

    ResultMessage updatePayable(String id, double change);
}
