package VO;

import PO.UserPO;
import Util.EmptyValue;
import Util.UserLevel;
import Util.UserRole;

/**
 * Created by Jeven on 2017/10/22.
 */
public class UserVO {
    String userID = EmptyValue.getString();
    String name = EmptyValue.getString();
    String password = EmptyValue.getString();
    UserLevel level = UserLevel.EMPTYLEVEL;
    UserRole role = UserRole.EMPTYROLE;

    /**
     * @param userID    用户ID
     * @param password  用户密码
     * @param name      用户名称
     * @param userLevel 用户权限
     * @param role      用户角色
     */
    public UserVO(String userID, String password, String name, UserLevel userLevel, UserRole role) {
        this.userID = userID;
        this.password = password;
        this.level = userLevel;
        this.name = name;
        this.role = role;
    }

    public UserVO(UserPO po) {
        this(po.getUserID(),
                po.getPassword(),
                po.getName(),
                po.getLevel(),
                po.getRole());
    }

    public UserVO() {

    }

    public String getUserID() {
        return userID;
    }

    public UserLevel getLevel() {
        return level;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
