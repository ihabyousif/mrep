package com.sis.mrep.client.content.manager;

import com.google.gwt.user.client.Command;

public class CoverageReportCommand {
	
	public static Command getPlanCommand(){
		
		 Command command = new Command() {

		      public void execute() {
		    	  
		    	  CoverageReportPanel.loadEmployeePanel();
		      }
		    };
		    
		    return command;
	}

}
