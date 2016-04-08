package com.sis.mrep.server.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sis.mrep.client.proxy.AdminManager;
import com.sis.mrep.server.dao.AdminDAO;
import com.sis.mrep.shared.model.Brick;
import com.sis.mrep.shared.model.Customer;
import com.sis.mrep.shared.model.Employee;
import com.sis.mrep.shared.model.Line;
import com.sis.mrep.shared.model.Plan;
import com.sis.mrep.shared.model.PlanItem;
import com.sis.mrep.shared.model.Product;
import com.sis.mrep.shared.model.Territory;
import com.sis.mrep.shared.model.Zone;

public class AdminManagerImpl extends RemoteServiceServlet implements
AdminManager {

	public List<Zone> getZoneList(String s) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getZoneList();
	}

	public Zone saveZone(Zone zone) {
		AdminDAO adminDao = new AdminDAO();
		adminDao.saveZone(zone);
		return zone;
	}

	public Zone deleteZone(Zone zone) {
		AdminDAO adminDao = new AdminDAO();
		adminDao.deleteZone(zone);
		return zone;
	}

	public List<Line> getLineList(String s) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getLineList();
	}

	public Line saveLine(Line line) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.saveLine(line);
	}

	public Line deleteLine(Line line) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.deleteLine(line);
	}

	public List<Territory> getTerritoryList(String s) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getTerritoryList();
	}

	public Territory saveTerritory(Territory t) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.saveTerritory(t);
	}

	public Territory deleteTerritory(Territory t) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.deleteTerritory(t);
	}

	public List<Brick> getBrickList(String s) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getBrickList();
	}

	public Brick saveBrick(Brick brick) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.saveBrick(brick);
	}

	public Brick deleteBrick(Brick brick) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.deleteBrick(brick);
	}

	public List<Product> getProductList(String s) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getProductList();
	}

	public Product saveProduct(Product product) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.saveProduct(product);
	}

	public Product deleteProduct(Product product) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.deleteProduct(product);
	}

	public List<Employee> getEmployeeList() {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getEmployeeList();
	}

	public Employee saveEmployee(Employee emp) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.saveEmployee(emp);
	}
	
	public Employee saveEmployeeProfile(Employee emp) {
		AdminDAO adminDao = new AdminDAO();
		adminDao.saveEmployeeProfile(emp);
		setCurrentUser(emp);
		return emp;
	}

	public Employee deleteEmployee(Employee emp) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.deleteEmployee(emp);
	}

	public List<Customer> getCustomerList(String s) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getCustomerList();
	}

	public List<Customer> getCustomerListByBrick(int bid) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getCustomerListByBrick(bid);
	}

	public Customer saveCustomer(Customer customer) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.saveCustomer(customer);
	}

	public Customer deleteCustomer(Customer customer) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.deleteCustomer(customer);
	}

	public List<Plan> getPlanList() {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getPlanList();
	}

	public Plan savePlan(Plan plan) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.savePlan(plan);
	}

	public Plan deletePlan(Plan plan) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.deletePlan(plan);
	}

	public List<PlanItem> getPlanItemListForPlanId(int pid) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getPlanItemListForPlanId(pid);
	}

	public PlanItem savePlanItem(PlanItem pi) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.savePlanItem(pi);
	}

	public String savePlanItemList(List<PlanItem> piList) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.savePlanItemList(piList);
	}

	public PlanItem deletePlanItem(PlanItem pi) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.savePlanItem(pi);
	}

	public List<PlanItem> getPlanProposalByBrick(int planId, int brickId) {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getPlanProposalByBrick(planId, brickId);
	}

	public Employee getCurrentUser() {
		HttpServletRequest request = this.getThreadLocalRequest();
		Employee employee = (Employee) request.getSession(true).getAttribute(
				"user");
		return employee;
	}
	
	public void setCurrentUser(Employee emp){
		HttpServletRequest request = this.getThreadLocalRequest();
		request.getSession(true).setAttribute("user", emp);
	}

}
