package VO;

/**
 * 单据中的用户的VO.
 */
public class UserForDocVO {

    private String id;
    private String name;

    public UserForDocVO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
