package PO;
import Util.CustomerKinds;

import java.io.Serializable;

/**
 * Created by Jeven on 2017/10/22.
 */
public class CustomerPO implements Serializable {
    String customerID;
    String customerName;
    CustomerKinds role;
    int level;
    String telNumber;
    String address;
    String zipCode;
    String email;
    double quata; //应收额度
    double receivable; //应收
    double payable; //应付
    String clerk;
    String comment;

    public CustomerPO(String customerID, String customerName, CustomerKinds role, int level, String telNumber, String address, String zipCode, String email, double quata, double receivable, double payable, String clerk, String comment)
    {
        this.customerID = customerID;
        this.customerName = customerName;
        this.role = role;
        this.level = level;
        this.telNumber =telNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.email = email;
        this.quata = quata;
        this.receivable = receivable;
        this.payable = payable;
        this.clerk = clerk;
        this.comment=comment;
    }
    public String getCustomerId()
    {
        return customerID;
    }
    public String getCustomerName()
    {
        return customerName;
    }
    public CustomerKinds getCustomerKinds()
    {
        return role;
    }
    public int getLevel()
    {
        return level;
    }
    public String getTelNumber()
    {
        return telNumber;
    }
    public String getAddress()
    {
        return address;
    }
    public String getZipCode()
    {
        return zipCode;
    }
    public String getEmail()
    {
        return email;
    }
    public double getQuata()
    {
        return quata;
    }
    public double getReceivable()
    {
        return receivable;
    }
    public double getPayable()
    {
        return payable;
    }
    public String getClerk()
    {
        return clerk;
    }

    public String getComment() {
        return comment;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setRole(CustomerKinds role) {
        this.role = role;
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
