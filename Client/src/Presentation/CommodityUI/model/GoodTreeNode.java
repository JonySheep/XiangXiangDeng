package Presentation.CommodityUI.model;

import Util.EmptyValue;
import Util.GoodTreeNodeType;

import java.util.ArrayList;

public abstract class GoodTreeNode {
    private GoodTreeNodeType nodeType = GoodTreeNodeType.EMPTY;
    private int index = EmptyValue.getInteger();
    private int fatherIndex = EmptyValue.getInteger();
    private ArrayList<Integer> sonIndexes = new ArrayList<>();

    public GoodTreeNodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(GoodTreeNodeType nodeType) {
        this.nodeType = nodeType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getFatherIndex() {
        return fatherIndex;
    }

    public void setFatherIndex(int fatherIndex) {
        this.fatherIndex = fatherIndex;
    }

    public ArrayList<Integer> getSonIndexes() {
        return sonIndexes;
    }

    public void setSonIndexes(ArrayList<Integer> sonIndexes) {
        this.sonIndexes = sonIndexes;
    }
}
