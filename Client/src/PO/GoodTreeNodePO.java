package PO;

import Util.EmptyValue;
import Util.GoodTreeNodeType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jason on 2017/11/10.
 */
public abstract class GoodTreeNodePO implements Serializable {
    /**
     * nodeType 指示这个节点是CategoryPO还是GoodPO
     * index 指示这个节点在GoodTreePO中NodeList里的位置
     * fatherIndex 指示这个节点的父节点在GoodTreePO中NodeList里的位置
     * sonIndexes 指示这个节点的子节点在GoodTreePO中NodeList里的位置，
     *            由数据库对象到po时进行初始化，而从po到数据库对象时应保持为空
     */
    private GoodTreeNodeType nodeType = GoodTreeNodeType.EMPTY;
    private int index = EmptyValue.getInteger();
    private int fatherIndex = EmptyValue.getInteger();
    private ArrayList<Integer> sonIndexes = new ArrayList<>();


    /*
     * 以下是getter和setter
     */

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
