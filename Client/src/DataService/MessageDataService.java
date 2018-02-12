package DataService;

import Util.Message;
import Util.ResultMessage;

import java.rmi.Remote;
import java.util.ArrayList;

public interface MessageDataService extends Remote {

    /**
     * 有新信息返回true,没有新信息返回false
     * @return
     */
    boolean hasNewMessage();

    /**
     * 用UserId查询他的信箱
     * @param userId
     * @return
     */
    ArrayList<Message> getMessages(String userId);

    /**
     * 删除一个用户的信箱中的一条信息
     * @param msg
     * @return
     */
    ResultMessage delete(String userId, Message msg);
}
