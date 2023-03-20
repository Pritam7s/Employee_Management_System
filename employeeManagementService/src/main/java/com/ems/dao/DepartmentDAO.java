package com.ems.dao;

import com.ems.entity.Department;

public interface DepartmentDAO {
	void saveDepartment(Department dept);		// add new department
	Department getDepartmentUsingID(int id);	// fetch department details
	Department updateDepartmentUsingID(int id, Department dept);	// update department details
	void deleteDepartmentByID(int id);		// delete department details
		
	void assignEmployeeToDept(int employeeId, int deptId);		// assign employee to a department
	void assignManagerToDept(int mId, int deptId);			//assign manager to a department
}
