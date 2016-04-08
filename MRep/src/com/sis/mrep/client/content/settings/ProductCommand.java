package com.sis.mrep.client.content.settings;

import com.sis.mrep.client.content.util.PanelUtil;
import com.google.gwt.user.client.Command;

public class ProductCommand {
	
	public static Command getProductCommand(){
		
		 Command command = new Command() {

		      public void execute() {
		    	  
		    	  ProductPanel.loadProductPanel();
				
		      }
		    };
		    
		    return command;
	}

}
