package DataService;

import PO.UserPO;
import Util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Jeven on 2017/10/22.
 */
public interface UserDataService extends Remote {
    /**
     *
     * 新增一个用户
     * @param po
     * @return 返回新增结果
     * @throws RemoteException
     */
    ResultMessage insert(UserPO po) throws RemoteException;

    /**
     * 删除一个用户
     * @param id
     * @return 返回删除结果
     * @throws RemoteException
     */
    ResultMessage disguiseDelete(String id) throws RemoteException;

    /**
     * 更新所有非empty的用户信息
     * @param po
     * @return 返回更新结果
     * @throws RemoteException
     */
    ResultMessage update(UserPO po) throws RemoteException;

    /**
     * 根据id找到用户信息
     * @param id
     * @return 返回po或null
     * @throws RemoteException
     */
    UserPO find(String id) throws RemoteException;

    /**
     * 登录
     * @param id
     * @param password
     * @return 返回登录结果
     * @throws RemoteException
     */
    ResultMessage login(String id, String password) throws RemoteException;

    /**
     * 登出
     * @param id
     * @return
     * @throws RemoteException
     */
     boolean logout(String id) throws RemoteException;

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws RemoteException
     */
    boolean resetPassword(String id, String oldPassword, String newPassword) throws RemoteException;

    /**
     * 获得数据库所有的UserPO
     * @return 返回ArrayList
     * @throws RemoteException
     */
    ArrayList<UserPO> getAllUser() throws RemoteException;

    /**
     * 根据name或id模糊查找
     * @return 返回ArrayList
     * @throws RemoteException
     */
    ArrayList<UserPO> findUsers(String str) throws RemoteException;

}
