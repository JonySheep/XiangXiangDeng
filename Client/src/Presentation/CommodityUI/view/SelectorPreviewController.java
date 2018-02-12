package Presentation.CommodityUI.view;

import Presentation.CommodityUI.CommodityUI;
import Presentation.CommodityUI.GoodListSettable;
import Presentation.CommodityUI.model.Category;
import Presentation.CommodityUI.model.Good;
import Presentation.CommodityUI.model.GoodTreeNode;
import Util.GoodSelectItem;
import Util.GoodTreeNodeType;
import VO.GoodItemForPurchaseSaleDocVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class SelectorPreviewController {

    @FXML
    private AnchorPane detailsPane;
    @FXML
    private Label typeLabel;
    @FXML
    private Label nowAmountLabel;
    @FXML
    private Label warningAmountLabel;
    @FXML
    private Label buyingPriceLabel;
    @FXML
    private Label sellingPriceLabel;
    @FXML
    private Label recentBuyingPriceLabel;
    @FXML
    private Label recentSellingPriceLabel;

    @FXML
    private Text id;
    @FXML
    private Text name;
    @FXML
    private Text type;
    @FXML
    private Text nowAmount;
    @FXML
    private Text warningAmount;
    @FXML
    private Text buyingPrice;
    @FXML
    private Text sellingPrice;
    @FXML
    private Text recentBuyingPrice;
    @FXML
    private Text recentSellingPrice;
    @FXML
    private BorderPane leftPane;

    @FXML
    private ListView<GoodSelectItem> listView;

    private CommodityLayoutController commodityLayoutController;

    private GoodListSettable controller = null;

    private Stage stage = null;


    /**
     * 用于商品选择的新窗口的舞台
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * 获得需要获得商品列表的controller
     * @param controller
     */
    public void setController(GoodListSettable controller) {
        this.controller = controller;
    }

    /**
     * 接受部分的GoodList
     * @param goodList
     */
    public void setGoodList(ArrayList<GoodSelectItem> goodList) {
        ObservableList<GoodSelectItem> l = FXCollections.observableArrayList(goodList);
        listView.setItems(l);
        refresh();
    }

    /**
     * 刷新右下listView
     */
    private void refresh(){
        listView.setCellFactory(new Callback<ListView<GoodSelectItem>, ListCell<GoodSelectItem>>() {
            @Override
            public ListCell<GoodSelectItem> call(ListView<GoodSelectItem> param) {
                return new GoodItemNode();
            }
        });
    }

    @FXML
    private void initialize(){
        listView.setCellFactory(new Callback<ListView<GoodSelectItem>, ListCell<GoodSelectItem>>() {
            @Override
            public ListCell<GoodSelectItem> call(ListView<GoodSelectItem> param) {
                return new GoodItemNode();
            }
        });

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Presentation.CommodityUI.CommodityUI.class.getResource("view/CommodityLayout.fxml"));
            BorderPane borderPane = (BorderPane)loader.load();
            commodityLayoutController  = loader.getController();

            commodityLayoutController.initializeSelector(this);

            leftPane.setCenter(borderPane);

        } catch (IOException e) {
            e.printStackTrace();
        }

        show(null);
    }

    public void cancel(ActionEvent actionEvent) {
        if(controller == null)
            return;
        stage.close();
    }

    public void confirm(ActionEvent actionEvent) {
        if(controller == null)
            return;

        ArrayList<GoodSelectItem> arr = new ArrayList<>();
        for(GoodSelectItem e:listView.getItems()){
            arr.add(e);
        }
        controller.setGoodList(arr);
        stage.close();
    }

    private class GoodItemNode extends ListCell<GoodSelectItem>{
        public GoodItemNode() {

        }

        @Override
        protected void updateItem(GoodSelectItem item, boolean empty) {
            super.updateItem(item, empty);

            if(!empty && item!=null){

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(CommodityUI.class.getResource("view/GoodItemPreview.fxml"));

                try {
                    AnchorPane preview = loader.load();
                    GoodItemPreviewController controller = loader.getController();
                    setGraphic(preview);
                    controller.setName(item.getName());
                    controller.setAmount(item.getNumber());
                    controller.setItem(item);


                    final ContextMenu menu = new ContextMenu();
                    MenuItem cutMenuItem = new MenuItem("编辑");
                    menu.getItems().add(cutMenuItem);
                    cutMenuItem.setOnAction((ActionEvent t) -> {
                        controller.edit();
                    });

                    MenuItem pasteMenuItem = new MenuItem("删除");
                    menu.getItems().add(pasteMenuItem);
                    pasteMenuItem.setOnAction((ActionEvent t) -> {
                        listView.getItems().remove(item);
                        refresh();
                    });
                    setContextMenu(menu);


                } catch (IOException e) {
                    e.printStackTrace();
                }

//                setText(item.getName()+"  "+item.getNumber());
            }
        }
    }

    public Text getId() {
        return id;
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public Text getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public Text getType() {
        return type;
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public Text getNowAmount() {
        return nowAmount;
    }

    public void setNowAmount(String nowAmount) {
        this.nowAmount.setText(nowAmount);
    }

    public Text getWarningAmount() {
        return warningAmount;
    }

    public void setWarningAmount(String warningAmount) {
        this.warningAmount.setText(warningAmount);
    }

    public Text getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice.setText(buyingPrice);
    }

    public Text getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice.setText(sellingPrice);
    }

    public Text getRecentBuyingPrice() {
        return recentBuyingPrice;
    }

    public void setRecentBuyingPrice(String recentBuyingPrice) {
        this.recentBuyingPrice.setText(recentBuyingPrice);
    }

    public Text getRecentSellingPrice() {
        return recentSellingPrice;
    }

    public void setRecentSellingPrice(String recentSellingPrice) {
        this.recentSellingPrice.setText(recentSellingPrice);
    }

    public void show(GoodTreeNode newValue) {
        if(newValue == null){
            detailsPane.setVisible(false);
            return;
        }
        if(newValue.getNodeType() == GoodTreeNodeType.GOOD){
            detailsPane.setVisible(true);
            Good good = (Good)newValue;
            setId(good.getId());
            setName(good.getName());
            setType(good.getType());
            setNowAmount(""+good.getNowAmount());
            setWarningAmount(""+good.getWarningAmount());
            setBuyingPrice(""+good.getBuyingPrice());
            setSellingPrice(""+good.getSellingPrice());
            setRecentBuyingPrice(""+good.getRecentBuyingPrice());
            setRecentSellingPrice(""+good.getRecentSellingPrice());
        }

    }

    public void addToList(GoodTreeNode node, int number){
        if(node.getNodeType() == GoodTreeNodeType.CATEGORY)return;
        Good good = (Good)node;
        GoodSelectItem item = new GoodSelectItem(
            good,number
        );


        GoodSelectItem p = null;
        for(GoodSelectItem e: listView.getItems()){
            if(e.getId().equals(item.getId())){
                p = e;
            }
        }
        if(p == null){
            listView.getItems().add(item);
            refresh();
        }else{
            p.setNumber(p.getNumber()+item.getNumber());
            refresh();
        }

    }


}
