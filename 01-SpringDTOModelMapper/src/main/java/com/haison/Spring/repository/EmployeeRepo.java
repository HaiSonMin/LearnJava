package com.haison.Spring.repository;

import com.haison.Spring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {

}
