package BusinessLogicService;

import Util.ResultMessage;
import VO.UserVO;

public interface UserLoginService {

    /**
     *
     * @param id 表示用户
     * @param password 表示表示密码
     * @return 返回一个登录状态
     */
    ResultMessage login(String id, String password);

    /**
     *
     * @return 返回更新后的登录状态
     */
    boolean logout();

    /**
     *
     * @param userID 表示用户编号
     * @param oldPassword 表示原来的密码
     * @param newPassword 表示新密码
     * @return 返回一个ResultMessage表示密码是否修改成功
     */
    boolean reset(String userID, String oldPassword, String newPassword);

    /**
     *
     * 获得当前操作员
     * @return
     */
    UserVO getCurrentUser();
}

