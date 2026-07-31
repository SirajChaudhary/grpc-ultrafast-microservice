package com.sirajchaudhary.employeedirectory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name = "employees",
        indexes = {
                @Index(name = "idx_employee_code", columnList = "employee_code"),
                @Index(name = "idx_email", columnList = "email"),
                @Index(name = "idx_department", columnList = "department")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "employee_code", nullable = false, unique = true, length = 20)
    private String employeeCode;

    @NotBlank
    @Size(max = 50)
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Size(max = 15)
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @NotBlank
    @Size(max = 50)
    @Column(name = "department", nullable = false, length = 50)
    private String department;

    @NotBlank
    @Size(max = 50)
    @Column(name = "designation", nullable = false, length = 50)
    private String designation;

    @Size(max = 100)
    @Column(name = "office_location", length = 100)
    private String officeLocation;

    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @NotBlank
    @Size(max = 20)
    @Column(name = "status", nullable = false, length = 20)
    private String status;
}