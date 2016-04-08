package com.sis.mrep.client.content.plan;

import com.google.gwt.user.client.Command;

public class MyPlanCommand {
	
	public static Command getPlanCommand(){
		
		 Command command = new Command() {

		      public void execute() {
		    	  
		    	  MyPlanPanel.loadMyPlanPanel();
		      }
		    };
		    
		    return command;
	}

}
