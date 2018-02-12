package Presentation.Util.view;

import Util.Message;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MessageCellLayoutController {

    @FXML
    private Text text;

    private Message message;

    private MessageBoxLayoutController messageBoxLayoutController;


    public void setContent(String content) {
        this.text.setText(content);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void delete(MouseEvent mouseEvent) {
        messageBoxLayoutController.delete(message);
    }

    public void setMessageBoxLayoutController(MessageBoxLayoutController messageBoxLayoutController) {
        this.messageBoxLayoutController = messageBoxLayoutController;
    }
}
