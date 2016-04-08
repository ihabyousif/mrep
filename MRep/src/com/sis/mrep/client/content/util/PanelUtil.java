package com.sis.mrep.client.content.util;

import java.util.Iterator;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class PanelUtil {

	public static void hideChildPanels(Widget parent) {
		if (parent instanceof HasWidgets) {
			Iterator<Widget> iter = ((HasWidgets) parent).iterator();
			while (iter.hasNext()) {
				iter.next().setVisible(false);
			}
		}

	}
	
	public static void appendToCurrentWidget(Widget widget ){
		RootPanel.get("body.header").add(widget);
		widget.setVisible(true);
	}
	
	public static void displayThisWidget(Widget widget){
		hideChildPanels(RootPanel.get("body.header"));
		RootPanel.get("body.header").add(widget);
		widget.setVisible(true);
	}
	
	public static void displayLoginWidget(Widget widget){
		hideChildPanels(RootPanel.get("body.header.login"));
		RootPanel.get("body.header.login").add(widget);
		widget.setVisible(true);
	}
	
	
	
}
