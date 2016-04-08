package com.sis.mrep.client.content.customer;

import com.google.gwt.user.client.Command;

public class CustomerCommand {
	
	public static Command getCustomerCommand(){
		
		 Command command = new Command() {

		      public void execute() {
		    	  
		    	  CustomerPanel.loadCustomerPanel();;
				
		      }
		    };
		    
		    return command;
	}

}
