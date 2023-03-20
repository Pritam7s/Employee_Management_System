package com.ems.daoimpl;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ems.AdminCRUD;
import com.ems.Application;
import com.ems.DepartmentCRUD;
import com.ems.EmployeeCRUD;
import com.ems.ManagerCRUD;
import com.ems.config.HibernateUtil;
import com.ems.dao.AdminDAO;
import com.ems.entity.Admin;
import com.ems.service.AdminService;
import com.ems.serviceimpl.AdminServiceImpl;

public class AdminDAOImpl implements AdminDAO{

	@Override
	public void saveAdmin(Admin admin) {
		try(Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			session.save(admin);
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
	public boolean login(String username, String password) {
		try(Session session = HibernateUtil.getSession()){		
			
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID: ", "Type Here.." ));
			Admin admin = session.get(Admin.class, id);
			
			if(admin.getUserName().equals(username) && admin.getPassword().equals(password) && admin.getRole().equals("admin")) {
				JOptionPane.showMessageDialog(null, "LogIn Successful...");
				String ed;
				do {
					System.out.println("\n~~~~~~~~~~>  Admin Sub-Menu  <~~~~~~~~~~");
					System.out.println("\n A --> Admin CRUD  \n D --> Department CRUD \n E --> Employee CRUD \n M --> Manager CRUD \n G --> Assign Employee to Department \n N --> Assign Manager to Department \n X --> Admin Menu");
					ed = JOptionPane.showInputDialog("Admin Sub-Menu","Type A / D / E / M / G / N / X");
					ed = ed.toUpperCase();
					switch(ed) {
					case "A":
						adminCRUD();		//for admin modifications (usage on mdifiedApp)
			//			admService.updateAdmin(id, admin);		//here i can update own details
						break;
						
					case "D":
						departmentCRUD();	//for department modification (usage on mdifiedApp)
						break;
						
					case "E":
						employeeCRUD();		//for employee modification (usage on modifiedApp)
						break;
						
					case "M":
						managerCRUD();		// for manager modifications
						break;
						
					case "G":
						DepartmentCRUD.assignEmpToDept();	//assign employee to a department
						break;
						
					case "N":
						DepartmentCRUD.assignManagerToDept();	//assign manager to department
						break;
						
					case "X":
						Application.adminMenu();	//admin's menu
						break;
						
					default:
						System.out.println("Wrong Input. Use Specified Keys!!!");
						break;
							
					}
				}while(ed!="X");
				
				return true;
			}
			
			else if(admin.getUserName().equals("SuperAdmin") && admin.getPassword().equals("Super123X") && admin.getRole().equals("adminX") && admin.getId() == 1) {		
				JOptionPane.showMessageDialog(null, "LogIn Successful...");
				String sup;
				do {
					System.out.println("\n~~~~~~~~~~>  Super Admin Menu  <~~~~~~~~~~");
					System.out.println("\n A --> Add new Admin \n X --> Admin Menu");
					sup = JOptionPane.showInputDialog("Super Admin Menu","Type A / X");
					sup = sup.toUpperCase();
					
					switch(sup) {
					case "A":
						AdminCRUD.saveAdmin();		//super admin can add any admin
						break;
						
					case "X":
						Application.adminMenu();	//admin's menu
						break;
						
					default:
						System.out.println("Wrong Input. Use Specified Keys!!!");
						break;
					}
				}while(sup!="X");
				return true;
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Wrong Credentials!!!");
				return false;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	
//================================================================inside login================================================================================================	

	
	
		public static void adminCRUD() {
			System.out.println();
			while(true) {
				System.out.println("\n--------------------------- Admin Operations --------------------------\n");
				System.out.println("\n C --> Add \n R --> Show \n U --> Update \n D --> Delete \n X --> Exit From Admin Operations ");
				String choice = JOptionPane.showInputDialog("Admin  Operations", "Type C / R / U / D / X");
				choice = choice.toUpperCase();
			
				switch(choice) {
				case "C":
					AdminCRUD.saveAdmin();
					break;
				
				case "R":
					AdminCRUD.getAdmin();
					break;
				
				case "U":
					AdminCRUD.updateAdmin();
					break;
				
				case "D":
					AdminCRUD.deleteAdmin();
					break;
					
				case "X":
					System.out.println("\nExiting Admin Operations... ");
					return;
					
				default:
					System.out.println("\nNot a Valid Input WE Are Expecting....");
				}
			}
		}
	
	//using department CRUD for modifiedAPP
	//Department CRUD operations
		public static void departmentCRUD() {
			while(true) {
				System.out.println("\n--------------------------- Department Operations --------------------------\n");
				System.out.println("\n C --> Add \n R --> Show \n U --> Update \n D --> Delete \n X --> Exit from Department");
				String dc = JOptionPane.showInputDialog("Department  Operations","Type C / R / U / D / X");
				dc = dc.toUpperCase();
			
				switch(dc) {
				case "C":
					DepartmentCRUD.saveDepartment();
					break;
				
				case"R":
					DepartmentCRUD.getDepartment();
					break;
				
				case "U":
					DepartmentCRUD.updateDepartment();
					break;
				
				case "D":
					DepartmentCRUD.deleteDepartment();
					break;
					
//				case "G":
//					DepartmentCRUD.assignEmpToDept();
//					break;
//				
				case "X":
					System.out.println("\nExiting Department Operations... ");
					return;
				
				default:
					System.out.println("\nPlease Enter a Valid Key!!!");
				}
			}
		}
		
		
		//using employee CRUD for modifiedAPP
		//employee CRUD Operations
		public static void employeeCRUD() {
			System.out.println();
			while(true) {
				System.out.println("\n--------------------------- Employee Operations --------------------------\n");
				System.out.println("\n C --> Add \n R --> Show \n U --> Update \n D --> Delete \n X --> Exit From Employee Operations ");
				String choice = JOptionPane.showInputDialog("Employee  Operations ", "Type C / R / U / D / X");
				choice = choice.toUpperCase();
			
				switch(choice) {
				case "C":
					EmployeeCRUD.saveEmpoloyee();
					break;
				
				case "R":
					EmployeeCRUD.getEmployeeByID();
					break;
				
				case "U":
					EmployeeCRUD.updateEmployee();
					break;
				
				case "D":
					EmployeeCRUD.deleteEmployee();
					break;
					
				case "X":
					System.out.println("\nExiting Employee Operations... ");
					return;
					
				default:
					System.out.println("\nNot a Valid Input WE Are Expecting....");
				}
			}
		}

		
		
		//using manager CRUD for modifiedAPP
				//manager CRUD Operations
				public static void managerCRUD() {
					System.out.println();
					while(true) {
						System.out.println("\n--------------------------- Manager Operations --------------------------\n");
						System.out.println("\n C --> Add \n R --> Show \n U --> Update \n D --> Delete \n X --> Exit From Manager Operations ");
						String choice = JOptionPane.showInputDialog("Manager  Operations ", "Type C / R / U / D / X");
						choice = choice.toUpperCase();
					
						switch(choice) {
						case "C":
							ManagerCRUD.saveManager();
							break;
						
						case "R":
							ManagerCRUD.getManager();
							break;
						
						case "U":
							ManagerCRUD.updateManager();
							break;
						
						case "D":
							ManagerCRUD.deleteManager();
							break;
							
						case "X":
							System.out.println("\nExiting Manager Operations... ");
							return;
							
						default:
							System.out.println("\nNot a Valid Input WE Are Expecting....");
						}
					}
				}

				
				
//=================================================================== end of inside login ====================================================================================
				
				@Override
				public Admin getAdmin(int id) {
					try(Session session = HibernateUtil.getSession()) {
						Admin admin = session.get(Admin.class, id);
						return admin;
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
				public Admin updateAdmin(int id, Admin adm) {
					try(Session session = HibernateUtil.getSession()) {
						session.beginTransaction();
						Admin admin = session.get(Admin.class, id);
						
						//admin.setId(adm.getId());
						admin.setAdminName(adm.getAdminName());
						admin.setAdminEmail(adm.getAdminEmail());
						admin.setUserName(adm.getUserName());
						admin.setPassword(adm.getPassword());
						
						session.saveOrUpdate(admin);
						session.getTransaction().commit();
						System.out.println("Details Updated");
						return admin;
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
				public void deleteAdmin(int id) {
					try(Session session = HibernateUtil.getSession()) {
						Admin admin = session.load(Admin.class, id);
						
						session.beginTransaction();
						int input = JOptionPane.showConfirmDialog(null, "Do You Wnt to Delete", "Are You Sure?", JOptionPane.YES_NO_OPTION);
						if(input==0) {
							//System.out.println("\n INPUT VAL = " + input + " \n");
							session.delete(admin);
							System.out.println("\nRecord Deleted from ID: "+id);
							
						}
						else {
							//System.out.println("\n INPUT VAL = " + input + " \n");
							JOptionPane.showMessageDialog(null, "User wants to Retain Data.");
							System.out.println("\nUser Denied to Deleted from ID: "+id);
						}
						session.getTransaction().commit();
					}
					catch(HibernateException e) {
						System.out.println(e.getMessage());
					}
				}

				
				//using this for adding SuperAdmin automatically
				@Override
				public void saveSuperAdmin(Admin admin) {

					try(Session session = HibernateUtil.getSession()) {
						session.beginTransaction();
						session.saveOrUpdate(admin);
						session.getTransaction().commit();
						session.clear();
						
						String sql = "delete from User u where u.role =: r and u.id!=: i";
						Query q = session.createQuery(sql);
						q.setParameter("r", "adminX");
						q.setParameter("i", 1);
					}
					catch(HibernateException e) {
						System.out.println(e.getMessage());
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
					}
				}
		
}
