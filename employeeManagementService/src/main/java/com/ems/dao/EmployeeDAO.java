package com.ems.dao;

import com.ems.entity.Employee;

public interface EmployeeDAO {
	void saveEmployee(Employee employee);
	Employee getEmployeeUsingID(int id);
	Employee updateEmployeeUsingID(int id, Employee employee);
	void deleteEmployeeByID(int id);
	
	Employee getEmployeeByMail(String mail);
	boolean empLogin(String username, String password);
}
