package Presentation.CommodityUI.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category extends GoodTreeNode{

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty fatherCategoryId;

    public Category(String id, String name, String fatherCategoryId) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.fatherCategoryId = new SimpleStringProperty(fatherCategoryId);
    }

    public String getId() {
        return id.get();
    }


    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFatherCategoryId() {
        return fatherCategoryId.get();
    }

    public void setFatherCategoryId(String fatherCategoryId) {
        this.fatherCategoryId.set(fatherCategoryId);
    }
}
