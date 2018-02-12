package BusinessLogic.BookBL;

import BusinessLogicService.BookBLService;
import Util.ResultMessage;
import VO.BookVO;

import javax.xml.crypto.Data;

public class BookControll implements BookBLService {

    /**
     *
     * @param bookvo 表示一个BookVO对象
     * @return 返回新增是否成功
     */
    @Override
    public ResultMessage addBook(BookVO bookvo) {
        return null;
    }

    /**
     *
     * @param data 表示日期
     * @return 返回对应的建账信息
     */
    @Override
    public BookVO readBook(Data data) {
        return null;
    }
}
