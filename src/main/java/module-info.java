module com.example.tidsrejseagenturet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.example.tidsrejseagenturet;


    opens com.example.tidsrejseagenturet to javafx.fxml;
    exports com.example.tidsrejseagenturet;
}