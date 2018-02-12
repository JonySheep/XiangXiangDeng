package BusinessLogic.MessageBL;

import BusinessLogicService.MessageInterface;
import Util.Message;
import Util.ResultMessage;

import java.util.ArrayList;

public class MessageHandler implements MessageInterface {

    /**
     * 有新信息返回true,没有新信息返回false
     *
     * @return
     */
    @Override
    public boolean hasNewMessage() {
        /*
         * 链接Data
         */
        return false;
    }

    /**
     * 用UserId查询他的信箱
     *
     * @param Id
     * @return
     */
    @Override
    public ArrayList<Message> getMessages(String Id) {
        /*
         * 链接Data
         */
        return null;
    }

    /**
     * 删除一个用户的信箱中的一条信息
     *
     * @param userId
     * @param msg
     * @return
     */
    @Override
    public ResultMessage delete(String userId, Message msg) {
        /*
         * 链接Data
         */
        return ResultMessage.SUCCESS;
    }
}
