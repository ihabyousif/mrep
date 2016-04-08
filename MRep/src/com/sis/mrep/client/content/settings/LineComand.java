package com.sis.mrep.client.content.settings;

import com.google.gwt.user.client.Command;

public class LineComand {

	public static Command getLineCommand(){
		
		 Command menuCommand = new Command() {

		      public void execute() {
		    	  
				LinePanel.loadLinePanel();
				
		      }
		    };
		    
		    return menuCommand;
	}

}
