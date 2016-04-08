package com.sis.mrep.client.proxy;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sis.mrep.shared.model.Brick;
import com.sis.mrep.shared.model.Customer;
import com.sis.mrep.shared.model.Employee;
import com.sis.mrep.shared.model.Line;
import com.sis.mrep.shared.model.Plan;
import com.sis.mrep.shared.model.PlanItem;
import com.sis.mrep.shared.model.Product;
import com.sis.mrep.shared.model.Territory;
import com.sis.mrep.shared.model.Zone;

@RemoteServiceRelativePath("AdminManager")

public interface AdminManager extends RemoteService{


	public List<Zone> getZoneList(String s) ;

	public Zone saveZone(Zone zone) ;

	public Zone deleteZone(Zone zone) ;

	public List<Line> getLineList(String s) ;

	public Line saveLine(Line line) ;

	public Line deleteLine(Line line) ;

	public List<Territory> getTerritoryList(String s) ;

	public Territory saveTerritory(Territory t) ;

	public Territory deleteTerritory(Territory t) ;

	public List<Brick> getBrickList(String s) ;

	public Brick saveBrick(Brick brick) ;

	public Brick deleteBrick(Brick brick) ;

	public List<Product> getProductList(String s) ;

	public Product saveProduct(Product product) ;

	public Product deleteProduct(Product product);
	
	public List<Employee> getEmployeeList() ;

	public Employee saveEmployee(Employee emp) ;

	public Employee saveEmployeeProfile(Employee emp);
	
	public Employee deleteEmployee(Employee emp) ;

	public List<Customer> getCustomerList(String s) ;

	public List<Customer> getCustomerListByBrick(int bid);

	public Customer saveCustomer(Customer customer) ;

	public Customer deleteCustomer(Customer customer);

	public List<Plan> getPlanList() ;

	public Plan savePlan(Plan plan) ;

	public Plan deletePlan(Plan plan) ;

	public List<PlanItem> getPlanItemListForPlanId(int pid);

	public PlanItem savePlanItem(PlanItem pi) ;

	public String savePlanItemList(List<PlanItem> piList);

	public PlanItem deletePlanItem(PlanItem pi);

	public List<PlanItem> getPlanProposalByBrick(int planId, int brickId);

	public Employee getCurrentUser();
	
	public void setCurrentUser(Employee emp);

}
