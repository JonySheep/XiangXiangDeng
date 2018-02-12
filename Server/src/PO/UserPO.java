package PO;

import Util.UserLevel;
import Util.UserRole;
import java.io.Serializable;

/**
 * Created by Jeven on 2017/10/22.
 */
public class UserPO implements Serializable {
    String userID;
    String password;
    String name;
    UserLevel level;
    UserRole role;

    /**
     * @param userID    用户账号
     * @param password  用户密码
     * @param name      用户名称
     * @param userLevel 用户权限
     * @param role      用户角色
     */
public UserPO(String userID, String password, String name, UserLevel userLevel, UserRole role) {
        this.userID = userID;
        this.password = password;
        this.level = userLevel;
        this.role = role;
        this.name = name;
    }

    public UserPO(){}

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


}
