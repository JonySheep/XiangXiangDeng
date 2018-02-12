package BusinessLogicService;

import Util.ResultMessage;
import VO.CustomerVO;

import java.util.ArrayList;

/**
 * Created by Jven on 2017/10/22.
 */
public interface CustomerBLService
{
    public ResultMessage addCustomer(CustomerVO Customervo);

    public ResultMessage deleteCustomer(CustomerVO Customervo);

    public ResultMessage modifyCustomer(CustomerVO Customervo);

    public ArrayList<CustomerVO> search(String str);

    public CustomerVO searchByID(String id);

    public ArrayList<CustomerVO> getCustomer();

}
