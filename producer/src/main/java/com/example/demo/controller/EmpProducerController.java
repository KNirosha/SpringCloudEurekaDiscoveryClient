package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmpProducerController {
	@Autowired
	EmployeeService employeeService;
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@RequestMapping(method=RequestMethod.GET,value="/api/getAllEmployes")
	public List<Employee> getEmployeeList(){
		return employeeService.getAllEmployees();
	}
	@RequestMapping(method=RequestMethod.POST,value="/api/employe")
	public Employee addEmployee(@RequestBody Employee e){
		return employeeService.addEmployee(e);
	}

}
