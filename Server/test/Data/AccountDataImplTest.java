package Data;

import PO.AccountPO;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDataImplTest {

    AccountDataImpl account = new AccountDataImpl();
    @Test
    public void insert() throws Exception {
        AccountPO accountPO = new AccountPO("1","至尊卡",200000,"随便用");
        System.out.println(account.insert(accountPO));
    }

    @Test
    public void update() throws Exception {
        AccountPO accountPO = new AccountPO("1","至尊卡",0,"不能随便用");
        System.out.println(account.update(accountPO));

    }

    @Test
    public void search() throws Exception {
    }

    @Test
    public void disguiseDeleteAccount() throws Exception {
        account.disguiseDeleteAccount("50024219900000");
    }

    @Test
    public void delete() throws Exception {
        AccountPO accountPO = new AccountPO("50024219900002","至尊卡",200000,"随便用");
        System.out.println(account.delete(accountPO));
    }

    @Test
    public void getInfo() throws Exception {
        System.out.println(account.getInfo("1").getNotes());
    }

}