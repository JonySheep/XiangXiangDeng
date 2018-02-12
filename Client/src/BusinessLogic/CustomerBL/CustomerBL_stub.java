package BusinessLogic.CustomerBL;

import BusinessLogicService.CustomerBLService;
import Util.CustomerKinds;
import Util.ResultMessage;
import VO.CustomerVO;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/23.
 * 用于展示层的测试
 */

public class CustomerBL_stub implements CustomerBLService,CustomerBLInfo {

    String customerID;
    String customerName;
    CustomerKinds role;
    int level;
    String telNumber;
    String address;
    String zipCode;
    String email;
    double quata; //应收额度
    double receivable; //应收
    double payable; //应付
    String clerk;
    String comment;

    public CustomerBL_stub(String id,String name,CustomerKinds kinds,int l,String tele,String addr,String zip,String eml,double q,double rec,double pay, String currentClerk,String comment){
        customerID=id;
        customerName=name;
        role=kinds;
        level=l;
        telNumber=tele;
        address=addr;
        zipCode=zip;
        email=eml;
        quata=q;
        receivable=rec;
        payable=pay;
        clerk=currentClerk;
        this.comment=comment;
    }

    /**
     *增加客户
     * @param Customervo
     * @return 成功信息
     */
    @Override
    public ResultMessage addCustomer(CustomerVO Customervo) {

        return ResultMessage.SUCCESS;
    }

    /**
     * 删除客户
     * @param Customervo
     * @return 成功信息
     */
    @Override
    public ResultMessage deleteCustomer(CustomerVO Customervo) {
        return ResultMessage.SUCCESS;
    }


    /**
     * 修改客户信息
     * @param Customervo
     * @return 成功信息
     */
    @Override
    public ResultMessage modifyCustomer(CustomerVO Customervo) {
        return ResultMessage.SUCCESS;
    }


    /**
     * 根据客户姓名进行模糊查找
     * @param name
     * @return ArrayList<Customer>满足条件的客户列表
     */
    @Override
    public ArrayList<CustomerVO> search(String name) {
        ArrayList<CustomerVO> list=null;

        CustomerVO Cus1=new CustomerVO("001","YYQ",CustomerKinds.SALER,5,"12345","NJU","230021","@@",200,0,0,"XXD","Good!");

        CustomerVO Cus2=new CustomerVO("002","YYQ",CustomerKinds.SALER,5,"12345","NJU","230021","@@",200,0,0,"XXD","wow!");

        list.add(Cus1);
        list.add(Cus2);

        return list;
    }


    /**
     * 根据客户ID进行模糊查找
     * @param id
     * @return 满足条件的客户
     */
    @Override
    public CustomerVO searchByID(String id) {

        CustomerVO Cus1=new CustomerVO("001","YYQ",CustomerKinds.SALER,5,"12345","NJU","230021","@@",200,0,0,"XXD","Good!");

        return Cus1;
    }


    /**
     * 得到客户对象
     * @param
     * @return CustomerVO
     */
    @Override
    public ArrayList<CustomerVO> getCustomer(){
        return null;
    }


    /**
     *获得供应商列表
     * @return 供应商名称列表
     */
    @Override
    public ArrayList<CustomerVO> getSupplier() {
        ArrayList<CustomerVO> SupplierList=null;

        /*SupplierList.add("YYQ");
        SupplierList.add("YYT");
        SupplierList.add("ZBB");*/

        return SupplierList;
    }


    /**
     * 获得销售商列表
     * @return 销售商名称列表
     */
    @Override
    public ArrayList<CustomerVO> getSaler() {
        ArrayList<CustomerVO> SalerList=null;


        return SalerList;
    }

    @Override
    public ResultMessage updateReceivable(String id, double change) {
        return null;
    }

    @Override
    public ResultMessage updatePayable(String id, double change) {
        return null;
    }
}
