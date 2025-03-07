package com.app.employeeapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double salary;

    public EmployeeModel() {}

    public EmployeeModel(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}