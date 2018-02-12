package VO;

import Util.DocType;

/**
 * 所有单据类VO的父类.
 *
 * prKey：单据的唯一标识符
 * id：单据的编号（ID）
 * operator：单据操作员
 * type：单据具体类型（如：库存报溢单）
 */
public abstract class DocVO {

    private String prKey;
    private String id;
    private UserForDocVO operator;
    private DocType type;

    public DocVO(String prKey, String id, UserForDocVO operator, DocType type) {
        this.prKey = prKey;
        this.id = id;
        this.operator = operator;
        this.type = type;
    }

    public String getPrKey() {
        return prKey;
    }

    public String getId() {
        return id;
    }

    public UserForDocVO getOperator() {
        return operator;
    }

    public DocType getType() {
        return type;
    }

}
