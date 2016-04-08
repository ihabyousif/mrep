package com.sis.mrep.client.content.settings;

import com.google.gwt.user.client.Command;

public class TerritoryMenuCommand {

	
	public static Command getTerritoryCommand(){
		Command territoryCommand = new Command() {
		      public void execute() {
		    	  TerritoryPanel.loadTerritoryPanel();
		    	  
				    //PanelUtil.displayThisWidget(TerritoryPanel.getTerritoryPanel());
			}
		};
		
		return territoryCommand;
	}

	
}
