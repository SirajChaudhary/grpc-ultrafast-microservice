package com.sirajchaudhary.employeedirectory.service.impl;

import com.sirajchaudhary.employeedirectory.entity.Employee;
import com.sirajchaudhary.employeedirectory.exception.EmployeeAlreadyExistsException;
import com.sirajchaudhary.employeedirectory.exception.EmployeeNotFoundException;
import com.sirajchaudhary.employeedirectory.repository.EmployeeRepository;
import com.sirajchaudhary.employeedirectory.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {

        if (employeeRepository.existsByEmployeeCode(employee.getEmployeeCode())) {
            throw new EmployeeAlreadyExistsException("employeeCode", employee.getEmployeeCode());
        }

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new EmployeeAlreadyExistsException("email", employee.getEmail());
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {

        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        Employee existingEmployee = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));

        existingEmployee.setEmployeeCode(employee.getEmployeeCode());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setOfficeLocation(employee.getOfficeLocation());
        existingEmployee.setJoiningDate(employee.getJoiningDate());
        existingEmployee.setStatus(employee.getStatus());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.delete(employee);
    }

    @Override
    public Page<Employee> searchEmployees(String keyword, Pageable pageable) {

        return employeeRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmployeeCodeContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword,
                        pageable
                );
    }

    @Override
    public Page<Employee> findEmployeesByDepartment(String department, Pageable pageable) {

        return employeeRepository.findByDepartmentIgnoreCase(department, pageable);
    }

    @Override
    public boolean existsByEmployeeCode(String employeeCode) {

        return employeeRepository.existsByEmployeeCode(employeeCode);
    }

    @Override
    public boolean existsByEmail(String email) {

        return employeeRepository.existsByEmail(email);
    }

    @Override
    public long countEmployees() {

        return employeeRepository.count();
    }
}