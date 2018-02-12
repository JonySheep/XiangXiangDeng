package Presentation.UserUI.view;

import Presentation.UserUI.UserDataHelper;
import Presentation.UserUI.UserUI;
import Presentation.UserUI.model.User;
import Util.ResultMessage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Optional;

/**
 * AccountLayoutController和DetailsLayoutController相互持有引用
 */
public class UserLayoutController {

    private UserDataHelper userDataHelper;
    private DetailsLayoutController detailsLayoutController;
    private BorderPane detailsLayout;

    @FXML
    private ImageView cancelSearch;

    @FXML
    private BorderPane userLayout;

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<User> userList;

    public UserLayoutController() {
        this.userDataHelper = UserDataHelper.getInstance();
    }

    @FXML
    private void initialize() {
        userList.setCellFactory((ListView<User> CellData) -> new UserPreview());

        userList.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue)-> showUserDetails(newValue)
        );

        userList.setItems(userDataHelper.getUserList());

    }


    /**
     * 左栏用户预览单元的类
     */
    private class UserPreview extends ListCell<User> {
        @Override
        protected void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);

            if (!empty && item != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(UserUI.class.getResource("view/Preview.fxml"));

                try {
                    Pane preview = loader.load();
                    PreviewController controller = loader.getController();
                    setGraphic(preview);
                    controller.setUsername(item.getUsername());
                    controller.setRole(item.getRole());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }




    }


    /**
     * 当点击左栏的一个单元，在右栏展示详细信息界面
     * @param user
     */
    private void showUserDetails(User user) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserUI.class.getResource("view/DetailsLayout.fxml"));
            detailsLayout = (BorderPane)loader.load();

            userLayout.setCenter(detailsLayout);
            detailsLayoutController = loader.getController();
            detailsLayoutController.setUserLayoutController(this);
            detailsLayoutController.showUserDetails(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加系统用户
     * @param actionEvent
     */
    public void addUser(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserUI.class.getResource("view/DetailsLayout.fxml"));
            detailsLayout = (BorderPane)loader.load();

            userLayout.setCenter(detailsLayout);

            detailsLayoutController = loader.getController();
            detailsLayoutController.setUserLayoutController(this);
            detailsLayoutController.add();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除系统用户
     * @param actionEvent
     */
    public void delUser(ActionEvent actionEvent) {
        Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION);
        delAlert.setTitle("删除系统用户");
        delAlert.setHeaderText(null);
        delAlert.setContentText("确定删除？");

        Optional<ButtonType> result = delAlert.showAndWait();
        if (result.get() == ButtonType.OK){
            //用户确定删除
            ObservableList<Integer> list=  userList.getSelectionModel().getSelectedIndices();
            if(userDataHelper.delUser(list) == ResultMessage.SUCCESS){
                //服务器删除成功
                refresh();
                if(userDataHelper.getUserList().size()==0)
                    detailsLayoutController.hideDetailsLayout();
            }else{
                //服务器删除失败
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("删除失败");
                alert.setHeaderText(null);
                alert.setContentText("尝试与服务器同步qwq");

                alert.showAndWait();

                userDataHelper.syncUserList();
                refresh();
                selectFirst();
            }
        } else {
            //用户取消删除
        }
    }

    /**
     * 搜索银行账户
     * @param actionEvent
     */
    public void searchUser(ActionEvent actionEvent) {
        String str = searchTextField.getText();
        if (str != null && !str.equals("")) {
            if(userDataHelper.searchUser(str) == ResultMessage.SUCCESS){
                refresh();
                selectFirst();
                cancelSearch.setVisible(true);
            }else{
                //搜索失败，提示服务器有问题，请稍后再试
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("搜索失败");
                alert.setHeaderText(null);
                alert.setContentText("服务器可能挂了，请稍后再试qwq");
                alert.showAndWait();
            }
        }
    }

    /**
     * 返回搜索
     * @param mouseEvent
     */
    public void searchBack(MouseEvent mouseEvent) {
        cancelSearch.setVisible(false);
        userDataHelper.searchBack();
        refresh();
        selectFirst();
    }


    /**
     * 强行刷新，不刷新不好使
     */
    public void refresh(){

        userList.setCellFactory((ListView<User> CellData) -> new UserPreview());

        userList.setItems(userDataHelper.getUserList());
    }

    /**
     * 选中末尾
     */
    public void selectLast(){
        userList.getSelectionModel().selectLast();
    }

    /**
     * 选中开头
     */
    public void selectFirst(){
        userList.getSelectionModel().selectFirst();
    }

    /**
     * 选中某个银行账户
     * @param obj
     */
    public void select(User obj){
        userList.getSelectionModel().select(obj);
    }

}
