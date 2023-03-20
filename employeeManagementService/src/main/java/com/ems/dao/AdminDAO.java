package com.ems.dao;

import com.ems.entity.Admin;

public interface AdminDAO {

	void saveAdmin(Admin admin);		//add new admin
	Admin getAdmin(int id);				//fetch admin details
	Admin updateAdmin(int id, Admin adm);	//update admin details
	void deleteAdmin(int id);		//delete admin details
	
	boolean login(String username, String password);	// admin login
	
	void saveSuperAdmin(Admin admin);	//for autosave superadmin details at the start of connection
}
