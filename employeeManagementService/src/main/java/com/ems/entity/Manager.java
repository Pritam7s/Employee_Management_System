package com.ems.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manager extends User{
	
	@Column(length = 30, nullable = false)
	private String mName;
	@Column(length = 30, nullable = false, unique = true)
	private String mEmail;
	@Column(length = 30, nullable = false)
	private String mAddress;
	@Column(length = 20, nullable = false)
	private double mSalary;
	
	@OneToMany
	private List<Employee> employee;
	
	
}
