package Presentation.CommodityUI;

import BusinessLogic.AccountBL.Account;
import BusinessLogic.CommodityBL.CommodityController;
import BusinessLogicService.CommodityBLService;
import Presentation.CommodityUI.model.Category;
import Presentation.CommodityUI.model.Good;
import Presentation.CommodityUI.model.GoodTreeNode;
import Util.EmptyValue;
import Util.GoodTreeNodeType;
import Util.ResultMessage;
import VO.CategoryVO;
import VO.GoodTreeNodeVO;
import VO.GoodVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommodityDataHelper {
    private static CommodityDataHelper ourInstance = null;

    public static CommodityDataHelper getInstance() {
        if(ourInstance == null){
            ourInstance =  new CommodityDataHelper();
        }
        return ourInstance;

    }

    private ArrayList<GoodTreeNode> goodTree = new ArrayList<>();
    private ObservableList<GoodTreeNode> searchResult = FXCollections.observableArrayList();

    private CommodityBLService commodityBLService = CommodityController.getInstance();

    private CommodityDataHelper() {
        //仅供测试
        ArrayList<GoodTreeNodeVO> l =  commodityBLService.getGoodTree().getNodeList();
        for(int i = 0; i<l.size(); i++){

            GoodTreeNodeVO vo = l.get(i);

            if(vo.getNodeType() == GoodTreeNodeType.CATEGORY){
                GoodTreeNode node = new Category(
                        ((CategoryVO)vo).getId(),
                        ((CategoryVO)vo).getName(),
                        ((CategoryVO)vo).getFatherCategoryId()
                );
                node.setIndex(vo.getIndex());
                node.setNodeType(GoodTreeNodeType.CATEGORY);
                node.setSonIndexes(vo.getSonIndexes());
                node.setFatherIndex(vo.getFatherIndex());

                goodTree.add(node);
            }else if(vo.getNodeType() == GoodTreeNodeType.GOOD){
                GoodTreeNode node = new Good(
                        ((GoodVO)vo).getId(),
                        ((GoodVO)vo).getName(),
                        ((GoodVO)vo).getFatherCategoryId(),
                        ((GoodVO)vo).getType(),
                        ((GoodVO)vo).getWarningAmount(),
                        ((GoodVO)vo).getBuyingPrice(),
                        ((GoodVO)vo).getSellingPrice(),
                        ((GoodVO)vo).getNowAmount(),
                        ((GoodVO)vo).getRecentBuyingPrice(),
                        ((GoodVO)vo).getRecentSellingPrice(),
                        ((GoodVO)vo).getComment()

                );
                node.setIndex(vo.getIndex());
                node.setNodeType(GoodTreeNodeType.GOOD);
                node.setSonIndexes(vo.getSonIndexes());
                node.setFatherIndex(vo.getFatherIndex());

                goodTree.add(node);
            }
        }
    }


    public ArrayList<GoodTreeNode> getGoodTree() {
        return goodTree;
    }

    public ObservableList<GoodTreeNode> getSearchResult() {
        return searchResult;
    }


    /**
     * 删除一个节点
     * @param nowSelected
     * @return
     */
    public ResultMessage delNode(GoodTreeNode nowSelected) {
        ResultMessage rm = ResultMessage.FAIL;
        if(nowSelected.getNodeType() == GoodTreeNodeType.CATEGORY){
            rm = commodityBLService.delCategory(((Category)nowSelected).getId());
        }else if(nowSelected.getNodeType() == GoodTreeNodeType.GOOD){
            rm = commodityBLService.delGood(((Good)nowSelected).getId());
        }

        if(rm != ResultMessage.SUCCESS){
            return ResultMessage.FAIL;
        }

        GoodTreeNode father = goodTree.get(nowSelected.getFatherIndex());
        for(Iterator<Integer> it = father.getSonIndexes().iterator(); it.hasNext();){
            Integer val = it.next();
            if(val.equals(nowSelected.getIndex())){
                it.remove();
            }
        }
        nowSelected = null;
        return ResultMessage.SUCCESS;
    }

    /**
     * 与服务器同步商品层次
     */
    public void syncGoodTree() {
        goodTree = new ArrayList<>();
        ArrayList<GoodTreeNodeVO> l =  commodityBLService.getGoodTree().getNodeList();
        for(int i = 0; i<l.size(); i++){

            GoodTreeNodeVO vo = l.get(i);

            if(vo.getNodeType() == GoodTreeNodeType.CATEGORY){
                GoodTreeNode node = new Category(
                        ((CategoryVO)vo).getId(),
                        ((CategoryVO)vo).getName(),
                        ((CategoryVO)vo).getFatherCategoryId()
                );
                node.setIndex(vo.getIndex());
                node.setNodeType(GoodTreeNodeType.CATEGORY);
                node.setSonIndexes(vo.getSonIndexes());
                node.setFatherIndex(vo.getFatherIndex());

                goodTree.add(node);
            }else if(vo.getNodeType() == GoodTreeNodeType.GOOD){
                GoodTreeNode node = new Good(
                        ((GoodVO)vo).getId(),
                        ((GoodVO)vo).getName(),
                        ((GoodVO)vo).getFatherCategoryId(),
                        ((GoodVO)vo).getType(),
                        ((GoodVO)vo).getWarningAmount(),
                        ((GoodVO)vo).getBuyingPrice(),
                        ((GoodVO)vo).getSellingPrice(),
                        ((GoodVO)vo).getNowAmount(),
                        ((GoodVO)vo).getRecentBuyingPrice(),
                        ((GoodVO)vo).getRecentSellingPrice(),
                        ((GoodVO)vo).getComment()

                );
                node.setIndex(vo.getIndex());
                node.setNodeType(GoodTreeNodeType.GOOD);
                node.setSonIndexes(vo.getSonIndexes());
                node.setFatherIndex(vo.getFatherIndex());

                goodTree.add(node);
            }
        }

    }


    /**
     * 更新商品或商品层次
     * @param node
     */
    public ResultMessage update(GoodTreeNode node) {
        ResultMessage rm = ResultMessage.FAIL;
        if(node.getNodeType() == GoodTreeNodeType.CATEGORY){
            rm = commodityBLService.updateCategory(new CategoryVO(
                    ((Category)node).getId(),
                    ((Category)node).getName(),
                    ((Category)node).getFatherCategoryId()
            ));
        }else if(node.getNodeType() == GoodTreeNodeType.GOOD){
            rm = commodityBLService.updateGood(new GoodVO(
                    ((Good)node).getId(),
                    ((Good)node).getName(),
                    ((Good)node).getFatherCategoryId(),
                    ((Good)node).getType(),
                    ((Good)node).getWarningAmount(),
                    ((Good)node).getBuyingPrice(),
                    ((Good)node).getSellingPrice(),
                    ((Good)node).getComment()
            ));
        }

        if(rm != ResultMessage.SUCCESS){
            return ResultMessage.FAIL;
        }

        if(node.getNodeType()==GoodTreeNodeType.GOOD) {
            for (GoodTreeNode n : goodTree) {
                if (n.getNodeType() != GoodTreeNodeType.GOOD) {
                    continue;
                }
                Good update = ((Good) node);

                //甄别改动
                if (((Good) n).getId().equals(update.getId())) {
                    if (!update.getName().equals(EmptyValue.getString())) {
                        ((Good) n).setName(update.getName());
                    }
                    if (!update.getType().equals(EmptyValue.getString())) {
                        ((Good) n).setType(update.getType());
                    }
                    if (update.getWarningAmount() != EmptyValue.getDouble()) {
                        ((Good) n).setWarningAmount(update.getWarningAmount());
                    }
                    if (update.getBuyingPrice() != EmptyValue.getDouble()) {
                        ((Good) n).setBuyingPrice(update.getBuyingPrice());
                    }
                    if (update.getSellingPrice() != EmptyValue.getDouble()) {
                        ((Good) n).setSellingPrice(update.getSellingPrice());
                    }
                }

            }
        } else if(node.getNodeType()==GoodTreeNodeType.CATEGORY){
            for (GoodTreeNode n : goodTree) {
                if (n.getNodeType() != GoodTreeNodeType.CATEGORY) {
                    continue;
                }
                Category update = ((Category) node);

                //甄别改动
                if (((Category) n).getId().equals(update.getId())) {
                    if (!update.getName().equals(EmptyValue.getString())) {
                        ((Category) n).setName(update.getName());
                    }
                }
            }
        }


        return ResultMessage.SUCCESS;
    }

    /**
     * 剪切
     * @param cutted
     * @param nowSelected
     * @return
     */
    public ResultMessage moveTo(GoodTreeNode cutted, GoodTreeNode nowSelected) {

        ResultMessage rm = ResultMessage.FAIL;
        if(cutted.getNodeType() == GoodTreeNodeType.CATEGORY){
            rm = commodityBLService.updateCategory(new CategoryVO(
                    ((Category)cutted).getId(),
                    EmptyValue.getString(),
                    ((Category)nowSelected).getId()
            ));
        }else if(cutted.getNodeType() == GoodTreeNodeType.GOOD){
            rm = commodityBLService.updateGood(new GoodVO(
                    ((Good)cutted).getId(),
                    EmptyValue.getString(),
                    ((Category)nowSelected).getId(),
                    EmptyValue.getString(),
                    EmptyValue.getInteger(),
                    EmptyValue.getDouble(),
                    EmptyValue.getDouble(),
                    EmptyValue.getString()
            ));
        }

        if(rm != ResultMessage.SUCCESS){
            return ResultMessage.FAIL;
        }else return ResultMessage.SUCCESS;
    }

    /**
     * 搜索
     * @param str
     * @return
     */
    public ResultMessage search(String str, String CategoryId){
        //仅供测试
        searchResult.clear();
        ArrayList<GoodVO> l =  commodityBLService.searchGood(str,CategoryId);
        for(GoodVO vo:l){
            GoodTreeNode good = new Good(
                    vo.getId(),
                    vo.getName(),
                    vo.getFatherCategoryId(),
                    vo.getType(),
                    vo.getWarningAmount(),
                    vo.getBuyingPrice(),
                    vo.getSellingPrice(),
                    vo.getNowAmount(),
                    vo.getRecentBuyingPrice(),
                    vo.getRecentSellingPrice(),
                    vo.getComment()
            );
            good.setNodeType(GoodTreeNodeType.GOOD);
            //找到idndex
            for(GoodTreeNode n:goodTree){
                if(n.getNodeType() == GoodTreeNodeType.GOOD && ((Good)n).getId().equals(vo.getId())){
                    good.setIndex(n.getIndex());
                    good.setFatherIndex(n.getFatherIndex());
                    good.setSonIndexes(n.getSonIndexes());
                }
            }

            searchResult.add(good);
        }

        return ResultMessage.SUCCESS;
    }

    /**
     * 增加商品分类
     * @param node
     * @return
     */
    public ResultMessage addCategory(GoodTreeNode node){

        ResultMessage rm;
        Category category = (Category)node;
        rm = commodityBLService.addCategory(new CategoryVO(
                category.getId(),
                category.getName(),
                category.getFatherCategoryId()
        ));

        if(rm != ResultMessage.SUCCESS){
            return ResultMessage.FAIL;
        }else return ResultMessage.SUCCESS;
    }

    /**
     * 增加商品
     * @param node
     * @return
     */
    public ResultMessage addGood(GoodTreeNode node){

        ResultMessage rm;
        Good good = (Good)node;
        rm = commodityBLService.addGood(new GoodVO(
                good.getId(),
                good.getName(),
                good.getFatherCategoryId(),
                good.getType(),
                good.getWarningAmount(),
                good.getBuyingPrice(),
                good.getSellingPrice(),
                good.getComment()
        ));

        if(rm != ResultMessage.SUCCESS){
            return ResultMessage.FAIL;
        }else return ResultMessage.SUCCESS;

    }
}
