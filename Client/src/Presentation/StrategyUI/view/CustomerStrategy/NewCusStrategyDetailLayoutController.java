package Presentation.StrategyUI.view.CustomerStrategy;

import Presentation.StrategyUI.StrategyDataHelper;
import Presentation.StrategyUI.StrategyMainUI;
import Presentation.StrategyUI.view.DiscountDetailController;
import Presentation.StrategyUI.view.GiftDetailController;
import Presentation.StrategyUI.view.VoucherDetailController;
import Util.StrategyType;
import VO.CustomerStrategyVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Date;

public class NewCusStrategyDetailLayoutController {

    @FXML
    private Button giftButton;

    @FXML
    private Button discountButton;

    @FXML
    private Button VoucherButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancleButton;

    @FXML
    private BorderPane NewCusStrategyDetailLayout;

    private GiftDetailController giftController;
    private DiscountDetailController discountController;
    private VoucherDetailController voucherController;

    private Stage addStage;


    @FXML
    private void initialize(){

        /**
         * 默认显示赠送礼品界面
         */
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/GiftDetail.fxml"));

            AnchorPane GiftDetailLayout=(AnchorPane)loader.load();
            NewCusStrategyDetailLayout.setCenter(GiftDetailLayout);
            giftController=loader.getController();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void toGift(){
        /**
         * 显示赠送礼品界面
         */
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/GiftDetail.fxml"));

            AnchorPane GiftDetailLayout=(AnchorPane)loader.load();
            NewCusStrategyDetailLayout.setCenter(GiftDetailLayout);
            giftController=loader.getController();
            giftController.setCusMode();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void toDiscount(){
        /**
         * 显示价格折让界面
         */
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/DiscountDetail.fxml"));

            AnchorPane DiscountDetailLayout=(AnchorPane)loader.load();
            NewCusStrategyDetailLayout.setCenter(DiscountDetailLayout);
            discountController=loader.getController();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void toVoucher(){
        /**
         * 显示赠送代金券界面
         */
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(StrategyMainUI.class.getResource("view/VoucherDetail.fxml"));

            AnchorPane VoucherDetailLayout=(AnchorPane)loader.load();
            NewCusStrategyDetailLayout.setCenter(VoucherDetailLayout);
            voucherController=loader.getController();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 保存客户回馈策略
     * 给DataHelper对象传回数据，加入数据库
     */
    @FXML
    private void save(){
        StrategyDataHelper dataHelper=new StrategyDataHelper();
        dataHelper.addCusStrategy(StrategyType.CUSTOMER,giftController.getBegin(),giftController.getEnd()
                ,giftController.getLevel(),giftController.getGifts(),voucherController.getVoucher(),discountController.getDiscount());
        addStage.close();
    }


    /**
     * 取消客户回馈策略
     */
    @FXML
    private void cancle(){
        addStage.close();
    }


    /**
     * 设置需要关闭的界面
     * @param stage
     */
    public void setAddStage(Stage stage){
        this.addStage=stage;
    }
}
