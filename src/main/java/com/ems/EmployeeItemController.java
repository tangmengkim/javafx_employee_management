package com.ems;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EmployeeItemController implements Initializable {


    @FXML private Label id;
    @FXML private Label firstname;
    @FXML private Label lastname;
    @FXML private Label age;
    @FXML private Label address;
    @FXML private Label gender;
    @FXML private Button btnDelete;
    @FXML private Button btnEdit;

    private EmployeeController employeeController;
    private Employee employee;
    
    @FXML
    void handleBtnDelete(ActionEvent event) {
        if(employee != null){
            employeeController.deleteEmployee(employee);
        }
    }

    @FXML
    void handleBtnEdit(ActionEvent event) {
        if(employee != null){
            employeeController.openPopup(employee);
        }
    }


    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }
    

    public void setData(Employee employee){
        try {
            id.setText(Integer.toString(employee.getId()));
            firstname.setText(employee.getFirstName());
            lastname.setText(employee.getLastName());
            gender.setText(employee.getGender());
            age.setText(Integer.toString(employee.getAge()));
            address.setText(employee.getAddress());
            this.employee = employee;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }
}
