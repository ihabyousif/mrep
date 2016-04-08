package com.sis.mrep.server.service;

import javax.servlet.http.HttpServletRequest;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sis.mrep.client.proxy.EmployeeManager;
import com.sis.mrep.server.dao.EmployeeDAO;
import com.sis.mrep.shared.model.Call;
import com.sis.mrep.shared.model.Employee;
import com.sis.mrep.shared.model.Plan;

public class EmployeeManagerImpl extends RemoteServiceServlet implements
EmployeeManager{

	public void updateEmployeePass(String old, String newPass) {
		Employee emp =  getCurrentUser();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeDAO.updateEmployeePass(old, newPass, emp);
	}

    public Plan getPlanForCurrentEmployee() {
    	Employee emp =  getCurrentUser();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		return employeeDAO.getPlanForEmp(emp.getId());

	}
	
	public Plan getPlanForEmp(int empId) {
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		return employeeDAO.getPlanForEmp(empId);

	}
	
	public Call saveCall(Call c) {

		EmployeeDAO employeeDAO = new EmployeeDAO();
		return employeeDAO.saveCall(c);
	}
	
	public Employee getCurrentUser() {
	  HttpServletRequest request = this.getThreadLocalRequest();
	  Employee employee = (Employee) request.getSession(true).getAttribute(
			"user");
	  return employee;
   }
	
}
