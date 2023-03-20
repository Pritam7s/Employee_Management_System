package com.ems.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="dept")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptId;
	@Column(name="department_name", length=20, nullable = false)
	private String deptName;
	@Column(name = "total_employees", length = 10)
	private int totalEmp;
	@Column(length = 20)
	private String location;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Employee> emps;
	
	@OneToOne
	private Manager mg;
}
