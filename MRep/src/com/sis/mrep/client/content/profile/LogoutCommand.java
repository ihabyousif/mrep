package com.sis.mrep.client.content.profile;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;

public class LogoutCommand {
	
	public static Command logoutCommand(){
		
		 Command command = new Command() {

		      public void execute() {
		    	  
		    	  
		    	  Window.Location.reload();
				
		      }
		    };
		    
		    return command;
	}

}
