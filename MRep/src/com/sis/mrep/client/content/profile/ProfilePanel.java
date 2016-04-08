package com.sis.mrep.client.content.profile;

import java.util.List;

import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.proxy.AdminManager;
import com.sis.mrep.client.proxy.AdminManagerAsync;
import com.sis.mrep.shared.model.Brick;
import com.sis.mrep.shared.model.Employee;
import com.sis.mrep.shared.model.Line;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ProfilePanel {

	// A simple data type that represents a contact.
	static Employee employee;
	// The list of data to display.

	private static List<Employee> list;
	private static AdminManagerAsync adminService = GWT.create(AdminManager.class);

	public static void loadcurrentUserPanel(){
		
		adminService.getCurrentUser( new AsyncCallback<Employee>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Employee employee) {
						PanelUtil.displayThisWidget(getUpdateWidget(employee));
					}
		});
		
	}

	final static DecoratorPanel updatePanel = new DecoratorPanel();

	public static Widget getUpdateWidget(Employee employee) {
		updatePanel.setVisible(true);
		final TextBox employeeId = new TextBox();
		final TextBox employeeName = new TextBox();
		final TextBox employeeUserName = new TextBox();
		final PasswordTextBox employeePass = new PasswordTextBox();
		final TextBox employeePhone = new TextBox();
		final TextBox employeeAddress = new TextBox();
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
		        	  int id = (employeeId.getText().equals(""))? 0:Integer.parseInt(employeeId.getText());
		        	  int brickId = Integer.parseInt(brickDropBox.getValue(brickDropBox.getSelectedIndex()));
		        	  int lineId = Integer.parseInt(lineDropBox.getValue(lineDropBox.getSelectedIndex()));
		        	  
		        	  Employee e = new Employee(id, employeeName.getText(), employeePhone.getText(), employeeAddress.getText(),
		        			  employeeUserName.getText(), employeePass.getText(), 0, 0 , brickId , lineId);

		        	  adminService.saveEmployeeProfile(e, new AsyncCallback<Employee>(){

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Unable to obtain server response: "
								      + caught.getMessage());	
							
						}

						@Override
						public void onSuccess(Employee e) {
							
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
		
		layout.setHTML(3, 0, "User name : ");
		layout.setWidget(3, 1, employeeUserName);
		
		layout.setHTML(4, 0, "User pass : ");
		layout.setWidget(4, 1, employeePass);
		
		layout.setHTML(5, 0, "phone");
		layout.setWidget(5, 1, employeePhone);
		
		layout.setHTML(6, 0, "address");
		layout.setWidget(6, 1, employeeAddress);

		
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
	
	private static ListBox lb = new ListBox();
	
	private static ListBox getEmployeeType(){
		
		
		lb.addItem("Manager", "1");
		lb.addItem("Admin", "2");
		lb.addItem("Rep", "3");
		return lb;
	}
	
	private static ListBox brickDropBox = new ListBox(false);
	
	public static ListBox loadBrickListBox(List<Brick> Bricks){

			for (Brick b : Bricks) {
				brickDropBox.addItem(b.getName(), ""+b.getId());
			}

			return brickDropBox;
	}
	
	private static ListBox lineDropBox = new ListBox(false);
	
	public static ListBox loadLineListBox(List<Line> Lines){

			for (Line line : Lines) {
				lineDropBox.addItem(line.getName(), ""+line.getId());
			}

			return lineDropBox;
	}

}
