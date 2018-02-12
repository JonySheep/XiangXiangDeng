package Data;

import PO.CustomerPO;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomerDataImplTest {

    CustomerDataImpl customerData = new CustomerDataImpl();
    @Test
    public void insert() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void searchCustomers() throws Exception {
        String input = "猪";
        ArrayList<CustomerPO> customerPOS = customerData.searchCustomers(input);
       int len = customerPOS.size();
       for (int i=0;i<len;i++){
           System.out.println(customerPOS.get(i).getCustomerId());
       }

    }

    @Test
    public void searchByID() throws Exception {
        String id = "SAL000001";
        CustomerPO customerPO = customerData.searchByID(id);
        ArrayList<CustomerPO> customerPOS = null;
        customerPOS.add(customerPO);
        System.out.println(customerPOS);
    }

    @Test
    public void getSupplier() throws Exception {
    }

    @Test
    public void getSaler() throws Exception {
    }

    @Test
    public void getCustomer() throws Exception {
    }

    @Test
    public void disguiseDeleteCustomer() throws Exception {
    }

}