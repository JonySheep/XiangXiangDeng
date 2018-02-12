package BusinessLogic.UserBL;

import DataService.UserDataService;
import PO.UserPO;
import Rmi.RemoteHelper;
import Util.ResultMessage;
import VO.UserVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class User {

    //获得服务器接口
    private static UserDataService userDataService = RemoteHelper.getInstance().getUserDataService();

    //获得测试数据接口
    //private static UserDataService userDataService = new UserDataProvider();

    //保存当前操作员的vo信息
    private static UserVO currentWorker = new UserVO();

    private UserPO VOToPO(UserVO uservo){
        return  new UserPO(uservo.getUserID(), uservo.getPassword(), uservo.getName(), uservo.getLevel(), uservo.getRole());
    }

    /**
     *
     * @param uservo 表示一个UserVO对象
     * @return 返回新增用户结果
     */
    public ResultMessage add(UserVO uservo){
        UserPO userPO = VOToPO(uservo);
        System.out.println(userPO);
        try {
            return userDataService.insert(userPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    /**
     *
     * 根据输入字符串进行模糊查找
     * @param str 输入的字段
     * @return 返回符合匹配的用户列表
     */
    public ArrayList<UserVO> RandomSearch(String str) {
        try {
            ArrayList<UserPO> userPOS = userDataService.findUsers(str);
            return getUserVOList(userPOS);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * 根据ID单一查找
     * @param id 客户编号
     * @return 返回userVO
     */
    public UserVO searchByID(String id){
        try {
            UserPO userPO = userDataService.find(id);
            UserVO userVO = new UserVO(userPO);
            return userVO;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     *
     * @param userID 表示用户账号
     * @return 返回删除结果
     */
    public ResultMessage delete(String userID){
        try {
            return userDataService.disguiseDelete(userID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    /**
     *
     * @param uservo 表示一个UserVO对象
     * @return 返回是否更新成功
     */
    public ResultMessage update(UserVO uservo){
        UserPO userPO = VOToPO(uservo);
        try {
            return userDataService.update(userPO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    /**
     *
     * @return 返回数据库中的所有用户列表
     */
    public ArrayList<UserVO> getAllUser() {

        try {
            return getUserVOList(userDataService.getAllUser());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param id 表示用户账号
     * @param password 表示用户密码
     * @return 更新登录状态
     */
    public ResultMessage login(String id, String password) {
        try {
            ResultMessage resultMessage = userDataService.login(id,password);
            if (resultMessage == ResultMessage.SUCCESS)
            {
                currentWorker = searchByID(id);
            }
            return resultMessage;

        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }

    }

    /**
     *
     * @return 更新登录状态
     */
    public boolean logout() {
        try {
            return userDataService.logout(currentWorker.getUserID());
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param userID 表示用户账号
     * @param oldPassword 表示用户原先密码
     * @param newPassword 表示用户新密码
     * @return 返回修改密码是否成功
     */
    public boolean reset(String userID, String oldPassword, String newPassword){
        try {
            return userDataService.resetPassword(userID,oldPassword,newPassword);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @return 返回用户权限
     */
    public UserVO getCurrentUser(){
        return currentWorker;
    }


    //将ArrayList<UserPO>转成ArrayList<UserVO>
    private ArrayList<UserVO> getUserVOList(ArrayList<UserPO> userPOS){
        ArrayList<UserVO> userVOS = new ArrayList<>();
        int len = userPOS.size();
        for (int i=0;i<len;i++){
            UserVO temp = new UserVO(userPOS.get(i));
            userVOS.add(temp);
        }
        return userVOS;
    }
}
