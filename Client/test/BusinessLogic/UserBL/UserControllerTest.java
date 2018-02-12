package BusinessLogic.UserBL;

import Rmi.RemoteHelper;
import Util.UserLevel;
import Util.UserRole;
import VO.UserVO;
import org.junit.Test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class UserControllerTest {

    private RemoteHelper remoteHelper;

    UserController userController = new UserController();
    UserVO userVO1 = new UserVO("","123456","三胖", UserLevel.GENERAL, UserRole.IMPORT);
    @Test
    public void add() throws Exception {
        linkToServer();
        System.out.println(userController.add(userVO1));

    }

    @Test
    public void randomSearch() throws Exception {
    }

    @Test
    public void searchByID() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void getAllUser() throws Exception {
    }

    @Test
    public void login() throws Exception {
    }

    @Test
    public void logout() throws Exception {
    }

    @Test
    public void reset() throws Exception {
    }

    @Test
    public void getCurrentUser() throws Exception {
    }


    /**
     * 链接服务器
     */
    private void linkToServer() {
        try {
            remoteHelper = RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://127.0.0.1:8887/DataRemoteObject"));
            System.out.println("linked");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}