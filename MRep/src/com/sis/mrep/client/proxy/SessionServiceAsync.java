package com.sis.mrep.client.proxy;

public interface SessionServiceAsync {

	public String getUserSessionId();
    public Boolean isSessionAlive(String sId);
    
}
