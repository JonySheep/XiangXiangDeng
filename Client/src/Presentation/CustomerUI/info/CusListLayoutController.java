package Presentation.CustomerUI.info;

import Presentation.CustomerUI.CustomerDataHelper;
import Presentation.CustomerUI.CustomerMainUI;
import Presentation.CustomerUI.model.Customer;
import VO.CustomerForDocVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class CusListLayoutController {

    @FXML
    private ListView<Customer> CusList;

    @FXML
    private Button okButton;

    private CustomerDataHelper dataHelper=new CustomerDataHelper();
    private CustomerSettable controller;
    private CustomerForDocVO CusForDoc;

    private Stage stage;

    private boolean okClick=false;

    @FXML
    private void initialize(){

        CusList.setItems(dataHelper.getAllCustomerList());
        CusList.setCellFactory((ListView<Customer> cus)->new CusPreview());


    }

    private class CusPreview extends ListCell<Customer> {
        @Override
        protected  void updateItem(Customer item, boolean empty){
            super.updateItem(item,empty);

            if(!empty&&item!=null){
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(CustomerMainUI.class.getResource("info/CusListPreview.fxml"));

                try{
                    AnchorPane preview=loader.load();
                    CusListPreviewController controller=loader.getController();

                    setGraphic(preview);
                    controller.setCusID(item.getCustomerID());
                    controller.setCusName(item.getCustomerName());
                    controller.setCusLevel(String.valueOf(item.getLevel()));
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void OK(){
        Customer selectCus=CusList.getSelectionModel().getSelectedItem();
        CusForDoc= new CustomerForDocVO(selectCus.getCustomerID(),selectCus.getCustomerName(),selectCus.getLevel());

        controller.setCustomer(CusForDoc);
        this.stage.close();
    }


    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setController(CustomerSettable controller){
        this.controller=controller;
    }

}
