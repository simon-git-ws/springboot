package com.srawj.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srawj.app.entity.Employee;
import com.srawj.app.service.EmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> save(@RequestBody @Valid Employee employee) {
		return employeeService.save(employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@RequestBody @Valid Employee employee, @PathVariable @NotNull @Positive Integer id) {
		return employeeService.update(employee, id);
	}

	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		return employeeService.findAll();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable @NotNull @Positive Integer id) {
		return employeeService.deleteById(id);
	}
}