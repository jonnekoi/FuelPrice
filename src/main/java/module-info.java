module org.example.fuelprice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.fuelprice to javafx.fxml;
    exports org.example.fuelprice;
}