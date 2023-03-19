package com.ems;

import javax.swing.JOptionPane;


import com.ems.service.AdminService;
import com.ems.serviceimpl.AdminServiceImpl;

public class ModifiedApp {

	public static void main(String[] args) {
		int ch;
		do {
			System.out.println("\n<---___________________ Welcome to Employee Management System ___________________--->\n");
			System.out.println(" 1> Admin \n 2> Employee \n 3> Exit");
			ch = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 for Admin, 2 for Employee, 3 to Exit. ", "Type 1 / 2 / 3"));
		
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
			
			default:
				System.out.println("\nWrong Input");
			}
		}while(ch!=3);
		
	
	}
	
	
	//admin's sub-menu
	public static void admin() {
		String ac;
		while(true) {
			System.out.println("\n--------------------------- Admin Section ---------------------------\n");
			System.out.println("Enter A for Admin Login  X for MainMenu");
			ac = JOptionPane.showInputDialog("Enter A for AdminLogin, X for MainMenu", "Type A / X");
			ac = ac.toUpperCase();
			
			switch(ac) {			
			case "A":		//Admin Login	--> that can access all CRUD operations from there
				adminLogin();				
				break;
			
			case "X":		//Exit to Main Menu
				System.out.println("\nGoing to MainMenu...");
				return;						
			}
		}
	}
	
	
	//admin login for CRUD Operations
	public static void adminLogin() {
		AdminService admService = new AdminServiceImpl();
		String user = JOptionPane.showInputDialog("Enter UserName: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		admService.login(user, pass);
	}

	
//=====================================================================================================================================================================	
	
	
	//employee sub-menu
	public static void employee() {
		String ec;
		while(true) {
			System.out.println("\n--------------------------- Employee Section ---------------------------\n");
			System.out.println("Enter 1 for Add new Employee  2 for Login  3 for MainMenu");
			ec = JOptionPane.showInputDialog("Enter 1 for Add Employee, 2 for Login, 3 for MainMenu", "Type 1 / 2 / 3");
			switch(ec) {
			case "1":
				EmployeeCRUD.saveEmpoloyee();		//add new employee details
				System.out.println("\nEmployee Details Has been added...");
				break;
			
			case "2":
				employeeCRUD();		// employee CRUD operations 
				break;
			
			case "3":
				System.out.println("\nGoing to MainMenu...");
				return;
			}
		}
	}
	
	
	//employee CRUD Operations
	public static void employeeCRUD() {
		System.out.println();
		while(true) {
			System.out.println("\n--------------------------- Employee Operations --------------------------\n");
			System.out.println("Enter (R) for Show Details (X) to Exit From Employee Operations ");
			String choice = JOptionPane.showInputDialog("Enter R to Show, X to Exit ", "Type R / X");
			choice = choice.toUpperCase();
		
			switch(choice) {
//			case "C":
//				EmployeeCRUD.saveEmpoloyee();	//for add new employee details
//				break;
			
			case "R":
				EmployeeCRUD.getEmployeeByID();		//get employee details by using id
				break;
			
			case "U":
			//	EmployeeCRUD.updateOwnEmployee();	//only the logged employee details will get updated
				break;
//			
//			case "D":
//				EmployeeCRUD.deleteEmployee();
//				break;
				
			case "X":
				System.out.println("\nExiting Employee Operations... ");
				return;
				
			default:
				System.out.println("\nNot a Valid Input WE Are Expecting....");
			}
		}
	}

	
	//========================================================================================================================================================
	
	public static void manager() {
		
	}
	
}
