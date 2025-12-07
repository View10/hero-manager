package kevin.hero_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller1 implements Initializable {
    @FXML private Label name;
    @FXML private Label hClass;
    @FXML private Label hp;
    @FXML private Label sa;
    @FXML private Button add;
    @FXML private Button edit;
    @FXML private Button delete;
    @FXML private Button save;
    @FXML private ListView<Hero> list;

    @FXML
    protected void switchToAddScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-view.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToUpdateScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("update-view.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteHero(){
        HeroDAO.delete(HeroDAO.selectedHero);
        list.getItems().remove(HeroDAO.selectedHero);
        list.refresh();
        setDefaultHeroDetails();
    }

    @FXML
    private void saveToCSV(){
        Alert alert = null;

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heroes")))) {
            for(Hero h : list.getItems()){
                out.write(h.toCSV() + "\n");
            }

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data saved");
            alert.setContentText("All the data was saved to heroes.csv file!");

            Optional<ButtonType> result = alert.showAndWait();
        }catch (IOException e){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Something went wrong!");
            alert.setContentText("Data not saved to .csv file!");
            alert.showAndWait();
        }
    }

    private void setDefaultHeroDetails(){
        HeroDAO.selectedHero = null;
        name.setText("Hero");
        hClass.setText("None");
        hp.setText("None");
        sa.setText("None");
    }

    private void showHeroDetails(Hero h){
        HeroDAO.selectedHero = h;
        name.setText(h.getName());
        hClass.setText(h.gethClass().toString());
        hp.setText(h.getHp() + "");
        sa.setText(h.getSa().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.setItems(HeroDAO.getData());

        list.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> showHeroDetails(newVal)
        );
    }
}
