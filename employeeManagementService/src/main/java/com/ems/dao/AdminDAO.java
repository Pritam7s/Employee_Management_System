package com.ems.dao;

import com.ems.entity.Admin;

public interface AdminDAO {

	void saveAdmin(Admin admin);
	Admin getAdmin(int id);
	Admin updateAdmin(int id, Admin adm);
	void deleteAdmin(int id);
	
	boolean login(String username, String password);
	
	void saveSuperAdmin(Admin admin);
}
