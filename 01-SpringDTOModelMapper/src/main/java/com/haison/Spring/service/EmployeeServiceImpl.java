package com.haison.Spring.service;

import com.haison.Spring.dto.EmployeeDTO;
import com.haison.Spring.entity.Employee;
import com.haison.Spring.mapperDTO.EmployeeMapper;
import com.haison.Spring.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor // Auto inject @Autowired
public class EmployeeServiceImpl implements  EmployeeService{

    private EmployeeRepo employeeRepo;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        // Employee employee = EmployeeMapper.mapperToEmployee(employeeDTO);

        Employee employee = modelMapper.map(employeeDTO, Employee.class); // Convert employeeDTO => Employee

        return modelMapper.map(this.employeeRepo.save(employee),EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeById(long id) {
        Employee employee= this.employeeRepo.findById(id).get();

        // return EmployeeMapper.mapperToEmployeeDTO(employee);
        return modelMapper.map(employee,EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getEmployeeAll() {

        List<Employee> listEmployee = this.employeeRepo.findAll();

//        List<EmployeeDTO> listEmployeeDTO = listEmployee.stream()
//                                                        .map(EmployeeMapper::mapperToEmployeeDTO)
//                                                        .collect(Collectors.toList());
        //
        List<EmployeeDTO> listEmployeeDTO = listEmployee.stream()
                                                        .map(employee ->  modelMapper.map(employee,EmployeeDTO.class))
                                                        .collect(Collectors.toList());
        return listEmployeeDTO;
    }

    @Override
    public EmployeeDTO putEmployee(EmployeeDTO employeeDTO) {

        Employee employee = this.employeeRepo.findById(employeeDTO.getId()).get();

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());

//        return EmployeeMapper.mapperToEmployeeDTO(this.employeeRepo.save(employee));
        return modelMapper.map(this.employeeRepo.save(employee),EmployeeDTO.class); // Convert employee => EmployeeDTO

    }

    @Override
    public void deleteEmployeeById(long id) {

        EmployeeDTO employeeDTO = this.getEmployeeById(id);

//        Employee employee = EmployeeMapper.mapperToEmployee(employeeDTO);

        Employee employee = modelMapper.map(employeeDTO,Employee.class);

        this.employeeRepo.delete(employee);

    }
}
