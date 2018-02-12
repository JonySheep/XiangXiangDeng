package Presentation.AccountUI.view;

import Presentation.AccountUI.AccountDataHelper;
import Presentation.AccountUI.model.Account;
import Util.EmptyValue;
import Util.ResultMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * AccountLayoutController和DetailsLayoutController相互持有引用
 */
public class DetailsLayoutController {

    private Account selectedAccount = null;
    private AccountDataHelper accountDataHelper = AccountDataHelper.getInstance();
    private AccountLayoutController accountLayoutController = null;

    @FXML
    private BorderPane detailsLayout;

    @FXML
    private HBox originalButtonSet;

    @FXML
    private HBox editButtonSet;


    @FXML
    private Label title;

    @FXML
    private Text cardName;

    @FXML
    private Text cardNumber;

    @FXML
    private Text balance;

    @FXML
    private Text remark;


    @FXML
    private TextField cardNameField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField balanceField;

    @FXML
    private TextField remarkField;


    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getCardName() {
        return cardName.getText();
    }

    public void setCardName(String cardName) {
        this.cardName.setText(cardName);
    }

    public String getCardNumber() {
        return cardNumber.getText();
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.setText(cardNumber);
    }

    public String getBalance() {
        return balance.getText();
    }

    public void setBalance(String balance) {
        this.balance.setText(balance);
    }

    public String getRemark() {
        return remark.getText();
    }

    public void setRemark(String remark) {
        this.remark.setText(remark);
    }

    public void setAccountLayoutController(AccountLayoutController accountLayoutController){
        this.accountLayoutController = accountLayoutController;
    }


    /**
     * 左栏点击一个草稿单元
     * @param account
     */
    public void showAccountDetails(Account account) {
        if(account == null)
            return;//防止更新ListView的时候由于监听器的关系造成NullPointerException
        setTitle(account.getCardName());
        setCardName(account.getCardName());
        setCardNumber(account.getCardNumber());
        setBalance(""+account.getBalance());
        setRemark(account.getRemark());
        this.selectedAccount = account;
    }

    /**
     * 在左栏点击新建
     */
    public void add(){
        setTitle("请输入银行账户信息");
        cardNameField.setVisible(true);
        remarkField.setVisible(true);
        cardNumberField.setVisible(true);
        balanceField.setVisible(true);

        originalButtonSet.setVisible(false);
        editButtonSet.setVisible(true);

    }

    /**
     * 点击编辑按钮
     * @param actionEvent
     */
    public void edit(ActionEvent actionEvent) {

        cardNameField.setText(cardName.getText());
        remarkField.setText(remark.getText());
        cardNameField.setVisible(true);
        remarkField.setVisible(true);

        originalButtonSet.setVisible(false);
        editButtonSet.setVisible(true);

    }

    /**
     * 点击取消按钮
     * @param actionEvent
     */
    public void cancel(ActionEvent actionEvent) {
        if(selectedAccount != null){//如果是编辑之后的取消
            cardNameField.setVisible(false);
            remarkField.setVisible(false);
            cardNameField.setText("");
            remarkField.setText("");

            originalButtonSet.setVisible(true);
            editButtonSet.setVisible(false);
        }else{//如果是新建之后的取消
            cardNameField.setVisible(false);
            cardNumberField.setVisible(false);
            balanceField.setVisible(false);
            remarkField.setVisible(false);

            cardNameField.setText("");
            remarkField.setText("");
            balanceField.setText("");
            remarkField.setText("");
            detailsLayout.setVisible(false);
        }

    }

    /**
     * 点击保存按钮
     * @param actionEvent
     */
    public void save(ActionEvent actionEvent) {

        if(selectedAccount != null){//如果是编辑之后的保存
            String cardName = cardNameField.getText();
            String remark = remarkField.getText();

            //控制修改时的数据
            if(cardName.equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("输入错误");
                alert.setHeaderText(null);
                alert.setContentText("银行账户名称不能为空，请重新设置");

                alert.showAndWait();
                return;
            }

            //辨别改动
            Account account = new Account(selectedAccount.getCardNumber(),EmptyValue.getString(),EmptyValue.getDouble(),EmptyValue.getString());
            if(cardName != selectedAccount.getCardName()){
                account.setCardName(cardName);
            }
            if(remark != selectedAccount.getRemark()){
                account.setRemark(remark);
            }

            ResultMessage rm = accountDataHelper.updateAccount(account);
            if(rm == ResultMessage.SUCCESS){
                selectedAccount.setCardName(cardName);
                selectedAccount.setRemark(remark);
                setTitle(cardName);
                setCardName(cardName);
                setRemark(remark);

                cardNameField.setVisible(false);
                remarkField.setVisible(false);
                cardNameField.setText("");
                remarkField.setText("");

                originalButtonSet.setVisible(true);
                editButtonSet.setVisible(false);
                accountLayoutController.refresh();
                accountLayoutController.select(selectedAccount);
            }else{
                //当修改不成功，应该是修改的商品被删掉了，应该与服务器同步，并弹窗提示用户
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("编辑失败");
                alert.setHeaderText(null);
                alert.setContentText("尝试与服务器同步qwq");

                alert.showAndWait();

                accountDataHelper.syncAccountList();
                accountLayoutController.refresh();
                accountLayoutController.selectFirst();
            }
        }else{//如果是新建之后的保存
            String cardName = cardNameField.getText();
            String cardNumber = cardNumberField.getText();
            Double balance = balanceField.getText().equals("")?0:Double.valueOf(balanceField.getText());
            String remark = remarkField.getText();

            if(cardName.equals("") || cardNumber.equals("") || balance<0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("输入错误");
                alert.setHeaderText(null);
                alert.setContentText("您设置的银行账户名称和卡号为空，或余额小于零，请重新设置");

                alert.showAndWait();
                return;
            }

            if(accountDataHelper.addAccount(cardNumber,cardName,balance,remark) == ResultMessage.SUCCESS){
                accountLayoutController.refresh();
                accountLayoutController.selectLast();
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
     * 当银行账户列表为空时，调用该方法把默认的DetailsLayout隐藏
     */
    public void hideDetailsLayout(){
        detailsLayout.setVisible(false);
    }
}
