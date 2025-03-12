package com.app.employeeapp.service;

import com.app.employeeapp.dto.EmployeeDTO;
import com.app.employeeapp.model.EmployeeModel;
import com.app.employeeapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<EmployeeModel> getAllEmployees() {
        log.info("Retrieving all employees");
        return repository.findAll();
    }

    @Override
    public Optional<EmployeeModel> getEmployeeById(Long id) {
        log.info("Searching for employee with ID: {}", id);
        return repository.findById(id);
    }

    @Override
    public EmployeeModel createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating new employee: {}", employeeDTO.getName());
        EmployeeModel employee = new EmployeeModel(employeeDTO.getName(), employeeDTO.getSalary());
        return repository.save(employee);
    }

    @Override
    public Optional<EmployeeModel> updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        if (!repository.existsById(id)) {
            log.error("Error: Employee not found with ID {}", id);
            return Optional.empty();
        }
        EmployeeModel employee = new EmployeeModel(employeeDTO.getName(), employeeDTO.getSalary());
        employee.setId(id);
        return Optional.of(repository.save(employee));
    }

    @Override
    public boolean deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        if (!repository.existsById(id)) {
            log.error("Error: Employee not found with ID {}", id);
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
