package com.sis.mrep.client.proxy;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("SessionServlet")
public interface SessionService {

	public String getUserSessionId();
    public Boolean isSessionAlive(String sId);
}
