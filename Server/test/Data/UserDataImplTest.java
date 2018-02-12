package Data;

import PO.UserPO;
import Util.ResultMessage;
import Util.UserLevel;
import Util.UserRole;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDataImplTest {

    UserDataImpl user = new UserDataImpl();
    UserPO userPO1 = new UserPO("00000012","123456","xiao ming",UserLevel.FINALLEVEL, UserRole.FINANCIAL);


    //新增测试成功
    @Test
    public void insert() throws Exception {
        user.insert(userPO1);
    }


    //登录测试完成
    @Test
    public void login() throws Exception {
        String userID = "00000013";
        String password = "1234567";
        System.out.println(user.login(userID,password));

    }

    //登出测试成功
    @Test
    public void logout() throws Exception {
        String id = "00000010";
        System.out.println(user.logout(id));
    }

    //修改密码测试成功
    @Test
    public void resetPassword() throws Exception {
        String id = "00000012";
        String oldpassword = "njueducn123";
        String newpassword = "njueducn";
        System.out.println(user.resetPassword(id,oldpassword,newpassword));
    }

    //获得全部user测试成功
    @Test
    public void getAllUser() throws Exception {
        System.out.println(user.getAllUser());
    }

    //伪删除测试成功
    @Test
    public void disguiseDelete() throws Exception {
        String id = "000006";
        System.out.println(user.disguiseDelete(id));
    }

    //模糊查找测试成功
    @Test
    public void findUsers() throws Exception {
        String str = "三";
        System.out.println(user.findUsers(str));
    }

    //更新测试成功
    @Test
    public void update() throws Exception {
        ResultMessage re = user.update(userPO1);
        System.out.println(re);

    }

    @Test
    public void find() throws Exception {
    }

}