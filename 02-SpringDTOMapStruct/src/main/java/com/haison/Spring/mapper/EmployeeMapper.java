package com.haison.Spring.mapper;

import com.haison.Spring.dto.EmployeeDTO;
import com.haison.Spring.entity.Employee;

// Not recommended for use with large projects
public class EmployeeMapper {

    // Convert Employee Entity to EmployeeDTO
    public static EmployeeDTO mapperToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    // Convert EmployeeDTO to Employee Entity
    public static Employee mapperToEmployee(EmployeeDTO employeeDTO) {
        return new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmailAddress()
        );
    }
}
