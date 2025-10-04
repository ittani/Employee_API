package com.example.apimanagement.Service;

import com.example.apimanagement.model.Employee;
import com.example.apimanagement.repository.EmployeeRepository;
import com.example.apimanagement.dto.EmployeeDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setPosition(employeeDTO.getPosition());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        employee.setHireDate(employeeDTO.getHireDate());
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployee(Long id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(employeeDTO.getName());
                    employee.setPosition(employeeDTO.getPosition());
                    employee.setDepartment(employeeDTO.getDepartment());
                    employee.setSalary(employeeDTO.getSalary());
                    employee.setHireDate(employeeDTO.getHireDate());
                    return employeeRepository.save(employee);
                });
    }

    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

