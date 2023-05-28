package com.haison.Spring.controller;

import com.haison.Spring.dto.EmployeeDTO;
import com.haison.Spring.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag( // Config for Swagger ( http://localhost:9000/swagger-ui/index.html )
        name = "CRUD REST APIs for Employee Resource",
        description = "CRUD REST APIs: Create Employee, Get Employee, Get All Employee, Update Employee, Delete Employee"
)
@RestController
@RequestMapping("api")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @Operation(
            summary = "Create Employee with REST API",
            description = "Create Employee with REST API and save it into Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status:201 CREATED"
    )
    @PostMapping("employee")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {

        EmployeeDTO employeeCreate = this.employeeService.createEmployee(employeeDTO);

       return new ResponseEntity<>(employeeCreate, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Employee by ID with REST API",
            description = "Get Employee by ID with REST API from Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status:200 OK"
    )
    @GetMapping("employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "id") long employeeId) {
        EmployeeDTO employeeGetById = this.employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeGetById,HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Employee with REST API",
            description = "Get All Employee with REST API from Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status:200 OK"
    )
    @GetMapping("employee")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeAll() {

        List<EmployeeDTO> employeeList = this.employeeService.getEmployeeAll();

        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    @Operation(
            summary = "Update Employee by ID with REST API",
            description = "Update Employee by ID with REST API and update for Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status:200 OK"
    )
    @PutMapping("employee")
    public ResponseEntity<EmployeeDTO> putEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeDTO employeePut = this.employeeService.putEmployee(employeeDTO);
        return new ResponseEntity<>(employeePut,HttpStatus.OK);
    }


    @Operation(
            summary = "Delete Employee by ID with REST API",
            description = "Delete Employee by ID with REST API and delete into Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status:200 OK"
    )
    @DeleteMapping("employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable(name = "id") long employeeId) {
        this.employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }
}
