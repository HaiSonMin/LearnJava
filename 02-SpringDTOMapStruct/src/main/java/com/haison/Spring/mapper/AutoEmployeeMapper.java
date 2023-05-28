package com.haison.Spring.mapper;

import com.haison.Spring.dto.EmployeeDTO;
import com.haison.Spring.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoEmployeeMapper {

    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);

    @Mapping(source = "email", target = "emailAddress") // Convert from source Employee to source EmployeeDTO
    EmployeeDTO mapToEmployeeDTO(Employee employee);

    @Mapping(source = "emailAddress", target = "email") // Convert from source EmployeeDTO to source Employee

    Employee mapToEmployee(EmployeeDTO employeeDTO);
}
