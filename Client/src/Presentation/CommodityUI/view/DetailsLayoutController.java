package Presentation.CommodityUI.view;

import Presentation.CommodityUI.CommodityDataHelper;
import Presentation.CommodityUI.model.Category;
import Presentation.CommodityUI.model.Good;
import Presentation.CommodityUI.model.GoodTreeNode;
import Util.EmptyValue;
import Util.GoodTreeNodeType;
import Util.ResultMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Optional;

public class DetailsLayoutController {
    private CommodityLayoutController commodityLayoutController = null;
    private CommodityDataHelper commodityDataHelper = CommodityDataHelper.getInstance();
    private GoodTreeNode nowSelected = null;

    private String nowAddingFatherId = null;
    private GoodTreeNodeType nowAddingType = GoodTreeNodeType.EMPTY;

    @FXML
    private HBox originalButtonSet;
    @FXML
    private HBox editButtonSet;

    @FXML
    private BorderPane detailsPane;

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
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField nowAmountField;
    @FXML
    private TextField warningAmountField;
    @FXML
    private TextField buyingPriceField;
    @FXML
    private TextField sellingPriceField;
    @FXML
    private TextField recentBuyingPriceField;
    @FXML
    private TextField recentSellingPriceField;

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

    public void setCommodityLayoutController(CommodityLayoutController commodityLayoutController) {
        this.commodityLayoutController = commodityLayoutController;
    }

    public void setIdField(String idField) {
        this.idField.setText(idField);
    }

    public void setNameField(String nameField) {
        this.nameField.setText(nameField);
    }

    public void setTypeField(String typeField) {
        this.typeField.setText(typeField);
    }

    public void setNowAmountField(String nowAmountField) {
        this.nowAmountField.setText(nowAmountField);
    }

    public void setWarningAmountField(String warningAmountField) {
        this.warningAmountField.setText(warningAmountField);
    }

    public void setBuyingPriceField(String buyingPriceField) {
        this.buyingPriceField.setText(buyingPriceField);
    }

    public void setSellingPriceField(String sellingPriceField) {
        this.sellingPriceField.setText(sellingPriceField);
    }

    public void setRecentBuyingPriceField(String recentBuyingPriceField) {
        this.recentBuyingPriceField.setText(recentBuyingPriceField);
    }

    public void setRecentSellingPriceField(String recentSellingPriceField) {
        this.recentSellingPriceField.setText(recentSellingPriceField);
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

    /************************************/

    public void show(GoodTreeNode node){
        if(node == null){
            detailsPane.setVisible(false);
            return;
        }else{
            nowSelected = node;
        }
        if(node.getNodeType() == GoodTreeNodeType.GOOD){
            Good good = (Good)node;
            setId(good.getId());
            setName(good.getName());
            setType(good.getType());

            if(good.getNowAmount()<0){
                setNowAmount("未初始化");
            }else setNowAmount(""+good.getNowAmount());

            setWarningAmount(""+good.getWarningAmount());
            setBuyingPrice(""+good.getBuyingPrice());
            setSellingPrice(""+good.getSellingPrice());

            if(good.getRecentBuyingPrice()<0){
                setRecentBuyingPrice("未初始化");
            }else setRecentBuyingPrice(""+good.getRecentBuyingPrice());

            if(good.getRecentSellingPrice()<0){
                setRecentSellingPrice("未初始化");
            }else setRecentSellingPrice(""+good.getRecentSellingPrice());

        }else if(node.getNodeType() == GoodTreeNodeType.CATEGORY){
            Category category = (Category)node;
            type.setVisible(false);
            nowAmount.setVisible(false);
            warningAmount.setVisible(false);
            buyingPrice.setVisible(false);
            sellingPrice.setVisible(false);
            recentBuyingPrice.setVisible(false);
            recentSellingPrice.setVisible(false);

            typeLabel.setVisible(false);
            nowAmountLabel.setVisible(false);
            warningAmountLabel.setVisible(false);
            buyingPriceLabel.setVisible(false);
            sellingPriceLabel.setVisible(false);
            recentBuyingPriceLabel.setVisible(false);
            recentSellingPriceLabel.setVisible(false);

            setId(category.getId());
            setName(category.getName());
        }
    }

    public void del(ActionEvent actionEvent) {
        if(nowSelected.getSonIndexes()!=null && nowSelected.getSonIndexes().size()>0){
            Alert delAlert = new Alert(Alert.AlertType.WARNING);
            delAlert.setTitle("删除失败");
            delAlert.setHeaderText(null);
            delAlert.setContentText("删除的商品分类下，包含商品或商品分类，不能删除");

            delAlert.showAndWait();
            return;
        }else if(nowSelected.getNodeType() == GoodTreeNodeType.GOOD && ((Good)nowSelected).getNowAmount()>0){
            Alert delAlert = new Alert(Alert.AlertType.WARNING);
            delAlert.setTitle("删除失败");
            delAlert.setHeaderText(null);
            delAlert.setContentText("删除的商品还有库存，不能删除");

            delAlert.showAndWait();
            return;
        }


        Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION);
        delAlert.setTitle("删除一个商品/商品分类");
        delAlert.setHeaderText(null);
        delAlert.setContentText("确定删除？");

        Optional<ButtonType> result = delAlert.showAndWait();
        if (result.get() == ButtonType.OK){
            //用户确定删除
            ResultMessage rm = commodityDataHelper.delNode(nowSelected);
            if(rm == ResultMessage.SUCCESS){
                //服务器删除成功
                hide();
                commodityDataHelper.syncGoodTree();
                commodityLayoutController.refresh();
            }else if(rm == ResultMessage.FAIL){
                //服务器删除失败
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("删除失败");
                alert.setHeaderText(null);
                alert.setContentText("尝试与服务器同步qwq");

                alert.showAndWait();

                commodityDataHelper.syncGoodTree();
            }
        } else {
            //用户取消删除
        }
    }

    public void edit(ActionEvent actionEvent) {
        if(nowSelected.getNodeType() == GoodTreeNodeType.GOOD){
            originalButtonSet.setVisible(false);
            editButtonSet.setVisible(true);

            nameField.setVisible(true);
            typeField.setVisible(true);
            warningAmountField.setVisible(true);
            buyingPriceField.setVisible(true);
            sellingPriceField.setVisible(true);

            nameField.setText(name.getText());
            typeField.setText(type.getText());
            warningAmountField.setText(warningAmount.getText());
            buyingPriceField.setText(buyingPrice.getText());
            sellingPriceField.setText(sellingPrice.getText());
        }else if(nowSelected.getNodeType() == GoodTreeNodeType.CATEGORY){
            originalButtonSet.setVisible(false);
            editButtonSet.setVisible(true);

            nameField.setVisible(true);

            nameField.setText(name.getText());
        }
    }

    public void cancel(ActionEvent actionEvent) {
        if(nowSelected == null){//新建之后的取消
            hide();
        }else if(nowSelected.getNodeType() == GoodTreeNodeType.GOOD){
            originalButtonSet.setVisible(true);
            editButtonSet.setVisible(false);

            nameField.setVisible(false);
            typeField.setVisible(false);
            warningAmountField.setVisible(false);
            buyingPriceField.setVisible(false);
            sellingPriceField.setVisible(false);
        }else if(nowSelected.getNodeType() == GoodTreeNodeType.CATEGORY){
            originalButtonSet.setVisible(true);
            editButtonSet.setVisible(false);

            nameField.setVisible(false);
        }
    }

    public void save(ActionEvent actionEvent) {

        if(nowSelected == null){
            /*
             * 新建之后的保存
             */
            if(nowAddingType == GoodTreeNodeType.CATEGORY){

                GoodTreeNode node = new Category(EmptyValue.getString(),nameField.getText(),nowAddingFatherId);

                ResultMessage rm = commodityDataHelper.addCategory(node);
                if(rm == ResultMessage.SUCCESS){
                    hide();
                    nowAddingType = GoodTreeNodeType.EMPTY;
                    nowAddingFatherId= null;

                    commodityDataHelper.syncGoodTree();
                    commodityLayoutController.refresh();

                }else if(rm == ResultMessage.FAIL){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("增加商品分类失败");
                    alert.setHeaderText(null);
                    alert.setContentText("服务器可能出了问题，请稍后再试qwq");

                    alert.showAndWait();
                }

            }else if(nowAddingType == GoodTreeNodeType.GOOD){

                try{
                    GoodTreeNode node = new Good(
                            EmptyValue.getString(),
                            nameField.getText(),
                            nowAddingFatherId,
                            typeField.getText(),
                            Integer.valueOf(warningAmountField.getText()),
                            Double.valueOf(buyingPriceField.getText()),
                            Double.valueOf(sellingPriceField.getText()),
                            0,
                            0.0,
                            0.0,
                            ""
                    );

                    if( ((Good)node).getWarningAmount() <= 0
                            || ((Good)node).getBuyingPrice()<=0
                            || ((Good)node).getSellingPrice()<=0)
                        throw new NumberFormatException();

                    ResultMessage rm = commodityDataHelper.addGood(node);
                    if(rm == ResultMessage.SUCCESS){
                        hide();
                        nowAddingType = GoodTreeNodeType.EMPTY;
                        nowAddingFatherId= null;

                        commodityDataHelper.syncGoodTree();
                        commodityLayoutController.refresh();


                    }else if(rm == ResultMessage.FAIL){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("增加商品失败");
                        alert.setHeaderText(null);
                        alert.setContentText("服务器可能出了问题，请稍后再试qwq");

                        alert.showAndWait();
                    }
                }catch (NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("输入的数据有误");
                    alert.setHeaderText(null);
                    alert.setContentText("请重新填写库存警戒数量，进价或零售价");

                    alert.showAndWait();
                }

            }


        }else if(nowSelected.getNodeType() == GoodTreeNodeType.GOOD){
            /*
             * 编辑商品之后的保存
             */
            GoodTreeNode node = new Good(
                    ((Good)nowSelected).getId(),
                    EmptyValue.getString(),
                    EmptyValue.getString(),
                    EmptyValue.getString(),
                    EmptyValue.getInteger(),
                    EmptyValue.getDouble(),
                    EmptyValue.getDouble(),
                    EmptyValue.getInteger(),
                    EmptyValue.getDouble(),
                    EmptyValue.getDouble(),
                    EmptyValue.getString()
            );
            node.setNodeType(GoodTreeNodeType.GOOD);

            try {
                String name = nameField.getText();
                String type = typeField.getText();
                Integer warningAmount = Integer.valueOf(warningAmountField.getText());
                Double buyingPrice = Double.valueOf(buyingPriceField.getText());
                Double sellingPrice = Double.valueOf(sellingPriceField.getText());
                if(warningAmount<=0 || buyingPrice<=0 || sellingPrice<=0)
                    throw new NumberFormatException();

                //不一样的字段进行赋值,一样的字段置null
                if(!this.name.getText().equals(name)){
                    ((Good)node).setName(name);
                }else name = null;
                if(!this.type.getText().equals(type)){
                    ((Good)node).setType(type);
                }else type = null;
                if(!this.warningAmount.getText().equals(warningAmount+"")){
                    ((Good)node).setWarningAmount(warningAmount);
                }else warningAmount = null;
                if(!this.buyingPrice.getText().equals(buyingPrice+"")){
                    ((Good)node).setBuyingPrice(buyingPrice);
                }else buyingPrice = null;
                if(!this.sellingPrice.getText().equals(sellingPrice+"")){
                    ((Good)node).setSellingPrice(sellingPrice);
                }else sellingPrice = null;


                ResultMessage rm = commodityDataHelper.update(node);
                if(rm == ResultMessage.SUCCESS){
                    originalButtonSet.setVisible(true);
                    editButtonSet.setVisible(false);

                    nameField.setVisible(false);
                    typeField.setVisible(false);
                    warningAmountField.setVisible(false);
                    buyingPriceField.setVisible(false);
                    sellingPriceField.setVisible(false);

                    if(name!= null){ this.name.setText(name); }
                    if(type!= null){ this.type.setText(type); }
                    if(warningAmount!= null){ this.warningAmount.setText(String.valueOf(warningAmount)); }
                    if(buyingPrice!= null){ this.buyingPrice.setText(String.valueOf(buyingPrice)); }
                    if(buyingPrice!= null){ this.sellingPrice.setText(String.valueOf(sellingPrice)); }

                    commodityLayoutController.select(
                            commodityDataHelper.getGoodTree().get(nowSelected.getFatherIndex())
                    );
                }

            }catch (NumberFormatException e){

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("输入的数据有误");
                alert.setHeaderText(null);
                alert.setContentText("请重新填写库存警戒数量，进价或零售价");

                alert.showAndWait();
            }

        }else if(nowSelected.getNodeType() == GoodTreeNodeType.CATEGORY){
            /*
             * 编辑商品分类之后的保存
             */
            GoodTreeNode node = new Category(
                    ((Category)nowSelected).getId(),
                    EmptyValue.getString(),
                    EmptyValue.getString()
            );
            node.setNodeType(GoodTreeNodeType.CATEGORY);

            String name = nameField.getText();

            if(!this.name.getText().equals(name)){
                ((Category)node).setName(name);
            }


            ResultMessage rm = commodityDataHelper.update(node);
            if(rm == ResultMessage.SUCCESS){
                originalButtonSet.setVisible(true);
                editButtonSet.setVisible(false);

                nameField.setVisible(false);

                commodityLayoutController.select(
                        commodityDataHelper.getGoodTree().get(nowSelected.getFatherIndex())
                );
            }

        }

    }

    public void hide(){
        detailsPane.setVisible(false);
    }

    public void addCategory(String id){
        nowAddingFatherId = id;
        nowAddingType = GoodTreeNodeType.CATEGORY;

        originalButtonSet.setVisible(false);
        editButtonSet.setVisible(true);

        type.setVisible(false);
        nowAmount.setVisible(false);
        warningAmount.setVisible(false);
        buyingPrice.setVisible(false);
        sellingPrice.setVisible(false);
        recentBuyingPrice.setVisible(false);
        recentSellingPrice.setVisible(false);

        typeLabel.setVisible(false);
        nowAmountLabel.setVisible(false);
        warningAmountLabel.setVisible(false);
        buyingPriceLabel.setVisible(false);
        sellingPriceLabel.setVisible(false);
        recentBuyingPriceLabel.setVisible(false);
        recentSellingPriceLabel.setVisible(false);

        this.id.setText("等待系统分配");
        nameField.setVisible(true);


    }

    public void addGood(String id){
        nowAddingFatherId = id;
        nowAddingType = GoodTreeNodeType.GOOD;

        originalButtonSet.setVisible(false);
        editButtonSet.setVisible(true);

        this.id.setText("等待系统分配");
        recentSellingPrice.setText("0.0");
        recentBuyingPrice.setText("0.0");
        nowAmount.setText("0");

        nameField.setVisible(true);
        typeField.setVisible(true);
        warningAmountField.setVisible(true);
        buyingPriceField.setVisible(true);
        sellingPriceField.setVisible(true);
    }


}