package com.ems.daoimpl;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ems.Application;
import com.ems.EmployeeCRUD;
import com.ems.config.HibernateUtil;
import com.ems.dao.EmployeeDAO;
import com.ems.entity.Employee;
import com.ems.service.EmployeeService;
import com.ems.serviceimpl.EmployeeServiceImpl;

public class EmployeeDAOImpl implements EmployeeDAO{

	//this method gets the session to save the new employee details
	@Override
	public void saveEmployee(Employee employee) {

		try(Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			
			//add all the new employee details
			session.save(employee);
			
			//java object saved to the database
			session.getTransaction().commit();
			
			//clear the session
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
	public Employee getEmployeeUsingID(int id) {
		try(Session session = HibernateUtil.getSession()) {
			Employee employee = session.get(Employee.class, id);
			return employee;
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
	public Employee updateEmployeeUsingID(int id, Employee empl) {
		try(Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			Employee emp = session.get(Employee.class, id);
			
			//updating existing details with the new one
			emp.setEmpName(empl.getEmpName());
			emp.setEmpAddress(empl.getEmpAddress());
			emp.setSalary(empl.getSalary());
			emp.setContact(empl.getContact());
			emp.setEmail(empl.getEmail());
			emp.setDesignation(empl.getDesignation());
			emp.setDOJ(empl.getDOJ());
			emp.setUserName(empl.getUserName());
			emp.setPassword(empl.getPassword());
			
			session.saveOrUpdate(emp);
			session.getTransaction().commit();
			
			return emp;
			
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
	public void deleteEmployeeByID(int id) {
		try(Session session = HibernateUtil.getSession()) {
			Employee employee = session.load(Employee.class, id);
			
			session.beginTransaction();
			int input = JOptionPane.showConfirmDialog(null, "Do You Wnt to Delete", "Are You Sure?", JOptionPane.YES_NO_OPTION);
			if(input==0) {
				System.out.println("\n INPUT VAL = " + input + " \n");
				session.delete(employee);
				System.out.println("\nRecord Deleted from ID: "+id);
				
				//session.evict(employee);
			}
			else {
				System.out.println("\n INPUT VAL = " + input + " \n");
				JOptionPane.showMessageDialog(null, "User wants to Retain It.");
				System.out.println("\nUser Denied to Deleted from ID: "+id);
			}
			session.getTransaction().commit();
		}
		catch(HibernateException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeByMail(String mail) {
		try(Session session = HibernateUtil.getSession()) {
			//Employee employee = session.get(Employee.class, mail);	//this is only work for id
			String query = "from Employee e where e.email =: m";
			Query q = session.createQuery(query);
			q.setParameter("m", mail);
			
			Employee emp = (Employee) q.uniqueResult();
			
			return emp;
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
	public boolean empLogin(String username, String password) {
		try(Session session = HibernateUtil.getSession()) {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID: ", "Type Here.." ));
			Employee employee = session.get(Employee.class, id);
			EmployeeService empService = new EmployeeServiceImpl();
			
			if(employee.getUserName().equals(username) && employee.getPassword().equals(password) && employee.getRole().equals("employee")) {
				System.out.println("Login Successful");
				JOptionPane.showMessageDialog(null, "LogIn Successful.");
				String el;
				do {
					System.out.println("\n~~~~~~~~~~>  Employee Sub-Menu  <~~~~~~~~~~");
					System.out.println("\n I --> Details Using ID \n E --> Details Using Email \n U --> Update Own Details \n X --> Employee Menu");
					el = JOptionPane.showInputDialog("Employee Sub-Menu","Type I / E / U / X");
					el = el.toUpperCase();
					
					switch(el) {
					case "I":
						EmployeeCRUD.getEmployeeByID();
						break;
						
					case "E":
						EmployeeCRUD.getEmployeeByEmail();
						break;
						
					case "U":	//update own details only
						Employee emp2 = new Employee();
						
						String name = JOptionPane.showInputDialog("Enter Name: ", "Type Here");
						String add = JOptionPane.showInputDialog("Enter Address: ", "Type Here");
						Double sal = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary: ", "Type Here"));
						String cont = JOptionPane.showInputDialog("Enter Phone Number: ", "Type Here");
						String email = JOptionPane.showInputDialog("Enter Email: ", "Type Here");
						String des = JOptionPane.showInputDialog("Enter Designation: ", "Type Here");
						LocalDate date = LocalDate.parse(JOptionPane.showInputDialog("Enter D.O.J: ", "yyyy-mm-dd"));
						String user = JOptionPane.showInputDialog("Enter User-Name: ", "Type Here");
						String pass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
						
						emp2.setEmpName(name);
						emp2.setEmpAddress(add);
						emp2.setSalary(sal);
						emp2.setContact(cont);
						emp2.setEmail(email);
						emp2.setDesignation(des);
						emp2.setDOJ(date);
						emp2.setUserName(user);
						emp2.setPassword(pass);
						emp2.setRole("employee");

						empService.updateEmployeeUsingID(id, emp2);
						System.out.println("Details Updated Successfully...");
						JOptionPane.showMessageDialog(null, "Details Updated Successfully");
						break;
						
					case "X":
						Application.employeeMenu();	//employee's menu
						break;
					}
					
				}while(el!="X");
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Wrong Credentials!!!");
				return false;
			}
		}
		catch(HibernateException e) {
			System.out.println(e.getMessage());
			return false;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	
}
