package com.sis.mrep.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sis.mrep.client.proxy.SessionService;
import com.sis.mrep.server.util.SessionHistory;


public class SessionServiceImpl extends RemoteServiceServlet implements SessionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5793040009742453502L;

	@Override
	public String getUserSessionId() {
		return getThreadLocalRequest().getSession().getId();
	}

	@Override
	public Boolean isSessionAlive(String sId) {
		return SessionHistory.ActiveSessionMap.containsKey(sId);
	}


}
