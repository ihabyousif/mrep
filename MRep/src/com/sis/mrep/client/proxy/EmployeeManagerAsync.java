package com.sis.mrep.client.proxy;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sis.mrep.shared.model.Call;
import com.sis.mrep.shared.model.Plan;

public interface EmployeeManagerAsync {

	void updateEmployeePass(String old, String newPass,
			AsyncCallback<Void> callback);

	void getPlanForCurrentEmployee(AsyncCallback<Plan> callback);

	void getPlanForEmp(int empId, AsyncCallback<Plan> callback);

	void saveCall(Call c, AsyncCallback<Call> callback);

}
