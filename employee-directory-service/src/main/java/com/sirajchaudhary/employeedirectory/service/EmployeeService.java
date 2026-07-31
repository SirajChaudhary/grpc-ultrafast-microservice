package com.sirajchaudhary.employeedirectory.service;

import com.sirajchaudhary.employeedirectory.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee getEmployee(Long id);

    Page<Employee> getAllEmployees(Pageable pageable);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long id);

    Page<Employee> searchEmployees(String keyword, Pageable pageable);

    Page<Employee> findEmployeesByDepartment(String department, Pageable pageable);

    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByEmail(String email);

    long countEmployees();
}