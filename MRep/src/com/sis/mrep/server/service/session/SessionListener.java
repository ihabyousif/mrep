package com.sis.mrep.server.service.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sis.mrep.server.util.SessionHistory;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent session) {
		System.out.println("Added id = "+session.getSession().getId());
		SessionHistory.ActiveSessionMap.put(session.getSession().getId(), true);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent session) {
		System.out.println("Remove id = "+session.getSession().getId());
		SessionHistory.ActiveSessionMap.remove(session.getSession().getId());
	}

}
