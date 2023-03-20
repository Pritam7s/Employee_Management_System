package com.ems.dao;

import com.ems.entity.Employee;

public interface EmployeeDAO {
	void saveEmployee(Employee employee);		// add new employee
	Employee getEmployeeUsingID(int id);		// fetch employee details using id
	Employee updateEmployeeUsingID(int id, Employee employee);		//update employee details using id
	void deleteEmployeeByID(int id);		//delete employee details
	
	Employee getEmployeeByMail(String mail);		// fetch employee details using using email
	boolean empLogin(String username, String password);		// for employee login
}
