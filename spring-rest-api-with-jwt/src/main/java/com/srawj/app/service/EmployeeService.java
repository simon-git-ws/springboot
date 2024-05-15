package com.srawj.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.srawj.app.entity.Employee;

public interface EmployeeService {

	ResponseEntity<Employee> save(Employee employee);

	ResponseEntity<Employee> update(Employee employee, Integer id);

	ResponseEntity<List<Employee>> findAll();

	ResponseEntity<?> deleteById(Integer id);

}