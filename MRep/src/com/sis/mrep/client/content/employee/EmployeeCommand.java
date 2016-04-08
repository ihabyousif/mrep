package com.sis.mrep.client.content.employee;

import com.google.gwt.user.client.Command;

public class EmployeeCommand {
	
	public static Command getEmployeeCommand(){
		
		 Command command = new Command() {

		      public void execute() {
		    	  
		    	  EmployeePanel.loadEmployeePanel();
				
		      }
		    };
		    
		    return command;
	}

}
