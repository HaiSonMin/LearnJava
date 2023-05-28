package com.haison.Spring.service;

import com.haison.Spring.dto.EmployeeDTO;
import com.haison.Spring.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeById(long id);
    List<EmployeeDTO> getEmployeeAll();
    EmployeeDTO putEmployee(EmployeeDTO employeeDTO);
    void deleteEmployeeById(long id);

}
