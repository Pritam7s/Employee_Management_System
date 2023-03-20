package com.ems;

import java.time.LocalDate;

import javax.swing.JOptionPane;


import com.ems.entity.Employee;
import com.ems.exception.GlobalException;
import com.ems.model.EmployeeDTO;
import com.ems.service.EmployeeService;
import com.ems.serviceimpl.EmployeeServiceImpl;

public class EmployeeCRUD {

	static EmployeeService empService = new EmployeeServiceImpl();
	
	// add new employee
	public static void saveEmpoloyee() {
		Employee emp = new Employee();
//		sc.nextLine();
//		System.out.println("Enter Name: ");
//		String name= sc.nextLine();
//		String name = JOptionPane.showInputDialog("Enter Name: ", "Type Here...");
//		System.out.println("Enter Address: ");
//		String add = sc.nextLine();
//		System.out.println("Ente Salary: ");
//		double sal = sc.nextDouble();
//		System.out.println("Enter Contact Number: ");
//		String cont = sc.next();
//		sc.nextLine();
//		System.out.println("Enter Email: ");
//		String email = sc.nextLine();
//		System.out.println("Enter Designation: ");
//		String des = sc.next();
//		System.out.println("Enter Date of Joining: ");
//		LocalDate date = LocalDate.parse(sc.next());
//		System.out.println("Enter UserName: ");
//		String user = sc.next();
//		System.out.println("Enter Password: ");
//		String pass = sc.next();
		
		String name = JOptionPane.showInputDialog("Enter Name: ", "Type Here");
		String add = JOptionPane.showInputDialog("Enter Address: ", "Type Here");
		Double sal = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary: ", "Type Here"));
		String cont = JOptionPane.showInputDialog("Enter Phone Number: ", "Type Here");
		String email = JOptionPane.showInputDialog("Enter Email: ", "Type Here");
		String des = JOptionPane.showInputDialog("Enter Designation: ", "Type Here");
		LocalDate date = LocalDate.parse(JOptionPane.showInputDialog("Enter D.O.J: ", "yyyy-mm-dd"));
		String user = JOptionPane.showInputDialog("Enter User-Name: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		emp.setEmpName(name);
		emp.setEmpAddress(add);
		emp.setSalary(sal);
		emp.setContact(cont);
		emp.setEmail(email);
		emp.setDesignation(des);
		emp.setDOJ(date);
		emp.setUserName(user);
		emp.setPassword(pass);
		emp.setRole("employee");
		
		empService.saveEmployee(emp);
		System.out.println("Employee Details Saved.");
	}
	
	
	// fetch details of employee using ID
	public static void getEmployeeByID() throws GlobalException {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to search Details: ", "Enter ID Here..."));
		EmployeeDTO emp1 = empService.getEmployeeUsingID(id);
		
		System.out.println("\nEmployee Name: " + emp1.getEmpName());
		System.out.println("Employee Address: " + emp1.getEmpAddress());
		System.out.println("Employee Salary: " + emp1.getSalary());
		System.out.println("Employee Contact: " + emp1.getContact());
		System.out.println("Employee Email: " + emp1.getEmail());
		System.out.println("Employee Designation: " + emp1.getDesignation());
		System.out.println("Employee D.O.J: " + emp1.getDOJ() + "\n");
		//System.out.println("Assigned Department: " + emp1.getDept().getDeptId());
		//System.out.println("Assigned Manager: " + emp1.getDept().getDeptId());
		
	}
	
	
	//update employee details
	public static void updateEmployee() throws GlobalException {
		
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to Update Details: ", "Enter ID Here..."));
		Employee emp2 = new Employee();
		
		String name = JOptionPane.showInputDialog("Enter Name: ", "Type Here");
		String add = JOptionPane.showInputDialog("Enter Address: ", "Type Here");
		Double sal = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary: ", "Type Here"));
		String cont = JOptionPane.showInputDialog("Enter Phone Number: ", "Type Here");
		String email = JOptionPane.showInputDialog("Enter Email: ", "Type Here");
		String des = JOptionPane.showInputDialog("Enter Designation: ", "Type Here");
		LocalDate date = LocalDate.parse(JOptionPane.showInputDialog("Enter D.O.J: ", "yyyy-mm-dd"));
		String user = JOptionPane.showInputDialog("Enter User-Name: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		emp2.setEmpName(name);
		emp2.setEmpAddress(add);
		emp2.setSalary(sal);
		emp2.setContact(cont);
		emp2.setEmail(email);
		emp2.setDesignation(des);
		emp2.setDOJ(date);
		emp2.setUserName(user);
		emp2.setPassword(pass);
		emp2.setRole("employee");
		
		empService.updateEmployeeUsingID(id, emp2);
		System.out.println("\nDetails Updated Successfully...\n");
	}
	
	
	// delete employee details
	public static void deleteEmployee() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID to Delete", "Tpe ID Here"));
		empService.deleteEmployeeByID(id);
		System.out.println("\nEmployee Details Deleted...");
	}
	
	
	
	//update only the details who will log in 
	public static void empLogin() {
		String user = JOptionPane.showInputDialog("Enter UserName: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		empService.empLogin(user, pass);
	}
	
	
	//get details using email
	public static void getEmployeeByEmail() throws GlobalException {
		String mail = JOptionPane.showInputDialog("Enter Email to search Details: ", "Enter Email Here...");
		EmployeeDTO emp1 = empService.getEmployeeByMail(mail);
		
		System.out.println("\nEmployee Name: " + emp1.getEmpName());
		System.out.println("Employee Address: " + emp1.getEmpAddress());
		System.out.println("Employee Salary: " + emp1.getSalary());
		System.out.println("Employee Contact: " + emp1.getContact());
		System.out.println("Employee Email: " + emp1.getEmail());
		System.out.println("Employee Designation: " + emp1.getDesignation());
		System.out.println("Employee D.O.J: " + emp1.getDOJ() + "\n");
		
	}
}
