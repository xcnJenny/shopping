package com.xcn.shopping.service;

import java.util.List;

import org.nxf.frame.dao.DAO;
import org.nxf.frame.service.AbstractService;

import com.xcn.shopping.bean.UserRole;
import com.xcn.shopping.dao.UserRoleDao;

public class UserRoleService extends AbstractService {
	private UserRoleDao urd;
	

	public UserRoleService(DAO dao) {
		super(dao);
		urd = (UserRoleDao)dao;
	}
	public List<UserRole> select(){
		try {
			return urd.select();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	


}
