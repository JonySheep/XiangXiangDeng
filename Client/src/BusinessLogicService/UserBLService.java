package BusinessLogicService;
import Util.*;
import VO.*;

import java.util.ArrayList;

/**
 * Created by Jeven on 2017/10/22.
 */
public interface UserBLService
{
    /**
     *
     * @param uservo 指一个UserVO对象
     * @return 返回一个ResultMessage表示新增结果
     */
    ResultMessage add(UserVO uservo);

    /**
     *
     * @param str 指输入的用户账号
     * @return 返回符合输入的用户组成的列表
     */
    ArrayList<UserVO> RandomSearch(String str);

    /**
     *
     * @param id 用户编号
     * @return 返回id对应的user信息
     */
    UserVO searchByID(String id);
    /**
     *
     * @param userID 表示用户账号
     * @return 返回删除结果
     */
    ResultMessage delete(String userID);

    /**
     *
     * @param uservo 表示一个UserVO对象
     * @return 返回权限是否更改成功
     */
    ResultMessage update(UserVO uservo);

    /**
     *
     * @return 返回数据库中所有用户组成的列表
     */
    ArrayList<UserVO> getAllUser();

}
