package kevin.hero_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {
    @FXML TextField name;
    @FXML ComboBox<String> hClass;
    @FXML TextField hp;
    @FXML ComboBox<String> sa;
    @FXML Button cancel;
    @FXML Button add;

    @FXML
    private void switchToMainScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addHero(){
        HeroDAO.add(new Hero(name.getText(), HeroClass.valueOf(hClass.getSelectionModel().getSelectedItem().toUpperCase()), Integer.parseInt(hp.getText()), SpecialAbility.valueOf(sa.getSelectionModel().getSelectedItem().toUpperCase())));
        cancel.fire();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hClass.getItems().addAll("Knight", "Barbarian", "Mage", "Archer", "Assassin", "Druid");
        sa.getItems().addAll("Doom", "Clearance", "Divine_Fire", "Arrow_Parade");
    }
}
