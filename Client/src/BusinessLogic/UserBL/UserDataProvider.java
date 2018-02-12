package BusinessLogic.UserBL;

import DataService.UserDataService;
import PO.GoodPO;
import PO.UserPO;
import Util.EmptyValue;
import Util.ResultMessage;
import Util.UserLevel;
import Util.UserRole;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class UserDataProvider implements UserDataService {
    ArrayList<UserPO> userList = new ArrayList<>(Arrays.asList(
            new UserPO("001", "000","葛平", UserLevel.GENERAL, UserRole.IMPORT),
            new UserPO("002", "000","张全蛋", UserLevel.GENERAL, UserRole.FINANCIAL),
            new UserPO("003", "000","克里斯托弗·诺拉", UserLevel.FINALLEVEL, UserRole.GENERALMANAGER)

    ));

    /**
     * 新增一个用户
     *
     * @param po
     * @return 返回新增结果
     * @throws RemoteException
     */
    @Override
    public ResultMessage insert(UserPO po) throws RemoteException {
        userList.add(po);
        return ResultMessage.SUCCESS;
    }

    /**
     * 删除一个用户
     *
     * @param id
     * @return 返回删除结果
     * @throws RemoteException
     */
    @Override
    public ResultMessage disguiseDelete(String id) throws RemoteException {
        UserPO toDelete = null;
        for(UserPO po:userList){
            if(po.getUserID().equals(id)){
                toDelete = po;
            }
        }

        if(toDelete!=null){
            userList.remove(toDelete);
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAIL;
        }
    }

    /**
     * 更新所有非empty的用户信息
     *
     * @param po
     * @return 返回更新结果
     * @throws RemoteException
     */
    @Override
    public ResultMessage update(UserPO po) throws RemoteException {
        String userId = po.getUserID();
        String password = po.getPassword();
        UserLevel level = po.getLevel();
        UserRole role = po.getRole();
        String name = po.getName();

        UserPO now_po = null;
        for(UserPO e:userList){
            if(e.getUserID().equals(userId)){
                now_po = e;
                break;
            }
        }

        if(now_po!=null){
            if(now_po.getPassword().equals(EmptyValue.getString())){
                password = now_po.getPassword();
            }
            if(now_po.getLevel().equals(UserLevel.EMPTYLEVEL)){
                level = now_po.getLevel();
            }
            if(now_po.getRole().equals(UserRole.EMPTYROLE)){
                role = now_po.getRole();
            }
            if(now_po.getName().equals(EmptyValue.getString())){
                name = now_po.getName();
            }
        }

        userList.remove(now_po);
        userList.add(
                new UserPO(po.getUserID(),password,name,level,role)
        );

        return ResultMessage.SUCCESS;
    }

    /**
     * 根据id找到用户信息
     *
     * @param id
     * @return 返回po或null
     * @throws RemoteException
     */
    @Override
    public UserPO find(String id) throws RemoteException {
        UserPO now_po = null;
        for(UserPO e:userList){
            if(e.getUserID().equals(id)){
                now_po = e;
                break;
            }
        }
        return now_po;
    }

    /**
     * 登录
     *
     * @param id
     * @param password
     * @return 返回登录结果
     * @throws RemoteException
     */
    @Override
    public ResultMessage login(String id, String password) throws RemoteException {
        UserPO now_po = null;
        for(UserPO e:userList){
            if(e.getUserID().equals(id)){
                now_po = e;
                break;
            }
        }
        if(now_po.getPassword().equals(password)){;
            return ResultMessage.SUCCESS;
        }
        else return ResultMessage.FAIL;

    }

    /**
     * 登出
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean logout(String id) throws RemoteException {
        return true;
    }

    /**
     * 修改密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean resetPassword(String id, String oldPassword, String newPassword) throws RemoteException {


        return false;
    }

    /**
     * 获得数据库所有的UserPO
     *
     * @return 返回ArrayList
     * @throws RemoteException
     */
    @Override
    public ArrayList<UserPO> getAllUser() throws RemoteException {
        return userList;
    }

    /**
     * 根据name或id模糊查找
     *
     * @param str
     * @return 返回ArrayList
     * @throws RemoteException
     */
    @Override
    public ArrayList<UserPO> findUsers(String str) throws RemoteException {
        return null;
    }
}
