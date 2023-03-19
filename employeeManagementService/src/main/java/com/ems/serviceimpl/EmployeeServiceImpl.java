package com.ems.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.ems.dao.EmployeeDAO;
import com.ems.daoimpl.EmployeeDAOImpl;
import com.ems.entity.Employee;
import com.ems.exception.GlobalException;
import com.ems.model.EmployeeDTO;
import com.ems.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{
	
	//logger used to keep track the details
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class); 

	//accessing the Data Access Layer (DAO) to call the methods
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	
	@Override
	public void saveEmployee(Employee employee) {
		log.info("-------------------------------------New Employee Details Added." + employee.toString());		//log will add this line in logger when new employee added
		employeeDAO.saveEmployee(employee);
	}
	
	@Override
	public EmployeeDTO getEmployeeUsingID(int id) {
		Employee employee = employeeDAO.getEmployeeUsingID(id);
		if(employee!=null) {
			log.info("---------------------------------Employee with id: "+ id + " was retrieved at" + new Date());		//log will add this in logger when accessing any employee
			return new ModelMapper().map(employee, EmployeeDTO.class);
		}
		else {
			throw new GlobalException("Employee Details Not Found!!!");
		}
	}

	@Override
	public EmployeeDTO updateEmployeeUsingID(int id, Employee employee) {
		Employee emp = employeeDAO.updateEmployeeUsingID(id, employee);
		if(emp!=null) {
			log.info("-------------------------------------------------Updated Employee Details of ID: " + id + "at Time: " + new Date());
			return new ModelMapper().map(emp, EmployeeDTO.class);
		}
		else {
			throw new GlobalException("Employee ID: " + id + " not found!!!");
		}
	}

	@Override
	public void deleteEmployeeByID(int id) {
		log.info("------------------------------------------------- Employee Details Deleted with ID: " + id + "at Time: " +new Date());
		employeeDAO.deleteEmployeeByID(id);
	}

	@Override
	public EmployeeDTO getEmployeeByMail(String mail) {
		Employee emp = employeeDAO.getEmployeeByMail(mail);
		if(emp!=null) {
			log.info("---------------------------------Employee with Mail: "+ mail + " was retrieved at" + new Date());		//log will add this in logger when accessing any employee

			return new ModelMapper().map(emp, EmployeeDTO.class);
		}
		else {
			throw new GlobalException("Employee Details with email: " + mail + " Not Found!!!");
		}
	}

	@Override
	public boolean empLogin(String username, String password) {
		
		return employeeDAO.empLogin(username, password);
	}

}
