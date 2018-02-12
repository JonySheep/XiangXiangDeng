package Presentation.CommodityUI.view;

import Presentation.CommodityUI.CommodityDataHelper;
import Presentation.CommodityUI.model.Category;
import Presentation.CommodityUI.model.Good;
import Presentation.CommodityUI.model.GoodTreeNode;
import Util.GoodTreeNodeType;
import Util.ResultMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class CommodityLayoutController {



    private CommodityDataHelper commodityDataHelper = CommodityDataHelper.getInstance();
    private ArrayList<GoodTreeNode> list =  commodityDataHelper.getGoodTree();
    private DetailsLayoutController detailsLayoutController = null;

    private Stack<TreeItem<GoodTreeNode>> historyStack = new Stack<>();
    private GoodTreeNode cutted = null;

    @FXML
    private TextField addAmountField;

    @FXML
    private AnchorPane addtoListPane;

    @FXML
    private MenuButton createMenuButton;

    @FXML
    private SplitPane splitPane;

    @FXML
    private BorderPane commodityLayout;

    @FXML
    private TreeView<GoodTreeNode> treeView;

    @FXML
    private ListView<GoodTreeNode> listView;

    @FXML
    private TextField searchTextField;

    @FXML
    private ImageView cancelSearch;


    @FXML
    public void initialize(){
        refreshTree();

        treeView.setCellFactory(param -> new GoodTreeCell());
        treeView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)->historyStack.push(newValue)
        );
    }


    public void initializeManager(){

        createMenuButton.setVisible(true);

        treeView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)->showChildren(newValue==null?null:newValue.getValue())//避免删除的问题
        );

        //初始化中栏列表视图
        listView.setCellFactory(new Callback<ListView<GoodTreeNode>, ListCell<GoodTreeNode>>() {
            @Override
            public ListCell<GoodTreeNode> call(ListView<GoodTreeNode> param) {
                return new GoodTreeNodeCell();
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)->showDetails(newValue)
        );

        final ContextMenu menu = new ContextMenu();
        MenuItem pasteMenuItem = new MenuItem("粘贴");
        menu.getItems().add(pasteMenuItem);
        pasteMenuItem.setOnAction((ActionEvent t) -> {
            paste();

        });
        listView.setContextMenu(menu);
    }




    /**
     * 左栏商品树项目
     */
    private class GoodTreeItem extends TreeItem<GoodTreeNode>{


        private boolean notInitialized = true;

        public GoodTreeItem(final GoodTreeNode node){
            super(node);
        }

        @Override
        public ObservableList<TreeItem<GoodTreeNode>> getChildren(){
            if(notInitialized){
                notInitialized = false;
                if(getValue().getNodeType() == GoodTreeNodeType.CATEGORY && getValue().getSonIndexes()!=null){
                    for(final Integer i:getValue().getSonIndexes()){
                        //发现已经子节点已经是商品了，在左栏不再显示
                        if(list.get(i).getNodeType() == GoodTreeNodeType.GOOD){
                            break;
                        }
                        super.getChildren().add(new GoodTreeItem(list.get(i)));
                    }
                }
            }
            return super.getChildren();
        }
        @Override
        public boolean isLeaf(){
            return getValue().getNodeType() == GoodTreeNodeType.GOOD;
        }

    }
    /**
     * 左栏商品树单元
     */
    private class GoodTreeCell extends TreeCell<GoodTreeNode>{


        public GoodTreeCell() {
        }

        @Override
        protected void updateItem(GoodTreeNode item, boolean empty) {
            super.updateItem(item, empty);

            if (!empty && item != null){
                if(item.getNodeType() == GoodTreeNodeType.GOOD){
                    setText("商品："+((Good)item).getName());
                }else if(item.getNodeType() == GoodTreeNodeType.CATEGORY){
                    setText("分类："+((Category)item).getName());

                }

            }else{
                setText(null);
                setGraphic(null);
            }
        }

    }

    /**
     * 中栏列表单元
     */
    private class GoodTreeNodeCell extends ListCell<GoodTreeNode>{


        GoodTreeNodeCell(){

        }
        @Override
        protected void updateItem(GoodTreeNode item, boolean empty) {
            super.updateItem(item, empty);

            if(!empty && item!=null){

                final ContextMenu menu = new ContextMenu();
                MenuItem cutMenuItem = new MenuItem("剪切");
                menu.getItems().add(cutMenuItem);
                cutMenuItem.setOnAction((ActionEvent t) -> {
                    cut(this);
                });

                MenuItem pasteMenuItem = new MenuItem("粘贴");
                menu.getItems().add(pasteMenuItem);
                pasteMenuItem.setOnAction((ActionEvent t) -> {
                    paste();
                });
                setContextMenu(menu);


                if(item.getNodeType() == GoodTreeNodeType.GOOD){
                    setText("商品:"+((Good)item).getName());
                }else if(item.getNodeType() == GoodTreeNodeType.CATEGORY){
                    setText("分类:"+((Category)item).getName());
                }
            }
        }

    }

    /**
     * 获得左栏正选中的单元的GoodTreeNode值
     * @return
     */
    private GoodTreeNode getWantedItem(){
        return historyStack.peek().getValue();
    }

    /**
     * 剪切
     * @param cell
     */
    private void cut(GoodTreeNodeCell cell){
        cutted = cell.getItem();
    }

    /**
     * 粘贴
     */
    private void paste(){
        if(cutted == null)
            return;

        if(cutted.getNodeType()  == GoodTreeNodeType.GOOD){
            ArrayList<Integer> l = getWantedItem().getSonIndexes();
            //只要出现了商品分类，那么一个商品是不可以和它平行的
            if(l!=null){
                for(Integer e: l){
                    if(list.get(e).getNodeType() == GoodTreeNodeType.CATEGORY){

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("移动商品失败");
                        alert.setHeaderText(null);
                        alert.setContentText("父分类下已经存在商品分类，无法将商品移动到此处");

                        alert.showAndWait();

                        return;
                    }
                }
            }
        }else if(cutted.getNodeType()  == GoodTreeNodeType.CATEGORY){
            ArrayList<Integer> l = getWantedItem().getSonIndexes();
            //只要出现了商品，那么一个商品分类是不可以和它平行的
            if(l!=null){
                for(Integer e: l){
                    if(list.get(e).getNodeType() == GoodTreeNodeType.GOOD){

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("移动商品分类失败");
                        alert.setHeaderText(null);
                        alert.setContentText("父分类下已经存在商品，无法将商品分类移动到此处");

                        alert.showAndWait();

                        return;
                    }
                }
            }
        }

        if(commodityDataHelper.moveTo(cutted, getWantedItem()) == ResultMessage.SUCCESS){
            //父亲增加子id
            ArrayList<Integer> sonIndexes = ((Category) getWantedItem()).getSonIndexes();
            if(sonIndexes==null){
                sonIndexes = new ArrayList<Integer>();
            }
            if(!sonIndexes.contains(cutted.getIndex())){
                sonIndexes.add(cutted.getIndex());
                ((Category) getWantedItem()).setSonIndexes(sonIndexes);

                //原来的父亲删除子id
                list.get(cutted.getFatherIndex()).getSonIndexes().remove(new Integer(cutted.getIndex()));

                //儿子更新父id
                cutted.setFatherIndex(getWantedItem().getIndex());
                refresh();
                select(list.get(cutted.getFatherIndex()));
                cutted = null;
            }
        }
    }

    /**
     * 刷新左栏和中栏
     */
    public void refresh(){
        listView.setItems(null);
        refreshTree();
    }

    /**
     * 刷新左栏
     */
    private void refreshTree(){
        list = commodityDataHelper.getGoodTree();

        //初始化左栏树视图
        for(GoodTreeNode item: list){
            if(item.getFatherIndex() == -1){
                treeView.setRoot(new GoodTreeItem(item));
                treeView.getRoot().setExpanded(true);

                TreeItem<GoodTreeNode> node = treeView.getRoot();
                for(TreeItem<GoodTreeNode> e: node.getChildren()){
                    e.setExpanded(true);
                }
            }
        }
        treeView.setCellFactory(param -> new GoodTreeCell());
        if(!historyStack.empty()){
            historyStack.pop();
            treeView.getSelectionModel().select(historyStack.peek());
            historyStack.pop();
        }
    }

    /**
     * 刷新中栏列表
     */
    private void refreshListView(){
        listView.setCellFactory(new Callback<ListView<GoodTreeNode>, ListCell<GoodTreeNode>>() {
            @Override
            public ListCell<GoodTreeNode> call(ListView<GoodTreeNode> param) {
                return new GoodTreeNodeCell();
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)->showDetails(newValue)
        );
    }

    /**
     * 左栏点击一个商品分类，中栏展示他的所有子商品分类，或子商品
     * @param value
     */
    private void showChildren(GoodTreeNode value) {

        if(value == null){
            return;
        }
        if(value.getSonIndexes() == null){
            listView.setItems(null);
        }else{
            ObservableList<GoodTreeNode> temp = FXCollections.observableArrayList();
            for(Integer i: value.getSonIndexes()){
                temp.add(list.get(i));
            }
            listView.setCellFactory(new Callback<ListView<GoodTreeNode>, ListCell<GoodTreeNode>>() {
                @Override
                public ListCell<GoodTreeNode> call(ListView<GoodTreeNode> param) {
                    return new GoodTreeNodeCell();
                }
            });
            listView.setItems(temp);
        }
    }

    /**
     * 中栏点击一个商品分类或商品，右栏显示他的所有属性
     * @param newValue
     */
    private void showDetails(GoodTreeNode newValue) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Presentation.CommodityUI.CommodityUI.class.getResource("view/DetailsLayout.fxml"));

            BorderPane borderPane = loader.load();
            detailsLayoutController = loader.getController();
            detailsLayoutController.setCommodityLayoutController(this);

            detailsLayoutController.show(newValue);

            commodityLayout.setRight(borderPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 返回上一个点击的左栏项目
     * @param actionEvent
     */
    public void back(ActionEvent actionEvent) {
        if(historyStack.empty())return;
        historyStack.pop();
        if(historyStack.empty())return;

        treeView.getSelectionModel().select(historyStack.peek());
        historyStack.pop();//弹掉上一句select入栈的
    }

    /**
     * 搜索关键词
     * @param actionEvent
     */
    public void search(ActionEvent actionEvent) {
        if(searchTextField.getText() == null || searchTextField.getText().equals("")){
            return;
        }
        cancelSearch.setVisible(true);
        ResultMessage rm = commodityDataHelper.search(searchTextField.getText(), ((Category)getWantedItem()).getId());
        if(rm == ResultMessage.SUCCESS){
            ObservableList<GoodTreeNode> list = commodityDataHelper.getSearchResult();
            listView.setItems(list);
            refreshListView();
        }
    }

    /**
     * 返回未搜索时候的状态
     * @param mouseEvent
     */
    public void searchBack(MouseEvent mouseEvent) {

        if(historyStack.empty())return;

        treeView.getSelectionModel().select(null);
        historyStack.pop();
        treeView.getSelectionModel().select(historyStack.peek());
        historyStack.pop();//弹掉上一句select入栈的

        cancelSearch.setVisible(false);
    }

    /**
     * 增加一个商品分类
     * @param actionEvent
     */
    public void addCategory(ActionEvent actionEvent) {
        Category father = (Category) getWantedItem();
        if(father.getSonIndexes()!=null){
            for (Integer e: father.getSonIndexes()){
                if(list.get(e).getNodeType() == GoodTreeNodeType.GOOD){

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("增加商品分类失败");
                    alert.setHeaderText(null);
                    alert.setContentText("父分类下已经存在商品，无法添加商品分类");

                    alert.showAndWait();

                    return;
                }
            }

            //TODO
            //重名问题
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Presentation.CommodityUI.CommodityUI.class.getResource("view/DetailsLayout.fxml"));

            BorderPane borderPane = loader.load();
            detailsLayoutController = loader.getController();
            detailsLayoutController.setCommodityLayoutController(this);

            commodityLayout.setRight(borderPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

        detailsLayoutController.addCategory(father.getId());
    }

    /**
     * 增加一个商品
     * @param actionEvent
     */
    public void addGood(ActionEvent actionEvent) {
        Category father = (Category) getWantedItem();
        if(father.getSonIndexes()!=null){
            for (Integer e: father.getSonIndexes()){
                if(list.get(e).getNodeType() == GoodTreeNodeType.CATEGORY){

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("增加商品失败");
                    alert.setHeaderText(null);
                    alert.setContentText("父分类下已经存在商品分类，无法添加商品");

                    alert.showAndWait();

                    return;
                }
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Presentation.CommodityUI.CommodityUI.class.getResource("view/DetailsLayout.fxml"));

            BorderPane borderPane = loader.load();
            detailsLayoutController = loader.getController();
            detailsLayoutController.setCommodityLayoutController(this);

            commodityLayout.setRight(borderPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

        detailsLayoutController.addGood(father.getId());
    }

    /**
     * 选中一个节点
     * @param obj
     */
    public void select(GoodTreeNode obj){
        TreeItem<GoodTreeNode> item = new TreeItem<>(obj);
        if(obj == null){
            treeView.getSelectionModel().select(null);
        }

        TreeItem<GoodTreeNode> goodItem =  treeView.getSelectionModel().getSelectedItem();
        if(goodItem!=null){
            goodItem.setExpanded(false);
            goodItem.setExpanded(true);
        }

        treeView.getSelectionModel().select(item);
    }


    /*******************************************************************************
     *                                                                             *
     * 以下是GoodSelector的方法                                                      *
     *                                                                             *
     ******************************************************************************/

    private GoodTreeNode wantedItem = null;
    private SelectorPreviewController selectorPreviewController = null;
    public void setPreviewController(SelectorPreviewController previewController) {
        this.selectorPreviewController = previewController;
    }

    /**
     * 中栏列表单元
     */
    private class SimpleGoodTreeNodeCell extends ListCell<GoodTreeNode>{


        SimpleGoodTreeNodeCell(){

        }
        @Override
        protected void updateItem(GoodTreeNode item, boolean empty) {
            super.updateItem(item, empty);

            if(!empty && item!=null){

                if(item.getNodeType() == GoodTreeNodeType.GOOD){
                    setText("商品:"+((Good)item).getName());
                }else if(item.getNodeType() == GoodTreeNodeType.CATEGORY){
                    setText("分类:"+((Category)item).getName());
                }
            }
        }

    }

    public void initializeSelector(SelectorPreviewController selectorPreviewController){

        addtoListPane.setVisible(true);
        commodityLayout.setRight(null);

        treeView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)->simpleShowChildren(newValue==null?null:newValue.getValue())//避免删除的问题
        );


        listView.setCellFactory(new Callback<ListView<GoodTreeNode>, ListCell<GoodTreeNode>>() {
            @Override
            public ListCell<GoodTreeNode> call(ListView<GoodTreeNode> param) {

                 return new SimpleGoodTreeNodeCell();
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)->previewAndAdd(newValue)
        );

        setPreviewController(selectorPreviewController);
    }

    private void simpleShowChildren(GoodTreeNode value) {
        if(value == null){
            return;
        }
        if(value.getSonIndexes() == null){
            listView.setItems(null);
        }else{
            ObservableList<GoodTreeNode> temp = FXCollections.observableArrayList();
            for(Integer i: value.getSonIndexes()){
                temp.add(list.get(i));
            }
            listView.setCellFactory(new Callback<ListView<GoodTreeNode>, ListCell<GoodTreeNode>>() {
                @Override
                public ListCell<GoodTreeNode> call(ListView<GoodTreeNode> param) {
                    return new SimpleGoodTreeNodeCell();
                }
            });
            listView.setItems(temp);
        }
    }

    /**
     * 中栏点击一个商品分类或商品，右栏显示他的所有属性
     * @param newValue
     */
    private void previewAndAdd(GoodTreeNode newValue) {
        if(newValue == null || newValue.getNodeType() == GoodTreeNodeType.CATEGORY)
            return;

        selectorPreviewController.show(newValue);
        wantedItem = newValue;
    }

    /**
     * 设置分割线的比例
     * @param d
     */
    public void setDividerPos(double d){
        splitPane.setDividerPositions(d);

    }

    /**
     * 向右栏添加相应数量的商品
     * @param mouseEvent
     */
    public void addToList(MouseEvent mouseEvent) {
        int amount;
        try{
            amount = Integer.parseInt(addAmountField.getText());
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("输入的数据有误");
            alert.setHeaderText(null);
            alert.setContentText("请重新填写要添加的商品数量");

            alert.showAndWait();

            return;
        }

        if(wantedItem != null){
            addAmountField.setText("");
            selectorPreviewController.addToList(wantedItem,amount);
        }
    }






}
