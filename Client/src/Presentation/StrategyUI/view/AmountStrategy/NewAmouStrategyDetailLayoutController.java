package Presentation.StrategyUI.view.AmountStrategy;

import Presentation.StrategyUI.StrategyDataHelper;
import Presentation.StrategyUI.StrategyMainUI;
import Presentation.StrategyUI.view.DiscountDetailController;
import Presentation.StrategyUI.view.GiftDetailController;
import Presentation.StrategyUI.view.VoucherDetailController;
import Util.StrategyType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NewAmouStrategyDetailLayoutController {

    @FXML
    private Button giftButton;

    @FXML
    private Button VoucherButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancleButton;

    @FXML
    private BorderPane NewCusStrategyDetailLayout;

    private GiftDetailController giftController;
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
            giftController.setAmouMode();
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
        dataHelper.addAmouStrategy(StrategyType.CUSTOMER,giftController.getBegin(),giftController.getEnd()
                ,giftController.getAmount(),giftController.getGifts(),voucherController.getVoucher());
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
