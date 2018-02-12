package Presentation.CustomerUI.view;

import Presentation.CustomerUI.CustomerDataHelper;
import Presentation.CustomerUI.model.Customer;
import Util.CustomerKinds;

import Util.ResultMessage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DetailLayoutController {

    private Customer currentCus=null;
    private Customer savedCus;
    private CustomerLayOutController LayoutController=null;

    private String currentLevel;
    private Stage primaryStage;

    @FXML
    private Label titleName;

    @FXML
    private Label titleLevel;

    @FXML
    private TextArea name;


    @FXML
    private ComboBox<String> kind;

    @FXML
    private ComboBox<String> level;

    @FXML
    private TextArea telephone;

    @FXML
    private TextArea address;

    @FXML
    private TextArea zipcode;

    @FXML
    private TextArea email;

    @FXML
    private TextArea quato;

    @FXML
    private TextArea receivable;

    @FXML
    private TextArea payable;

    @FXML
    private TextArea clerk;

    @FXML
    private TextField comment;

    @FXML
    private Button editButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancleButton;

    @FXML
    private BorderPane detaiLayout;


    public String getName() {
        return name.getText();
    }

    public int getLevel() {
        return Integer.parseInt(level.getValue());
    }

    public String getComment() {
        return comment.getText();
    }

    public void setTitleName(String titleName) {
        this.titleName.setText(titleName);
    }

    public void setTitleLevel(String titleLevel) {
        this.titleLevel.setText(titleLevel);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setKind(CustomerKinds kind) {
        if(kind==CustomerKinds.SALER){
            this.kind.setValue("销售商");
        }
        else if(kind==CustomerKinds.SUPPLIER){
            this.kind.setValue("供应商");
        }
    }

    public void setLevel(int level) {
        this.level.setValue(String.valueOf(level));
    }

    public void setTelephone(String telephone) {
        this.telephone .setText(telephone);
    }

    public void setAddress(String address) {
        this.address.setText(address);
    }

    public void setZipcode(String zipcode) {
        this.zipcode .setText(zipcode);
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public void setQuato(String quato) {
        this.quato.setText(quato);
    }

    public void setReceivable(String receivable) {
        this.receivable.setText(receivable);
    }

    public void setPayable(String payable) {
        this.payable.setText(payable);
    }

    public void setClerk(String clerk) {
        this.clerk.setText(clerk);
    }

    public void setComment(String comment) {
        this.comment.setText(comment);
    }

    public void setLayoutController(CustomerLayOutController controller){
        LayoutController=controller;
    }


    /**
     * 显示客户具体信息的方法
     * @param Cus
     */
    public void showCusDetail(Customer Cus){

        if(Cus == null)
            return;//防止更新ListView的时候由于监听器的关系造成NullPointerException

        //set title
        setTitleName(Cus.getCustomerName());
        setTitleLevel("VIP"+String.valueOf(Cus.getLevel()));

        //set Text
        setName(Cus.getCustomerName());
        setKind(Cus.getKind());
        setLevel(Cus.getLevel());
        setTelephone(Cus.getTelNumber());
        setAddress(Cus.getAddress());
        setZipcode(Cus.getZipCode());
        setEmail(Cus.getEmail());
        setQuato(String.valueOf(Cus.getQuata()));
        setReceivable(String.valueOf(Cus.getReceivable()));
        setPayable(String.valueOf(Cus.getPayable()));
        setClerk(Cus.getClerk());
        setComment(Cus.getComment());

        setReadMode();

        this.currentCus=Cus;
    }

    private void setReadMode(){

        //保存、取消按钮设置为不可见
        saveButton.setVisible(false);
        cancleButton.setVisible(false);

        //编辑按钮设置为可见
        editButton.setVisible(true);

        //属性设置为只读
        name.setEditable(false);
        kind.setEditable(false);
        level.setEditable(false);
        telephone.setEditable(false);
        address.setEditable(false);
        zipcode.setEditable(false);
        email.setEditable(false);
        quato.setEditable(false);
        receivable.setEditable(false);  //应收无法修改
        payable.setEditable(false);  //应付无法修改
        clerk.setEditable(false);
        comment.setEditable(false);
    }

    public void setWriteMode(){
        //显示保存、取消按钮,不显示编辑按钮
        saveButton.setVisible(true);
        cancleButton.setVisible(true);
        editButton.setVisible(false);

        //属性设置为可改
        name.setEditable(true);
        kind.setEditable(true);
        level.setEditable(true);
        telephone.setEditable(true);
        address.setEditable(true);
        zipcode.setEditable(true);
        email.setEditable(true);
        quato.setEditable(true);
        clerk.setEditable(false);
        comment.setEditable(true);


        kind.setItems(FXCollections.observableArrayList("供应商","销售商"));
        level.setItems(FXCollections.observableArrayList("1","2","3","4","5"));
    }


    /**
     * 编辑按钮的响应方法
     */
    @FXML
    private void Edit(){
        setWriteMode();
    }



    /**
     * 取消保存按钮的响应方法
     */
    @FXML
    private void Cancle(){

        //返回修改前状态，不保存修改
        //显示编辑按钮，不显示取消、保存按钮
        setReadMode();
    }


    /**
     * 保存按钮的响应方法
     */
    @FXML
    public void Save(){


        /**
         * 若是新增的保存
         */
        if(currentCus.getCustomerName().equals("")){

            ResultMessage isSuccess=LayoutController.getDataHelper().addCustomer(name.getText(),currentCus.getKind(),Integer.parseInt(level.getSelectionModel().getSelectedItem().toString()),
                    telephone.getText(),address.getText(),zipcode.getText(), email.getText(),Double.parseDouble(quato.getText()),Double.parseDouble(receivable.getText()),Double.parseDouble(payable.getText()),
                    comment.getText());

            if(isSuccess==ResultMessage.SUCCESS){
                //新增客户成功，刷新列表，并检查
                LayoutController.refresh();
                LayoutController.selectLast();
            }

        }

        /**
         * 若是编辑的保存，进行比较
         */
        else{
            Customer savedCustomer=SavedCus(currentCus);
            this.savedCus=savedCustomer;

            if(LayoutController.getDataHelper().modifyCustomer(savedCus)==ResultMessage.SUCCESS){
                LayoutController.refresh();
            }
        }

        setReadMode();
    }


    /**
     *
     * @param oldCus
     * @return
     */
    private Customer SavedCus(Customer oldCus){
        Customer Cus=new Customer();

        String newName=name.getText();
        String newKind=kind.getValue();
        int newLevel=Integer.parseInt(level.getSelectionModel().getSelectedItem().toString());
        String newTelphone=telephone.getText();
        String newAddress=address.getText();
        String newZipcode=zipcode.getText();
        String newEmail=email.getText();
        double newQuato=Double.parseDouble(quato.getText());
        String newClerk=clerk.getText();
        String newComment=comment.getText();


        //ID
        Cus.setCustomerID(oldCus.getCustomerID());

        //对比得到经过改动的属性部分
        //Name
        if(!oldCus.getCustomerName().equals(newName)){
            Cus.setCustomerName(newName);
        }

        //Kind
        CustomerKinds Kind;
        if(newKind.equals("供应商")){
            Kind=CustomerKinds.SUPPLIER;
        }
        else{
            Kind=CustomerKinds.SALER;
        }
        if(oldCus.getKind()!=Kind){
            Cus.setCustomerKind(Kind);
        }
        setKind(Kind);

        //Level
        if(oldCus.getLevel()!=newLevel){
            Cus.setLevel(newLevel);
        }
        setLevel(newLevel);

        //Address
        if(!oldCus.getAddress().equals(newAddress)){
            Cus.setAddress(newAddress);
        }

        //Telephone
        if(!oldCus.getTelNumber().equals(newTelphone)){
            Cus.setTelNumber(newTelphone);
        }

        //Zipcode
        if(!oldCus.getZipCode().equals(newZipcode)){
            Cus.setZipCode(newZipcode);
        }

        //Email
        if(!oldCus.getEmail().equals(newEmail)){
            Cus.setEmail(newEmail);
        }

        //Quato
        if(oldCus.getQuata()!=newQuato){
            Cus.setQuata(newQuato);
        }

        //Clerk
        if(!oldCus.getClerk().equals(newClerk)){
            Cus.setClerk(newClerk);
        }

        //Comment
        if(newComment!=null&&!oldCus.getComment().equals(newComment)){
            Cus.setComment(newComment);
        }

        return Cus;
    }


    /**
     *得到修改后的Customer
     * @return
     */
    public Customer getSavedCus(){
        return savedCus;
    }



    /**
     * 当删除掉全部时，隐藏掉detail界面
     */
    public void hideDetailLayout(){
        detaiLayout.setVisible(false);
    }

}

