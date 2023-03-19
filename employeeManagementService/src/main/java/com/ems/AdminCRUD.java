package com.ems;

import javax.swing.JOptionPane;

import com.ems.entity.Admin;
import com.ems.model.AdminDTO;
import com.ems.service.AdminService;
import com.ems.serviceimpl.AdminServiceImpl;

public class AdminCRUD {
	static AdminService admService = new AdminServiceImpl();
	
	public static void saveAdmin() {
		Admin admin = new Admin();
		String name = JOptionPane.showInputDialog("Enter Admin Name: ", "Type Here");
		String email = JOptionPane.showInputDialog("Enter Admin Email: ", "Type Here");
		String user = JOptionPane.showInputDialog("Enter UserName: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");

		admin.setAdminName(name);
		admin.setAdminEmail(email);
		admin.setUserName(user);
		admin.setPassword(pass);
		admin.setRole("admin");
		
		admService.saveAdmin(admin);
		System.out.println("New Admin Details Added Successfully...");
	}
	
	// get or see admin details using id 
	public static void getAdmin() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Admin ID to get Details: ","Type here..."));
		AdminDTO adm = admService.getAdmin(id);
		
		System.out.println("\nAdmin Name: " + adm.getAdminName());
		System.out.println("Admin Email: " + adm.getAdminEmail());
	}
	
	
	// update admin details
	public static void updateAdmin() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Admin Id: ", "Type here..."));
		Admin uadmin = new Admin();
		
		String name = JOptionPane.showInputDialog("Enter Admin Name: ", "Type Here");
		String email = JOptionPane.showInputDialog("Enter Admin Email: ", "Type Here");
		String user = JOptionPane.showInputDialog("Enter UserName: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");

		uadmin.setAdminName(name);
		uadmin.setAdminEmail(email);
		uadmin.setUserName(user);
		uadmin.setPassword(pass);
		uadmin.setRole("admin");
		admService.updateAdmin(id, uadmin);
		System.out.println("Admin Details Updated Successfully...");
	}
	
	
	// delete admin details
	public static void deleteAdmin() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Admin ID to Delete", "Tpe ID Here"));
		admService.deleteAdmin(id);
		//System.out.println("\nAdmin Data Deleted...");	//message is given in implementation part
	}
	
	
	//
	public static void login() {
		String user = JOptionPane.showInputDialog("Enter UserName: ", "Type Here");
		String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
		
		admService.login(user, pass);
	}
	
	public static void addSuperADmin() {
		//int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Admin Id: ", "Type here..."));
		
		//----------------for adding super Admin--------------------------------
		Admin uadmin = new Admin();
		AdminService admService = new AdminServiceImpl();	//for adding  super_Admin details

		//uadmin.setId(0);		// can't set an auto generated Identity, we have to turn on the IDENTITY_INSERT for the table
		uadmin.setAdminName("Super Admin");
		uadmin.setAdminEmail("supadmin@mail.com");
		uadmin.setUserName("SuperAdmin");
		uadmin.setPassword("Super123X");
		uadmin.setRole("adminX");
		admService.saveSuperAdmin(uadmin);
		//admService.updateAdmin(id, uadmin);
		//-----------------------end of add super_Admin----------------------------
		
		//it will delete the newly added super_Admins
		admService.saveSuperAdmin(uadmin);
		
	}
}
