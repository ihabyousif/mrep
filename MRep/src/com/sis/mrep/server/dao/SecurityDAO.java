package com.sis.mrep.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sis.mrep.server.common.BaseDAO;
import com.sis.mrep.shared.model.Employee;

public class SecurityDAO extends BaseDAO{

	
	public Employee login(String userName, String userPass) {

		Employee employee = null;
		try {
			ResultSet rs = getObjectList(
					"select * from employee where emp_username ='"
							+ userName.trim() + "' and emp_pass = '"
							+ userPass.trim() + "'");
			if (rs.next()) {
				employee = new Employee(rs.getInt("emp_id"),
						rs.getString("emp_name"), rs.getString("emp_phone"),
						rs.getString("emp_address"),
						rs.getString("emp_username"), rs.getString("emp_pass"),
						rs.getInt("emp_manager"), rs.getInt("emp_type"),
						rs.getInt("emp_brick"), rs.getInt("l_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}


	
}
