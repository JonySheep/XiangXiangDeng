package VO;

import BusinessLogic.CustomerBL.Customer;
import Util.CustomerKinds;
import Util.EmptyValue;
import Util.StrategyType;
import javafx.beans.property.*;

/**
 * Created by Administrator on 2017/10/22.
 */
public class CustomerVO {

    private String customerID=EmptyValue.getString();
    private String customerName=EmptyValue.getString();
    CustomerKinds kind=CustomerKinds.EMPTY;
    private int level=EmptyValue.getInteger();
    private String telNumber=EmptyValue.getString();
    private String address=EmptyValue.getString();
    private String zipCode=EmptyValue.getString();
    private String email=EmptyValue.getString();
    private double quata=EmptyValue.getDouble();//额度
    private double receivable=EmptyValue.getDouble(); //应收
    private double payable=EmptyValue.getDouble(); //应付
    private String clerk=EmptyValue.getString();
    private String comment=EmptyValue.getString();


    public CustomerVO(){

        //默认构造方法
    }

    public CustomerVO(CustomerKinds type){
        //空的CustomerVO
        this.customerName="";
        this.kind = type;
        this.level =0;
        this.telNumber ="";
        this.address = "";
        this.zipCode = "";
        this.email = "";
        this.quata = 0.0;
        this.receivable = 0.0;
        this.payable = 0.0;
        this.clerk ="";
        this.comment="";
    }


    /**
     * 没有ID的构造方法，用于新建客户，ID由传入Data层后自动生成
     * @param customerName
     * @param kind
     * @param level
     * @param telNumber
     * @param address
     * @param zipCode
     * @param email
     * @param quata
     * @param receivable
     * @param payable
     * @param clerk
     * @param comment
     */
    public CustomerVO(String customerName, CustomerKinds kind, int level, String telNumber, String address, String zipCode, String email, double quata, double receivable, double payable, String clerk, String comment) {
        this.customerName = customerName;
        this.kind = kind;
        this.level = level;
        this.telNumber = telNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.email = email;
        this.quata = quata;
        this.receivable = receivable;
        this.payable = payable;
        this.clerk = clerk;
        this.comment = comment;
    }


    /**
     * 带ID的构造方法
     * @param customerID
     * @param customerName
     * @param role
     * @param level
     * @param telNumber
     * @param address
     * @param zipCode
     * @param email
     * @param quata
     * @param receivable
     * @param payable
     * @param clerk
     * @param comment
     */
    public CustomerVO(String customerID, String customerName, CustomerKinds role, int level, String telNumber, String address, String zipCode, String email, double quata, double receivable, double payable, String clerk, String comment) {
        this.customerID=customerID;
        this.customerName=customerName;
        this.kind = role;
        this.level =level;
        this.telNumber =telNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.email = email;
        this.quata = quata;
        this.receivable = receivable;
        this.payable = payable;
        this.clerk =clerk;
        this.comment=comment;
    }

    //get methods

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public CustomerKinds getKind() {
        return kind;
    }

    public int getLevel() {
        return level;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getEmail() {
        return email;
    }

    public double getQuata() {
        return quata;
    }

    public double getReceivable() {
        return receivable;
    }

    public double getPayable() {
        return payable;
    }

    public String getClerk() {
        return clerk;
    }

    public String getComment() {
        return comment;
    }

    //setter methods
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setKind(CustomerKinds role) {
        this.kind = role;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQuata(double quata) {
        this.quata = quata;
    }

    public void setReceivable(double receivable) {
        this.receivable = receivable;
    }

    public void setPayable(double payable) {
        this.payable = payable;
    }

    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
