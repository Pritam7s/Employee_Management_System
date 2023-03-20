package com.ems.service;

import com.ems.entity.Department;
import com.ems.model.DepartmentDTO;

public interface DepartmentService {
	void saveDepartment(Department dept);
	DepartmentDTO getDepartmentUsingId(int id);
	DepartmentDTO updateDepartmentUsingID(int id, Department dept);
	void assignEmployeeToDept(int employeeId, int deptId);
	void deleteDepartmentByID(int id);
	void assignManagerToDept(int mId, int deptId);
}
