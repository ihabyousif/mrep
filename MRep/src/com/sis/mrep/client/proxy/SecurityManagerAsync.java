package com.sis.mrep.client.proxy;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sis.mrep.shared.model.Employee;

public interface SecurityManagerAsync {

	void login(String userName, String userPass,
			AsyncCallback<Employee> callback);

	void getCurrentUser(AsyncCallback<Employee> callback);

}
