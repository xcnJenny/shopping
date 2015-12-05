package com.xcn.shopping.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nxf.frame.action.Action;

import com.xcn.shopping.bean.User;
import com.xcn.shopping.bean.UserRole;
import com.xcn.shopping.dao.UserRoleDao;
import com.xcn.shopping.service.RoleService;
import com.xcn.shopping.service.UserRoleService;
import com.xcn.shopping.service.UserService;

public class UserAction extends Action {
	private static final long serialVersionUID =1L;
	private RoleService roleService;
	private UserService userService = new UserService("tbl_user","com.xcn.shopping.bean.UserRole");
	
	public UserAction() {
		setDir("jsp/user");
		setService(new UserService("tbl_user","com.xcn.shopping.bean.User"));
		roleService = new RoleService("tble_user_role","com.xcn.shopping.bean.Role");			
	}
	
@Override
public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setRole(Integer.parseInt(request.getParameter("role")));
		service.save(user);
		super.save(request, response);
}

@Override
public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UserRoleDao dao = new UserRoleDao();
	UserRoleService urs = new UserRoleService(dao);
	List<UserRole> list = urs.select();
	request.setAttribute("urList", list);
	request.getRequestDispatcher("jsp/user/list.jsp").forward(request,response);
}
			

public void reg(HttpServletRequest request ,HttpServletResponse response)
throws ServletException,IOException{
	List<?> list = roleService.getAll();
	request.setAttribute("roleList",list);
	request.getRequestDispatcher("jsp/user/register.jsp").forward(request, response);
}

public void login(HttpServletRequest request,HttpServletResponse response)
throws ServletException,IOException{
	request.getRequestDispatcher("jsp/user/login.jsp").forward(request, response);
	}
public void checklogin(HttpServletRequest request,HttpServletResponse response)
throws ServletException,IOException{
	HttpSession session = request.getSession();
	String verifycode = request.getParameter("verify");
	String sessionVC = (String)session.getAttribute("VerifyCode");
	if(!verifycode.equals(sessionVC)){
		session.setAttribute("errMsg","验证码错误");
		response.sendRedirect("user.do?a=login");
		return;
	}
	
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	User user = userService.checkLogin(username, password);
	if(user!=null){
		session.setAttribute("user",user);
		response.sendRedirect("goods.do");
	}else{
		session.setAttribute("errMsg", "用户名或者密码错误");
		response.sendRedirect("user.do?a=login");
	}
			
}
	
public void exit(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
	request.getSession().removeAttribute("user");
	response.sendRedirect("goods.do");
}
	

}
