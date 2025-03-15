package com.app.employeeapp.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import com.app.employeeapp.dto.EmployeeDTO;
import com.app.employeeapp.model.EmployeeModel;
import com.app.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/hello")
    public String sayHello() {
        log.info("Saying hello to user");
        return "Welcome to the Employee Payroll App!";
    }

    @GetMapping("/get/all")
    public List<EmployeeModel> getAllEmployees() {
        log.info("Fetching all employees");
        return service.getAllEmployees();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        Optional<EmployeeModel> employee = service.getEmployeeById(id);
        return employee.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Employee not found with ID " + id));
    }

    @PostMapping("/create")
    public EmployeeModel createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("Creating new employee: {}", employeeDTO.getName());
        return service.createEmployee(employeeDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        Optional<EmployeeModel> updatedEmployee = service.updateEmployee(id, employeeDTO);
        return updatedEmployee.map(emp -> ResponseEntity.ok("Employee updated successfully with ID " + id))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Employee not found with ID " + id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: {}", id);
        boolean deleted = service.deleteEmployee(id);
        if (!deleted) {
            return ResponseEntity.status(404).body("Error: Employee not found with ID " + id);
        }
        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
    }
}
