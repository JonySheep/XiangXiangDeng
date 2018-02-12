package BusinessLogicService;

import Util.ResultMessage;
import VO.BookVO;

import javax.xml.crypto.Data;

/**
 * Created by Jeven on 2017/10/22.
 */
public interface BookBLService
{
    /**
     *
     * @param bookvo 表示一个BookVO对象
     * @return 返回新增结果
     */
    ResultMessage addBook(BookVO bookvo);

    /**
     *
     * @param data 表示日期
     * @return 返回一个BookVO对象
     */
    BookVO readBook(Data data);
}
