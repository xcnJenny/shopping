package com.xcn.shopping.service;

import java.util.List;

import org.nxf.frame.service.AbstractService;

import com.xcn.shopping.bean.User;

public class UserService extends AbstractService {

	public UserService(String table_name, String bean_name) {
		super(table_name, bean_name);
		// TODO Auto-generated constructor stub
	}
	public User checkLogin(String username,String password){
		String str = "username='"+username+"' and password='"+password+"'";
		List<?> list = this.select(str);
		User user = null;
		if(list.size()>0){
			user = (User)list.get(0);
		}
		return user;
		
		
	}

}
