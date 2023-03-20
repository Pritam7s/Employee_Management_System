package com.ems;

import javax.swing.JOptionPane;

import com.ems.entity.Manager;
import com.ems.exception.GlobalException;
import com.ems.model.ManagerDTO;
import com.ems.service.ManagerService;
import com.ems.serviceimpl.ManagerServiceImpl;

public class ManagerCRUD {

	static ManagerService mgService = new ManagerServiceImpl();
	// for adding new manager
	public static void saveManager() {
		Manager mg = new Manager();
		
		String mname = JOptionPane.showInputDialog("Enter Name: ", "Type Here");
		String memail = JOptionPane.showInputDialog("Enter Email: ", "Type Here");
		String madd = JOptionPane.showInputDialog("Enter Address: ", "Type Here");
		Double msal = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary: ", "Type Here"));
		String muser = JOptionPane.showInputDialog("Enter User-Name: ", "Type Here");
		String mpass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		mg.setMName(mname);
		mg.setMEmail(memail);
		mg.setMAddress(madd);
		mg.setMSalary(msal);
		mg.setUserName(muser);
		mg.setPassword(mpass);
		mg.setRole("Manager");
		
		mgService.saveManager(mg);
		System.out.println("Manager Details Saved.");
	}
	
	//for fetching the details of manager
	public static void getManager() throws GlobalException {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to search Details: ", "Enter ID Here..."));
		ManagerDTO mg1 = mgService.getManagerByID(id);
		
		System.out.println("\nManager Name: " + mg1.getMName());
		System.out.println("Manager Email: " + mg1.getMEmail());
		System.out.println("Manager Address: " + mg1.getMAddress());
		System.out.println("Manager Salary: " + mg1.getMSalary());
		
	}
	
	// update manager details
	public static void updateManager() throws GlobalException {
		
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to Update Details: ", "Enter ID Here..."));
		Manager mg2 = new Manager();
		
		String mname = JOptionPane.showInputDialog("Enter Name: ", "Type Here");
		String memail = JOptionPane.showInputDialog("Enter Email: ", "Type Here");
		String madd = JOptionPane.showInputDialog("Enter Address: ", "Type Here");
		Double msal = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary: ", "Type Here"));
		String muser = JOptionPane.showInputDialog("Enter User-Name: ", "Type Here");
		String mpass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		mg2.setMName(mname);
		mg2.setMEmail(memail);
		mg2.setMAddress(madd);
		mg2.setMSalary(msal);
		mg2.setUserName(muser);
		mg2.setPassword(mpass);
		mg2.setRole("Manager");
		
		mgService.updateManagerByID(id, mg2);
		System.out.println("\nDetails Updated Successfully...\n");
	}
	
	// delete manager details
	public static void deleteManager() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Manager ID to Delete", "Tpe ID Here"));
		mgService.deleteManagerByID(id);
	}
	
	
	// assign employee to a manager
	public static void assignEmployeeToManager() {
		int empId = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID: ","Type Here"));
		int mId = Integer.parseInt(JOptionPane.showInputDialog("Enter Manager ID: ","Type Here"));

		mgService.assignEmployeeToManager(empId, mId);
		JOptionPane.showMessageDialog(null, "Employee Assiged to Manager Successfully");
	}
	
	// for manager login
	public static void manLogin() {
		String user = JOptionPane.showInputDialog("Enter UserName: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		mgService.manLogin(user, pass);
	}
	
}
