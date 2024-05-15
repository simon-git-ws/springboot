package com.srawj.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.srawj.app.entity.Employee;
import com.srawj.app.service.EmployeeService;

public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
    public void testSaveEmployee() throws Exception {
        when(employeeService.save(any(Employee.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(testData().get(0)));
        ResponseEntity<Employee> response = employeeController.save(testData().get(0));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(employeeService).save(any(Employee.class));
    }

	@Test
    public void testUpdateEmployee() throws Exception {
        when(employeeService.update(any(Employee.class), eq(1))).thenReturn(ResponseEntity.ok(testData().get(0)));        
        ResponseEntity<Employee> response = employeeController.update(testData().get(0),1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(employeeService).update(any(Employee.class), eq(1));
    }

	@Test
    public void testFindAllEmployees() throws Exception {
        when(employeeService.findAll()).thenReturn(ResponseEntity.ok(testData()));
        ResponseEntity<List<Employee>> response = employeeController.findAll();
        assertEquals(testData().size(), response.getBody().size());
        verify(employeeService).findAll();
    }

	@Test
	public void testDeleteEmployeeById() throws Exception {
		when(employeeService.deleteById(eq(1))).thenReturn(ResponseEntity.ok().build());
		ResponseEntity<?> response = employeeController.deleteById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(employeeService).deleteById(1);
	}

	private List<Employee> testData() {
		return Arrays.asList(new Employee(1, "Abc", 1000), new Employee(2, "Xyz", 2000));
	}
}
