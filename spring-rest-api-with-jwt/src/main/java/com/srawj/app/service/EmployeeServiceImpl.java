package com.srawj.app.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.srawj.app.entity.Employee;
import com.srawj.app.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public ResponseEntity<Employee> save(Employee employee) {
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employee));
	}
	
	@Override
	public ResponseEntity<Employee> update(Employee employee, Integer id) {
		Employee exEmployee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException());
		BeanUtils.copyProperties(employee, exEmployee);
		exEmployee.setEmployeeId(id);
		return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.save(exEmployee));
	}
	
	@Override
	public ResponseEntity<List<Employee>> findAll() {
		return ResponseEntity.ok(employeeRepository.findAll());
	}
	
	@Override
	public ResponseEntity<?> deleteById(Integer id) {
		employeeRepository.findById(id).orElseThrow(() -> new RuntimeException());
		employeeRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
