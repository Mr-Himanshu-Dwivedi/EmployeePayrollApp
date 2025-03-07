package com.app.employeeapp.controller;

import com.app.employeeapp.dto.EmployeeDTO;
import com.app.employeeapp.model.EmployeeModel;
import com.app.employeeapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/hello")
    public String sayHello() {
        return "Welcome to the Employee Payroll App!";
    }

    @GetMapping("/get/all")
    public List<EmployeeModel> getAllEmployees() {
        return repository.findAll();
    }

    @GetMapping("/get/{id}")
    public EmployeeModel getEmployeeById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Employee not found with ID " + id));
    }


    @PostMapping("/create")
    public EmployeeModel createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeModel employee = new EmployeeModel(employeeDTO.getName(), employeeDTO.getSalary());
        return repository.save(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).body("Error: Employee not found with ID " + id);
        }
        EmployeeModel employee = new EmployeeModel(employeeDTO.getName(), employeeDTO.getSalary());
        employee.setId(id);
        return ResponseEntity.ok(repository.save(employee));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).body("Error: Employee not found with ID " + id);
        }
        repository.deleteById(id);
        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
    }
}