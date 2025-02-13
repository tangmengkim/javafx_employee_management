package com.ems;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeeController implements Initializable {

    @FXML private Button btnAdd;
    @FXML private Button btnRefresh;
    @FXML private Button btnSearch;
    @FXML private VBox employeeItemLayout;
    @FXML private TextField tfSearch;

    private List<Employee> employeeList; // Original full list of employees
    private List<Employee> filteredEmployees; // Filtered list for searching
    private boolean sortAscending = true; // Toggle sorting order 
    private final EmployeeService employeeService = new EmployeeService();

    @FXML void onEnter(ActionEvent ae) { filteredEmployees(); }
    @FXML void handleBtnSearch(ActionEvent event) { filteredEmployees(); }
    @FXML void handleBtnRefresh(ActionEvent event) { refreshEmployeeList(); }
    @FXML void handleBtnAddEmployee(ActionEvent event) { openPopup(null); }

    @FXML
    void sortByID(MouseEvent event) {
        Collections.sort(employeeList, sortAscending ? Comparator.comparing(Employee::getId) : Comparator.comparing(Employee::getId).reversed());
        sortAscending = !sortAscending;
        displayEmployees(employeeList);
    }

    @FXML
    void sortByFirstName(MouseEvent event) {
        Collections.sort(employeeList, sortAscending ? Comparator.comparing(Employee::getFirstName) : Comparator.comparing(Employee::getFirstName).reversed());
        sortAscending = !sortAscending;
        displayEmployees(employeeList);
    }

    @FXML
    void sortByLastName(MouseEvent event) {
        Collections.sort(employeeList, sortAscending ? Comparator.comparing(Employee::getLastName) : Comparator.comparing(Employee::getLastName).reversed());
        sortAscending = !sortAscending;
        displayEmployees(employeeList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEmployees();
    }

    private List<Employee> employees() {
        EmployeeService empService = new EmployeeService();
        return empService.getAllEmployees();
    }

    private void loadEmployees() {
        employeeItemLayout.getChildren().clear();
        employeeList = employees(); // Load full employee list
        filteredEmployees = employeeList; // Initially, show all employees
        displayEmployees(filteredEmployees);
    }
    
    public void refreshEmployeeList() {
        loadEmployees();
        System.out.println("Employee list refreshed!");
    }

    public void deleteEmployee(Employee employee){
        employeeService.deleteEmployee(employee);
        refreshEmployeeList();
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
    private void displayEmployees(List<Employee> employees) {
        employeeItemLayout.getChildren().clear();
        employees.forEach(employee -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee_item.fxml"));
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
    private void filteredEmployees(){
        String keyword = tfSearch.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            filteredEmployees = employeeList; // Reset to full list
        } else {
            filteredEmployees = employeeList.stream()
                .filter(emp -> emp.getFirstName().toLowerCase().contains(keyword) ||
                               emp.getLastName().toLowerCase().contains(keyword) ||
                               String.valueOf(emp.getId()).contains(keyword)) // Search by ID too
                .collect(Collectors.toList());
        }
        displayEmployees(filteredEmployees);
    }
}
