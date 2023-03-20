package com.ems;

import javax.swing.JOptionPane;

import com.ems.service.AdminService;
import com.ems.service.EmployeeService;
import com.ems.service.ManagerService;
import com.ems.serviceimpl.AdminServiceImpl;
import com.ems.serviceimpl.EmployeeServiceImpl;
import com.ems.serviceimpl.ManagerServiceImpl;

public class Application {

	public static void main(String[] args) {
		mainMenu();
	}
	
	
//------------------------------------------------------------------Main Menu----------------------------------------------------------------------------------------------------
	public static void mainMenu() {
		String c;
		do {
			System.out.println("\n<---___________________ Welcome to Employee Management System ___________________--->");
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println(" 1--> Admin \n 2--> Employee \n 3--> Manager \n 0--> Exit");
			c = JOptionPane.showInputDialog("Main Menu","Type 1 / 2 / 3 / 0");
			
			switch(c) {
			case "1":
				adminMenu();
				break;
				
			case "2":
				employeeMenu();
				break;
			
			case "3":
				managerMenu();
				break;
				
			case "0":
				System.out.println("\n Bye.....");
				System.exit(0);
				break;
				
				default: 
					JOptionPane.showMessageDialog(null, "Wrong Input. Please Type Carefully!");
					break;
			}
		}while(c!="0");
	}
//===============================================================================================================================================================================
	
	
//------------------------------------------------------------------Admin's Menu---------------------------------------------------------------------------------------------------
	public static void adminMenu() {
		String me;
		do {
			System.out.println("\n~~~~~~~~~~>  Admin Section  <~~~~~~~~~~");
			System.out.println("\n L --> LogIn  \n X --> MainMenu");
			me = JOptionPane.showInputDialog("Admin Menu","Type L / X");
			me = me.toUpperCase();
			
			switch(me) {
			case "L":
				AdminCRUD.addSuperADmin();
				adminLogin();
				break;
				
			case "X":
				mainMenu();
				break;
				
				default:
					JOptionPane.showMessageDialog(null, "Wrong Input. Please Type Carefully!");
					break;
			}
		}while(me!="X");
	}
	
	
	public static void adminLogin() {
		AdminService admService = new AdminServiceImpl();
		String user = JOptionPane.showInputDialog("Enter UserName: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		admService.login(user, pass);
	}
	
//===============================================================================================================================================================================
	

	
//------------------------------------------------------------------Employee's Menu-------------------------------------------------------------------------------------------------
	public static void employeeMenu() {
		String me;
		do {
			System.out.println("\n~~~~~~~~~~>  Employee Section  <~~~~~~~~~~");
			System.out.println("\n L --> LogIn  \n X --> MainMenu");
			me = JOptionPane.showInputDialog("Employee Menu","Type L / X");
			me = me.toUpperCase();
			
			switch(me) {
			case "L":
				empLogin();
				break;
				
			case "X":
				mainMenu();
				break;
				
				default:
					JOptionPane.showMessageDialog(null, "Wrong Input. Please Type Carefully!");
					break;
			}
		}while(me!="X");
	}
	
	public static void empLogin() {
		EmployeeService empService = new EmployeeServiceImpl();
		String user = JOptionPane.showInputDialog("Enter UserName: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		empService.empLogin(user, pass);
	}
//===============================================================================================================================================================================

	
	
//------------------------------------------------------------------Manager's Menu-------------------------------------------------------------------------------------------------
	public static void managerMenu() {
		String me;
		do {
			System.out.println("\n~~~~~~~~~~>  Manager Section  <~~~~~~~~~~");
			System.out.println("\n L --> LogIn  \n X --> MainMenu");
			me = JOptionPane.showInputDialog("Manager Menu","Type L / X");
			me = me.toUpperCase();
			
			switch(me) {
			case "L":
				manLogin();
				break;
				
			case "X":
				mainMenu();
				break;
				
				default:
					JOptionPane.showMessageDialog(null, "Wrong Input. Please Type Carefully!");
					break;
			}
		}while(me!="X");
	} 
	
	public static void manLogin() {
		ManagerService manService = new ManagerServiceImpl();
		String user = JOptionPane.showInputDialog("Enter UserName: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		manService.manLogin(user, pass);
	}
//===============================================================================================================================================================================

}
