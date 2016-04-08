package com.sis.mrep.client.content.plan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.content.util.StaticMaps;
import com.sis.mrep.client.proxy.AdminManager;
import com.sis.mrep.client.proxy.AdminManagerAsync;
import com.sis.mrep.client.proxy.EmployeeManager;
import com.sis.mrep.client.proxy.EmployeeManagerAsync;
import com.sis.mrep.shared.model.Call;
import com.sis.mrep.shared.model.Plan;
import com.sis.mrep.shared.model.PlanItem;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.view.client.ListDataProvider;

public class MyPlanPanel {

	// The list of data to display.
	private static Plan plan = new Plan();


	private static EmployeeManagerAsync empService = GWT
			.create(EmployeeManager.class);

	private static AdminManagerAsync adminService = GWT
			.create(AdminManager.class);
	private static VerticalPanel vPanel = null;

	public static void loadMyPlanPanel() {

		empService.getPlanForCurrentEmployee(new AsyncCallback<Plan>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Plan p) {
				plan  = p;
				PanelUtil.displayThisWidget(getMyPlanPanel(p));
			}
		});

	}

	public static Widget getMyPlanPanel(Plan p) {
		if (dp1 != null) {
			return dp1;
		} else {
			dp1 = new DecoratorPanel();
		}
		
		loadItemsPanel(p.getId() , p.getBrickId());
	
		return dp1;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void loadItemsPanel(int planId , int pid) {

		adminService.getPlanItemListForPlanId(planId,
				new AsyncCallback<List<PlanItem>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(List<PlanItem> piList) {
						PlanItems = piList;
						// Customers = customerList;
						Widget w = getPlanItemPanel();
						
						dp1 = new DecoratorPanel();
						dp1.add(w);
						    
					    PanelUtil.appendToCurrentWidget(dp1);
					}
				});

	}
	
	private static DecoratorPanel dp1 = null;

	private static VerticalPanel vPlanItemPanel = null;

	private static List<PlanItem> PlanItems = new ArrayList<PlanItem>();

	private static List<PlanItem> listPlanItems;

	public static Widget getPlanItemPanel() {
		
		if (vPlanItemPanel != null) {
			return vPlanItemPanel;
		} else {
			vPlanItemPanel = new VerticalPanel();
		}

		//// search panel
		DecoratorPanel searchPanel = new DecoratorPanel();
		
		FlexTable layout = new FlexTable();
	    layout.setCellSpacing(6);
	    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

	    // Add a title to the form
	    layout.setHTML(0, 0, "Filter Customers");
	    cellFormatter.setColSpan(0, 0, 2);
	    cellFormatter.setHorizontalAlignment(
	        0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	    final ListBox tierLB = StaticMaps.getTierLB();
	    final ListBox specLB = StaticMaps.getSpecLB();
	    // Add some standard form options
	    layout.setHTML(1, 0, "Tier");
	    layout.setWidget(1, 1, tierLB);
	    layout.setHTML(1, 3, "Speciality");
	    layout.setWidget(1, 4, specLB);
	    Button filter = new Button("Filter");
	    Button cancel = new Button("Cancel");
	    HorizontalPanel hp = new HorizontalPanel();
	    
	    new Button("Cancel");
	    hp.setSpacing(8);
	    hp.add(filter);
	    hp.add(cancel);
	    layout.setWidget(3, 2, hp);
	    
	    vPlanItemPanel.add(layout);
		
		CellTable<PlanItem> piTable = new CellTable<PlanItem>();
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		// Create a CellTable.

		pager.setDisplay(piTable);

		// Create name column.
		TextColumn<PlanItem> cusIdColumn = new TextColumn<PlanItem>() {
			@Override
			public String getValue(PlanItem pi) {
				return "" + pi.getCusId();
			}
		};

		// Create name column.
		TextColumn<PlanItem> cusNameColumn = new TextColumn<PlanItem>() {
			@Override
			public String getValue(PlanItem pi) {
				return pi.getCusName();
			}
		};

		// Make the name column sortable.
		cusNameColumn.setSortable(true);

		// Create phone column.
		TextColumn<PlanItem> cusTierColumn = new TextColumn<PlanItem>() {
			@Override
			public String getValue(PlanItem pi) {
				return StaticMaps.tierMap.get(pi.getCusTier());
			}
		};
		
		// Create speciality column.
				TextColumn<PlanItem> cusSpecialityColumn = new TextColumn<PlanItem>() {
					@Override
					public String getValue(PlanItem pi) {
						return StaticMaps.specialityMap.get(pi.getCusSpeciality());
					}
				};

		// Create address column.
		TextColumn<PlanItem> callsColumn = new TextColumn<PlanItem>() {

			public String getValue(PlanItem pi) {
				return "" + pi.getCalls();
			}
		};

		// ButtonCell.
		Column c = addColumnPlanItem(new ButtonCell(), "",
				new GetValuePlanItem<String>() {
					@Override
					public String getValue(PlanItem pi) {
						return "Add call";
					}
				}, new FieldUpdater<PlanItem, String>() {
					@Override
					public void update(int index, PlanItem pi, String value) {
//						 final DialogBox dialogBox = createDialogBox(pi.getId());
//						    dialogBox.setGlassEnabled(true);
//						    dialogBox.setAnimationEnabled(true);
//                            dialogBox.center();
//						    dialogBox.show();
						PanelUtil.displayThisWidget(AddCallPanel.getAddCallPanel(pi));
					}
				});

		// Add the columns.
		piTable.addColumn(cusIdColumn, "Customer Id");
		piTable.addColumn(cusNameColumn, "Customer Name");
		piTable.addColumn(cusTierColumn, "Tier");
		piTable.addColumn(cusSpecialityColumn, "Speciality");
		piTable.addColumn(callsColumn, "Calls");

		piTable.addColumn(c, "add call");

		// Create a data provider.
		final ListDataProvider<PlanItem> dataProvider = new ListDataProvider<PlanItem>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(piTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		listPlanItems = dataProvider.getList();
		for (PlanItem pi : PlanItems) {
			listPlanItems.add(pi);
		}
		
		filter.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				int sid = Integer.parseInt(specLB.getValue(specLB.getSelectedIndex()));
				int tid = Integer.parseInt(tierLB.getValue(tierLB.getSelectedIndex()));
				listPlanItems = dataProvider.getList();
				listPlanItems.clear();
				for (PlanItem pi : PlanItems) {
					if(pi.getCusSpeciality() == sid && pi.getCusTier() == tid){
					   listPlanItems.add(pi);
					}
				}
				
			}
		});
		
		cancel.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				listPlanItems.clear();
				for (PlanItem pi : PlanItems) {
					   listPlanItems.add(pi);
				}
				
			}
		});

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<PlanItem> columnSortHandler = new ListHandler<PlanItem>(
				listPlanItems);
		columnSortHandler.setComparator(cusNameColumn,
				new Comparator<PlanItem>() {
					public int compare(PlanItem o1, PlanItem o2) {
						if (o1 == o2) {
							return 0;
						}

						// Compare the name columns.
						if (o1 != null) {
							return (o2 != null) ? o1.getCusName().compareTo(
									o2.getCusName()) : 1;
						}
						return -1;
					}
				});
		piTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		piTable.getColumnSortList().push(cusNameColumn);

		// Add a selection model to handle user selection.

		vPlanItemPanel.add(piTable);
		vPlanItemPanel.add(pager);
		return vPlanItemPanel;
	}
	
	
	
	private static <C> Column<PlanItem, C> addColumnPlanItem(Cell<C> cell,
			String headerText, final GetValuePlanItem<C> getter,
			FieldUpdater<PlanItem, C> fieldUpdater) {
		Column<PlanItem, C> column = new Column<PlanItem, C>(cell) {
			@Override
			public C getValue(PlanItem object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		return column;
	}

	private static interface GetValuePlanItem<C> {
		C getValue(PlanItem pi);
	}
	
	
	
	  private static DialogBox createDialogBox(int piId) {
		    // Create a dialog box and set the caption text
		    final DialogBox dialogBox = new DialogBox();

		    dialogBox.setText("Add call");

		    // Create a table to layout the content
		    VerticalPanel dialogContents = new VerticalPanel();
		    dialogContents.setSpacing(4);
		    dialogBox.setWidget(dialogContents);

		    final TextBox planItemId = new TextBox();
		    planItemId.setEnabled(false);
		    planItemId.setText(""+piId);
		    final TextArea comment = new TextArea(); 
		    		

		    FlexTable layout = new FlexTable();
			layout.setCellSpacing(6);
			FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
		    
		    layout.setHTML(0, 0, "Add call");
			cellFormatter.setColSpan(0, 0, 2);
			cellFormatter.setHorizontalAlignment(0, 0,
			HasHorizontalAlignment.ALIGN_CENTER);

			// Add some standard form options
			layout.setHTML(1, 0, "Plan item id ");
			layout.setWidget(1, 1, planItemId);
			
			layout.setHTML(2, 0, "Comment ");
			layout.setWidget(2, 1, comment);
			
			// Wrap the content in a DecoratorPanel
			DecoratorPanel dp = new DecoratorPanel();
			dp.setWidget(layout);

			Button saveButton = new Button(
			        "Save", new ClickHandler() {
			          public void onClick(ClickEvent event) {
			        	  Call c = new Call();
			        	  c.setPiId(Integer.parseInt(planItemId.getText()));
			        	  c.setComment(comment.getText());
			        	  empService.saveCall(c , new AsyncCallback<Call>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("error in saving the call");
							}

							@Override
							public void onSuccess(Call result) {
								Window.alert("call saved");
							}
						});
			        	  
			            dialogBox.hide();
			          }
			        });
			    
	
			// Add a close button at the bottom of the dialog
		    Button closeButton = new Button(
		        "Close", new ClickHandler() {
		          public void onClick(ClickEvent event) {
		            dialogBox.hide();
		          }
		        });
		    
			layout.setWidget(4, 0, saveButton);
			layout.setWidget(4, 1, closeButton);

			
		    dialogContents.add(dp);
		    
		    // Return the dialog box
		    return dialogBox;
		  }


}
