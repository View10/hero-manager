module kevin.hero_manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;

    opens kevin.hero_manager to javafx.fxml;
    exports kevin.hero_manager;
}