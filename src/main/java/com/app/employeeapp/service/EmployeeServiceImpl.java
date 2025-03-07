package com.app.employeeapp.service;

import com.app.employeeapp.dto.EmployeeDTO;
import com.app.employeeapp.model.EmployeeModel;
import com.app.employeeapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<EmployeeModel> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Optional<EmployeeModel> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    @Override
    public EmployeeModel createEmployee(EmployeeDTO employeeDTO) {
        EmployeeModel employee = new EmployeeModel(employeeDTO.getName(), employeeDTO.getSalary());
        return repository.save(employee);
    }

    @Override
    public Optional<EmployeeModel> updateEmployee(Long id, EmployeeDTO employeeDTO) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }
        EmployeeModel employee = new EmployeeModel(employeeDTO.getName(), employeeDTO.getSalary());
        employee.setId(id);
        return Optional.of(repository.save(employee));
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
