package com.ems.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.ems.config.HibernateUtil;
import com.ems.entity.Employee;
import com.ems.exception.GlobalException;
import com.ems.model.EmployeeDTO;
import com.ems.service.EmployeeService;
import com.ems.serviceimpl.EmployeeServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceTest {
	EmployeeService empService = new EmployeeServiceImpl();
	
	private static SessionFactory sessionFactory;
	private Session session;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if(sessionFactory!=null) {
			sessionFactory.close();
			System.out.println("Session Factory Closed...");
		}
	}

	@BeforeEach
	void setUp() throws Exception {
		session = sessionFactory.openSession();
	}

	@AfterEach
	void tearDown() throws Exception {
		if(session!=null) {
			session.close();
			System.out.println("Session Closed...");
		}
	}
	
	
	@Test
	@DisplayName("Testing save employee") 
	@Order(1)
	void testSaveEmployee() {
	Transaction tx = session.beginTransaction();
	Employee emp = Employee.builder().empName("Arindam").empAddress("Kolkata").salary(25000).contact("9876543218").designation("Developer").email("arindam@gmail.com").DOJ(LocalDate.parse("2023-05-23")).build(); 
	empService.saveEmployee(emp);
	tx.commit();
	assertEquals("Arindam", emp.getEmpName());
	}
	
	
	

	@Test
	@DisplayName("Testing Employee By ID")
	@Order(2)
	void testGetEmpById() {
		EmployeeDTO eDTO = empService.getEmployeeUsingID(1);
		assertThat(eDTO.getEmpName()).isEqualTo("Pritam");
	}
	
	
	@Test
	@DisplayName("Testing Update Emp By ID")
	@Order(3)
	void testUpdateEmpById() {
		Employee emp = new Employee();
		emp.setEmpName("Steven Rogers");
		emp.setEmpAddress("Broklyn");
		emp.setContact("1245783256");
		emp.setDesignation("Avengers");
		emp.setEmail("captain@mail.com");
		emp.setDOJ(LocalDate.parse("1865-03-07"));
		emp.setUserName("Captain");
		emp.setPassword("cap7");
		emp.setRole("employee");
		
		EmployeeDTO eDTO = empService.updateEmployeeUsingID(11, emp);
		assertEquals("Steven Rogers", eDTO.getEmpName());
	}
	
	
	@Test
	@DisplayName("Testing Delete Emp By ID")
	@Order(4)
	void testDeleteEmpById() {
		empService.deleteEmployeeByID(11);
		assertThrows(GlobalException.class, ()-> empService.getEmployeeUsingID(11));
	}
	
	@Test
	@DisplayName("Testing Emp By Email")
	@Order(5)
	void testEmpByEmail() {
		EmployeeDTO eDTO = empService.getEmployeeByMail("ptm@gmail.com");
		assertThat(eDTO.getEmpName()).isEqualTo("Pritam");
	}

}
