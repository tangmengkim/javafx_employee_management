module com.ems {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens com.ems to javafx.fxml;
    exports com.ems;

    // opens com.ems.controller to javafx.fxml;
    // exports com.ems.controller;
    
    // opens com.ems.db to javafx.fxml;
    // exports com.ems.db;
    
    // opens com.ems.model to javafx.fxml;
    // exports com.ems.model;

    // opens com.ems.service to javafx.fxml;
    // exports com.ems.service;
}
