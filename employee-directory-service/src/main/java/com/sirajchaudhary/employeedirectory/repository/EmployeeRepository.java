package com.sirajchaudhary.employeedirectory.repository;

import com.sirajchaudhary.employeedirectory.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByEmail(String email);

    Page<Employee> findByDepartmentIgnoreCase(String department, Pageable pageable);

    Page<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmployeeCodeContainingIgnoreCase(
            String firstName,
            String lastName,
            String employeeCode,
            Pageable pageable
    );
}