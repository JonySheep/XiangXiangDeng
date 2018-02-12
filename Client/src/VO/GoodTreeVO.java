package VO;

import PO.CategoryPO;
import PO.GoodPO;
import PO.GoodTreeNodePO;
import PO.GoodTreePO;
import Util.GoodTreeNodeType;

import java.util.ArrayList;

public class GoodTreeVO {

    private ArrayList<GoodTreeNodeVO> nodeList = new ArrayList<>();

    /**
     * 直接构造器
     * @param nodeList 将构造好的NodeList传入
     */
    public GoodTreeVO(ArrayList<GoodTreeNodeVO> nodeList) {

        this.nodeList = nodeList;
    }

    /**
     * 由GoodTreePO构造GoodTreeVO
     * @param po
     */
    public GoodTreeVO(GoodTreePO po) {
        for(int i = 0; i<po.getNodeListSize(); i++ ){
            GoodTreeNodePO nodePO = po.getNode(i);
            if(nodePO.getNodeType() == GoodTreeNodeType.GOOD){

                GoodTreeNodeVO nodeVO = new GoodVO((GoodPO) po.getNode(i));
                nodeVO.setNodeType(GoodTreeNodeType.GOOD);
                nodeVO.setIndex(i);
                nodeVO.setFatherIndex(po.getNode(i).getFatherIndex());
                nodeVO.setSonIndexes(po.getNode(i).getSonIndexes());
                nodeList.add(nodeVO);

            }else if(nodePO.getNodeType() == GoodTreeNodeType.CATEGORY){

                GoodTreeNodeVO nodeVO = new CategoryVO((CategoryPO) po.getNode(i));
                nodeVO.setNodeType(GoodTreeNodeType.CATEGORY);
                nodeVO.setIndex(i);
                nodeVO.setFatherIndex(po.getNode(i).getFatherIndex());
                nodeVO.setSonIndexes(po.getNode(i).getSonIndexes());
                nodeList.add(nodeVO);
            }

        }
    }

    public ArrayList<GoodTreeNodeVO> getNodeList() {
        return nodeList;
    }

    public void setNodeList(ArrayList<GoodTreeNodeVO> nodeList) {
        this.nodeList = nodeList;
    }


}

