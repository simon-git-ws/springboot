package com.srawj.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.srawj.app.entity.Employee;
import com.srawj.app.repository.EmployeeRepository;

public class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveEmployee() {
		when(employeeRepository.save(any(Employee.class))).thenReturn(testData().get(0));
		ResponseEntity<Employee> response = employeeService.save(testData().get(0));
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Abc", response.getBody().getEmployeeName());
		verify(employeeRepository, times(1)).save(any(Employee.class));
	}

	@Test
	public void testUpdateEmployee() {
		when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(testData().get(0)));
		when(employeeRepository.save(any(Employee.class))).thenReturn(testData().get(0));

		ResponseEntity<Employee> response = employeeService.update(testData().get(0), 1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Abc", response.getBody().getEmployeeName());

		verify(employeeRepository, times(1)).findById(eq(1));
		verify(employeeRepository, times(1)).save(any(Employee.class));
	}

	@Test
	public void testFindAllEmployees() {
		when(employeeRepository.findAll()).thenReturn(testData());

		ResponseEntity<List<Employee>> response = employeeService.findAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, response.getBody().size());
		assertEquals("Abc", response.getBody().get(0).getEmployeeName());
		assertEquals("Xyz", response.getBody().get(1).getEmployeeName());

		verify(employeeRepository, times(1)).findAll();
	}

	@Test
    public void testDeleteById() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(testData().get(0)));
        ResponseEntity<?> response = employeeService.deleteById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(employeeRepository, times(1)).findById(eq(1));
        verify(employeeRepository, times(1)).deleteById(eq(1));
    }

	private List<Employee> testData() {
		return Arrays.asList(new Employee(1, "Abc", 1000), new Employee(2, "Xyz", 2000));
	}
}