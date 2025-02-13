package com.ems;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeeController implements Initializable {

    @FXML private Button btnAdd;
    @FXML private Button btnRefresh;
    @FXML private Button btnSearch;
    @FXML private VBox employeeItemLayout;

    private final EmployeeService employeeService = new EmployeeService();

    @FXML
    void handleBtnRefresh(ActionEvent event){
        refreshEmployeeList();
    }
    @FXML
    void handleBtnAddEmployee(ActionEvent event) {
        openPopup(null);
    }

    private void addEmployeeToList(Employee employee) {
        // Logic to update the UI with new employee
        System.out.println("New Employee Added: " + employee.getFirstName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEmployees();
    }
    private void loadEmployees() {
        // Clear the VBox before adding new items
        employeeItemLayout.getChildren().clear();

        // Fetch updated employee list
        List<Employee> employees = employees();

        // Populate VBox with new data
        employees.forEach((employee) -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("employee_item.fxml"));

            try {
                HBox hBox = fxmlLoader.load();
                EmployeeItemController eic = fxmlLoader.getController();
                eic.setEmployeeController(this);
                eic.setData(employee);
                employeeItemLayout.getChildren().add(hBox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void openPopup(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("employee_form.fxml"));
            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(loader.load()));
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle(employee == null ? "Add Item" : "Update Item");

            EmployeePopupFormController controller = loader.getController();
            controller.setEmployeeController(this);
            controller.setEmployeeData(employee);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void refreshEmployeeList() {
        loadEmployees();
        System.out.println("Employee list refreshed!");
    }
    public void setItems() {

    }
    public void deleteEmployee(Employee employee){
        employeeService.deleteEmployee(employee);
        refreshEmployeeList();
    }
    private List<Employee> employees() {
        EmployeeService empService = new EmployeeService();
        return empService.getAllEmployees();
    }
}
