package com.sis.mrep.client.proxy;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sis.mrep.shared.model.CoverageReport;

@RemoteServiceRelativePath("ReportingManager")

public interface ReportingManager extends RemoteService{

	public List<CoverageReport> getCoverageReportList();
}
