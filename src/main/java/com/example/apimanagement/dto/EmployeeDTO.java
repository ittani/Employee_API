package com.example.apimanagement.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Position is mandatory")
    private String position;

    @NotBlank(message = "Department is mandatory")
    private String department;

    @NotNull(message = "Salary is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    private BigDecimal salary;

    @NotNull(message = "Hire date is mandatory")
    @PastOrPresent(message = "Hire date cannot be in the future")
    private LocalDate hireDate;
}

