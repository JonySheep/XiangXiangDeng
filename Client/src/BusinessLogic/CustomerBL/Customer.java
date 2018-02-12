package BusinessLogic.CustomerBL;


import DataService.CustomerDataService;
import PO.CustomerPO;
import Rmi.RemoteHelper;
import Util.ResultMessage;
import VO.CustomerVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Customer {

    private static CustomerDataService
            customerData = RemoteHelper.getInstance().getCustomerDataService();
    private ResultMessage message=ResultMessage.SUCCESS;
    private static VOPOConverter converter=new VOPOConverter();

    /**
     * 用户请求新增客户
     * @param customervo
     * @return ResultMessage
     */
    ResultMessage addCustomer(CustomerVO customervo){

        //VO->PO
        CustomerPO CusPO=converter.voTOpo(customervo);

        try{
            message=customerData.insert(CusPO);
            return message;
        }
        catch (RemoteException e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }


    /**
     * 用户请求删除客户
     * @param id
     * @return ResultMessage
     */
    ResultMessage deleteCustomer(String id){

        try{
            message=customerData.disguiseDeleteCustomer(id);
            return message;
        }
        catch (RemoteException e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }


    /**
     * 用户请求修改客户信息
     * @param customervo
     * @return ResultMessage
     */
    ResultMessage modifyCustomer(CustomerVO customervo){

        //VO->PO
        CustomerPO CusPO=converter.voTOpo(customervo);

        try{
            message=customerData.update(CusPO);
            return message;
        }
        catch (RemoteException e){
            e.printStackTrace();
            return ResultMessage.FAIL;
        }
    }


    /**
     * 用户请求根据客户名字进行模糊查找
     * @param name
     * @return ArrayList<Customer>
     */
    ArrayList<CustomerVO> search(String name){

        try{
            ArrayList<CustomerPO> CusPOList=customerData.searchCustomers(name);

            if(CusPOList.size()!=0){
                //PO->VO
                ArrayList<CustomerVO> CusVOList = new ArrayList<>();
                for(CustomerPO cusPO:CusPOList){
                    CusVOList.add(converter.poTOvo(cusPO));
                }

                return CusVOList;
            }
            else{
                return null;
            }
        }
        catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 用户请求根据客户ID进行模糊查找
     * @param id
     * @return ArrayList<Customer>
     */
    CustomerVO searchByID(String id){

        try{
            CustomerVO Cus=converter.poTOvo(customerData.searchByID(id));
            return Cus;

        }
        catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 用户请求得到全部客户列表
     * @return Customer
     */
    ArrayList<CustomerVO> getCustomer(){
        try{
            ArrayList<CustomerVO> CusVO=new ArrayList<>();

            ArrayList<CustomerPO> CusPO=customerData.getCustomer();

            for(CustomerPO po:CusPO){
                CusVO.add(converter.poTOvo(po));
            }

            return CusVO;
        }
        catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 用户请求得到供应商列表
     * 并将数据层传回的PO转为VO传给界面层
     * @return ArrayList<Customer>
     */
    ArrayList<CustomerVO> getSupplier(){
        try{
            ArrayList<CustomerVO> VOList=new ArrayList<>();

            for(CustomerPO po:customerData.getSupplier()){
                VOList.add(converter.poTOvo(po));
            }

            return VOList;

        }
        catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 用户请求得到销售商列表
     * 并将数据层传回的PO转为VO传给界面层
     * @return ArrayList<Customer>
     */
    ArrayList<CustomerVO> getSaler(){

        try{
            ArrayList<CustomerVO> VOList=new ArrayList<>();

            for(CustomerPO po:customerData.getSaler()){
                VOList.add(converter.poTOvo(po));
            }


            return VOList;

        }
        catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }
}
