package com.ems;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmployeePopupFormController implements Initializable {

    @FXML private Button btnSubmit;
    @FXML private Button btnCancel;
    @FXML private TextField txt_address;
    @FXML private TextField txt_age;
    @FXML private TextField txt_firstname;
    @FXML private TextField txt_gender;
    @FXML private TextField txt_lastname;

    private Employee employee;
    // private Stage popupStage; // Reference to the popup stage
    private EmployeeController employeeController;
    private final EmployeeService employeeService = new EmployeeService();

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSubmit.setOnAction(event -> handleSubmitAction());
        btnCancel.setOnAction(event -> handleCancelAction());
    }

    @FXML
    private void handleSubmitAction() {
        try {

            Employee newEmployee = new Employee();
            newEmployee.setFirstName(txt_firstname.getText());
            newEmployee.setLastName(txt_lastname.getText());
            newEmployee.setGender(txt_gender.getText());
            newEmployee.setAddress(txt_address.getText());

            try {
                newEmployee.setAge(Integer.parseInt(txt_age.getText()));
            } catch (NumberFormatException e) {
                newEmployee.setAge(0); // Default value if parsing fails
            }

            if (employee == null) {
                employeeService.addEmployee(newEmployee);
            } else {
                System.out.println("update");
                newEmployee.setId(employee.getId());
                employeeService.updateEmployee(newEmployee);
            }
            employeeController.refreshEmployeeList();
            closeWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle Cancel Button Click
    @FXML
    private void handleCancelAction() {
        employee = null; // Reset if canceled
        closeWindow();

    }

    public void setEmployeeData(Employee employee) {
        if (employee == null) {
            return;
        }

        this.employee = employee;
        if (txt_firstname != null) {
            txt_firstname.setText(employee.getFirstName());
        }
        if (txt_lastname != null) {
            txt_lastname.setText(employee.getLastName());
        }
        if (txt_gender != null) {
            txt_gender.setText(employee.getGender());
        }
        if (txt_age != null) {
            txt_age.setText(String.valueOf(employee.getAge()));
        }
        if (txt_address != null) {
            txt_address.setText(employee.getAddress());
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) btnSubmit.getScene().getWindow();
        stage.close();
    }

}
