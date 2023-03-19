package com.ems.dao;

import com.ems.entity.Department;

public interface DepartmentDAO {
	void saveDepartment(Department dept);
	Department getDepartmentUsingID(int id);
	Department updateDepartmentUsingID(int id, Department dept);
	void deleteDepartmentByID(int id);
	
	void assignEmployeeToDept(int employeeId, int deptId);
	void assignManagerToDept(int mId, int deptId);
}
