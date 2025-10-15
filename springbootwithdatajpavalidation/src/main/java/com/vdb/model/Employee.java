package com.vdb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    @Pattern(regexp = "^[A-Za-z]+$", message = "First name should contain only alphabets without space or digits")
    private String empFirstName;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name should contain only alphabets without space or digits")
    private String empLastName;

    @Size(min = 2, message = "Address must contain at least 2 characters")
    private String empAddress;

    @NotNull
    private long empContactNumber;

    @Positive(message = "Salary must be positive")
    private double empSalary;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDob;

    @Email(message = "Email should be valid and not empty")
    private String empEmailId;

    @Size(min = 3, max = 9, message = "Password must contain between 3 and 9 characters")
    private String empPassword;
}
