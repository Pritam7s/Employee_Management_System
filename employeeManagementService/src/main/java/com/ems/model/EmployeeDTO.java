package com.ems.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO extends UserDTO{

	private String empName;
	private String empAddress;
	private double salary;
	private String contact;
	private String email;
	private String designation;
	private LocalDate DOJ;
	

	private DepartmentDTO dept;
}
