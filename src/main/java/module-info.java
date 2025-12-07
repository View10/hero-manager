module kevin.hero_manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens kevin.hero_manager to javafx.fxml;
    exports kevin.hero_manager;
}