package com.haison.Spring.repository;

import com.haison.Spring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Optional<Employee> findByEmail(String email);// Find Employee By Email
    Optional<Employee> findByLastName(String lastName);// Find Employee By Email
}
