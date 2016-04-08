package com.sis.mrep.client.content.login;

import com.sis.mrep.client.content.common.MenuPanel;
import com.sis.mrep.client.proxy.SecurityManager;
import com.sis.mrep.client.proxy.SecurityManagerAsync;
import com.sis.mrep.shared.FieldVerifier;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class LoginPanel {

	private static SecurityManagerAsync adminService = GWT.create(SecurityManager.class);
	private static Employee emp=null;

	final static  Label unv = new Label();
	final  static Label psv = new Label();
	
	public static Employee loadLoginPanel() {

		final DecoratorPanel loginPanel = new DecoratorPanel();
		FlexTable loginLayout = new FlexTable();
		loginLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = loginLayout.getFlexCellFormatter();

		// Add a title to the form
		loginLayout.setHTML(0, 0, "Login to start work");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		final TextBox un = new TextBox();
		un.setMaxLength(15);
		un.setWidth("40");
		final PasswordTextBox ps = new PasswordTextBox();
		ps.setMaxLength(8);
		ps.setWidth("40");
		// Add a normal button
		Button loginButton = new Button("Login", new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				 if (!FieldVerifier.isValidName(un.getText())) {
					unv.setText("Please enter at least four characters");
					return;
				 }
				 if (!FieldVerifier.isValidName(ps.getText())) {
						psv.setText("Please enter at least four characters");
						return;
					 }
				  adminService.login(un.getText() , ps.getText(), new
				  AsyncCallback<Employee>(){
				  
				  @Override public void onFailure(Throwable caught) {
				  Window.alert("Unable to obtain server response: " +
				  caught.getMessage());
				  
				  }
				  
				@Override
				public void onSuccess(Employee employee) {
					
					if (employee != null) {
						RootPanel.get("content").setVisible(false);
						loginPanel.setVisible(false);
					    RootPanel.get("mrep_menu").add(MenuPanel.getHeaderMenu(employee.getType()));
					}else{
						Window.alert("Invalid login");
					}
					un.setText("");
					ps.setText("");
					emp = employee;
				}
				  
				 });
				 
				
			}
		});

		// Add a normal button
		Button cancelButton = new Button("Cancel", new ClickHandler() {
			public void onClick(ClickEvent event) {
				un.setText("");
				ps.setText("");
			}
		});


		// Add some standard form options
		loginLayout.setHTML(1, 0, "User Name :");
		loginLayout.setWidget(1, 1, un);
		loginLayout.setWidget(1, 2, unv);

		loginLayout.setHTML(2, 0, "Password :");
		loginLayout.setWidget(2, 1, ps);
		loginLayout.setWidget(2, 2, psv);

		loginLayout.setWidget(3, 0, loginButton);
		loginLayout.setWidget(3, 1, cancelButton);

		// Wrap the content in a DecoratorPanel

		loginPanel.setWidget(loginLayout);
		
		
		RootPanel.get("body.header.login").add(loginPanel);
		RootPanel.get("content").setVisible(true);

		return emp;
	}

}
