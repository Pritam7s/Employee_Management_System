package com.ems.serviceimpl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ems.dao.AdminDAO;
import com.ems.daoimpl.AdminDAOImpl;
import com.ems.entity.Admin;
import com.ems.exception.GlobalException;
import com.ems.model.AdminDTO;
import com.ems.service.AdminService;

public class AdminServiceImpl implements AdminService{

	//logger used to keep track the details
		private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class); 

	
	AdminDAO adminDAO = new AdminDAOImpl();
	@Override
	public void saveAdmin(Admin admin) {
		log.info("-------------------------------------------------New Admin Details saved " + admin.toString());
		adminDAO.saveAdmin(admin);
	}

	@Override
	public boolean login(String username, String password) {
		return adminDAO.login(username, password);
	}

	@Override
	public AdminDTO getAdmin(int id) {
		Admin admin = adminDAO.getAdmin(id);
		if(admin!=null) {
			log.info("-------------------------------------------------Admin with id: "+ id + " was retrieved at" + new Date());		//log will add this in logger when accessing any admin
			return new ModelMapper().map(admin, AdminDTO.class);
		}
		else {
			throw new GlobalException("Admin Details Not Found!!!");
		}
	}

	@Override
	public AdminDTO updateAdmin(int id, Admin admin) {
		Admin adm = adminDAO.updateAdmin(id, admin);
		log.info("-------------------------------------------------Updated Admin Details of ID: " + id);
		if(adm!=null) {
			return new ModelMapper().map(adm, AdminDTO.class);
		}
		else {
			throw new GlobalException("Admin ID: " + id + " not found!!!");
		}
	}

	@Override
	public void deleteAdmin(int id) {
		log.info("-------------------------------------------------Admin Details Deleted with ID: " + id);
		adminDAO.deleteAdmin(id);
		
	}

	@Override
	public void saveSuperAdmin(Admin admin) {

		log.info("-------------------------------------------------Super Admin Details saved " + admin.toString());
		adminDAO.saveSuperAdmin(admin);
	}

}
