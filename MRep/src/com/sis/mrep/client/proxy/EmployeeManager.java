package com.sis.mrep.client.proxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sis.mrep.shared.model.Call;
import com.sis.mrep.shared.model.Plan;

@RemoteServiceRelativePath("EmployeeManager")

public interface EmployeeManager  extends RemoteService{

	public void updateEmployeePass(String old, String newPass) ;
    public Plan getPlanForCurrentEmployee() ;
	public Plan getPlanForEmp(int empId);	
	public Call saveCall(Call c) ;	
}
