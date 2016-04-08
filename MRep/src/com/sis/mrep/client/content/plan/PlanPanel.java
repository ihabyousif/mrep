package com.sis.mrep.client.content.plan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.proxy.AdminManager;
import com.sis.mrep.client.proxy.AdminManagerAsync;
import com.sis.mrep.shared.FieldVerifier;
import com.sis.mrep.shared.model.Brick;
import com.sis.mrep.shared.model.Employee;
import com.sis.mrep.shared.model.Plan;
import com.sis.mrep.shared.model.PlanItem;

public class PlanPanel {

	// The list of data to display.
	private static List<Plan> Plans = new ArrayList<Plan>();

	private static List<Plan> list;

	private static AdminManagerAsync adminService = GWT
			.create(AdminManager.class);

	private static VerticalPanel vPanel = null;

	public static void loadPlanPanel() {

		adminService.getPlanList(new AsyncCallback<List<Plan>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<Plan> PlanList) {
				Plans = PlanList;
				PanelUtil.displayThisWidget(getPlanPanel());
			}
		});

	}

	public static Widget getPlanPanel() {
		if (vPanel != null) {
			return vPanel;
		} else {
			vPanel = new VerticalPanel();
		}

		CellTable<Plan> planTable = new CellTable<Plan>();
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		// Create a CellTable.

		pager.setDisplay(planTable);

		// Create name column.
		TextColumn<Plan> idColumn = new TextColumn<Plan>() {
			@Override
			public String getValue(Plan plan) {
				return "" + plan.getId();
			}
		};

		// Create name column.
		TextColumn<Plan> nameColumn = new TextColumn<Plan>() {
			@Override
			public String getValue(Plan plan) {
				return plan.getName();
			}
		};

		// Make the name column sortable.
		nameColumn.setSortable(true);

		// Create phone column.
		TextColumn<Plan> dateColumn = new TextColumn<Plan>() {
			@Override
			public String getValue(Plan plan) {
				return "" + plan.getPlanDate();
			}
		};

		// Create address column.
		TextColumn<Plan> repColumn = new TextColumn<Plan>() {

			public String getValue(Plan plan) {
				return "" + plan.getRepId();
			}
		};

		// Create address column.
		TextColumn<Plan> brickColumn = new TextColumn<Plan>() {
			@Override
			public String getValue(Plan plan) {
				return "" + plan.getBrickId();
			}
		};

		// ButtonCell.
		// Column c = addColumn(new ButtonCell(), "Button", new
		// GetValue<String>() {
		// @Override
		// public String getValue(Plan contact) {
		// return "Click " ;
		// }
		// });
		
		Column editColumn = addColumn(new ButtonCell(), "Button",
				new GetValue<String>() {
					@Override
					public String getValue(Plan p) {
						return "Edit";
					}
				}, new FieldUpdater<Plan, String>() {
					@Override
					public void update(int index, Plan p, String value) {
						PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
					}
				});

		// ButtonCell.
		Column c = addColumn(new ButtonCell(), "Button",
				new GetValue<String>() {
					@Override
					public String getValue(Plan p) {
						return "Items";
					}
				}, new FieldUpdater<Plan, String>() {
					@Override
					public void update(int index, Plan p, String value) {
						loadItemsPanel(p.getId() , p.getBrickId());
					}
				});

		// Add the columns.
		planTable.addColumn(idColumn, "Id");
		planTable.addColumn(nameColumn, "Name");
		planTable.addColumn(dateColumn, "Plan Date");
		planTable.addColumn(repColumn, "Rep");
		planTable.addColumn(brickColumn, "Brick");
		planTable.addColumn(editColumn, "Edit");
		planTable.addColumn(c, "Plan Items");

		// Create a data provider.
		ListDataProvider<Plan> dataProvider = new ListDataProvider<Plan>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(planTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		list = dataProvider.getList();
		for (Plan plan : Plans) {
			list.add(plan);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Plan> columnSortHandler = new ListHandler<Plan>(list);
		columnSortHandler.setComparator(nameColumn, new Comparator<Plan>() {
			public int compare(Plan o1, Plan o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.getName().compareTo(o2.getName())
							: 1;
				}
				return -1;
			}
		});
		planTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		planTable.getColumnSortList().push(nameColumn);

		// Add a selection model to handle user selection.
//		final SingleSelectionModel<Plan> selectionModel = new SingleSelectionModel<Plan>();
//		planTable.setSelectionModel(selectionModel);
//		selectionModel
//				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//					public void onSelectionChange(SelectionChangeEvent event) {
//						Plan plan = selectionModel.getSelectedObject();
//						if (plan != null) {
//							PanelUtil
//									.appendToCurrentWidget(getUpdateWidget(plan));
//						}
//					}
//				});

		
		vPanel.add(planTable);
		vPanel.add(pager);
		Anchor addNew = new Anchor("Add new");
		addNew.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				PanelUtil.appendToCurrentWidget(getUpdateWidget(null));
			}
		});
		vPanel.add(addNew);

		return vPanel;
	}

	final static DecoratorPanel updatePanel = new DecoratorPanel();

	public static Widget getUpdateWidget(Plan plan) {
		updatePanel.setVisible(true);
		final TextBox planId = new TextBox();
		final TextBox planName = new TextBox();
		final Label planNamev = new Label();
		// Create a DateBox
		DateTimeFormat dateFormat = DateTimeFormat.getShortDateTimeFormat();
		final DateBox planDate = new DateBox();
		planDate.setFormat(new DateBox.DefaultFormat(dateFormat));

		planId.setEnabled(false);

		if (plan != null) {
			planId.setText("" + plan.getId());
			planName.setText(plan.getName());
			planDate.setValue(plan.getPlanDate());

		}

		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a normal button
		Button saveButton = new Button("Save", new ClickHandler() {
			public void onClick(ClickEvent event) {
				 if (!FieldVerifier.isValidName(planName.getText())) {
					 planNamev.setText("Please enter at least four characters");
						return;
					 }
				int id = (planId.getText().equals("")) ? 0
						: Integer.parseInt(planId.getText());
				int repId = Integer.parseInt(employeeList.getValue(employeeList
						.getSelectedIndex()));
				int brickId = Integer.parseInt(brickDropBox
						.getValue(brickDropBox.getSelectedIndex()));

				Plan plan = new Plan(id, planName.getText(),
						planDate.getValue(), brickId, repId, 0);

				adminService.savePlan(plan, new AsyncCallback<Plan>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Unable to obtain server response: "
								+ caught.getMessage());

					}

					@Override
					public void onSuccess(Plan p) {
						list.remove(p);
						list.add(p);
					}

				});

				updatePanel.setVisible(false);
			}
		});
		saveButton.ensureDebugId("employee Save Button");

		// Add a normal button
		Button cancelButton = new Button("Cancel", new ClickHandler() {
			public void onClick(ClickEvent event) {
				updatePanel.setVisible(false);
			}
		});
		cancelButton.ensureDebugId("cwBasicButton-normal");

		// Add a title to the form
		layout.setHTML(0, 0, "Plan  Information");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		// Add some standard form options
		layout.setHTML(1, 0, "Id :");
		layout.setWidget(1, 1, planId);

		layout.setHTML(2, 0, "Plan :");
		layout.setWidget(2, 1, planName);
		layout.setWidget(2, 2, planNamev);

		layout.setHTML(3, 0, "Date :");
		layout.setWidget(3, 1, planDate);

		layout.setHTML(8, 0, "Rep :");
		layout.setWidget(8, 1, employeeList);

		layout.setHTML(9, 0, "Brick :");
		layout.setWidget(9, 1, brickDropBox);

		layout.setWidget(10, 0, saveButton);
		layout.setWidget(10, 1, cancelButton);

		// Wrap the content in a DecoratorPanel

		adminService.getBrickList("", new AsyncCallback<List<Brick>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<Brick> Bricks) {
				loadBrickListBox(Bricks);

			}
		});

		adminService.getEmployeeList(new AsyncCallback<List<Employee>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<Employee> Employees) {
				loadEmployeeListBox(Employees);

			}
		});

		updatePanel.setWidget(layout);

		return updatePanel;

	}

	private static ListBox employeeList = new ListBox();

	public static ListBox loadEmployeeListBox(List<Employee> Employees) {
		employeeList.clear();
		for (Employee emp : Employees) {
			employeeList.addItem(emp.getName(), "" + emp.getId());
		}

		return employeeList;
	}

	private static ListBox brickDropBox = new ListBox(false);

	public static ListBox loadBrickListBox(List<Brick> Bricks) {
		brickDropBox.clear();
		for (Brick b : Bricks) {
			brickDropBox.addItem(b.getName(), "" + b.getId());
		}

		return brickDropBox;
	}

	private static <C> Column<Plan, C> addColumn(Cell<C> cell,
			String headerText, final GetValue<C> getter,
			FieldUpdater<Plan, C> fieldUpdater) {
		Column<Plan, C> column = new Column<Plan, C>(cell) {
			@Override
			public C getValue(Plan object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		return column;
	}

	private static interface GetValue<C> {
		C getValue(Plan contact);
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

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void loadItemsPanel(int planId , int pid) {

		adminService.getPlanProposalByBrick(planId ,pid,
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
						final DecoratorPanel dp = new DecoratorPanel();
						dp.setVisible(true);
						DecoratorPanel dp1 = new DecoratorPanel();
						dp1.add(w);
						Widget w2 = getPlanItemPanel2();
						DecoratorPanel dp2 = new DecoratorPanel();
						dp2.add(w2);
						HorizontalPanel hPanel = new HorizontalPanel();
					    hPanel.setSpacing(5);
					    
					    dp.add(hPanel);
					    hPanel.add(dp1);
					    hPanel.add(dp2);
					    
					    final VerticalPanel vp = new VerticalPanel();
					    Anchor approve = new Anchor("Approve Plan Items .");
					    approve.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								List<PlanItem> piList = new ArrayList<PlanItem>();
								for (PlanItem p : listPlanItems2){
									piList.add(p);
								}
								adminService.savePlanItemList(piList, new AsyncCallback<String>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Error in saving plan items");

									}

									@Override
									public void onSuccess(String result) {
										Window.alert("Plan items Approved");
										
									}
								});
								
								vp.setVisible(false);
							}
						});
					    
					    Anchor cancel = new Anchor("Cancel Plan Items .");
					    cancel.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								vp.setVisible(false);
							}
						});
						// Create the dialog box
						//final DialogBox dialogBox = createDialogBox(hPanel);
						//dialogBox.setGlassEnabled(true);
						//dialogBox.setAnimationEnabled(true);
						//dialogBox.setWidth("600px");
						//dialogBox.center();
						//dialogBox.show();
					    
					    //dp.add(hPanel);
					    vp.add(dp);
					    vp.add(approve);
					    vp.add(cancel);
					    PanelUtil.displayThisWidget(vp);
					}
				});

	}

	private static VerticalPanel vPlanItemPanel = null;

	private static List<PlanItem> PlanItems = new ArrayList<PlanItem>();

	private static List<PlanItem> listPlanItems;

	public static Widget getPlanItemPanel() {
		if (vPlanItemPanel != null) {
			return vPlanItemPanel;
		} else {
			vPlanItemPanel = new VerticalPanel();
		}

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
				return tierMap.get(pi.getCusTier());
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
						return "Add to plan";
					}
				}, new FieldUpdater<PlanItem, String>() {
					@Override
					public void update(int index, PlanItem pi, String value) {
						// loadItemsPanel(pi.);
						listPlanItems.remove(pi);
						listPlanItems2.add(pi);
					}
				});

		// Add the columns.
		piTable.addColumn(cusIdColumn, "Customer Id");
		piTable.addColumn(cusNameColumn, "Customer Name");
		piTable.addColumn(cusTierColumn, "Tier");
		piTable.addColumn(callsColumn, "Calls");

		piTable.addColumn(c, "Add to plan");

		// Create a data provider.
		ListDataProvider<PlanItem> dataProvider = new ListDataProvider<PlanItem>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(piTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		listPlanItems = dataProvider.getList();
		for (PlanItem pi : PlanItems) {
			listPlanItems.add(pi);
		}

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

	
	
	
	private static VerticalPanel vPlanItemPanel2 = null;

	private static List<PlanItem> PlanItems2 = new ArrayList<PlanItem>();

	private static List<PlanItem> listPlanItems2;

	public static Widget getPlanItemPanel2() {
		if (vPlanItemPanel2 != null) {
			return vPlanItemPanel2;
		} else {
			vPlanItemPanel2 = new VerticalPanel();
		}

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
				return "" + pi.getCusTier();
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
						return "Remove from Plan";
					}
				}, new FieldUpdater<PlanItem, String>() {
					@Override
					public void update(int index, PlanItem pi, String value) {
						// loadItemsPanel(pi.);
						listPlanItems2.remove(pi);
						listPlanItems.add(pi);
					}
				});

		// Add the columns.
		piTable.addColumn(cusIdColumn, "Customer Id");
		piTable.addColumn(cusNameColumn, "Customer Name");
		piTable.addColumn(cusTierColumn, "Tier");
		piTable.addColumn(callsColumn, "Calls");

		piTable.addColumn(c, "Remove from plan");

		// Create a data provider.
		ListDataProvider<PlanItem> dataProvider = new ListDataProvider<PlanItem>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(piTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		listPlanItems2 = dataProvider.getList();
		for (PlanItem pi : PlanItems2) {
			listPlanItems2.add(pi);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<PlanItem> columnSortHandler = new ListHandler<PlanItem>(
				listPlanItems2);
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

		vPlanItemPanel2.add(piTable);
		vPlanItemPanel2.add(pager);
		return vPlanItemPanel2;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////
	private static DialogBox createDialogBox(Widget w) {
		// Create a dialog box and set the caption text
		final DialogBox dialogBox = new DialogBox();
		dialogBox.ensureDebugId("cwDialogBox");
		dialogBox.setText("Prepare Plan");

		// Create a table to layout the content
		VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSpacing(4);
		dialogBox.setWidget(dialogContents);

		dialogContents.add(w);

		// Add a close button at the bottom of the dialog
		Button closeButton = new Button("Close", new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		dialogContents.add(closeButton);

		// Return the dialog box
		return dialogBox;
	}
	
	private static Map<Integer, String> tierMap = new HashMap<Integer, String>();
	static {
		tierMap.put(1, "HH");
		tierMap.put(2, "MM");
		tierMap.put(3, "LL");
		tierMap.put(4, "HM");
		tierMap.put(5, "HL");
		tierMap.put(6, "MH");
		tierMap.put(7, "ML");
		tierMap.put(8, "LH");
		tierMap.put(9, "LM");
	}

}
