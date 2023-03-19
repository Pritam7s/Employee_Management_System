package com.ems;

import javax.swing.JOptionPane;

import com.ems.entity.Department;
import com.ems.exception.GlobalException;
import com.ems.model.DepartmentDTO;
import com.ems.service.DepartmentService;
import com.ems.serviceimpl.DepartmentServiceImpl;

public class DepartmentCRUD {

	static DepartmentService deptService = new DepartmentServiceImpl();
	public static void saveDepartment() {
		Department dept = new Department();
		String deptName = JOptionPane.showInputDialog("Enter Department Name:", "Type Name Here...");
		Integer totalEmp = Integer.parseInt(JOptionPane.showInputDialog("Enter Total Employee:", "Type Here..."));
		String location = JOptionPane.showInputDialog("Enter Department location:", "Type Location Here...");
		
		dept.setDeptName(deptName);
		dept.setTotalEmp(totalEmp);
		dept.setLocation(location);
		
		deptService.saveDepartment(dept);
		System.out.println("Department Details Saved...");
	}
	
	public static void getDepartment() throws GlobalException {
		int did = Integer.parseInt(JOptionPane.showInputDialog("Enter Department ID to see Details: ","Type Department Id"));
		DepartmentDTO dep = deptService.getDepartmentUsingId(did);
		
		System.out.println("\nDepartment Name: " + dep.getDeptName());
		System.out.println("Department Total Employee: " + dep.getTotalEmp());
		System.out.println("Department Location: " + dep.getLocation() + "\n");
		
	}
	
	public static void updateDepartment() throws GlobalException {

		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to Update Details: ", "Enter ID Here..."));
		Department dep2 = new Department();
		
		String deptName = JOptionPane.showInputDialog("Enter Department Name:", "Type Name Here...");
		Integer totalEmp = Integer.parseInt(JOptionPane.showInputDialog("Enter Total Employee:", "Type Here..."));
		String location = JOptionPane.showInputDialog("Enter Department location:", "Type Location Here...");
		
		dep2.setDeptName(deptName);
		dep2.setTotalEmp(totalEmp);
		dep2.setLocation(location);
		
		deptService.updateDepartmentUsingID(id, dep2);
		System.out.println("\nDetails Updated Successfully...\n");
	}
	
	public static void deleteDepartment() throws GlobalException {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Department ID to Delete", "Tpe ID Here"));
		deptService.deleteDepartmentByID(id);
		System.out.println("\nRecord Deleted from ID: "+id);
	}
	
	public static void assignEmpToDept() {
		System.out.println("\n~~~~~~~~~~>  Assigning Employee to Department  <~~~~~~~~~~\n");	
		
		int deptId = Integer.parseInt(JOptionPane.showInputDialog("Enter Department ID: ","Type Here"));
		int empId = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID: ","Type Here"));
		
		deptService.assignEmployeeToDept(empId, deptId);
		JOptionPane.showMessageDialog(null, "Employee Has been Assigned Succesfully with A Department");
	}
	
	public static void assignManagerToDept() {
		System.out.println("\n~~~~~~~~~~>  Assigning Manager to Department  <~~~~~~~~~~\n");
		
		int dId = Integer.parseInt(JOptionPane.showInputDialog("Enter Department Id: ", "Type Here.."));
		int mId = Integer.parseInt(JOptionPane.showInputDialog("Enter Manager Id: ", "Type Here.."));
		
		deptService.assignManagerToDept(mId, dId);
		JOptionPane.showMessageDialog(null, "Manager has been Assigned Successfully...");
	}
}
