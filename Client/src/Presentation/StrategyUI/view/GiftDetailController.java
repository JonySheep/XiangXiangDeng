package Presentation.StrategyUI.view;

import Presentation.CommodityUI.GoodListSettable;
import Presentation.CommodityUI.GoodSelector;
import Presentation.StrategyUI.StrategyMainUI;
import Util.GiftItem;
import Util.GoodSelectItem;
import VO.AmountStrategyVO;
import VO.GoodItemForPurchaseSaleDocVO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GiftDetailController implements GoodListSettable{

    @FXML
    private ComboBox<String> level;

    @FXML
    private TextArea Amount;

    @FXML
    private Text Type;

    @FXML
    private DatePicker beginPicker;

    @FXML
    private DatePicker endPicker;

    @FXML
    private ListView<GiftItem> giftList;

    @FXML
    private Button addItemButton;

    /** 用于构造ListView的列表 */
    private ArrayList<GiftItem> giftListArrayList=new ArrayList<>();
    /** GoodSelector实际的列表 */
    private ArrayList<GoodSelectItem> goodSelectItems = new ArrayList<>();

    /**
     * 初始化GiftDetail页面
     * 主要是设置DatePicker的一些属性、初始化ListView
     */
    @FXML
    private void initialize() {
        giftList.setCellFactory((ListView<GiftItem> gifts) -> new giftPreview());
        level.setItems(FXCollections.observableArrayList("1","2","3","4","5"));
    }

    @Override
    public void setGoodList(ArrayList<GoodSelectItem> list) {

        goodSelectItems = list;
        giftListArrayList.clear();
        for(GoodSelectItem good:list){
            giftListArrayList.add(new GiftItem(good.getName(),good.getId(),good.getNumber()));
        }

        giftList.setItems(FXCollections.observableArrayList(giftListArrayList));
    }


    public class giftPreview extends ListCell<GiftItem>{
        @Override
        protected  void updateItem(GiftItem item, boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(StrategyMainUI.class.getResource("view/giftPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    giftPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setGoodName(item.getGoodName());
                    controller.setGoodID(item.getGoodID());
                    controller.setNumber(item.getNumber());

                    giftListArrayList.add(item);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 选择赠送的商品，在选择商品界面的controller中完成对GiftItemVO的包装
     * @return
     */
    @FXML
    private void addItem(){

        /**
         * 显示商品树进行商品选择
         * 点击确定后返回一个GiftItemVO对象
         */

        Stage stage=new Stage();
        //FIXME GoodSelector的构造参数修改了
        GoodSelector selector=new GoodSelector(stage,this,goodSelectItems);
    }


    //getter
    public int getLevel(){return Integer.parseInt(level.getValue());}

    public LocalDate getBegin(){return beginPicker.getValue();}

    public LocalDate getEnd(){return endPicker.getValue();}

    public ArrayList<GiftItem> getGifts(){return this.giftListArrayList;}

    public double getAmount(){return Double.parseDouble(Amount.getText());
    }

    public void setAmouMode(){
        level.setVisible(false);
        Amount.setVisible(true);
        Type.setText("总额额度");
    }

    public void setCusMode(){
        level.setVisible(true);
        Amount.setVisible(false);
        Type.setText("总额额度");
    }


}
