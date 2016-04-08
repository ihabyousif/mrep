package com.sis.mrep.server.service;

import javax.servlet.http.HttpServletRequest;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sis.mrep.server.dao.SecurityDAO;
import com.sis.mrep.shared.model.Employee;

public class SecurityManagerImpl extends RemoteServiceServlet implements com.sis.mrep.client.proxy.SecurityManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3653951527694793989L;


	public Employee login(String userName, String userPass) {

		SecurityDAO securityDAO = new SecurityDAO();
		Employee employee = securityDAO.login(userName, userPass);
		HttpServletRequest request = this.getThreadLocalRequest();
		request.getSession(true).setAttribute("user", employee);
		return employee;
	}

	
	public Employee getCurrentUser() {
		HttpServletRequest request = this.getThreadLocalRequest();
		Employee employee = (Employee) request.getSession(true).getAttribute(
				"user");

		return employee;
	}
}
