package com.sis.mrep.client.content.employee;

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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.proxy.AdminManager;
import com.sis.mrep.client.proxy.AdminManagerAsync;
import com.sis.mrep.shared.FieldVerifier;
import com.sis.mrep.shared.model.Brick;
import com.sis.mrep.shared.model.Employee;
import com.sis.mrep.shared.model.Line;

public class EmployeePanel {

	// A simple data type that represents a contact.
	static Employee employee;
	// The list of data to display.
	private static List<Employee> Employees = new ArrayList<Employee>();

	private static List<Employee> list;
	private static AdminManagerAsync adminService = GWT.create(AdminManager.class);

	private static VerticalPanel vPanel = null;
	
	public static void loadEmployeePanel(){
		
		adminService.getEmployeeList(new AsyncCallback<List<Employee>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<Employee> employeeList) {
						Employees = employeeList;
						PanelUtil.displayThisWidget(getEmployeePanel());
					}
		});
		
	}

	public static Widget getEmployeePanel() {
		if (vPanel != null) {
			return vPanel;
		} else {
			vPanel = new VerticalPanel();
		}

		CellTable<Employee> employeeTable = new CellTable<Employee>();
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		// Create a CellTable.

		pager.setDisplay(employeeTable);

		// Create name column.
		TextColumn<Employee> idColumn = new TextColumn<Employee>() {
			@Override
			public String getValue(Employee employee) {
				return "" + employee.getId();
			}
		};

		// Create name column.
		TextColumn<Employee> nameColumn = new TextColumn<Employee>() {
			@Override
			public String getValue(Employee employee) {
				return employee.getName();
			}
		};

		// Make the name column sortable.
		nameColumn.setSortable(true);

		// Create phone column.
		TextColumn<Employee> phoneColumn = new TextColumn<Employee>() {
			@Override
			public String getValue(Employee employee) {
				return "" + employee.getPhone();
			}
		};

		// Create address column.
		TextColumn<Employee> addressColumn = new TextColumn<Employee>() {

			public String getValue(Employee employee) {
				return "" + employee.getAddress();
			}
		};
		
		// Create address column.
				TextColumn<Employee> userNameColumn = new TextColumn<Employee>() {
					@Override
					public String getValue(Employee employee) {
						return "" + employee.getUserName();
					}
				};
				
		// Create type column.
		TextColumn<Employee> typeColumn = new TextColumn<Employee>() {
			@Override
			public String getValue(Employee employee) {

				return typeMap.get(employee.getType());
			}
		};
		
		Column editColumn = addColumn(new ButtonCell(), "Button",
				new GetValue<String>() {
					@Override
					public String getValue(Employee p) {
						return "Edit";
					}
				}, new FieldUpdater<Employee, String>() {
					@Override
					public void update(int index, Employee p, String value) {
						PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
					}
				});

		// Add the columns.
		employeeTable.addColumn(idColumn, "Id");
		employeeTable.addColumn(nameColumn, "Name");
		employeeTable.addColumn(userNameColumn, "User Name");
		employeeTable.addColumn(phoneColumn, "phone");
		employeeTable.addColumn(addressColumn, "Address");
		
		employeeTable.addColumn(typeColumn, "Type");
		
		employeeTable.addColumn(editColumn, "Edit");

		// Create a data provider.
		ListDataProvider<Employee> dataProvider = new ListDataProvider<Employee>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(employeeTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		list = dataProvider.getList();
		for (Employee employee : Employees) {
			list.add(employee);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Employee> columnSortHandler = new ListHandler<Employee>(
				list);
		columnSortHandler.setComparator(nameColumn, new Comparator<Employee>() {
			public int compare(Employee o1, Employee o2) {
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
		employeeTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		employeeTable.getColumnSortList().push(nameColumn);

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<Employee>();
		employeeTable.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Employee employee = selectionModel.getSelectedObject();
						if (employee != null) {
							PanelUtil
									.appendToCurrentWidget(getUpdateWidget(employee));
						}
					}
				});

		vPanel.add(employeeTable);

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

	public static Widget getUpdateWidget(Employee employee) {
		updatePanel.setVisible(true);
		final TextBox employeeId = new TextBox();
		final TextBox employeeName = new TextBox();
		final Label employeeNamev = new Label();
		final TextBox employeeUserName = new TextBox();
		final Label employeeUserNamev = new Label();
		final PasswordTextBox employeePass = new PasswordTextBox();
		final Label employeePassv = new Label();
		final TextBox employeePhone = new TextBox();
		final Label employeePhonev = new Label();
		final TextBox employeeAddress = new TextBox();
		final Label employeeAddressv = new Label();
		final TextBox employeeManager = new TextBox();

		
		
		employeeId.setEnabled(false);
		
		if (employee != null) {
			employeeId.setText(""+employee.getId());
			employeeName.setText(employee.getName());
			employeeUserName.setText(employee.getUserName());
			employeePass.setText(employee.getUserPass());
			employeePhone.setText(employee.getPhone());
			employeeAddress.setText(employee.getAddress());
			employeeManager.setText(""+employee.getManager());
		}

		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a normal button
		Button saveButton = new Button(
		        "Save", new ClickHandler() {
		          public void onClick(ClickEvent event) {
		        	  
		        	  if (!FieldVerifier.isValidName(employeeName.getText())) {
		        		  employeeNamev.setText("Please enter at least four characters");
							return;
						 }
		        	  if (!FieldVerifier.isValidName(employeeUserName.getText())) {
		        		  employeeUserNamev.setText("Please enter at least four characters");
							return;
						 }
		        	  if (!FieldVerifier.isValidName(employeePass.getText())) {
		        		  employeePassv.setText("Please enter at least four characters");
							return;
						 }
		        	  if (!FieldVerifier.isValidName(employeePhone.getText())) {
		        		  employeePhonev.setText("Please enter at least four characters");
							return;
						 }
		        	  if (!FieldVerifier.isValidName(employeeAddress.getText())) {
		        		  employeeAddressv.setText("Please enter at least four characters");
							return;
						 }
		        	  
		        	  
		        	  int id = (employeeId.getText().equals(""))? 0:Integer.parseInt(employeeId.getText());
		        	  int managerId = (employeeManager.getText().equals(""))? 0:Integer.parseInt(employeeManager.getText());
		        	  int type = Integer.parseInt(lb.getValue(lb.getSelectedIndex()));
		        	  int brickId = Integer.parseInt(brickDropBox.getValue(brickDropBox.getSelectedIndex()));
		        	  int lineId = Integer.parseInt(lineDropBox.getValue(lineDropBox.getSelectedIndex()));
		        	  
		        	  Employee e = new Employee(id, employeeName.getText(), employeePhone.getText(), employeeAddress.getText(),
		        			  employeeUserName.getText(), employeePass.getText(), managerId, type , brickId , lineId);

		        	  adminService.saveEmployee(e, new AsyncCallback<Employee>(){

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Unable to obtain server response: "
								      + caught.getMessage());	
							
						}

						@Override
						public void onSuccess(Employee e) {
							list.remove(e);
							list.add(e);
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
		layout.setHTML(0, 0, "Employee Information");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		// Add some standard form options
		layout.setHTML(1, 0, "Employee Id :");
		layout.setWidget(1, 1, employeeId);
		
		layout.setHTML(2, 0, "Employee");
		layout.setWidget(2, 1, employeeName);
		layout.setWidget(2, 2, employeeNamev);
		
		layout.setHTML(3, 0, "User name : ");
		layout.setWidget(3, 1, employeeUserName);
		layout.setWidget(3, 2, employeeUserNamev);
		
		layout.setHTML(4, 0, "User pass : ");
		layout.setWidget(4, 1, employeePass);
		layout.setWidget(4, 2, employeePassv);
		
		layout.setHTML(5, 0, "phone");
		layout.setWidget(5, 1, employeePhone);
		layout.setWidget(5, 2, employeePhonev);
		
		layout.setHTML(6, 0, "address");
		layout.setWidget(6, 1, employeeAddress);
		layout.setWidget(6, 2, employeeAddressv);
		
		layout.setHTML(7, 0, "Manager");
		layout.setWidget(7, 1, employeeManager);
		
		layout.setHTML(8, 0, "Type");
		layout.setWidget(8, 1, getEmployeeType());
		
		layout.setHTML(9, 0, "Brick");
		layout.setWidget(9, 1, brickDropBox);
		
		layout.setHTML(10, 0, "Line");
		layout.setWidget(10, 1, lineDropBox);

		layout.setWidget(11, 0, saveButton);
		layout.setWidget(11, 1, cancelButton);

		// Wrap the content in a DecoratorPanel

	    adminService.getBrickList("",new AsyncCallback<List<Brick>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Brick> Bricks) {
				loadBrickListBox(Bricks);
				
			}
});
	    
	    adminService.getLineList("",new AsyncCallback<List<Line>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Line> Lines) {
				loadLineListBox(Lines);
				
			}
});
	    
		updatePanel.setWidget(layout);

		return updatePanel;

	}
	
	private static Map<Integer, String> typeMap = new HashMap<Integer, String>();
	static {
		typeMap.put(1, "Manager");
		typeMap.put(2, "Admin");
		typeMap.put(3, "Rep");
		
	}
	private static ListBox lb = new ListBox();
	
	private static ListBox getEmployeeType(){

		lb.addItem("Manager", "1");
		lb.addItem("Admin", "2");
		lb.addItem("Rep", "3");
		return lb;
	}
	
	private static ListBox brickDropBox = new ListBox(false);
	
	public static ListBox loadBrickListBox(List<Brick> Bricks){
		brickDropBox.clear();
			for (Brick b : Bricks) {
				brickDropBox.addItem(b.getName(), ""+b.getId());
			}

			return brickDropBox;
	}
	
	private static ListBox lineDropBox = new ListBox(false);
	
	public static ListBox loadLineListBox(List<Line> Lines){
		lineDropBox.clear();
			for (Line line : Lines) {
				lineDropBox.addItem(line.getName(), ""+line.getId());
			}

			return lineDropBox;
	}
	
	private static <C> Column<Employee, C> addColumn(Cell<C> cell,
			String headerText, final GetValue<C> getter,
			FieldUpdater<Employee, C> fieldUpdater) {
		Column<Employee, C> column = new Column<Employee, C>(cell) {
			@Override
			public C getValue(Employee object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		return column;
	}

	private static interface GetValue<C> {
		C getValue(Employee c);
	}

}
