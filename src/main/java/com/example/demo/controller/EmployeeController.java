package com.example.demo.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.Employee;
import com.example.demo.service.EmployeeService;
import org.slf4j.Logger;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/saveEmployee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

		
		logger.info("''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''..........");
		employeeService.save(employee);

		return ResponseEntity.ok(employee);

	}

	@GetMapping("/findEmployee")
	public List<Employee> findAllEmployees() {
		List<Employee> listEmployee = employeeService.findAll();
		return listEmployee;

	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody  Employee employee,
			@PathVariable("id") long id) throws ResourceNotFoundException {
		Employee empolyeeFind = employeeService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource was not found for.. " + id));
		
		if(StringUtils.isNotBlank(empolyeeFind.getFirstName())) {
		empolyeeFind.setFirstName(employee.getFirstName());
		empolyeeFind.setLastName(employee.getLastName());
		empolyeeFind.setEmail(employee.getEmail());
		}
		
		employeeService.save(empolyeeFind);
		
		return ResponseEntity.ok(empolyeeFind);
		
		
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Employee> deleteEmployee(
			@PathVariable("id") long id) throws ResourceNotFoundException {
		Employee empolyeeFind = employeeService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource was not found for.. " + id));
		
		if(StringUtils.isNotBlank(empolyeeFind.getFirstName())) {
			employeeService.delete(empolyeeFind);
		}
		
		return ResponseEntity.ok(empolyeeFind);
		
		
		
	}
	
}
