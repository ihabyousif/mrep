package com.sis.mrep.client.content.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.proxy.ReportingManager;
import com.sis.mrep.client.proxy.ReportingManagerAsync;
import com.sis.mrep.server.service.ReportingManagerImpl;
import com.sis.mrep.shared.model.CoverageReport;

public class CoverageReportPanel {


	// A simple data type that represents a contact.
	//static CoverageReport CoverageReport;
	// The list of data to display.
	private static List<CoverageReport> CoverageReports = new ArrayList<CoverageReport>();

	private static List<CoverageReport> list;
	private static ReportingManagerAsync reportingService = GWT.create(ReportingManager.class);

	private static VerticalPanel vPanel = null;
	
	public static void loadEmployeePanel(){
		
		reportingService.getCoverageReportList(new AsyncCallback<List<CoverageReport>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<CoverageReport> crList) {
						CoverageReports = crList;
						PanelUtil.displayThisWidget(getCoverageReportPanel());
					}
		});
		
	}

	public static Widget getCoverageReportPanel() {
		if (vPanel != null) {
			return vPanel;
		} else {
			vPanel = new VerticalPanel();
		}

		CellTable<CoverageReport> coverageReportTable = new CellTable<CoverageReport>();
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		// Create a CellTable.

		pager.setDisplay(coverageReportTable);

		// Create name column.
		TextColumn<CoverageReport> planNameColumn = new TextColumn<CoverageReport>() {
			@Override
			public String getValue(CoverageReport cr) {
				return cr.getPlanName();
			}
		};

		// Create name column.
		TextColumn<CoverageReport> repNameColumn = new TextColumn<CoverageReport>() {
			@Override
			public String getValue(CoverageReport cr) {
				return cr.getRepName();
			}
		};

		// Make the name column sortable.
		repNameColumn.setSortable(true);

		// Create phone column.
		TextColumn<CoverageReport> cusNameColumn = new TextColumn<CoverageReport>() {
			@Override
			public String getValue(CoverageReport cr) {
				return cr.getCustomerName();
			}
		};

		// Create address column.
		TextColumn<CoverageReport> callsColumn = new TextColumn<CoverageReport>() {

			public String getValue(CoverageReport cr) {
				return ""+cr.getCalls();
			}
		};
		
		// Create address column.
		TextColumn<CoverageReport> callsDoneColumn = new TextColumn<CoverageReport>() {

			public String getValue(CoverageReport cr) {
				return ""+cr.getCallsDone();
			}
		};				
		
		
		coverageReportTable.addColumn(planNameColumn, "Plan Name");
		coverageReportTable.addColumn(repNameColumn, "Rep Name");
		coverageReportTable.addColumn(cusNameColumn, "Doctor Name");
		coverageReportTable.addColumn(callsColumn, "Calls");
		coverageReportTable.addColumn(callsDoneColumn, "Calls Done");
	
		// Create a data provider.
		ListDataProvider<CoverageReport> dataProvider = new ListDataProvider<CoverageReport>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(coverageReportTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		list = dataProvider.getList();
		for (CoverageReport coverageReport : CoverageReports) {
			list.add(coverageReport);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<CoverageReport> columnSortHandler = new ListHandler<CoverageReport>(
				list);
		columnSortHandler.setComparator(planNameColumn, new Comparator<CoverageReport>() {
			public int compare(CoverageReport o1, CoverageReport o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.getPlanName().compareTo(o2.getPlanName())
							: 1;
				}
				return -1;
			}
		});
		coverageReportTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		coverageReportTable.getColumnSortList().push(planNameColumn);


		vPanel.add(coverageReportTable);

		vPanel.add(pager);

		return vPanel;
	}


}
