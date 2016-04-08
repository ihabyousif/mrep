package com.sis.mrep.client.content.settings;

import com.sis.mrep.client.content.util.PanelUtil;
import com.google.gwt.user.client.Command;

public class BrickCommand {
	
	public static Command getBrickCommand(){
		
		 Command command = new Command() {

		      public void execute() {
		    	  
		    	  BrickPanel.loadBrickPanel();
				
		      }
		    };
		    
		    return command;
	}

}
