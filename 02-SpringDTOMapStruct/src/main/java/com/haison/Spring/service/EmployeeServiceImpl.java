package com.haison.Spring.service;

import com.haison.Spring.dto.EmployeeDTO;
import com.haison.Spring.entity.Employee;
import com.haison.Spring.exception.EmailAlreadyExistsException;
import com.haison.Spring.exception.EmployeeNotFoundException;
import com.haison.Spring.mapper.AutoEmployeeMapper;
import com.haison.Spring.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor // Auto inject @Autowired
public class EmployeeServiceImpl implements  EmployeeService{

    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        Optional<Employee> optionalEmployee = this.employeeRepo.findByEmail(employeeDTO.getEmailAddress());

        // Check Email have exists with findByEmail
        if(optionalEmployee.isPresent()) throw new EmailAlreadyExistsException("Email Already Exist!!!Please enter New Email");

        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDTO); // Convert employeeDTO => Employee

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDTO(this.employeeRepo.save(employee));
    }

    @Override
    public EmployeeDTO getEmployeeById(long id) {
        // If have error is NotFound then throw new Exception
        Employee employee= this.employeeRepo.findById(id).orElseThrow(()->{
            return new EmployeeNotFoundException("Employee","ID",id);
        });
        // return EmployeeMapper.mapperToEmployeeDTO(employee);
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeeAll() {

        List<Employee> listEmployee = this.employeeRepo.findAll();

//        List<EmployeeDTO> listEmployeeDTO = listEmployee.stream()
//                                                        .map(EmployeeMapper::mapperToEmployeeDTO)
//                                                        .collect(Collectors.toList());

        return listEmployee.stream().map(employee -> AutoEmployeeMapper.MAPPER.mapToEmployeeDTO(employee))
                                                        .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO putEmployee(EmployeeDTO employeeDTO) {

        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(this.getEmployeeById(employeeDTO.getId()));

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmailAddress());

//        return EmployeeMapper.mapperToEmployeeDTO(this.employeeRepo.save(employee));
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDTO(this.employeeRepo.save(employee)); // Convert employee => EmployeeDTO

    }

    @Override
    public void deleteEmployeeById(long id) {

        EmployeeDTO employeeDTO = this.getEmployeeById(id);

//        Employee employee = EmployeeMapper.mapperToEmployee(employeeDTO);

        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDTO);

        this.employeeRepo.delete(employee);

    }
}
