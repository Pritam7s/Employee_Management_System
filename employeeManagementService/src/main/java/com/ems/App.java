package com.ems;

import javax.swing.JOptionPane;

import com.ems.model.EmployeeDTO;
import com.ems.service.EmployeeService;
import com.ems.serviceimpl.EmployeeServiceImpl;

public class App {
	
	public static void main(String[] args) {

		int ch;
		do {
			System.out.println("\n<---___________________ Welcome to Employee Management System ___________________--->\n");
			System.out.println(" 1> Admin \n 2> Employee \n 3> Exit");
			ch = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 for Admin, 2 for Employee, 3 to Exit. 4 to Details Using Mail", "Type 1 / 2 / 3 / 4"));
		
			switch(ch) {
			case 1:
				admin();	// main menu admin
				break;
			
			case 2:
				employee();		// employee on main menu
				break;
			
			case 3:
				System.out.println("\nBye...");
				System.exit(0);		//exit from the program
				break;
				
			case 4:
				getEmployeeByMail();
				break;
			
			default:
				System.out.println("\nWrong Input");
			}
		}while(ch!=3);
	}
	
	
//	public static void aminCRUD() {
//		
//	}
//	
	
	public static void employeeCRUD() {
		System.out.println();
		while(true) {
			System.out.println("\n--------------------------- Employee Operations --------------------------\n");
			System.out.println("Enter (C) for Add new Details  (R) for Show Details (U) for Update  Details (D) for Delete any Details (X) to Exit From Employee Operations ");
			String choice = JOptionPane.showInputDialog("Enter C to Add, R to Show, U to Update, D to Delete, X to Exit ", "Type C / R / U / D / X");
			choice = choice.toUpperCase();
		
			switch(choice) {
			case "C":
				EmployeeCRUD.saveEmpoloyee();
				break;
			
			case "R":
				//EmployeeCRUD.getEmployee();
				break;
			
			case "U":
				EmployeeCRUD.updateEmployee();
				break;
			
			case "D":
				EmployeeCRUD.deleteEmployee();
				break;
				
			case "X":
				System.out.println("\nExiting Employee Operations... ");
				return;
				
			default:
				System.out.println("\nNot a Valid Input WE Are Expecting....");
			}
		}
	}
	
	
	public static void departmentCRUD() {
		while(true) {
			System.out.println("\n--------------------------- Department Section --------------------------\n");
			System.out.println("Enter <C> for Add new Details <R> for Show Details <U> for Update Details <D> fro Delete any Details <X> to Exit from Department");
			String dc = JOptionPane.showInputDialog("Enter C to Add, R to Show, U to Update, D to Delete, X to Exit ","Type C / R / U / D / X");
			dc = dc.toUpperCase();
		
			switch(dc) {
			case "C":
				DepartmentCRUD.saveDepartment();
				break;
			
			case"R":
				DepartmentCRUD.getDepartment();
				break;
			
			case "U":
				DepartmentCRUD.updateDepartment();
				break;
			
			case "D":
				System.out.println("\nDelete Operation Goes Here////");
				break;
				
			case "ASG":
				DepartmentCRUD.assignEmpToDept();
				break;
			
			case "X":
				System.out.println("\nExiting Department Operations... ");
				return;
			
			default:
				System.out.println("\nPlease Enter a Valid Key!!!");
			}
		}
	}
	
	
	public static void admin() {
		String ac;
		while(true) {
			System.out.println("\n--------------------------- Admin Section ---------------------------\n");
			System.out.println("Enter 1 for Add new Admin  2 for Login  3 for MainMenu");
			ac = JOptionPane.showInputDialog("Enter 1 for Add Admin, 2 for Login, 3 for MainMenu", "Type 1 / 2 / 3");
			switch(ac) {
			case "1":
				AdminCRUD.saveAdmin();
				break;
				
			case "2log":
				AdminCRUD.login();
				break;
			
			case "2":
				departmentCRUD();
				break;
			
			case "3":
				System.out.println("\nGoing to MainMenu...");
				return;
			}
		}
	}
	
	
	public static void employee() {
		String ec;
		while(true) {
			System.out.println("\n--------------------------- Employee Section ---------------------------\n");
			System.out.println("Enter 1 for Add new Employee  2 for Login  3 for MainMenu");
			ec = JOptionPane.showInputDialog("Enter 1 for Add Employee, 2 for Login, 3 for MainMenu", "Type 1 / 2 / 3");
			switch(ec) {
			case "1":
				System.out.println("\nEmployee Add Part will be here Later");
				break;
			
			case "2":
				employeeCRUD();
				break;
			
			case "3":
				System.out.println("\nGoing to MainMenu...");
				return;
			}
		}
	}
	
	
	
	//get employee using email
	
	static EmployeeService empService = new EmployeeServiceImpl();
	public static void getEmployeeByMail() {
		
		String mail = JOptionPane.showInputDialog("Enter Email to search Details: ", "Enter Email Here...");
		
		EmployeeDTO emps = empService.getEmployeeByMail(mail);
		
		System.out.println("\nEmployee Name: " + emps.getEmpName());
		System.out.println("Employee Address: " + emps.getEmpAddress());
		System.out.println("Employee Salary: " + emps.getSalary());
		System.out.println("Employee Contact: " + emps.getContact());
		System.out.println("Employee Email: " + emps.getEmail());
		System.out.println("Employee Designation: " + emps.getDesignation());
		System.out.println("Employee D.O.J: " + emps.getDOJ() + "\n");
	}
}