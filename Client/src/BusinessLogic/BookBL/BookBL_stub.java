package BusinessLogic.BookBL;

import BusinessLogicService.BookBLService;
import Util.ResultMessage;
import VO.BookVO;

import javax.xml.crypto.Data;

/**
 * Created by Administrator on 2017/10/23.
 */
public class BookBL_stub implements BookBLService {
    @Override
    public ResultMessage addBook(BookVO bookvo) {
        return null;
    }

    @Override
    public BookVO readBook(Data data) {
        return null;
    }
}
