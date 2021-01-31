package com.example.demo.service;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.Employee;

@Repository
public interface EmployeeService extends JpaRepository<Employee,Serializable>{

}
