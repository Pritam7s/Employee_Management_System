package com.ems.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {

	private int deptId;
	private String deptName;
	private int totalEmp;
	private String location;
	
	private List<EmployeeDTO> emps;
}
