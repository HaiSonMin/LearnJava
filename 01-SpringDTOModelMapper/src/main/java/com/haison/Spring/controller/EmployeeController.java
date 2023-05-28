package com.haison.Spring.controller;

import com.haison.Spring.dto.EmployeeDTO;
import com.haison.Spring.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("employee")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO employeeCreate = this.employeeService.createEmployee(employeeDTO);

       return new ResponseEntity<>(employeeCreate, HttpStatus.CREATED);
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "id") long employeeId) {
        EmployeeDTO employeeGetById = this.employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeGetById,HttpStatus.OK);
    }

    @GetMapping("employee")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeAll() {

        List<EmployeeDTO> employeeList = this.employeeService.getEmployeeAll();

        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    @PutMapping("employee")
    public ResponseEntity<EmployeeDTO> putEmployeeById(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employeePut = this.employeeService.putEmployee(employeeDTO);
        return new ResponseEntity<>(employeePut,HttpStatus.OK);
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable(name = "id") long employeeId) {
        this.employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }
}
