package kevin.hero_manager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller1 {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
