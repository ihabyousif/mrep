package com.sis.mrep.client.content.plan;

import com.google.gwt.user.client.Command;

public class PlanCommand {
	
	public static Command getPlanCommand(){
		
		 Command command = new Command() {

		      public void execute() {
		    	  
		    	  PlanPanel.loadPlanPanel();
				
		      }
		    };
		    
		    return command;
	}

}
