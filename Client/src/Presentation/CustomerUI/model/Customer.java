package Presentation.CustomerUI.model;

import Util.CustomerKinds;
import Util.EmptyValue;
import javafx.beans.property.*;

/**
 * Customer模型类
 * 用Property进行包装，可以保证界面的一致性
 *
 */
public class Customer {

    private final StringProperty customerID;
    private final StringProperty customerName;
    private CustomerKinds kind;
    private final IntegerProperty level;
    private final StringProperty telNumber;
    private final StringProperty address;
    private final StringProperty zipCode;
    private final StringProperty email;
    private final DoubleProperty quata; //应收额度
    private final DoubleProperty receivable;  //应收
    private final DoubleProperty payable; //应付
    private final StringProperty clerk;
    private final StringProperty comment;


    /**
     * 默认的构造方法，将所有都设为默认值
     */
    public Customer(){
        this.customerID=new SimpleStringProperty(EmptyValue.getString());
        this.customerName=new SimpleStringProperty(EmptyValue.getString());
        this.kind=CustomerKinds.EMPTY;
        this.level=new SimpleIntegerProperty(EmptyValue.getInteger());
        this.telNumber=new SimpleStringProperty(EmptyValue.getString());
        this.address=new SimpleStringProperty(EmptyValue.getString());
        this.zipCode=new SimpleStringProperty(EmptyValue.getString());
        this.email=new SimpleStringProperty(EmptyValue.getString());
        this.quata=new SimpleDoubleProperty(EmptyValue.getDouble());
        this.receivable=new SimpleDoubleProperty(EmptyValue.getDouble());
        this.payable=new SimpleDoubleProperty(EmptyValue.getDouble());
        this.clerk=new SimpleStringProperty(EmptyValue.getString());
        this.comment=new SimpleStringProperty(EmptyValue.getString());
    }


    /**
     * 供新增客户使用的构造方法，在DetailLayout中显示的数据都为空
     * @param kind
     */
    public Customer(CustomerKinds kind){
        this.customerID=new SimpleStringProperty("");
        this.customerName=new SimpleStringProperty("");
        this.kind=kind;
        this.level=new SimpleIntegerProperty(1);
        this.telNumber=new SimpleStringProperty("");
        this.address=new SimpleStringProperty("");
        this.zipCode=new SimpleStringProperty("");
        this.email=new SimpleStringProperty("");
        this.quata=new SimpleDoubleProperty(0.0);
        this.receivable=new SimpleDoubleProperty(0.0);
        this.payable=new SimpleDoubleProperty(0.0);
        this.clerk=new SimpleStringProperty("");
        this.comment=new SimpleStringProperty("");
    }


    /**
     * 带ID的构造方法
     * @param customerID 客户ID
     * @param customerName 客户名
     * @param kind 客户类型
     * @param telNumber 电话号码
     * @param address 地址
     * @param zipCode 邮编
     * @param email 邮箱
     * @param quata 应收额度
     * @param receivable 应收
     * @param payable 应付
     * @param clerk 操作员
     * @param comment 备注
     */
    public Customer(String customerID, String customerName, CustomerKinds kind,int level, String telNumber, String address, String zipCode, String email, Double quata, Double receivable, Double payable, String clerk, String comment) {

        this.customerID=new SimpleStringProperty(customerID);
        this.customerName=new SimpleStringProperty(customerName);
        this.kind = kind;
        this.level=new SimpleIntegerProperty(level);
        this.telNumber = new SimpleStringProperty(telNumber);
        this.address = new SimpleStringProperty(address);
        this.zipCode = new SimpleStringProperty(zipCode);
        this.email = new SimpleStringProperty(email);
        this.quata = new SimpleDoubleProperty(quata);
        this.receivable = new SimpleDoubleProperty(receivable);
        this.payable = new SimpleDoubleProperty(payable);
        this.clerk = new SimpleStringProperty(clerk);
        this.comment = new SimpleStringProperty(comment);
    }




    //getter

    public String getCustomerID() {
        return customerID.get();
    }

    public StringProperty customerIDProperty() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public CustomerKinds getKind() {
        return kind;
    }

    public String getTelNumber() {
        return telNumber.get();
    }

    public StringProperty telNumberProperty() {
        return telNumber;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public StringProperty zipCodeProperty() {
        return zipCode;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public double getQuata() {
        return quata.get();
    }

    public DoubleProperty quataProperty() {
        return quata;
    }

    public double getReceivable() {
        return receivable.get();
    }

    public DoubleProperty receivableProperty() {
        return receivable;
    }

    public double getPayable() {
        return payable.get();
    }

    public DoubleProperty payableProperty() {
        return payable;
    }

    public String getClerk() {
        return clerk.get();
    }

    public StringProperty clerkProperty() {
        return clerk;
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public int getLevel() {
        return level.get();
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    //setter

    public void setCustomerID(String customerID) {
        this.customerID.set(customerID);
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public void setCustomerKind(CustomerKinds kind){this.kind=kind; }

    public void setTelNumber(String telNumber) {
        this.telNumber.set(telNumber);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setQuata(double quata) {
        this.quata.set(quata);
    }

    public void setReceivable(double receivable) {
        this.receivable.set(receivable);
    }

    public void setPayable(double payable) {
        this.payable.set(payable);
    }

    public void setClerk(String clerk) {
        this.clerk.set(clerk);
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public void setLevel(int level) {
        this.level.set(level);
    }
}
