package com.sis.mrep.server.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {
	
	public static final int UPDATE_MODE = 1;
	public static final int INSERT_MODE = 0;

	public ResultSet getObjectList(String sql) {
		System.out.println("sql =  "+sql);
		Connection con = ConnectionManager.getConnection();
		ResultSet rs = null;
		try {
			rs = con.prepareStatement(sql)
					.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	
	public int saveObject(int mode , String sql) {
		System.out.println("sql =  "+sql);
		int id = 0;
		Connection con = ConnectionManager.getConnection();
		try {
			if (mode <= 0) {

				con.prepareStatement(sql).execute();
			} else {
				Statement st = con.createStatement();
				id = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public void deleteObject(String sql) {
		System.out.println("sql =  "+sql);
		Connection con = ConnectionManager.getConnection();
		try {
			con.prepareStatement(sql)
					.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	
}
