package com.sis.mrep.client;


import com.google.gwt.core.client.EntryPoint;
import com.sis.mrep.client.content.login.LoginPanel;
import com.sis.mrep.shared.model.Employee;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MRep implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	public static Employee emp = null;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		 //RootPanel.get("body.header").add(MenuPanel.getHeaderMenu());
		LoginPanel.loadLoginPanel();
		   
	}
	
}
