package com.rest.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutoDAO {
	
	//public static List<Automotive> auto = new ArrayList<>();
	private static AutoDAO instance = new AutoDAO();
	
	Connection con = null;
	
	private AutoDAO() {

	}
	
	static public AutoDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		String url = "jdbc:postgresql://localhost:5432/training";
		String uname = "postgres";
		String passwd = "postgres";
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, uname, passwd);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public List<Automotive> getCars() {
		List<Automotive> cars = new ArrayList<>();
		try (Connection con = getConnection()){
			if(con != null) {
				Statement ps = con.createStatement();
				ResultSet rs = ps.executeQuery("Select * from automotive");
				while(rs.next()) {
					Automotive obj = new Automotive();
					obj.setEngine(rs.getFloat("engine"));
					obj.setMake(rs.getString("make"));
					obj.setModel(rs.getString("model"));
					obj.setId(rs.getInt("car_id"));
					cars.add(obj);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cars;
	}
	
	public Automotive getCar(String name) {
		Automotive obj = new Automotive();
		try (Connection con = getConnection()){
			if(con != null) {
				PreparedStatement ps = con.prepareStatement("Select * from automotive where model = ?");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					obj.setEngine(rs.getFloat("engine"));
					obj.setMake(rs.getString("make"));
					obj.setModel(rs.getString("make"));
					obj.setId(rs.getInt("car_id"));
					System.out.println("=>"+rs.getFloat("engine")+"   "+rs.getString("make")+"   "+rs.getString("make"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public boolean addInventory(Automotive car) {
		try(Connection con = getConnection()) {
			String sqlQuery = "insert into automotive(make, model,engine) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, car.getMake());
			ps.setString(2, car.getModel());
			ps.setFloat(3, car.getEngine());
			int count= ps.executeUpdate();
			if(count > 0) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateInventory(Automotive car) {
		try(Connection con = getConnection()){
			String sqlQuery = "update automotive set make = ?, model=?, engine = ? where car_id = ?";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, car.getMake());
			ps.setString(2, car.getModel());
			ps.setFloat(3, car.getEngine());
			ps.setInt(4, car.getId());
			int count = ps.executeUpdate();
			System.out.println(count + " rows updated");
			if(count > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteInventory(Automotive car) {
		try(Connection con = getConnection()) {
			String sqlQuery = "Delete from automotive where car_id = ?";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, car.getId());
			int count = ps.executeUpdate();
			System.out.println(count + " rows deleted");
			if(count > 0) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
