package com.ems.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ems.config.HibernateUtil;
import com.ems.dao.DepartmentDAO;
import com.ems.entity.Department;
import com.ems.entity.Employee;
import com.ems.entity.Manager;

public class DepartmentDAOImpl implements DepartmentDAO{

	@Override
	public void saveDepartment(Department dept) {
		try(Session session = HibernateUtil.getSession()) {	
			session.beginTransaction();
			session.save(dept);
			session.getTransaction().commit();
			session.clear();
		}
		catch(HibernateException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Department getDepartmentUsingID(int id) {
		try(Session session = HibernateUtil.getSession()) {	
			Department dept = session.get(Department.class, id);
			return dept;
		}
		catch(HibernateException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Department updateDepartmentUsingID(int id, Department dept) {
		try(Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			Department dep = session.get(Department.class, id);
			
			//updating existing details with the new one
			dep.setDeptName(dept.getDeptName());
			dep.setLocation(dept.getLocation());
			dep.setTotalEmp(dept.getTotalEmp());
			
			session.saveOrUpdate(dep);
			session.getTransaction().commit();
			System.out.println("Details Updated");
			return dep;
			
		}
		catch(HibernateException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public void deleteDepartmentByID(int id) {
		try(Session session = HibernateUtil.getSession()) {
			Department dept = session.load(Department.class, id);
			
			session.beginTransaction();
			int input = JOptionPane.showConfirmDialog(null, "Do You Wnt to Delete", "Are You Sure?", JOptionPane.YES_NO_OPTION);
			if(input==0) {
				System.out.println("\n INPUT VAL = " + input + " \n");
				session.delete(dept);
				System.out.println("Record Deleted...");
				//session.evict(employee);
			}
			else {
				System.out.println("\n INPUT VAL = " + input + " \n");
				JOptionPane.showMessageDialog(null, "User wants to Retain It.");
				System.out.println("\nUser Enied to Delete...");
			}
			session.getTransaction().commit();
		}
		catch(HibernateException e) {
			System.out.println(e.getMessage());
		}		
	}
	
	@Override
	public void assignEmployeeToDept(int employeeId, int deptId) {
		try(Session session = HibernateUtil.getSession()) {
			Employee emp = session.get(Employee.class, employeeId);
			Department dept = session.get(Department.class, deptId);
			
			List<Employee> employees = new ArrayList<>();
			employees.add(emp);
			
			dept.setEmps(employees);
			emp.setDept(dept);
			
			emp.setMg(emp.getDept().getMg());		// this will also set the manager to an employee
			
			dept.setTotalEmp(dept.getTotalEmp()+1);	//this will add one employee to that department
			
			session.beginTransaction();
			session.saveOrUpdate(dept);
			
			session.getTransaction().commit();
			System.out.println("Assigned Employee to Department...");
			
		}
		catch(HibernateException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void assignManagerToDept(int mId, int deptId) {
		try(Session session = HibernateUtil.getSession()) {
			Manager man = session.get(Manager.class, mId);
			Department dept = session.get(Department.class, deptId);
			
			dept.setMg(man);
			
			dept.setTotalEmp(dept.getTotalEmp()+1);
			
			session.beginTransaction();
			session.saveOrUpdate(dept);
			
			session.getTransaction().commit();
			System.out.println("Assigned Manager to Department...");
			
		}
		catch(HibernateException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
