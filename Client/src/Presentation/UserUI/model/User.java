package Presentation.UserUI.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private final StringProperty userId;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty level;
    private final StringProperty role;

    /**
     * 默认构造器
     * @param userId 用户账号
     * @param username 用户名称
     * @param password 用户密码
     * @param level 用户权限
     * @param role 用户角色
     */
    public User(String userId, String username, String password, String level, String role) {
        this.userId = new SimpleStringProperty(userId);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.level = new SimpleStringProperty(level);
        this.role = new SimpleStringProperty(role);
    }



    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getLevel() {
        return level.get();
    }

    public void setLevel(String level) {
        this.level.set(level);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }
}
