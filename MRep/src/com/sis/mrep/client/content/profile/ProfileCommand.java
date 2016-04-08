package com.sis.mrep.client.content.profile;

import com.google.gwt.user.client.Command;

public class ProfileCommand {
	
	public static Command getEmployeeCommand(){
		
		 Command command = new Command() {

		      public void execute() {
		    	  
		    	  ProfilePanel.loadcurrentUserPanel();
				
		      }
		    };
		    
		    return command;
	}

}
