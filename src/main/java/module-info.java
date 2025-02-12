module lk.ijse.gdse.professionalsoftwareproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires mysql.connector.j;
    requires net.sf.jasperreports.core;
    requires java.desktop;
    requires commons.collections;


    opens lk.ijse.gdse.professionalsoftwareproject to javafx.fxml;
    opens lk.ijse.gdse.professionalsoftwareproject.view.tm to javafx.base;
    exports lk.ijse.gdse.professionalsoftwareproject;
    exports lk.ijse.gdse.professionalsoftwareproject.controller;
    opens lk.ijse.gdse.professionalsoftwareproject.controller to javafx.fxml;
}