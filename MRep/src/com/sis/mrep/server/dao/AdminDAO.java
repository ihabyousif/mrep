package com.sis.mrep.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sis.mrep.client.content.util.StaticMaps;
import com.sis.mrep.server.common.BaseDAO;
import com.sis.mrep.shared.model.Brick;
import com.sis.mrep.shared.model.Customer;
import com.sis.mrep.shared.model.Employee;
import com.sis.mrep.shared.model.Line;
import com.sis.mrep.shared.model.Plan;
import com.sis.mrep.shared.model.PlanItem;
import com.sis.mrep.shared.model.Product;
import com.sis.mrep.shared.model.Territory;
import com.sis.mrep.shared.model.Zone;

public class AdminDAO extends BaseDAO {

	public List<Zone> getZoneList() {
		List<Zone> zoneList = new ArrayList<Zone>();

		ResultSet rs = getObjectList("select * from zone");

		try {
			while (rs.next()) {
				Zone zone = new Zone(rs.getInt("z_id"), rs.getString("z_name"));
				zoneList.add(zone);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return zoneList;
	}

	public Zone saveZone(Zone zone) {

		String sql = "";
		if (zone.getId() > 0) {
			sql = "update zone set z_name = '" + zone.getName()
					+ "' where z_id = " + zone.getId();

		} else {
			sql = "insert into zone (z_name) values ('" + zone.getName() + "')";

		}
		int id = saveObject(zone.getId(), sql);
		zone.setId(id);
		return zone;
	}

	public Zone deleteZone(Zone zone) {

		deleteObject("delete from zone where z_id " + zone.getId());

		return zone;
	}

	public List<Line> getLineList() {
		List<Line> lineList = new ArrayList<Line>();
		ResultSet rs = getObjectList("select * from line");
		try {
			while (rs.next()) {
				Line line = new Line(rs.getInt("l_id"), rs.getString("l_name"));
				lineList.add(line);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lineList;
	}

	public Line saveLine(Line line) {
		String sql = "";
		if (line.getId() > 0) {
			sql = "update line set l_name = '" + line.getName()
					+ "' where l_id = " + line.getId();
		} else {
			sql = "insert into line (l_name) values ('" + line.getName() + "')";
		}
		int id = saveObject(line.getId(), sql);
		line.setId(id);

		return line;
	}

	public Line deleteLine(Line line) {
		deleteObject("delete from line where l_id " + line.getId());

		return line;
	}

	public List<Territory> getTerritoryList() {
		List<Territory> territoryList = new ArrayList<Territory>();

		try {
			ResultSet rs = getObjectList("select * from territory");
			while (rs.next()) {
				Territory territory = new Territory(rs.getInt("tor_id"),
						rs.getString("tor_name"), rs.getInt("zone_z_id"));
				territoryList.add(territory);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return territoryList;
	}

	public Territory saveTerritory(Territory t) {
		String sql = "";
		if (t.getId() > 0) {
			sql = "update territory set tor_name = '" + t.getName()
					+ "' , zone_z_id = " + t.getZone() + " where tor_id = "
					+ t.getId();
		} else {
			sql = "insert into territory (tor_name , zone_z_id) values ('"
					+ t.getName() + "' , " + t.getZone() + ")";

		}

		int id = saveObject(t.getId(), sql);
		t.setId(id);
		return t;
	}

	public Territory deleteTerritory(Territory t) {

		deleteObject("delete from territory where tor_id " + t.getId());
		return t;
	}

	public List<Brick> getBrickList() {
		List<Brick> brickList = new ArrayList<Brick>();
		try {
			ResultSet rs = getObjectList("select * from brick");
			while (rs.next()) {
				Brick brick = new Brick(rs.getInt("b_id"),
						rs.getString("b_name"), rs.getInt("territory_tor_id"));
				brickList.add(brick);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return brickList;
	}

	public Brick saveBrick(Brick brick) {
		String sql = "";
		if (brick.getId() > 0) {
			sql = "update brick set b_name = '" + brick.getName()
					+ "' , territory_tor_id =" + brick.getTerritory()
					+ " where b_id = " + brick.getId();
		} else {
			sql = "insert into brick (b_name , territory_tor_id) values ('"
					+ brick.getName() + "' , " + brick.getTerritory() + ")";
		}
		int id = saveObject(brick.getId(), sql);
		brick.setId(id);
		return brick;
	}

	public Brick deleteBrick(Brick brick) {
		deleteObject("delete from Brick where b_id " + brick.getId());
		return brick;
	}

	public List<Product> getProductList() {
		List<Product> productList = new ArrayList<Product>();
		try {
			ResultSet rs = getObjectList("select * from product");
			while (rs.next()) {
				Product product = new Product(rs.getInt("p_id"),
						rs.getString("p_name"), rs.getInt("l_id"));
				productList.add(product);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public Product saveProduct(Product product) {
		String sql = "";
		if (product.getId() > 0) {
			sql = "update product set p_name = '" + product.getName()
					+ "' , l_id =" + product.getLine() + " where b_id = "
					+ product.getId();
		} else {
			sql = "insert into product (p_name , l_id) values ('"
					+ product.getName() + "' , " + product.getLine() + ")";
		}
		int id = saveObject(product.getId(), sql);
		product.setId(id);
		return product;
	}

	public Product deleteProduct(Product product) {
		deleteObject("delete from Product where p_id " + product.getId());
		return product;
	}

	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = new ArrayList<Employee>();

		try {
			ResultSet rs = getObjectList("select * from employee");
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("emp_id"),
						rs.getString("emp_name"), rs.getString("emp_phone"),
						rs.getString("emp_address"),
						rs.getString("emp_username"), rs.getString("emp_pass"),
						rs.getInt("emp_manager"), rs.getInt("emp_type"),
						rs.getInt("emp_brick"), rs.getInt("l_id"));
				employeeList.add(employee);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeList;
	}

	public Employee saveEmployee(Employee emp) {
		String sql = "";
		if (emp.getId() > 0) {
			sql = "UPDATE `employee` SET `emp_name` = '" + emp.getName()
					+ "',`emp_username` = '" + emp.getUserName()
					+ "',`emp_pass` = '" + emp.getUserPass()
					+ "', `emp_manager` = " + emp.getManager()
					+ ",`emp_phone` ='" + emp.getPhone()
					+ "',`emp_address` = '" + emp.getAddress()
					+ "', `emp_brick` = " + emp.getBrick() + "', `emp_type` = "
					+ emp.getType() + "', `l_id` = " + emp.getLine()
					+ " where emp_id =" + emp.getId();

		} else {
			sql = "INSERT INTO `employee` ( `emp_name`, `emp_username`, `emp_pass`, `emp_manager`, `emp_phone`, `emp_address`, `emp_brick`, `emp_type`, `l_id`) "
					+ "VALUES ('"
					+ emp.getName()
					+ "','"
					+ emp.getUserName()
					+ "','"
					+ emp.getUserPass()
					+ "',"
					+ emp.getManager()
					+ ",'"
					+ emp.getPhone()
					+ "','"
					+ emp.getAddress()
					+ "',"
					+ emp.getBrick()
					+ ","
					+ emp.getType()
					+ ","
					+ emp.getLine() + ")";

		}
		int id = saveObject(emp.getId(), sql);
		emp.setId(id);

		return emp;
	}

	public Employee saveEmployeeProfile(Employee emp) {
		String sql = "UPDATE `employee` SET `emp_name` = '" + emp.getName()
					+ "',`emp_username` = '" + emp.getUserName()
					+ "',`emp_pass` = '" + emp.getUserPass()
					+ "',`emp_phone` ='" + emp.getPhone()
					+ "',`emp_address` = '" + emp.getAddress()
					+ "', `emp_brick` = " + emp.getBrick() + ", `l_id` = " + emp.getLine()
					+ " where emp_id =" + emp.getId();
		    saveObject(emp.getId(), sql);
			return emp;
	}

	
	public Employee deleteEmployee(Employee emp) {
		deleteObject("delete from employee where emp_id " + emp.getId());
		return emp;
	}

	public List<Customer> getCustomerList() {
		List<Customer> customerList = new ArrayList<Customer>();

		try {
			ResultSet rs = getObjectList("select * from customer");
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("cus_id"),
						rs.getString("cus_name"), rs.getString("cus_phone"),
						rs.getString("cus_address1"), rs.getInt("cus_tier"),
						rs.getInt("cus_speciality"), rs.getInt("cus_brick"),
						rs.getInt("cus_type"));
				customerList.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customerList;
	}

	public List<Customer> getCustomerListByBrick(int bid) {
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			ResultSet rs = getObjectList("select * from customer where cus_brick = "
					+ bid);
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("cus_id"),
						rs.getString("cus_name"), rs.getString("cus_phone"),
						rs.getString("cus_address1"), rs.getInt("cus_tier"),
						rs.getInt("cus_speciality"), rs.getInt("cus_brick"),
						rs.getInt("cus_type"));
				customerList.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customerList;
	}

	public Customer saveCustomer(Customer customer) {
		String sql = "";
		if (customer.getId() > 0) {
			sql = "UPDATE `customer` SET `cus_name` = " + customer.getName()
					+ ", `cus_phone` = '" + customer.getPhone()
					+ "', `cus_address1` = '" + customer.getAddress()
					+ "', `cus_brick` = " + customer.getBrick()
					+ ", `cus_tier` = " + customer.getTier()
					+ ", `cus_speciality` = " + customer.getSpeciality()
					+ ", `cus_type` = " + customer.getType()
					+ " where cus_id = " + customer.getId();

		} else {
			sql = "INSERT INTO `customer` ( `cus_name`, `cus_phone`, `cus_address1`, `cus_brick`, `cus_tier` , cus_speciality , cus_type)"
					+ "VALUES ( '"
					+ customer.getName()
					+ "','"
					+ customer.getPhone()
					+ "','"
					+ customer.getAddress()
					+ "',"
					+ customer.getBrick()
					+ ","
					+ customer.getTier()
					+ ","
					+ customer.getSpeciality()
					+ ","
					+ customer.getType()
					+ ")";

		}
		int id = saveObject(customer.getId(), sql);
		customer.setId(id);
		return customer;
	}

	public Customer deleteCustomer(Customer customer) {
		deleteObject("delete from customer where cus_id " + customer.getId());
		return customer;
	}

	public List<Plan> getPlanList() {
		List<Plan> planList = new ArrayList<Plan>();
		try {
			ResultSet rs = getObjectList("select * from plan");
			while (rs.next()) {

				Plan p = new Plan(rs.getInt("plan_id"),
						rs.getString("plan_name"), rs.getDate("plan_date"),
						rs.getInt("plan_brick"), rs.getInt("rep_id"),
						rs.getInt("admin_id"));
				planList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return planList;
	}

	public Plan savePlan(Plan plan) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your
		String dte = formatter.format(plan.getPlanDate());
		String sql = "";
		if (plan.getId() > 0) {
			sql = "UPDATE `plan` SET `plan_name` = '" + plan.getName()
					+ "', `plan_date` = '" + dte + "', `admin_id` = "
					+ plan.getAdminId() + " , `rep_id` = " + plan.getRepId()
					+ " `plan_brick` = " + plan.getBrickId()
					+ " WHERE plan_id = " + plan.getId();

		} else {
			sql = "INSERT INTO `plan` (`plan_name`,`plan_date`,`admin_id`,`rep_id`,`plan_brick`)VALUES"
					+ "('"
					+ plan.getName()
					+ "','"
					+ dte
					+ "',"
					+ plan.getRepId()
					+ ","
					+ plan.getRepId()
					+ ","
					+ plan.getBrickId() + ")";

		}
		int id = saveObject(plan.getId(), sql);
		plan.setId(id);
		return plan;
	}

	public Plan deletePlan(Plan plan) {
		deleteObject("delete from plan where plan_id " + plan.getId());
		return plan;
	}

	public List<PlanItem> getPlanItemListForPlanId(int pid) {
		List<PlanItem> piList = new ArrayList<PlanItem>();
		try {
			ResultSet rs = getObjectList("select pi.* , c.cus_name , c.cus_brick , c.cus_speciality , c.cus_tier , c.cus_type from plan_items pi, customer c where c.cus_id = pi.cus_id and plan_id="
					+ pid);
			while (rs.next()) {
				PlanItem p = new PlanItem(rs.getInt("pi_id"),
						rs.getInt("plan_id"), rs.getInt("pi_calls"),
						rs.getInt("cus_id"), rs.getString("c.cus_name"),
						rs.getInt("cus_tier"), rs.getInt("cus_speciality"),
						rs.getInt("cus_brick"));
				piList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return piList;
	}

	public PlanItem savePlanItem(PlanItem pi) {
		String sql = "";
		if (pi.getId() > 0) {
			sql = "UPDATE `plan_items` SET `plan_id` = " + pi.getPlanId()
					+ ",`pi_calls` = " + pi.getCalls() + ",`cus_id` = "
					+ pi.getCusId() + "WHERE pi_id =" + pi.getId();

		} else {
			sql = "INSERT INTO `plan_items`(`plan_id`,`pi_calls`,`cus_id`) VALUES"
					+ "("
					+ pi.getPlanId()
					+ ","
					+ pi.getCalls()
					+ ","
					+ pi.getCusId() + ")";

		}
		int id = saveObject(pi.getId(), sql);
		pi.setId(id);
		return pi;
	}

	public String savePlanItemList(List<PlanItem> piList) {
		for (PlanItem pi : piList) {
			savePlanItem(pi);
		}
		return "";
	}

	public PlanItem deletePlanItem(PlanItem pi) {
		deleteObject("delete from plan_items where pi_id " + pi.getId());
		return pi;
	}

	public List<PlanItem> getPlanProposalByBrick(int planId, int brickId) {
		List<Customer> cusList = getCustomerListByBrick(brickId);
		List<PlanItem> piList = new ArrayList<PlanItem>();
		for (Customer cus : cusList) {
			PlanItem pi = new PlanItem(0, planId,
					StaticMaps.callsForTier.get(cus.getTier()), cus.getId(),
					cus.getName(), cus.getTier(), cus.getSpeciality(),
					cus.getBrick());
			piList.add(pi);
		}
		return piList;
	}

}
