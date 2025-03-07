package com.app.employeeapp.service;

import com.app.employeeapp.dto.EmployeeDTO;
import com.app.employeeapp.model.EmployeeModel;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeModel> getAllEmployees();
    Optional<EmployeeModel> getEmployeeById(Long id);
    EmployeeModel createEmployee(EmployeeDTO employeeDTO);
    Optional<EmployeeModel> updateEmployee(Long id, EmployeeDTO employeeDTO);
    boolean deleteEmployee(Long id);
}