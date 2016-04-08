package com.sis.mrep.client.proxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sis.mrep.shared.model.Employee;

@RemoteServiceRelativePath("SecurityManager")

public interface SecurityManager extends RemoteService{

	public Employee login(String userName, String userPass);
	
	public Employee getCurrentUser();
	
}
