package VO;

import PO.GoodItemForGoodDocPO;

/**
 * 库存类单据中的商品属性的VO.
 */
public class GoodItemForGoodDocVO {

    private String id;
    private String name;
    private String type;

    private int expected;
    private int change;

    public GoodItemForGoodDocVO(String id, String name, String type, int expected, int change) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.expected = expected;
        this.change = change;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getExpected() {
        return expected;
    }

    public int getChange() {
        return change;
    }

    public GoodItemForGoodDocPO toPO() {
        return new GoodItemForGoodDocPO(id, change);
    }
}
