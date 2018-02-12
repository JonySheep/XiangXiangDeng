package VO;

import PO.GoodTreeNodePO;
import PO.GoodTreePO;
import Util.GoodTreeNodeType;

import java.util.ArrayList;

public class GoodTreeNodeVO {
    /**
     * nodeType 指示这个节点是CategoryVO还是GoodVO
     * index 指示这个节点在GoodTreeVO中NodeList里的位置
     * fatherIndex 指示这个节点的父节点在GoodTreeVO中NodeList里的位置
     * sonIndexes 指示这个节点的子节点在GoodTreeVO中NodeList里的位置
     *            由po到vo时进行初始化，而从vo到po时应保持为空
     */
    private GoodTreeNodeType nodeType = GoodTreeNodeType.EMPTY;
    private int index = -1;
    private int fatherIndex = -1;
    private ArrayList<Integer> sonIndexes = new ArrayList<>();

    /**
     * 用GoodTreeNodePO构造GoodTreeNodeVO
     * @param po
     */
    public GoodTreeNodeVO(GoodTreeNodePO po) {
        nodeType = po.getNodeType();
        index = po.getIndex();
        fatherIndex = po.getFatherIndex();
        sonIndexes = po.getSonIndexes();
    }

    /**
     * 默认构造器
     */
    public GoodTreeNodeVO(){

    }

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

    public void addSonIndex(int sonIndex) {
        this.sonIndexes.add(sonIndex);
    }

    public void setSonIndexes(ArrayList<Integer> sonIndexes) {
        this.sonIndexes = sonIndexes;
    }
}
