package Presentation.AccountUI.view;

import Presentation.AccountUI.AccountDataHelper;
import Presentation.AccountUI.AccountUI;
import Presentation.AccountUI.model.Account;
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
import java.util.Objects;
import java.util.Optional;

/**
 * AccountLayoutController和DetailsLayoutController相互持有引用
 */
public class AccountLayoutController {

    private AccountDataHelper accountDataHelper;
    private DetailsLayoutController detailsLayoutController;
    private BorderPane detailsLayout;

    @FXML
    private ImageView cancelSearch;

    @FXML
    private BorderPane accountLayout;

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<Account> bankAccountList;

    public AccountLayoutController() {
        this.accountDataHelper = AccountDataHelper.getInstance();
    }

    @FXML
    private void initialize() {
        bankAccountList.setCellFactory((ListView<Account> CellData) -> new AccountPreview());

        bankAccountList.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue)->showAccountDetails(newValue)
        );

        bankAccountList.setItems(accountDataHelper.getAccountList());

    }


    /**
     * 左栏草稿箱单元的类
     */
    private class AccountPreview extends ListCell<Account> {
        @Override
        protected void updateItem(Account item, boolean empty) {
            super.updateItem(item, empty);

            if (!empty && item != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AccountUI.class.getResource("view/Preview.fxml"));

                try {
                    Pane preview = loader.load();
                    PreviewController controller = loader.getController();
                    setGraphic(preview);
                    controller.setCardName(item.getCardName());
                    controller.setCardNumber(item.getCardNumber());
                    controller.setRemark(item.getRemark());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }




    }


    /**
     * 当点击左栏的一个单元，在右栏展示详细信息界面
     * @param account
     */
    private void showAccountDetails(Account account) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AccountUI.class.getResource("view/DetailsLayout.fxml"));
            detailsLayout = (BorderPane)loader.load();

            accountLayout.setCenter(detailsLayout);
            detailsLayoutController = loader.getController();
            detailsLayoutController.setAccountLayoutController(this);
            detailsLayoutController.showAccountDetails(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加银行账户
     * @param actionEvent
     */
    public void addAccount(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AccountUI.class.getResource("view/DetailsLayout.fxml"));
            detailsLayout = (BorderPane)loader.load();

            accountLayout.setCenter(detailsLayout);

            detailsLayoutController = loader.getController();
            detailsLayoutController.setAccountLayoutController(this);
            detailsLayoutController.add();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除银行账户
     * @param actionEvent
     */
    public void delAccount(ActionEvent actionEvent) {
        Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION);
        delAlert.setTitle("删除银行账户");
        delAlert.setHeaderText(null);
        delAlert.setContentText("确定删除？");

        Optional<ButtonType> result = delAlert.showAndWait();
        if (result.get() == ButtonType.OK){
            //用户确定删除
            ObservableList<Integer> list=  bankAccountList.getSelectionModel().getSelectedIndices();
            if(accountDataHelper.delAccount(list) == ResultMessage.SUCCESS){
                //服务器删除成功
                refresh();
                if(accountDataHelper.getAccountList().size()==0)
                    detailsLayoutController.hideDetailsLayout();
            }else{
                //服务器删除失败
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("删除失败");
                alert.setHeaderText(null);
                alert.setContentText("尝试与服务器同步qwq");

                alert.showAndWait();

                accountDataHelper.syncAccountList();
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
    public void searchAccount(ActionEvent actionEvent) {
        String str = searchTextField.getText();
        if (str != null && !Objects.equals(str, "")) {
            if(accountDataHelper.searchAccount(str) == ResultMessage.SUCCESS){
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
        accountDataHelper.searchBack();
        refresh();
        selectFirst();
    }


    /**
     * 强行刷新，不刷新不好使
     */
    public void refresh(){

        bankAccountList.setCellFactory((ListView<Account> CellData) -> new AccountPreview());

        bankAccountList.setItems(accountDataHelper.getAccountList());
    }

    /**
     * 选中末尾
     */
    public void selectLast(){
        bankAccountList.getSelectionModel().selectLast();
    }

    /**
     * 选中开头
     */
    public void selectFirst(){
        bankAccountList.getSelectionModel().selectFirst();
    }

    /**
     * 选中某个银行账户
     * @param obj
     */
    public void select(Account obj){
        bankAccountList.getSelectionModel().select(obj);

    }

}
