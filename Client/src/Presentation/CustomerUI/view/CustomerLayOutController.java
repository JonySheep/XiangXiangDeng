package Presentation.CustomerUI.view;

import Presentation.CustomerUI.CustomerDataHelper;
import Presentation.CustomerUI.CustomerMainUI;
import Presentation.CustomerUI.model.Customer;
import Util.CustomerKinds;
import Util.ResultMessage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;


public class CustomerLayOutController {

    private CustomerDataHelper dataHelper=new CustomerDataHelper();

    private ObservableList<Customer> CustomerList=dataHelper.getAllCustomerList();

    private DetailLayoutController detailLayoutController;


    @FXML
    private ListView<Customer> CusList;

    @FXML
    private BorderPane CusLayout;

    private BorderPane detailsLayout;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> CusKind;

    @FXML
    private TextField searchText;




    @FXML
    /**
     *初始化方法
     */
    private void initialize(){


        CusList.setCellFactory((ListView<Customer> Cus)->new CusPreview());

        //监听和同步CustomerVO的改变
        CusList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCusDetail(newValue));

        CusKind.setItems(FXCollections.observableArrayList("全部客户","供应商","销售商"));
        CusKind.setValue("全部客户");
    }


    /**
     *显示Customer详细信息的方法
     * @param Cus
     */
    private void showCusDetail(Customer Cus){
        try{
            FXMLLoader DetailLoader=new FXMLLoader();

            DetailLoader.setLocation(CustomerMainUI.class.getResource("view/DetailsLayout.fxml"));
            detailsLayout=DetailLoader.load();

            CusLayout.setCenter(detailsLayout);

            detailLayoutController=DetailLoader.getController();
            detailLayoutController.showCusDetail(Cus);
            detailLayoutController.setLayoutController(this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     *Preview的ListCell子类，用来实现ListView
     */
    private class CusPreview extends ListCell<Customer> {
        @Override
        protected  void updateItem(Customer item, boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(CustomerMainUI.class.getResource("view/Preview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    PreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setCustomerID(item.getCustomerID());
                    controller.setCustomerName(item.getCustomerName());
                    controller.setCustomerKind(item.getKind());
                    controller.setCustomerLevel(item.getLevel());
                    controller.setComment(item.getComment());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     *新增客户按钮的响应方法
     */
    @FXML
    private void Add(){
        boolean isValid=true;

        CustomerKinds  chosenKind=CustomerKinds.EMPTY;
        if(CusKind.getValue()=="供应商"){
            chosenKind=CustomerKinds.SUPPLIER;
        }
        else if(CusKind.getValue()=="销售商"){
            chosenKind=CustomerKinds.SALER;
        }
        else if(CusKind.getValue()=="全部客户"){
            isValid=false;

            Alert addNoKind=new Alert(Alert.AlertType.WARNING);
            addNoKind.setTitle("Warning");
            addNoKind.setHeaderText(null);
            addNoKind.setContentText("请先选择客户类型_(:з」∠)_");

            addNoKind.showAndWait();
        }


        /**
         * 显示具体信息界面供用户填写
         */
        if(isValid){
            try{
                FXMLLoader DetailLoader=new FXMLLoader();

                DetailLoader.setLocation(CustomerMainUI.class.getResource("view/DetailsLayout.fxml"));
                detailsLayout=DetailLoader.load();

                CusLayout.setCenter(detailsLayout);

                detailLayoutController=DetailLoader.getController();

                Customer newCus=new Customer(chosenKind);
                newCus.setClerk(dataHelper.getUser().getName());

                detailLayoutController.setLayoutController(this);
                detailLayoutController.showCusDetail(newCus);
                detailLayoutController.setWriteMode();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 提供给CustomerUI调用的方法，用来设定CustomerListView
     */
    public void setCustomer(){
        CusList.setItems(CustomerList);
    }



    /**
     *删除客户的响应方法
     */
    @FXML
    private void Delete(){
        Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION);
        delAlert.setTitle("删除客户");
        delAlert.setHeaderText(null);
        delAlert.setContentText("确定删除？");

        Optional<ButtonType> result = delAlert.showAndWait();
        if (result.get() == ButtonType.OK){
            //用户确定删除
            ObservableList<Integer> list=  CusList.getSelectionModel().getSelectedIndices();
            if(dataHelper.delCustomer(list) == ResultMessage.SUCCESS){
                //服务器删除成功
                dataHelper.syncCustomerList(); //更新数据
                refresh(); //刷新ListView

                if(dataHelper.getAllCustomerList().size()==0){
                    detailLayoutController.hideDetailLayout();
                }

            }
            else{
                //服务器删除失败
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("删除失败");
                alert.setHeaderText(null);
                alert.setContentText("尝试与服务器同步qwq");

                alert.showAndWait();

                dataHelper.syncCustomerList();
               // refresh();
                selectFirst();
            }
        }
    }


    /**
     *根据客户输入进行搜索，并显示搜索结果
     */
    @FXML
    private void Search(){

        String str=searchText.getText();
        ResultMessage isSuccess=dataHelper.getSearchResult(str);
        //手动刷新（滑稽
        CusList.setCellFactory((ListView<Customer> CellData) -> new CusPreview());
        CusList.setItems(dataHelper.getCustomerList());

        if(isSuccess==ResultMessage.SUCCESS){
            selectFirst();
        }
        //若搜索结果不存在
        if(dataHelper.getCustomerList().size()==0){
            detailLayoutController.hideDetailLayout();
        }
    }

    @FXML
    private void CancleSearch(){
        refresh();
    }


    /**
     * 根据选择的客户类型显示相应类型的客户列表
     */
    @FXML
    private void chooseKind(){
        refresh();
    }




    /**
     * 强行刷新，不刷新不好使
     */
    public void refresh(){

        if(CusKind.getValue().equals("供应商")){
            dataHelper.setSupplier();
        }
        //若选择销售商
        else if(CusKind.getValue().equals("销售商")){
            dataHelper.setSaler();
        }
        //若选择全部客户
        else if(CusKind.getValue().equals("全部客户")){
            dataHelper.setAllCustomer();
        }

        CusList.setCellFactory((ListView<Customer> CellData) -> new CusPreview());
        CusList.setItems(dataHelper.getCustomerList());
        selectFirst();

    }


        /**
         * 默认选中开头
         */
    public void selectFirst(){
        CusList.getSelectionModel().selectFirst();
    }



    /**
     * 选中末尾
     */
    public void selectLast(){
        CusList.getSelectionModel().selectLast();
    }


    /**
     * 选中某个客户
     * @param obj
     */
    public void select(Customer obj){
        CusList.getSelectionModel().select(obj);
    }


    public CustomerDataHelper getDataHelper(){return dataHelper;}
}
