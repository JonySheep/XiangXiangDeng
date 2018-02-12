package Presentation.UserUI.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PreviewController {
    @FXML
    private Label username;

    @FXML
    private Label role;

    public String getUsername() {
        return username.getText();
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public String getRole() {
        return role.getText();
    }

    public void setRole(String role) {
        this.role.setText(role);
    }

}
