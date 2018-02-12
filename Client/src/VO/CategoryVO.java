package VO;

import PO.CategoryPO;
import Util.EmptyValue;
import Util.GoodTreeNodeType;

/**
 * 界面还没定..暂且让它和CategoryPO长得差不多好了
 */
public class CategoryVO extends GoodTreeNodeVO {
    private String id = EmptyValue.getString();
    private String name = EmptyValue.getString();
    private String fatherCategoryId = EmptyValue.getString();

    public CategoryVO(String id, String name, String fatherCategoryId) {
        super.setNodeType(GoodTreeNodeType.CATEGORY);
        this.id = id;
        this.name = name;
        this.fatherCategoryId = fatherCategoryId;
    }

    public CategoryVO(CategoryPO categoryPO){
        super.setNodeType(GoodTreeNodeType.CATEGORY);
        this.id = categoryPO.getId();
        this.name = categoryPO.getName();
        this.fatherCategoryId = categoryPO.getFatherCategoryId();
    }

    public CategoryVO() {
    }

    public boolean equals(CategoryVO vo) {
        if(vo.getId() == this.id && vo.getName() == this.name && vo.fatherCategoryId == this.fatherCategoryId){
            return true;
        } else return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherCategoryId() {
        return fatherCategoryId;
    }

    public void setFatherCategoryId(String fatherCategoryId) {
        this.fatherCategoryId = fatherCategoryId;
    }
}
