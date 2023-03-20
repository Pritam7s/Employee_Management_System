package com.ems.dao;

import com.ems.entity.Manager;

public interface ManagerDAO {
	void saveManager(Manager manager);	// add new manager
	Manager getManagerByID(int id);		// fetch details of manager
	Manager updateManagerByID(int id, Manager manager);		// update manager details
	Manager getManagerByEmail(String mEmail);		// fetch manager details by email
	void deleteManagerByID(int id);		//delete manager details
	
	void assignEmployeeToManager(int eId, int mId);		// assign employee to a manager
	boolean manLogin(String username, String password);		// for manager login 
}
