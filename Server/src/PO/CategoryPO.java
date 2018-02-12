package PO;

import Util.EmptyValue;
import Util.GoodTreeNodeType;

import java.io.Serializable;

public class CategoryPO extends GoodTreeNodePO implements Serializable {

    private String id = EmptyValue.getString();
    private String name = EmptyValue.getString();
    private String fatherCategoryId = EmptyValue.getString();

    /**
     * CategoryPO的构造器
     * @param id 商品分类编号,
     * @param name  商品分类名称，格式类似 C00001;
     * @param fatherCategoryId 父分类编号
     */
    public CategoryPO(String id, String name, String fatherCategoryId){
        super.setNodeType(GoodTreeNodeType.CATEGORY);

        this.id = id;
        this.name = name;
        this.fatherCategoryId = fatherCategoryId;
    }

    /**
     * CategoryPO的构造器
     * @param name 商品分类名称
     * @param fatherCategoryId 父分类编号
     */
    public CategoryPO(String name, String fatherCategoryId){
        super.setNodeType(GoodTreeNodeType.CATEGORY);

        this.name = name;
        this.fatherCategoryId = fatherCategoryId;
    }

    public CategoryPO() {
    }

    /*
         * 以下是getter
         */
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFatherCategoryId() {
        return fatherCategoryId;
    }
}
