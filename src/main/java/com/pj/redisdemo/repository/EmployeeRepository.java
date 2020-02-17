package com.pj.redisdemo.repository;

import com.pj.redisdemo.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String>
{
}
