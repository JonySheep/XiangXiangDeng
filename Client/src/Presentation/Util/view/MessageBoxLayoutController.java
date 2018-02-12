package Presentation.Util.view;

import Presentation.MainUI.view.FinanceRootLayoutController;
import Presentation.MainUI.view.InventoryRootLayoutController;
import Presentation.MainUI.view.ManagerRootLayoutController;
import Presentation.MainUI.view.PurchaseSaleRootLayoutController;
import Util.Message;
import Util.ResultMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;

public class MessageBoxLayoutController {

    public ListView<Message> messageList;
    private InventoryRootLayoutController rootLayoutController;
    private PurchaseSaleRootLayoutController PurrootLayoutController;
    private FinanceRootLayoutController FinrootLayoutController;
    private ManagerRootLayoutController ManrootLayoutController;

    public void setRootLayoutController(InventoryRootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }

    public void setRootLayoutController(PurchaseSaleRootLayoutController rootLayoutController) {
        this.PurrootLayoutController = rootLayoutController;
    }

    public void setRootLayoutController(FinanceRootLayoutController rootLayoutController) {
        this.FinrootLayoutController = rootLayoutController;
    }

    public void setRootLayoutController(ManagerRootLayoutController rootLayoutController) {
        this.ManrootLayoutController = rootLayoutController;
    }

    public void delete(Message message) {
        if(message == null){
            return;
        }
        ResultMessage rm =  rootLayoutController.deleteMessage(message);
        if(rm == ResultMessage.SUCCESS){
            messageList.getItems().remove(message);

            messageList.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
                @Override
                public ListCell<Message> call(ListView<Message> param) {
                    return new MessagePreview();
                }
            });
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("删除失败");
            alert.setHeaderText(null);
            alert.setContentText("删除失败qwq");

            alert.showAndWait();
        }

    }


    /**
     * 消息单元的类
     */
    private class MessagePreview extends ListCell<Message> {
        @Override
        protected void updateItem(Message item, boolean empty) {
            super.updateItem(item, empty);

            if (!empty && item != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MessageBoxLayoutController.class.getResource("MessageCellLayout.fxml"));

                try {
                    AnchorPane preview = loader.load();
                    MessageCellLayoutController controller = loader.getController();

                    controller.setMessageBoxLayoutController(MessageBoxLayoutController.this);
                    setGraphic(preview);
                    controller.setContent(item.getContent());
                    controller.setMessage(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    @FXML
    public void initialize(){
        messageList.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> param) {
                return new MessagePreview();
            }
        });

        messageList.getItems().add(new Message("写不完啦！","00001"));
        messageList.getItems().add(new Message("真的写不完啦！","00002"));

    }


}
