package com.sis.mrep.client.content.util;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

public class MyImageCell extends ImageCell{

	   @Override
	    public Set<String> getConsumedEvents() {
	        Set<String> consumedEvents = new HashSet<String>();
	        consumedEvents.add("dblclick");
	        return consumedEvents;
	    }

	    @Override
	    public void onBrowserEvent(Context context, Element parent,
	            String value, NativeEvent event,
	            ValueUpdater<String> valueUpdater) {
	    	    Window.alert(event.getType());
	            switch (DOM.eventGetType((Event)event)) {
	            case Event.ONCLICK:
	                // TODO
	            	Window.alert("Hello");
	                break;

	            default:
	                break;
	            }
	    }

}
