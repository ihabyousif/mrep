package com.sis.mrep.client.proxy;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sis.mrep.shared.model.Brick;
import com.sis.mrep.shared.model.Customer;
import com.sis.mrep.shared.model.Employee;
import com.sis.mrep.shared.model.Line;
import com.sis.mrep.shared.model.Plan;
import com.sis.mrep.shared.model.PlanItem;
import com.sis.mrep.shared.model.Product;
import com.sis.mrep.shared.model.Territory;
import com.sis.mrep.shared.model.Zone;

public interface AdminManagerAsync {

	void getZoneList(String s, AsyncCallback<List<Zone>> callback);

	void saveZone(Zone zone, AsyncCallback<Zone> callback);

	void deleteBrick(Brick brick, AsyncCallback<Brick> callback);

	void deleteCustomer(Customer customer, AsyncCallback<Customer> callback);

	void deleteEmployee(Employee emp, AsyncCallback<Employee> callback);

	void deleteLine(Line line, AsyncCallback<Line> callback);

	void deletePlan(Plan plan, AsyncCallback<Plan> callback);

	void deletePlanItem(PlanItem pi, AsyncCallback<PlanItem> callback);

	void deleteProduct(Product product, AsyncCallback<Product> callback);

	void deleteTerritory(Territory t, AsyncCallback<Territory> callback);

	void deleteZone(Zone zone, AsyncCallback<Zone> callback);

	void getBrickList(String s, AsyncCallback<List<Brick>> callback);

	void getCurrentUser(AsyncCallback<Employee> callback);

	void getCustomerList(String s, AsyncCallback<List<Customer>> callback);

	void getCustomerListByBrick(int bid, AsyncCallback<List<Customer>> callback);

	void getEmployeeList(AsyncCallback<List<Employee>> callback);

	void getLineList(String s, AsyncCallback<List<Line>> callback);

	void getPlanItemListForPlanId(int pid,
			AsyncCallback<List<PlanItem>> callback);

	void getPlanList(AsyncCallback<List<Plan>> callback);

	void getPlanProposalByBrick(int planId, int brickId,
			AsyncCallback<List<PlanItem>> callback);

	void getProductList(String s, AsyncCallback<List<Product>> callback);

	void getTerritoryList(String s, AsyncCallback<List<Territory>> callback);

	void saveBrick(Brick brick, AsyncCallback<Brick> callback);

	void saveCustomer(Customer customer, AsyncCallback<Customer> callback);

	void saveEmployee(Employee emp, AsyncCallback<Employee> callback);

	void saveLine(Line line, AsyncCallback<Line> callback);

	void savePlan(Plan plan, AsyncCallback<Plan> callback);

	void savePlanItem(PlanItem pi, AsyncCallback<PlanItem> callback);

	void savePlanItemList(List<PlanItem> piList, AsyncCallback<String> callback);

	void saveProduct(Product product, AsyncCallback<Product> callback);

	void saveTerritory(Territory t, AsyncCallback<Territory> callback);

	void saveEmployeeProfile(Employee emp, AsyncCallback<Employee> callback);

	void setCurrentUser(Employee emp, AsyncCallback<Void> callback);

}
