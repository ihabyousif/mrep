package com.sis.mrep.client.content.settings;

import com.google.gwt.user.client.Command;

public class ZoneMenuComand {

	public static Command getZoneCommand(){
		
		 Command menuCommand = new Command() {

		      public void execute() {
		    	  
				ZonePanel.loadZonePanel();
				
		      }
		    };
		    
		    return menuCommand;
	}

}
