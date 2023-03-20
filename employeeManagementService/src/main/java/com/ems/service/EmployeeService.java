package com.ems.service;

import com.ems.entity.Employee;
import com.ems.model.EmployeeDTO;

public interface EmployeeService{
	void saveEmployee(Employee employee);
	EmployeeDTO getEmployeeUsingID(int id);
	EmployeeDTO updateEmployeeUsingID(int id, Employee employee);
	void deleteEmployeeByID(int id);
	
	EmployeeDTO getEmployeeByMail(String mail);
	boolean empLogin(String username, String password);
}
