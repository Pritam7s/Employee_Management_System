package com.ems.serviceimpl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ems.dao.ManagerDAO;
import com.ems.daoimpl.ManagerDAOImpl;
import com.ems.entity.Manager;
import com.ems.exception.GlobalException;
import com.ems.model.ManagerDTO;
import com.ems.service.ManagerService;

public class ManagerServiceImpl implements ManagerService{

	private static final Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);
	ManagerDAO managerDAO = new ManagerDAOImpl();
	
	@Override
	public void saveManager(Manager manager) {
		log.info("----------------------------------------------------------------------------New Manager Details Added");
		managerDAO.saveManager(manager);
	}

	@Override
	public ManagerDTO getManagerByID(int id) {
		Manager mg = managerDAO.getManagerByID(id);
		if(mg!=null) {
			log.info("----------------------------------------------------------------------------Manager Details Retrieved with Id: " + id + "at Time: " + new Date());
			return new ModelMapper().map(mg, ManagerDTO.class);
		}
		else {
			throw new GlobalException("Manager Details Not Found!!");
		}
	}

	@Override
	public ManagerDTO updateManagerByID(int id, Manager manager) {
		Manager man = managerDAO.updateManagerByID(id, manager);
		if(man!=null) {
			log.info("----------------------------------------------------------------------------Manager Details Updated with Id: " + id + "at Time: " + new Date());

			return new ModelMapper().map(man, ManagerDTO.class);
		}
		else {
			throw new GlobalException("Employee ID: " + id + " not found!!!");
		}
	}

	@Override
	public ManagerDTO getManagerByEmail(String mEmail) {
		Manager mg = managerDAO.getManagerByEmail(mEmail);
		if(mg!=null) {
			log.info("----------------------------------------------------------------------------Manager Details Retrieved with Email: " + mEmail + "at Time: " + new Date());

			return new ModelMapper().map(mg, ManagerDTO.class);
		}
		else {
			throw new GlobalException("Manager Details Not Found!!");
		}
	}

	@Override
	public void deleteManagerByID(int id) {
		log.info("----------------------------------------------------------------------------Manager Details Deleted with Id: " + id + "at Time: " + new Date());

		managerDAO.deleteManagerByID(id);
	}

	@Override
	public void assignEmployeeToManager(int eId, int mId) {
		log.info("----------------------------------------------------------------------------Employee with ID: " + eId + "is Assigned to Manager with ID: " + mId + "at Time: " + new Date());
		managerDAO.assignEmployeeToManager(eId, mId);
	}

	@Override
	public boolean manLogin(String username, String password) {
		return managerDAO.manLogin(username, password);
	}

}
