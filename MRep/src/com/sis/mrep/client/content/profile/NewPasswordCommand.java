package com.sis.mrep.client.content.profile;

import com.google.gwt.user.client.Command;

public class NewPasswordCommand {
	
	public static Command getEmployeeCommand(){
		
		 Command command = new Command() {

		      public void execute() {
		    	  
		    	 NewPasswordPanel.getChangePasswordWidget();
				
		      }
		    };
		    
		    return command;
	}

}
