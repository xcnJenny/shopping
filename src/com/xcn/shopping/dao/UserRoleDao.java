package com.xcn.shopping.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.nxf.frame.dao.DAO;

import com.xcn.shopping.bean.Role;
import com.xcn.shopping.bean.UserRole;

public class UserRoleDao extends DAO {
	public List<UserRole> select () throws Exception{
	String sql = "select tbl_user.id,tbl_user.username,tbl_user.password,tbl_user_role.id,tbl_user_role.rolename from"
			+ "tbl_user inner join tbl_user_role on tbl_user.role=tbl_user_role.id";
	List<UserRole> list = new ArrayList<UserRole>();
	Connection conn = this.getConn();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	while(rs.next()){
		UserRole user = new UserRole();
		user.setId(rs.getInt(1));
		user.setUsername(rs.getString(2));
		user.setPassword(rs.getString(3));
		
		Role role = new Role();
		role.setId(rs.getInt(4));
		role.setRolename(rs.getString(5));
		user.setRole(role);
		list.add(user);
		}
	return list;
	}

}
