package BusinessLogic.CustomerBL;

import BusinessLogicService.CustomerBLService;
import Util.ResultMessage;
import VO.CustomerVO;

import java.util.ArrayList;

public class CusManageController implements CustomerBLInfo, CustomerBLService{

    private Customer Cus=new Customer();

    /**
     * Controller控制增加客户操作
     * @param Customervo
     * @return ResultMessage
     */
    @Override
    public ResultMessage addCustomer(CustomerVO Customervo) {
        return Cus.addCustomer(Customervo);
    }


    /**
     * Controller控制删除客户操作
     * @param Customervo
     * @return ResultMessage
     */
    @Override
    public ResultMessage deleteCustomer(CustomerVO Customervo) {
        return Cus.deleteCustomer(Customervo.getCustomerID());
    }


    /**
     * Controller控制修改客户信息操作
     * @param Customervo
     * @return ResultMessage
     */
    @Override
    public ResultMessage modifyCustomer(CustomerVO Customervo) {
        return Cus.modifyCustomer(Customervo);
    }


    /**
     * Controller控制根据客户姓名进行模糊查找
     * @param str
     * @return ArrayList<Customer>
     */
    @Override
    public ArrayList<CustomerVO> search(String str) {
        return Cus.search(str);
    }


    /**
     * Controller控制根据客户ID进行模糊查找
     * @param id
     * @return Customer
     */
    @Override
    public CustomerVO searchByID(String id) {
        return Cus.searchByID(id);
    }


    /**
     *Controller控制得到Customer对象
     * @param
     */
    @Override
    public ArrayList<CustomerVO> getCustomer(){
        return Cus.getCustomer();
    }


    /**
     * Controller控制得到所有供应商列表
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<CustomerVO> getSupplier() {
        return Cus.getSupplier();
    }


    /**
     * Controller控制得到所有销售商列表
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<CustomerVO> getSaler(){
        return Cus.getSaler();
    }


    @Override
    public ResultMessage updateReceivable(String id, double change) {
        CustomerVO customerVO = new CustomerVO();
        CustomerVO temp = searchByID(id);

        customerVO.setCustomerID(id);
        customerVO.setReceivable(temp.getReceivable() + change);
        return Cus.modifyCustomer(customerVO);


    }

    @Override
    public ResultMessage updatePayable(String id, double change) {
        CustomerVO customerVO = new CustomerVO();
        CustomerVO temp = searchByID(id);

        customerVO.setCustomerID(id);
        customerVO.setPayable(temp.getPayable() + change);
        return Cus.modifyCustomer(customerVO);
    }
}