module com.example.patron_acces_donnees {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.patron_acces_donnees to javafx.fxml;
    exports com.example.patron_acces_donnees;
}