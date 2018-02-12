package PO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jason on 2017/10/22.
 * Updated by Jason on 2017/11/09.
 */
public class GoodTreePO implements Serializable {

    private ArrayList<GoodTreeNodePO> NodeList = new ArrayList<>();

    /**
     * 直接构造器
     * @param nodeList 将构造好的NodeList传入
     */
    public GoodTreePO(ArrayList<GoodTreeNodePO> nodeList) {

        NodeList = nodeList;
    }

    /**
     * 空构造器
     */
    public GoodTreePO(){

    }

    /**
     * 获得NodeList里面下标为i的Node
     * @param i 下标i
     * @return 一个GoodTreeNodePO，是CategoryPO和GoodPO的父类。
     */
    public GoodTreeNodePO getNode(int i){
        return NodeList.get(i);
    }

    /**
     * 获得NodeList的大小
     * @return
     */
    public int getNodeListSize(){
        return NodeList.size();
    }
}
