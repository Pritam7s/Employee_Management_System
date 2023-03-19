package com.ems.serviceimpl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ems.dao.DepartmentDAO;
import com.ems.daoimpl.DepartmentDAOImpl;
import com.ems.entity.Department;
import com.ems.exception.GlobalException;
import com.ems.model.DepartmentDTO;
import com.ems.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{
	
	private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	DepartmentDAO deptDAO = new DepartmentDAOImpl();

	@Override
	public void saveDepartment(Department dept) {
		log.info("-------------------------------------------------New Department Details Added.");
		deptDAO.saveDepartment(dept);
	}

	@Override
	public DepartmentDTO getDepartmentUsingId(int id) {
		Department dept = deptDAO.getDepartmentUsingID(id);
		if(dept!=null) {
			log.info("-------------------------------------------------Department Details Retrieved from ID: " + id);
			return new ModelMapper().map(dept, DepartmentDTO.class);
		}
		else {
			throw new GlobalException("Department Not Found!!!");
		}
	}

	@Override
	public DepartmentDTO updateDepartmentUsingID(int id, Department dept) {
		Department dep = deptDAO.updateDepartmentUsingID(id, dept);
		if(dep!=null) {
			log.info("-------------------------------------------------Updated Details of Department from ID: " + id);
			return new ModelMapper().map(dep, DepartmentDTO.class);
		}
		else {
			throw new GlobalException("Employee ID: " + id + " not found!!!");
		}
	}

	@Override
	public void assignEmployeeToDept(int employeeId, int deptId) {
		log.info("-------------------------------------------------Assigned Employee ID: " + employeeId + " to Department ID: " + deptId);
		deptDAO.assignEmployeeToDept(employeeId, deptId);
	}

	@Override
	public void deleteDepartmentByID(int id) {
		log.info("-------------------------------------------------Department Details Deleted From ID: " + id);
		deptDAO.deleteDepartmentByID(id);
	}

	@Override
	public void assignManagerToDept(int mId, int deptId) {
		log.info("-------------------------------------------------Assigned Manager ID: " + mId + " to Department ID: " + deptId);

		deptDAO.assignManagerToDept(mId, deptId);
	}

}
