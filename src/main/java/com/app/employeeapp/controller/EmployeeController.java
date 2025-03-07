package com.app.employeeapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Welcome to the Employee Payroll App!";
    }
}