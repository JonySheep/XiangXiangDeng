package Presentation.CustomerUI;


import BusinessLogic.CustomerBL.CusManageController;
import BusinessLogic.CustomerBL.CustomerBLInfo;
import BusinessLogic.UserBL.UserController;
import BusinessLogicService.CustomerBLService;
import Presentation.CustomerUI.model.Customer;
import Util.*;
import VO.CustomerVO;
import VO.UserVO;
import com.sun.org.apache.regexp.internal.RE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class CustomerDataHelper {

    private CustomerBLService blService=new CusManageController();
    private CustomerBLInfo blInfo=new CusManageController();
    private DataChecker checker=new DataChecker();

    //Testing Data
    private ObservableList<Customer> AllCustomerList= FXCollections.observableArrayList();
    private ObservableList<Customer> SupplierList= FXCollections.observableArrayList();
    private ObservableList<Customer> SalerList= FXCollections.observableArrayList();

    private ObservableList<Customer> CustomerList= FXCollections.observableArrayList();
    //private UserVO currentUser= new UserController().getCurrentUser();
    private UserVO currentUser= new UserVO("00000001","2333","YangYang", UserLevel.GENERAL, UserRole.IMPORT);


    public CustomerDataHelper(){
        /*AllCustomerList.add(new Customer("GYS000001","清流", CustomerKinds.SUPPLIER,5,"025-8888888","大活十一楼",
                "230021","888888888@nju.edu.cn",1000.0,0.0,1000.0,"羊","清流最喜欢香香灯啦"));
        AllCustomerList.add(new Customer("GYS000002","林俊杰", CustomerKinds.SUPPLIER,5,"025-327327327","六栋503-2三床",
                "327222","jjlin@nju.edu.cn",500000.0,0.0,10000.0,"羊","羊夫"));
        AllCustomerList.add(new Customer("XSS000001","李泽言", CustomerKinds.SALER,5,"888-8888","华锐有限公司总裁办公室",
                "327222","zeyanLi@huarui.cn",500000.0,20000.0,0.0,"羊","李怼怼专属卖灯处，该客户最喜欢臭臭羊灯"));
        AllCustomerList.add(new Customer("XSS000002","许墨", CustomerKinds.SALER,4,"222-2222","六栋503-2二床",
                "327222","xumo@nju.edu.cn",2000.0,1000.0,0.0,"羊","豆子的纸片人男朋友"));

        for(Customer Cus:AllCustomerList){
            if(Cus.getKind()==CustomerKinds.SUPPLIER){
                SupplierList.add(Cus);
            }
            else if(Cus.getKind()==CustomerKinds.SALER){
                SalerList.add(Cus);
            }
        }


        setAllCustomer();*/
    }


    /**
     * 传入新建客户输入的数据，传给BL层
     * @param name 客户名
     * @param kind 客户类型
     * @param level 客户等级
     * @param telNumber 电话
     * @param address 地址
     * @param zipCode 邮编
     * @param email 邮箱
     * @param quata 应收额度
     * @param rec 应收
     * @param pay 应付
     * @param comment 备注
     * @return ResultMessage
     */
    public ResultMessage addCustomer(String name,CustomerKinds kind,int level,String telNumber,String address,String zipCode,String email,double quata,
                                     double rec,double pay,String comment){

        //Data Check
        if(name.equals("")){
            checker.inValidName();
            return ResultMessage.FAIL;
        }

        ResultMessage isSuccess=ResultMessage.SUCCESS;


        CustomerVO newCus=new CustomerVO(name,kind,level,telNumber,address,zipCode,email,quata,rec,pay,currentUser.getName(),comment);

        //Link to BL
        try{
            isSuccess=blService.addCustomer(newCus);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(isSuccess==ResultMessage.SUCCESS){
            //界面层显示
            AllCustomerList.add(this.ConverterToModel(newCus));

            if(newCus.getKind()==CustomerKinds.SUPPLIER){
                SupplierList.add(this.ConverterToModel(newCus));
            }
            else{
                SalerList.add(this.ConverterToModel(newCus));
            }
        }


        /*
        若BL层返回错误
         */
        if(isSuccess==ResultMessage.FAIL){
            checker.FailToAdd();
        }


        return isSuccess;
    }


    /**
     * 删除客户，删除后需要更新客户列表
     * @param list
     * @return
     */
    public ResultMessage delCustomer(ObservableList<Integer> list){
        ResultMessage isSuccess=ResultMessage.SUCCESS;
        Customer delCus=new Customer();

        try{
            for(Integer e:list){
                delCus=CustomerList.get(e);
                isSuccess=blService.deleteCustomer(this.ConverterToVO(CustomerList.get(e)));
                if(isSuccess== ResultMessage.SUCCESS){
                    CustomerList.remove(e,e+1);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

            //删除AllCustomerList中对应的客户
            //SalerList和SupplierList在SynCustomer（）方法中同步更新
            for(Customer cus:AllCustomerList){
                if(cus.getCustomerID().equals(delCus.getCustomerID())){
                    AllCustomerList.remove(cus);
                    break;
                }
            }

        return isSuccess;
    }


    /**
     * 修改客户信息
     * @param cus
     * @return
     */
    public ResultMessage modifyCustomer(Customer cus){

        ResultMessage isSuccess=ResultMessage.SUCCESS;

        try{
            isSuccess=blService.modifyCustomer(this.ConverterToVO(cus));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //Data Checker
        if(cus.getCustomerName().equals("")){
            checker.inValidName();
        }
        if(cus.getClerk().equals("")){
            checker.inValidClerk();
        }

        //check data
        if(isSuccess==ResultMessage.SUCCESS){
            for(Customer oldCus:AllCustomerList){
                if(oldCus.getCustomerID().equals(cus.getCustomerID())){
                    if(!cus.getCustomerName().equals(EmptyValue.getString())){
                        oldCus.setCustomerName(cus.getCustomerName());
                    }
                    if(cus.getKind()!=CustomerKinds.EMPTY){
                        oldCus.setCustomerKind(cus.getKind());
                    }
                    if(cus.getLevel()!=EmptyValue.getInteger()){
                        oldCus.setLevel(cus.getLevel());
                    }
                    if(!cus.getTelNumber().equals(EmptyValue.getString())){
                        oldCus.setTelNumber(cus.getTelNumber());
                    }
                    if(!cus.getAddress().equals(EmptyValue.getString())){
                        oldCus.setAddress(cus.getAddress());
                    }
                    if(!cus.getZipCode().equals(EmptyValue.getString())){
                        oldCus.setZipCode(cus.getZipCode());
                    }
                    if(!cus.getEmail().equals(EmptyValue.getString())){
                        oldCus.setEmail(cus.getEmail());
                    }
                    if(cus.getQuata()!=EmptyValue.getDouble()){
                        oldCus.setQuata(cus.getQuata());
                    }
                    if(!cus.getClerk().equals(EmptyValue.getString())){
                        oldCus.setClerk(cus.getClerk());
                    }
                    if(!cus.getComment().equals(EmptyValue.getString())){
                        oldCus.setComment(cus.getComment());
                    }
                    break;
                }
            }
        }


        return isSuccess;
    }


    /**
     * 根据用户输入进行搜索(未测试）
     * @param str
     * @return
     */
    public ResultMessage getSearchResult(String str){
        ObservableList<Customer> searchResult=FXCollections.observableArrayList();

        try{
            ArrayList<CustomerVO> searchList=blService.search(str);
            if(searchList!=null){
                for(CustomerVO vo:searchList){
                    searchResult.add(this.ConverterToModel(vo));
                }
                this.CustomerList=searchResult;
            }
            else{
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("无对应客户，请您重新搜索");
                alert.showAndWait();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return ResultMessage.SUCCESS;
    }


    /**
     * 得到当前的客户列表（所有客户、供应商or销售商列表
     * @return
     */
    public ObservableList<Customer> getCustomerList(){

        return  CustomerList;

    }


    /**
     * 得到所有客户
     * @return
     */
    public ObservableList<Customer> getAllCustomerList() {

        //链接BL拿到所有客户列表
        ObservableList<Customer> obList=FXCollections.observableArrayList();

        try{
            for(CustomerVO vo:blService.getCustomer()){
                obList.add(this.ConverterToModel(vo));
            }

            this.CustomerList=obList;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        AllCustomerList=obList;
        return AllCustomerList;

    }

    /**
     * 得到当前的供应商客户列表
     * @return
     */
    public void setSupplier(){

        ObservableList<Customer> obList=FXCollections.observableArrayList();
        //Link to BL
        try{
            ArrayList<CustomerVO> suppliers=blInfo.getSupplier();


            //将ArrayList转换为ObservableList
            for(CustomerVO cusVO:suppliers){
                obList.add(this.ConverterToModel(cusVO));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        SupplierList=obList;
        CustomerList=SupplierList;
    }


    /**
     * 得到当前的销售商客户列表
     * @return
     */
    public void setSaler(){

       ObservableList<Customer> obList=FXCollections.observableArrayList();
        //Link to BL
        try{

            ArrayList<CustomerVO> salers=blInfo.getSaler();


            //将ArrayList转换为ObservableList
            for(CustomerVO cusVO:salers){
                obList.add(this.ConverterToModel(cusVO));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        SalerList=obList;
        CustomerList=SalerList;

    }

    public void setAllCustomer(){
        CustomerList=AllCustomerList;
    }


    /**
     *一般在删除和新增后进行
     * 同步更新客户列表
     */
    public void syncCustomerList(){

        /*ObservableList<Customer> obList=FXCollections.observableArrayList();

        try{
            for(CustomerVO vo:blService.getCustomer()){
                obList.add(this.ConverterToModel(vo));
            }

            this.CustomerList=obList;
        }
        catch (Exception e){
            e.printStackTrace();
        }*/

        ObservableList<Customer> Supplier= FXCollections.observableArrayList();
        ObservableList<Customer> Saler= FXCollections.observableArrayList();

        for(Customer Cus:AllCustomerList){
            if(Cus.getKind()==CustomerKinds.SUPPLIER){
                Supplier.add(Cus);
            }
            else if(Cus.getKind()==CustomerKinds.SALER){
                Saler.add(Cus);
            }
        }

        SupplierList=Supplier;
        SalerList=Saler;
    }


    /**
     * 将模型类转为VO传回BL
     * @param Cus
     * @return
     */
    private CustomerVO ConverterToVO(Customer Cus){
        return new CustomerVO(Cus.getCustomerID(),Cus.getCustomerName(),Cus.getKind(),Cus.getLevel(),Cus.getTelNumber(),Cus.getAddress(),
                Cus.getZipCode(),Cus.getEmail(),Cus.getQuata(),Cus.getReceivable(),Cus.getPayable(),Cus.getClerk(),Cus.getComment());
    }


    /**
     * 将BL传来的VO转为Model传到界面
     * @param Cus
     * @return
     */
    private Customer ConverterToModel(CustomerVO Cus){
        return new Customer(Cus.getCustomerID(),Cus.getCustomerName(),Cus.getKind(),Cus.getLevel(),Cus.getTelNumber(),Cus.getAddress(),
                Cus.getZipCode(),Cus.getEmail(),Cus.getQuata(),Cus.getReceivable(),Cus.getPayable(),Cus.getClerk(),Cus.getComment());
    }

    /**
     * 得到当前操作员
     * @return UserVO
     */
    public UserVO getUser(){return currentUser;}
}
