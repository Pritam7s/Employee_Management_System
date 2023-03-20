package com.ems.service;

import com.ems.entity.Manager;
import com.ems.model.ManagerDTO;

public interface ManagerService {
	void saveManager(Manager manager);
	ManagerDTO getManagerByID(int id);
	ManagerDTO updateManagerByID(int id, Manager manager);
	ManagerDTO getManagerByEmail(String mEmail);
	void deleteManagerByID(int id);
	void assignEmployeeToManager(int eId, int mId);
	boolean manLogin(String username, String password);
}
