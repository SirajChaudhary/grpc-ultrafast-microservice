package com.sirajchaudhary.employeedirectory.mapper;

import com.sirajchaudhary.employeedirectory.entity.Employee;
import com.sirajchaudhary.employeedirectory.grpc.CreateEmployeeRequest;
import com.sirajchaudhary.employeedirectory.grpc.EmployeeMessage;
import com.sirajchaudhary.employeedirectory.grpc.UpdateEmployeeRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeeMapper {

    public Employee toEntity(CreateEmployeeRequest request) {

        return Employee.builder()
                .employeeCode(request.getEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .officeLocation(request.getOfficeLocation())
                .joiningDate(LocalDate.parse(request.getJoiningDate()))
                .status(request.getStatus())
                .build();
    }

    public Employee toEntity(UpdateEmployeeRequest request) {

        return Employee.builder()
                .id(request.getId())
                .employeeCode(request.getEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .officeLocation(request.getOfficeLocation())
                .joiningDate(LocalDate.parse(request.getJoiningDate()))
                .status(request.getStatus())
                .build();
    }

    public EmployeeMessage toMessage(Employee employee) {

        return EmployeeMessage.newBuilder()
                .setId(employee.getId())
                .setEmployeeCode(employee.getEmployeeCode())
                .setFirstName(employee.getFirstName())
                .setLastName(employee.getLastName())
                .setEmail(employee.getEmail())
                .setPhoneNumber(employee.getPhoneNumber())
                .setDepartment(employee.getDepartment())
                .setDesignation(employee.getDesignation())
                .setOfficeLocation(employee.getOfficeLocation())
                .setJoiningDate(employee.getJoiningDate().toString())
                .setStatus(employee.getStatus())
                .build();
    }
}