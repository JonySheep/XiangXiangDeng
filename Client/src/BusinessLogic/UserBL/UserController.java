package BusinessLogic.UserBL;

import BusinessLogicService.UserBLService;
import BusinessLogicService.UserLoginService;
import Util.LoginState;
import Util.ResultMessage;
import Util.UserLevel;
import VO.UserVO;

import java.util.ArrayList;

public class UserController implements UserBLService, UserLoginService, UserInfo{
    private  User user;
    public UserController(){
        user = new User();
    }

    /**
     *
     * @param uservo 指一个UserVO对象
     * @return 返回新增结果
     */
    @Override
    public ResultMessage add(UserVO uservo) {
        return user.add(uservo);
    }

    /**
     *
     * @param str 指输入的用户账号
     * @return 返回查询结果
     */
    @Override
    public ArrayList<UserVO> RandomSearch(String str) {
        return user.RandomSearch(str);
    }

    /**
     *
     * @param id 用户编号
     * @return 返回id对应的user信息
     */
    @Override
    public UserVO searchByID(String id){
        return user.searchByID(id);
    }

    /**
     *
     * @param userID 表示用户账号
     * @return 返回删除结果
     */
    @Override
    public ResultMessage delete(String userID) {
        return user.delete(userID);
    }

    /**
     *
     * @param uservo 表示一个UserVO对象
     * @return 返回更新权限结果
     */
    @Override
    public ResultMessage update(UserVO uservo) {
        return user.update(uservo);
    }

    /**
     *
     * @return 返回所有用户列表
     */
    @Override
    public ArrayList<UserVO> getAllUser() {
        return user.getAllUser();
    }

    /**
     *
     * @param id 表示用户
     * @param password 表示表示密码
     * @return 返回登录状态
     */
    @Override
    public ResultMessage login(String id, String password) {
        return user.login(id,password);
    }

    /**
     *
     * @return 返回登录状态
     */
    @Override
    public boolean logout() {
        return user.logout();
    }

    /**
     *
     * @param userID 表示用户编号
     * @param oldPassword 表示原来的密码
     * @param newPassword 表示新密码
     * @return 返回修改密码结果
     */
    @Override
    public boolean reset(String userID, String oldPassword, String newPassword) {
        return user.reset(userID,oldPassword,newPassword);
    }

    /**
     *
     * @param
     * @return 返回当前操作员
     */
    @Override
    public UserVO getCurrentUser(){
        return user.getCurrentUser();
    }
}
