package com.sis.mrep.client.content.customer;

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
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.content.util.StaticMaps;
import com.sis.mrep.client.proxy.AdminManager;
import com.sis.mrep.client.proxy.AdminManagerAsync;
import com.sis.mrep.shared.FieldVerifier;
import com.sis.mrep.shared.model.Brick;
import com.sis.mrep.shared.model.Customer;

public class CustomerPanel {

	// A simple data type that represents a contact.
	static Customer customer;
	// The list of data to display.
	private static List<Customer> Customers = new ArrayList<Customer>();

	private static List<Customer> list;
	private static AdminManagerAsync adminService = GWT.create(AdminManager.class);
	
	private static VerticalPanel vPanel = null;

	public static void loadCustomerPanel(){
		
		adminService.getCustomerList("", new AsyncCallback<List<Customer>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<Customer> customerList) {
						Customers = customerList;
						PanelUtil.displayThisWidget(getCustomerPanel());
					}
		});
		
	}
	
	public static Widget getCustomerPanel() {
		if (vPanel != null) {
			return vPanel;
		} else {
			vPanel = new VerticalPanel();
		}

		CellTable<Customer> customerTable = new CellTable<Customer>();
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		// Create a CellTable.

		pager.setDisplay(customerTable);

		// Create name column.
		TextColumn<Customer> idColumn = new TextColumn<Customer>() {
			@Override
			public String getValue(Customer customer) {
				return "" + customer.getId();
			}
		};

		// Create name column.
		TextColumn<Customer> nameColumn = new TextColumn<Customer>() {
			@Override
			public String getValue(Customer customer) {
				return customer.getName();
			}
		};

		// Make the name column sortable.
		nameColumn.setSortable(true);

		// Create phone column.
		TextColumn<Customer> phoneColumn = new TextColumn<Customer>() {
			@Override
			public String getValue(Customer customer) {
				return "" + customer.getPhone();
			}
		};

		// Create address column.
		TextColumn<Customer> addressColumn = new TextColumn<Customer>() {

			public String getValue(Customer customer) {
				return "" + customer.getAddress();
			}
		};
		
		// Create tier column.
				TextColumn<Customer> tierColumn = new TextColumn<Customer>() {
					@Override
					public String getValue(Customer customer) {
						return StaticMaps.tierMap.get(customer.getTier());
					}
				};
				
		// Create Speciality column.
		TextColumn<Customer> specialityColumn = new TextColumn<Customer>() {
			@Override
			public String getValue(Customer customer) {
				return StaticMaps.specialityMap.get(customer.getSpeciality());
			}
		};
		
		Column editColumn = addColumn(new ButtonCell(), "Button",
				new GetValue<String>() {
					@Override
					public String getValue(Customer p) {
						return "Edit";
					}
				}, new FieldUpdater<Customer, String>() {
					@Override
					public void update(int index, Customer p, String value) {
						PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
					}
				});

		// Add the columns.
		customerTable.addColumn(idColumn, "Id");
		customerTable.addColumn(nameColumn, "Name");
		customerTable.addColumn(phoneColumn, "phone");
		customerTable.addColumn(addressColumn, "Address");
		
		customerTable.addColumn(tierColumn, "Tier");
		customerTable.addColumn(specialityColumn, "Speciality");
		
		customerTable.addColumn(editColumn, "Edit");
		

		// Create a data provider.
		ListDataProvider<Customer> dataProvider = new ListDataProvider<Customer>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(customerTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		list = dataProvider.getList();
		for (Customer customer : Customers) {
			list.add(customer);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Customer> columnSortHandler = new ListHandler<Customer>(
				list);
		columnSortHandler.setComparator(nameColumn, new Comparator<Customer>() {
			public int compare(Customer o1, Customer o2) {
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
		customerTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		customerTable.getColumnSortList().push(nameColumn);

		// Add a selection model to handle user selection.
//		final SingleSelectionModel<Customer> selectionModel = new SingleSelectionModel<Customer>();
//		customerTable.setSelectionModel(selectionModel);
//		selectionModel
//				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//					public void onSelectionChange(SelectionChangeEvent event) {
//						Customer customer = selectionModel.getSelectedObject();
//						if (customer != null) {
//							PanelUtil
//									.appendToCurrentWidget(getUpdateWidget(customer));
//						}
//					}
//				});

		vPanel.add(customerTable);
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

	public static Widget getUpdateWidget(Customer customer) {
		updatePanel.setVisible(true);
		final TextBox customerId = new TextBox();
		final TextBox customerName = new TextBox();
		final Label customerNamev = new Label();
		final TextBox customerPhone = new TextBox();
		final Label customerPhonev = new Label();
		final TextBox customerAddress = new TextBox();
		final Label customerAddressv = new Label();
		//final TextBox customerTier = new TextBox();
		//final TextBox customerSpeciality = new TextBox();
		//final TextBox customerBrick = new TextBox();
		//final TextBox customerType = new TextBox();
		
		customerId.setEnabled(false);
		if (customer != null) {
			customerId.setText(""+customer.getId());
			customerName.setText(customer.getName());
			customerPhone.setText(customer.getPhone());
			customerAddress.setText(customer.getAddress());
			//customerTier.setText(""+customer.getTier());
			//customerSpeciality.setText(""+customer.getSpeciality());
			//customerBrick.setText(""+customer.getBrick());
			//customerType.setText(""+customer.getType());
		}

		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a normal button
		 // Add a normal button
	    Button saveButton = new Button(
	        "Save", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	  if (!FieldVerifier.isValidName(customerName.getText())) {
	        		  customerNamev.setText("Please enter at least four characters");
						return;
					 }
	        	  if (!FieldVerifier.isValidName(customerPhone.getText())) {
	        		  customerPhonev.setText("Please enter at least four characters");
						return;
					 }
	        	  if (!FieldVerifier.isValidName(customerAddress.getText())) {
	        		  customerAddressv.setText("Please enter at least four characters");
						return;
					 }
	        	  int id = (customerId.getText().equals(""))? 0:Integer.parseInt(customerId.getText());
	        	  int tierId = Integer.parseInt(cusTier.getValue(cusTier.getSelectedIndex()));
	        	  int spId = Integer.parseInt(cusSpeciality.getValue(cusSpeciality.getSelectedIndex()));
	        	  int brickId = Integer.parseInt(brickDropBox.getValue(brickDropBox.getSelectedIndex()));
	        	  int typeId = Integer.parseInt(cusType.getValue(cusType.getSelectedIndex()));
	        	  
	        	  Customer c = new Customer(id, customerName.getText(), customerPhone.getText(), customerAddress.getText(),
	        			  tierId, spId , brickId , typeId);
	        	  adminService.saveCustomer(c, new AsyncCallback<Customer>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Unable to obtain server response: "
							      + caught.getMessage());	
						
					}

					@Override
					public void onSuccess(Customer c) {
						customerNamev.setText("");
			   		    customerPhonev.setText("");
						customerAddressv.setText("");
						list.remove(c);
						list.add(c);
					}
	        		  
	        	  });

	        	  updatePanel.setVisible(false);
	          }
	        });
	    
		saveButton.ensureDebugId("customer Save Button");

		// Add a normal button
		Button cancelButton = new Button("Cancel", new ClickHandler() {
			public void onClick(ClickEvent event) {
				customerNamev.setText("");
	   		    customerPhonev.setText("");
				customerAddressv.setText("");
				
				updatePanel.setVisible(false);
			}
		});
		cancelButton.ensureDebugId("cwBasicButton-normal");

		// Add a title to the form
		layout.setHTML(0, 0, "Customer Information");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		// Add some standard form options
		layout.setHTML(1, 0, "Customer Id :");
		layout.setWidget(1, 1, customerId);
		
		layout.setHTML(2, 0, "Customer");
		layout.setWidget(2, 1, customerName);
		
		layout.setHTML(3, 0, "phone");
		layout.setWidget(3, 1, customerPhone);
		
		layout.setHTML(4, 0, "address");
		layout.setWidget(4, 1, customerAddress);
		
		layout.setHTML(5, 0, "Tier : ");
		layout.setWidget(5, 1, getCusTier());
		
		layout.setHTML(6, 0, "Speciality");
		layout.setWidget(6, 1, getCusSpeciality());
		
		layout.setHTML(7, 0, "Brick");
		layout.setWidget(7, 1, brickDropBox);
		
		layout.setHTML(8, 0, "Type");
		layout.setWidget(8, 1, getEmployeeType());

		layout.setWidget(9, 0, saveButton);
		layout.setWidget(9, 1, cancelButton);

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
	    
		updatePanel.setWidget(layout);
		return updatePanel;

	}
	
	
	
	private static ListBox cusType = new ListBox();
	
	private static ListBox getEmployeeType(){
		if (cusType.getItemCount() <=0){
		cusType.addItem("Hospital", "1");
		cusType.addItem("Pharmacy", "1");
		cusType.addItem("Office", "1");
		}
		return cusType;
	}
	

	
private static ListBox cusSpeciality = new ListBox();
	
	private static ListBox getCusSpeciality(){
		if(cusSpeciality.getItemCount() <=0){
		cusSpeciality.addItem("Cardialoty", "1");
		cusSpeciality.addItem("Bateny", "2");
		}
		return cusSpeciality;
	}
	

	
private static ListBox cusTier = new ListBox();
	
	private static ListBox getCusTier(){
		if(cusTier.getItemCount() <= 0){
		cusTier.addItem("HH", "1");
		cusTier.addItem("MM", "2");
		cusTier.addItem("LL", "3");
		cusTier.addItem("HM", "4");
		cusTier.addItem("HL", "5");
		cusTier.addItem("MH", "6");
		cusTier.addItem("ML", "7");
		cusTier.addItem("LH", "8");
		cusTier.addItem("LM", "9");
		}
		return cusTier;
	}
	

	
	private static ListBox brickDropBox = new ListBox(false);
	
	public static ListBox loadBrickListBox(List<Brick> Bricks){
		    brickDropBox.clear();
			for (Brick b : Bricks) {
				brickDropBox.addItem(b.getName(), ""+b.getId());
			}

			return brickDropBox;
	}

	private static <C> Column<Customer, C> addColumn(Cell<C> cell,
			String headerText, final GetValue<C> getter,
			FieldUpdater<Customer, C> fieldUpdater) {
		Column<Customer, C> column = new Column<Customer, C>(cell) {
			@Override
			public C getValue(Customer object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		return column;
	}

	private static interface GetValue<C> {
		C getValue(Customer c);
	}
}
