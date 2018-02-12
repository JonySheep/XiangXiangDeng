package Presentation.UserUI.view;

import Presentation.UserUI.UserDataHelper;
import Presentation.UserUI.model.User;
import Util.EmptyValue;
import Util.ResultMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * AccountLayoutController和DetailsLayoutController相互持有引用
 */
public class DetailsLayoutController {


    private User selectedUser = null;
    private UserDataHelper userDataHelper = UserDataHelper.getInstance();
    private UserLayoutController userLayoutController = null;


    @FXML
    private BorderPane detailsLayout;

    @FXML
    private HBox originalButtonSet;

    @FXML
    private HBox editButtonSet;


    @FXML
    private Label title;

    @FXML
    private Text username;

    @FXML
    private Text userId;

    @FXML
    private Text password;

    @FXML
    private Text role;

    @FXML
    private Text level;


    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private ChoiceBox<String> roleChoice;

    @FXML
    private ChoiceBox<String> levelChoice;


    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getUsername() {
        return username.getText();
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public String getUserId() {
        return userId.getText();
    }

    public void setUserId(String userId) {
        this.userId.setText(userId);
    }

    public String getPassword() {
        return password.getText();
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }

    public String getRole() {
        return role.getText();
    }

    public void setRole(String role) {
        this.role.setText(role);
    }

    public String getLevel() {
        return level.getText();
    }

    public void setLevel(String level) {
        this.level.setText(level);
    }


    public void setUserLayoutController(UserLayoutController userLayoutController){
        this.userLayoutController = userLayoutController;
    }


    /**
     * 左栏点击一个草稿单元
     * @param user
     */
    public void showUserDetails(User user) {
        if(user == null)
            return;//防止更新ListView的时候由于监听器的关系造成NullPointerException
        setTitle(user.getUsername());
        setUsername(user.getUsername());
        setUserId(user.getUserId());
        setPassword(user.getPassword());
        setRole(user.getRole());
        setLevel(user.getLevel());
        this.selectedUser = user;
    }

    /**
     * 在左栏点击新建
     */
    public void add(){
        setTitle("请输入系统用户信息");
        usernameField.setVisible(true);
        userId.setText("等待系统分配");
        passwordField.setVisible(true);
        roleChoice.setVisible(true);
        levelChoice.setVisible(true);

        originalButtonSet.setVisible(false);
        editButtonSet.setVisible(true);

    }

    /**
     * 点击编辑按钮
     * @param actionEvent
     */
    public void edit(ActionEvent actionEvent) {

        usernameField.setText(username.getText());
        passwordField.setText(password.getText());
        roleChoice.getSelectionModel().select(role.getText());
        levelChoice.getSelectionModel().select(level.getText());

        usernameField.setVisible(true);
        passwordField.setVisible(true);
        roleChoice.setVisible(true);
        levelChoice.setVisible(true);

        originalButtonSet.setVisible(false);
        editButtonSet.setVisible(true);

    }

    /**
     * 点击取消按钮
     * @param actionEvent
     */
    public void cancel(ActionEvent actionEvent) {
        if(selectedUser != null){//如果是编辑之后的取消
            usernameField.setVisible(false);
            passwordField.setVisible(false);
            roleChoice.setVisible(false);
            levelChoice.setVisible(false);

            originalButtonSet.setVisible(true);
            editButtonSet.setVisible(false);
        }else{//如果是新建之后的取消
            detailsLayout.setVisible(false);
        }

    }

    /**
     * 点击保存按钮
     * @param actionEvent
     */
    public void save(ActionEvent actionEvent) {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleChoice.getValue();
        String level = levelChoice.getValue();
        if(selectedUser != null){//如果是编辑之后的保存

            //控制修改时的数据
            if(username.equals("") || password.equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("输入错误");
                alert.setHeaderText(null);
                alert.setContentText("用户名和密码不能为空，请重新设置");

                alert.showAndWait();
                return;
            }

            //辨别改动
            User user = new User(selectedUser.getUserId(),EmptyValue.getString(),EmptyValue.getString(),EmptyValue.getString(),EmptyValue.getString());
            if(!username.equals(selectedUser.getUsername())){
                user.setUsername(username);
            }
            if(!password.equals(selectedUser.getPassword())){
                user.setPassword(password);
            }
            if(!role.equals(selectedUser.getRole())){
                user.setRole(role);
            }
            if(!level.equals(selectedUser.getLevel())){
                user.setLevel(level);
            }

            ResultMessage rm = userDataHelper.updateUser(user);
            if(rm == ResultMessage.SUCCESS){
                usernameField.setVisible(false);
                passwordField.setVisible(false);
                roleChoice.setVisible(false);
                levelChoice.setVisible(false);

                setTitle(username);
                setUsername(username);
                setPassword(password);
                setLevel(level);
                setRole(role);

                originalButtonSet.setVisible(true);
                editButtonSet.setVisible(false);

                userLayoutController.refresh();
                userLayoutController.select(selectedUser);
            }else{
                //当修改不成功，应该是修改的系统用户被删掉了，应该与服务器同步，并弹窗提示用户
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("编辑失败");
                alert.setHeaderText(null);
                alert.setContentText("尝试与服务器同步qwq");

                alert.showAndWait();

                userDataHelper.syncUserList();
                userLayoutController.refresh();
                userLayoutController.selectFirst();
            }
        }else{//如果是新建之后的保存
            if(username.equals("") || password.equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("输入错误");
                alert.setHeaderText(null);
                alert.setContentText("您设置的用户名或密码为空，请重新设置");

                alert.showAndWait();
                return;
            }

            if(userDataHelper.addUser(username,password,level,role) == ResultMessage.SUCCESS){
                userLayoutController.refresh();
                userLayoutController.selectLast();
            }else{
                //当增加不成功，应该是服务器有问题，并弹窗提示用户稍后再试
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("新建失败");
                alert.setHeaderText(null);
                alert.setContentText("服务器可能挂了，请稍后再试qwq");

                alert.showAndWait();
            }
        }


    }

    /**
     * 当系统用户列表为空时，调用该方法把默认的DetailsLayout隐藏
     */
    public void hideDetailsLayout(){
        detailsLayout.setVisible(false);
    }
}
