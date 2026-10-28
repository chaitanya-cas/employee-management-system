package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Employee not found with ID: " + id
                        )
                );
    }

    public Employee updateEmployee(
            Long id,
            Employee updatedEmployee
    ) {
        Employee existingEmployee = getEmployeeById(id);

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setDepartment(
                updatedEmployee.getDepartment()
        );
        existingEmployee.setSalary(updatedEmployee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}