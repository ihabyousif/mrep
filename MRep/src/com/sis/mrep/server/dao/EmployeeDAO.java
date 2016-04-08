package com.sis.mrep.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sis.mrep.server.common.BaseDAO;
import com.sis.mrep.shared.model.Call;
import com.sis.mrep.shared.model.Employee;
import com.sis.mrep.shared.model.Plan;

public class EmployeeDAO extends BaseDAO{

	public void updateEmployeePass(String old, String newPass, Employee emp) {
		//int empId = getCurrentUser().getId();

		int empId = emp.getId();
		
		String sql = "UPDATE `employee` SET `emp_pass` = '" + newPass
				+ "' where emp_id =" + empId;
		saveObject(BaseDAO.UPDATE_MODE, sql);

	}

	public Plan getPlanForEmp(int empId) {
		
		//empId  = getCurrentUser().getId();
		List<Plan> planList = new ArrayList<Plan>();
		
		try {
			ResultSet rs = getObjectList(
					"select * from plan where rep_id=" + empId +" and status=0");
			while (rs.next()) {
				Plan p = new Plan(rs.getInt("plan_id"),
						rs.getString("plan_name"), rs.getDate("plan_date"),
						rs.getInt("plan_brick"), rs.getInt("rep_id"),
						rs.getInt("admin_id"));
				planList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return planList.get(0);
	}
	
	public Call saveCall(Call c) {

			String sql = "";
			if (c.getCallId() > 0) {
				System.out.println("###### update call not supported  ############");
				
			} else {
				sql = "INSERT INTO `call` (call_date, call_details, pi_id, samples, promMaterials, nph_id, nph_feedback, company_feedback)VALUES"
						+" (CURDATE(),'"+c.getComment()+"',"+c.getPiId()+" , "+c.getSamples()+" , "+c.getPromMaterials()+" , "+c.getNphId()+" , '"+c.getNphFeedback()+"' , "+c.getCompanyFeedback()+") ";
			}
			int id = saveObject(BaseDAO.INSERT_MODE, sql);
			c.setCallId(id);
			return c;
	}
}
