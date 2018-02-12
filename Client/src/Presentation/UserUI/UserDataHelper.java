package Presentation.UserUI;

import BusinessLogic.UserBL.UserController;
import BusinessLogic.UserBL.UserDataProvider;
import Presentation.UserUI.model.User;
import Util.EmptyValue;
import Util.ResultMessage;
import Util.UserLevel;
import Util.UserRole;
import VO.UserVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

public class UserDataHelper {

    private static UserDataHelper ourInstance = null;

    public static UserDataHelper getInstance() {
        if(ourInstance == null){
            ourInstance =  new UserDataHelper();
        }
        return ourInstance;
    }

    private User voToModel(UserVO vo){
        return new User(
                vo.getUserID(),
                vo.getName(),
                vo.getPassword(),
                levelToChinese(vo.getLevel()),
                roleToChinese(vo.getRole())
        );
    }

    private String levelToChinese(UserLevel level){
        switch (level){
            case GENERAL:
                return "普通权限";
            case FINALLEVEL:
                return "最高权限";
            case EMPTYLEVEL:
                return "空";//展示了这个就有问题
            default:
                return "?";
        }
    }
    private String roleToChinese(UserRole role){
        switch (role){
            case INVENTORY:
                return "库存管理人员";
            case SALES:
                return "销售人员";
            case SALESMANAGER:
                return "销售经理";
            case GENERALMANAGER:
                return "总经理";
            case FINANCIAL:
                return "财务人员";
            case IMPORT:
                return "进货人员";
            case EMPTYROLE:
                return "空";
            default:
                return "?";
        }
    }
    private UserLevel chineseToLevel(String str){
        switch (str){
            case "普通权限":
                return UserLevel.GENERAL;
            case "最高权限":
                return UserLevel.FINALLEVEL;
            default:
                return UserLevel.EMPTYLEVEL;
        }
    }
    private UserRole chineseToRole(String str){
        switch (str){
            case "库存管理人员":
                return UserRole.INVENTORY;
            case "销售人员":
                return UserRole.SALES;
            case "销售经理":
                return UserRole.SALESMANAGER;
            case "总经理":
                return UserRole.GENERALMANAGER;
            case "财务人员":
                return UserRole.FINANCIAL;
            case "进货人员":
                return UserRole.IMPORT;
            default:
                return UserRole.EMPTYROLE;
        }
    }

    private UserController userController = new UserController();

    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<User> originalUserList = null;

    private UserDataHelper() {

        for(UserVO vo: userController.getAllUser()){
            userList.add(
                    voToModel(vo)
            );
        }
        originalUserList = userList;
    }

    public ObservableList<User> getUserList() {
        return userList;
    }

    /**
     * 增加系统用户
     * @param username
     * @param password
     * @param level
     * @param role
     * @return
     */
    public ResultMessage addUser(String username, String password, String level, String role){

        ResultMessage rm = userController.add(
                new UserVO(
                        EmptyValue.getString(),
                        password,
                        username,
                        chineseToLevel(level),
                        chineseToRole(role)
                )
        );
        if(rm == ResultMessage.SUCCESS){
            userList.add(new User(EmptyValue.getString(),username,password,level,role));
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAIL;
        }
        //String id = "" + new Random().nextInt(1000000);
    }

    /**
     * 删除系统用户
     * @param list
     */
    public ResultMessage delUser(ObservableList<Integer> list){

        for(Integer e: list){//其实list里面就一个元素，所以可以这么写
            ResultMessage rm = userController.delete(
                userList.get(e).getUserId()
            );

            if(rm == ResultMessage.SUCCESS){
                userList.remove(e,e+1);
                return ResultMessage.SUCCESS;
            }else return ResultMessage.FAIL;
        }
        return ResultMessage.FAIL;
    }

    /**
     * 修改系统用户
     * @param user
     * @return
     */
    public ResultMessage updateUser(User user){

        ResultMessage rm = userController.update(
            new UserVO(
                    user.getUserId(),
                    user.getPassword(),
                    user.getUsername(),
                    chineseToLevel(user.getLevel()),
                    chineseToRole(user.getRole())
            )
        );
        if(rm == ResultMessage.SUCCESS){
            for(User e: userList){
                if(e.getUserId() == user.getUserId()) {
                    if(user.getUsername() != EmptyValue.getString()) {
                        e.setUsername(user.getUsername());
                    }
                    if(user.getPassword() != EmptyValue.getString()) {
                        e.setPassword(user.getPassword());
                    }
                    if(user.getRole() != EmptyValue.getString()) {
                        e.setRole(user.getRole());
                    }
                    if(user.getLevel() != EmptyValue.getString()) {
                        e.setLevel(user.getLevel());
                    }
                }
            }
            return ResultMessage.SUCCESS;
        }

        return ResultMessage.FAIL;
    }

    /**
     * 查询银行账户
     */
    public ResultMessage searchUser(String str){

        ArrayList<UserVO> voList =  userController.RandomSearch(str);
        ObservableList<User> list = FXCollections.observableArrayList();
        for(UserVO vo:voList){
            list.add(
                    voToModel(vo)
            );
        }
        //list.add(new User("233333","福大","666666","普通权限","销售人员"));
        if(originalUserList == null){//这样的话，多次搜索也好使
            originalUserList = userList;
        }
        userList = list;
        return ResultMessage.SUCCESS;
    }

    /**
     * 返回查询前状态
     */
    public ResultMessage searchBack(){
        userList = originalUserList;
        return ResultMessage.SUCCESS;
    }

    /**
     * 同步银行账户列表
     */
    public ResultMessage syncUserList(){
        /*
         * 链接BL
         */

        ArrayList<UserVO> voList =  userController.getAllUser();
        ObservableList<User> list = FXCollections.observableArrayList();
        for(UserVO vo:voList){
            list.add(
                    voToModel(vo)
            );
        }
        userList = list;
        originalUserList = null;
        return ResultMessage.SUCCESS;
    }
}

