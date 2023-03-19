package com.ems.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDTO extends UserDTO{
	
	private String mName;
	private String mEmail;
	private String mAddress;
	private double mSalary;
	
	private DepartmentDTO mdept;
}
