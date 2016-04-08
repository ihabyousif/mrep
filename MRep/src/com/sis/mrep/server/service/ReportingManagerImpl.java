package com.sis.mrep.server.service;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sis.mrep.client.proxy.ReportingManager;
import com.sis.mrep.server.dao.ReportingDAO;
import com.sis.mrep.shared.model.CoverageReport;

public class ReportingManagerImpl  extends RemoteServiceServlet implements
ReportingManager{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2819360658244385640L;

	public List<CoverageReport> getCoverageReportList() {
		ReportingDAO reportingDAO = new ReportingDAO();
		return reportingDAO.getCoverageReportList();
	}
}
