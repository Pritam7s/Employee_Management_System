package com.ems.dao;

import com.ems.entity.Manager;

public interface ManagerDAO {
	void saveManager(Manager manager);
	Manager getManagerByID(int id);
	Manager updateManagerByID(int id, Manager manager);
	Manager getManagerByEmail(String mEmail);
	void deleteManagerByID(int id);
	
	void assignEmployeeToManager(int eId, int mId);
	boolean manLogin(String username, String password);
}
