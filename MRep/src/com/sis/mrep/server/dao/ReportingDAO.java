package com.sis.mrep.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sis.mrep.server.common.BaseDAO;
import com.sis.mrep.shared.model.CoverageReport;

public class ReportingDAO extends BaseDAO{

	public static String rep1 = "SELECT pi.`pi_id`, pi.`plan_id`, pi.`pi_calls`, pi.`cus_id`, pi.`pi_calls_done` , "
			+" cus.cus_name ,  p.plan_name , p.rep_id , emp.emp_name "
	+" FROM plan_items pi, customer cus , plan p , employee emp "
	+" where pi.plan_id = p.plan_id and pi.cus_id = cus.cus_id and (pi_calls_done - pi_calls ) < 0 "
	 +" and p.rep_id = emp.emp_id ";
	
	public List<CoverageReport> getCoverageReportList() {
		List<CoverageReport> coverageReportList = new ArrayList<CoverageReport>();

		ResultSet rs = getObjectList(rep1);

		try {
			while (rs.next()) {
				CoverageReport cr = new CoverageReport();
				cr.setCustomerName(rs.getString("cus_name"));
				cr.setRepName(rs.getString("emp_name"));
				cr.setPlanName(rs.getString("plan_name"));
				cr.setCalls(rs.getInt("pi_calls"));
				cr.setCallsDone(rs.getInt("pi_calls_done"));
				coverageReportList.add(cr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return coverageReportList;
	}

	
	
	
	
	
}
