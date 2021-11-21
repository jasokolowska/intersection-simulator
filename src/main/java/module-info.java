module com.example.intersectionsimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.intersectionsimulator to javafx.fxml;
    exports com.example.intersectionsimulator;
}