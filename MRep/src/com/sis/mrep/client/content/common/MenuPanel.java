package com.sis.mrep.client.content.common;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.sis.mrep.client.content.customer.CustomerCommand;
import com.sis.mrep.client.content.employee.EmployeeCommand;
import com.sis.mrep.client.content.manager.CoverageReportCommand;
import com.sis.mrep.client.content.plan.MyPlanCommand;
import com.sis.mrep.client.content.plan.PlanCommand;
import com.sis.mrep.client.content.profile.LogoutCommand;
import com.sis.mrep.client.content.profile.NewPasswordCommand;
import com.sis.mrep.client.content.profile.ProfileCommand;
import com.sis.mrep.client.content.settings.BrickCommand;
import com.sis.mrep.client.content.settings.LineComand;
import com.sis.mrep.client.content.settings.ProductCommand;
import com.sis.mrep.client.content.settings.TerritoryMenuCommand;
import com.sis.mrep.client.content.settings.ZoneMenuComand;

public class MenuPanel {

	public static final int USER_TYPE_MANAGER = 1;
	public static final int USER_TYPE_ADMIN = 2;
	public static final int USER_TYPE_REP = 3;

	public static MenuBar getHeaderMenu(int userType) {
		// Create a menu bar
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setWidth("500px");
		menu.setAnimationEnabled(true);
		menu.getElement().setPropertyString("align", "center");

		// Create the file menu
		MenuBar myAccountMenu = new MenuBar(true);
		myAccountMenu.setAnimationEnabled(true);
		menu.addItem(new MenuItem("My Account", myAccountMenu));

		myAccountMenu.addSeparator();
		myAccountMenu
				.addItem("My profile", ProfileCommand.getEmployeeCommand());
		myAccountMenu.addSeparator();
		myAccountMenu.addItem("Change Password",
				NewPasswordCommand.getEmployeeCommand());
		myAccountMenu.addSeparator();
		myAccountMenu.addItem("Logout",
				LogoutCommand.logoutCommand());
		
		myAccountMenu.addSeparator();
		if (userType == USER_TYPE_REP) {
			// Create the edit menu
			MenuBar myActivitiesMenu = new MenuBar(true);
			menu.addItem(new MenuItem("My Activities", myActivitiesMenu));
			myActivitiesMenu.addSeparator();
			myActivitiesMenu.addItem("Current Plan",
					MyPlanCommand.getPlanCommand());
			myActivitiesMenu.addSeparator();
			// myActivitiesMenu.addItem("Add visit", getMyProfileCommand());
			// myActivitiesMenu.addSeparator();
		}
		if (userType == USER_TYPE_MANAGER) {
			// Create the GWT menu
			MenuBar reportMenu = new MenuBar(true);
			menu.addItem(new MenuItem("Reports", true, reportMenu));
			reportMenu.addSeparator();
			reportMenu.addItem("Coverage per specialty & tier",
					CoverageReportCommand.getPlanCommand());
			reportMenu.addSeparator();
			reportMenu.addItem("Calles per Doctor", CoverageReportCommand.getPlanCommand());
			reportMenu.addSeparator();
			reportMenu.addItem("Rep Productivity", CoverageReportCommand.getPlanCommand());
			reportMenu.addSeparator();
			reportMenu.addItem("Time management", CoverageReportCommand.getPlanCommand());
			reportMenu.addSeparator();
			reportMenu.addItem("Calls per products", CoverageReportCommand.getPlanCommand());
			reportMenu.addSeparator();
			reportMenu.addItem("Sample consumption", CoverageReportCommand.getPlanCommand());
			reportMenu.addSeparator();
		}
		if (userType == USER_TYPE_ADMIN) {
			// Create the GWT menu
			MenuBar adminMenu = new MenuBar(true);
			menu.addItem(new MenuItem("Administration", true, adminMenu));
			adminMenu.addSeparator();

			adminMenu.addItem("Employee Management",
					EmployeeCommand.getEmployeeCommand());
			adminMenu.addSeparator();

			adminMenu.addItem("Customer Management",
					CustomerCommand.getCustomerCommand());
			adminMenu.addSeparator();

			adminMenu.addItem("Plan Management", PlanCommand.getPlanCommand());
			adminMenu.addSeparator();

			// Create the help menu
			MenuBar settingsMenu = new MenuBar(true);
			menu.addItem(new MenuItem("Settings", settingsMenu));
			settingsMenu.addSeparator();
			settingsMenu.addItem("Zone", ZoneMenuComand.getZoneCommand());
			settingsMenu.addSeparator();
			settingsMenu.addItem("territory",
					TerritoryMenuCommand.getTerritoryCommand());
			settingsMenu.addSeparator();
			settingsMenu.addItem("Brick", BrickCommand.getBrickCommand());
			settingsMenu.addSeparator();
			settingsMenu.addItem("Line", LineComand.getLineCommand());
			settingsMenu.addSeparator();
			settingsMenu.addItem("Product", ProductCommand.getProductCommand());
			settingsMenu.addSeparator();
		}
		// Return the menu
		menu.ensureDebugId("cwMenuBar");
		return menu;
	}

	private static Command getMyProfileCommand() {
		Command menuCommand = new Command() {

			public void execute() {

			}
		};

		return menuCommand;
	}

}
