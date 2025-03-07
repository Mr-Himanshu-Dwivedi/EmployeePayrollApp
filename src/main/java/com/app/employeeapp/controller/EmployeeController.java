package com.app.employeeapp.controller;

import com.app.employeeapp.model.EmployeeModel;
import com.app.employeeapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public EmployeeModel createEmployee(@RequestBody EmployeeModel employee) {
        return repository.save(employee);
    }

    @PutMapping("/update/{id}")
    public EmployeeModel updateEmployee(@PathVariable Long id, @RequestBody EmployeeModel employee) {
        employee.setId(id);
        return repository.save(employee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}