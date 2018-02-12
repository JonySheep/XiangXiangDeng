package VO;

/**
 * 销售类、进货类、财务类单据中的客户的VO.
 */
public class CustomerForDocVO {

    private String id;
    private String name;
    private int level;

    public CustomerForDocVO(String id, String name,int level) {
        this.id = id;
        this.name = name;
        this.level=level;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
		return level;
	}
}
