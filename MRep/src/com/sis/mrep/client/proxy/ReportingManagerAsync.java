package com.sis.mrep.client.proxy;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sis.mrep.shared.model.CoverageReport;

public interface ReportingManagerAsync {

	void getCoverageReportList(AsyncCallback<List<CoverageReport>> callback);

}
