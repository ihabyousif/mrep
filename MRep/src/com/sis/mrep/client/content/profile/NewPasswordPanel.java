package com.sis.mrep.client.content.profile;

import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.proxy.EmployeeManager;
import com.sis.mrep.client.proxy.EmployeeManagerAsync;
import com.sis.mrep.shared.model.Employee;
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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewPasswordPanel {

	// A simple data type that represents a contact.
	static Employee employee;
	// The list of data to display.

	private static EmployeeManagerAsync adminService = GWT.create(EmployeeManager.class);
	
	final static DecoratorPanel updatePanel = new DecoratorPanel();

	public static Widget getChangePasswordWidget() {
		updatePanel.setVisible(true);
		final PasswordTextBox oldPassword = new PasswordTextBox();
		final PasswordTextBox newPassword = new PasswordTextBox();
		final PasswordTextBox newPasswordVerification = new PasswordTextBox();
	
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a normal button
		Button saveButton = new Button(
		        "Save", new ClickHandler() {
		          public void onClick(ClickEvent event) {
	
		        	  adminService.updateEmployeePass(oldPassword.getText(),newPassword.getText(), new AsyncCallback(){

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Unable to obtain server response: "
								      + caught.getMessage());	
						}

						@Override
						public void onSuccess(Object result) {
							Window.alert("Password updated ");	
						}
		        		  
		        	  });

		        	  updatePanel.setVisible(false);
		          }
		        });
		

		// Add a normal button
		Button cancelButton = new Button("Cancel", new ClickHandler() {
			public void onClick(ClickEvent event) {
				updatePanel.setVisible(false);
			}
		});
		

		// Add a title to the form
		layout.setHTML(0, 0, "Change Password ");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

		// Add some standard form options
		layout.setHTML(1, 0, "Old Password :");
		layout.setWidget(1, 1, oldPassword);
		
		layout.setHTML(2, 0, "New Password");
		layout.setWidget(2, 1, newPassword);
		
		layout.setHTML(3, 0, "Verify Password");
		layout.setWidget(3, 1, newPasswordVerification);
		
		layout.setWidget(4, 0, saveButton);
		layout.setWidget(4, 1, cancelButton);

		// Wrap the content in a DecoratorPanel
		updatePanel.setWidget(layout);

		PanelUtil.displayThisWidget(updatePanel);
		
		return updatePanel;

	}
	
}
