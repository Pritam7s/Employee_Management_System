package com.ems.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ems.Application;
import com.ems.ManagerCRUD;
import com.ems.config.HibernateUtil;
import com.ems.dao.ManagerDAO;
import com.ems.entity.Employee;
import com.ems.entity.Manager;
import com.ems.service.ManagerService;
import com.ems.serviceimpl.ManagerServiceImpl;

public class ManagerDAOImpl implements ManagerDAO{

	@Override
	public void saveManager(Manager manager) {
		try(Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			
			//add new manager details
			session.save(manager);
			
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
	public Manager getManagerByID(int id) {
		try(Session session = HibernateUtil.getSession()) {
			Manager manager = session.get(Manager.class, id);
			return manager;
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
	public Manager updateManagerByID(int id, Manager mang) {
		try(Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			Manager mg = session.get(Manager.class, id);
			
			//updating existing details with the new one
			mg.setMName(mang.getMName());
			mg.setMEmail(mang.getMEmail());
			mg.setMAddress(mang.getMAddress());
			mg.setMSalary(mang.getMSalary());
			mg.setUserName(mang.getUserName());
			mg.setPassword(mang.getPassword());
			
			session.saveOrUpdate(mg);
			session.getTransaction().commit();
			
			return mg;
			
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
	public Manager getManagerByEmail(String mEmail) {
		try(Session session = HibernateUtil.getSession()) {
			String query = "from Manager where mEmail =: m";
			Query q = session.createQuery(query);
			q.setParameter("m", mEmail);
			
			Manager mg = (Manager) q.uniqueResult();
			
			return mg;
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
	public void deleteManagerByID(int id) {
		try(Session session = HibernateUtil.getSession()) {
			Manager mg = session.load(Manager.class, id);
			
			session.beginTransaction();
			int input = JOptionPane.showConfirmDialog(null, "Do You Wnt to Delete", "Are You Sure?", JOptionPane.YES_NO_OPTION);
			if(input==0) {
				System.out.println("\n Checking VAL = " + input + " \n");
				session.delete(mg);
				System.out.println("\nRecord Deleted from ID: "+id);
				
				//session.evict(employee);
			}
			else {
				System.out.println("\n Checking VAL = " + input + " \n");
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
	public void assignEmployeeToManager(int eId, int mId) {
		try(Session session = HibernateUtil.getSession()) {
			Employee emp = session.get(Employee.class, eId);
			Manager mg = session.get(Manager.class, mId);
			
			List<Employee> emps =new ArrayList<>();
			emps.add(emp);
			
			mg.setEmployee(emps);
			emp.setMg(mg);
			
			session.beginTransaction();
			session.saveOrUpdate(mg);
			session.getTransaction().commit();
		}
		catch(HibernateException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public boolean manLogin(String username, String password) {
		try(Session session = HibernateUtil.getSession()) {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID: ", "Type Here.." ));
			Manager manager = session.get(Manager.class, id);
			ManagerService manService = new ManagerServiceImpl();
			
			if(manager.getUserName().equals(username) && manager.getPassword().equals(password) && manager.getRole().equals("Manager")) {
				System.out.println("Login Successful");
				JOptionPane.showMessageDialog(null, "LogIn Successful.");
				String ml;
				do {
					System.out.println("\n~~~~~~~~~~>  Manager Sub-Menu  <~~~~~~~~~~");
					System.out.println("\n I --> Details Using ID \n G --> Assign Employee to MAnager \n U --> Update Own Details \n X --> Manager Menu");
					ml = JOptionPane.showInputDialog("Manager Sub-Menu","Type I / G / U / X");
					ml = ml.toUpperCase();
					
					switch(ml) {
					case "I":
						ManagerCRUD.getManager();
						break;
						
					case "G":
						ManagerCRUD.assignEmployeeToManager();
						break;
						
					case "U":	//update own details only
						Manager manx = new Manager();
						
						String mname = JOptionPane.showInputDialog("Enter Name: ", "Type Here");
						String memail = JOptionPane.showInputDialog("Enter Email: ", "Type Here");
						String madd = JOptionPane.showInputDialog("Enter Address: ", "Type Here");
						Double msal = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary: ", "Type Here"));
						String muser = JOptionPane.showInputDialog("Enter User-Name: ", "Type Here");
						String mpass = JOptionPane.showInputDialog("Enter Password: ", "Type Here");
						
						manx.setMName(mname);
						manx.setMEmail(memail);
						manx.setMAddress(madd);
						manx.setMSalary(msal);
						manx.setUserName(muser);
						manx.setPassword(mpass);
						manx.setRole("Manager");
						
						manService.updateManagerByID(id, manx);
						System.out.println("Details Updated Successfully...");
						JOptionPane.showMessageDialog(null, "Details Updated Successfully");
						break;
						
					case "X":
						Application.managerMenu();
						break;
					}
					
				}while(ml!="X");
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
