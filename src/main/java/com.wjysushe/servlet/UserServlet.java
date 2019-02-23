/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LoginServlet
 * Author:   Administrator
 * Date:     2019/2/21 0021 21:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wjysushe.servlet;

import com.wjysushe.bean.User;
import com.wjysushe.dao.UserDao;
import com.wjysushe.service.UserService;
import com.wjysushe.utils.ReplyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.wjysushe.service.UserService.SESSION_USER;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/21 0021
 * @since 1.0.0
 */
public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action.equals("list")){
			list(req, resp);
		}else if (action.equals("delete")){
			delete(req, resp);
		}else if (action.equals("edit")){
			edit(req, resp);
		}else if (action.equals("add")){
			add(req, resp);
		}else if (action.equals("update")){
			update(req, resp);
		}else if (action.equals("save")){
			save(req, resp);
		}else if (action.equals("resetPassword")){
			resetPassword(req,resp);
		}
	}

	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		User user = (User) request.getSession().getAttribute(SESSION_USER);
		Integer ret = new UserService().resetPassword(String.valueOf(user.getId()), password, newPassword);
		if (ret ==0){
			request.setAttribute("msg", ReplyUtil.alter("修改成功"));
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else if (ret ==1){
			request.setAttribute("msg", ReplyUtil.alter("修改失败"));
			request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", ReplyUtil.alter("密码错误"));
			request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = request.getParameter("role");
		String name = request.getParameter("name");
		List<User> userList = new UserService().list(role, name);
		request.setAttribute("list", userList);
		request.getRequestDispatcher("/page/user/list.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		request.setAttribute("user", new UserService().get(id));
		request.getRequestDispatcher("/page/user/edit.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (new UserService().delete(id)){
			request.setAttribute("msg", ReplyUtil.alter("删除成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("删除失败"));
		}
		request.getRequestDispatcher("/user_action?action=list").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String role = request.getParameter("role");
		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		if (new UserService().update(id, role, sno, name)){
			request.setAttribute("msg", ReplyUtil.alter("修改成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("修改失败"));
		}
		request.getRequestDispatcher("/user_action?action=list&role=&name=").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/page/user/add.jsp").forward(request, response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = request.getParameter("role");
		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		if (new UserService().add(role, sno, name)){
			request.setAttribute("msg", ReplyUtil.alter("添加成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("添加失败"));
		}
		request.getRequestDispatcher("/user_action?action=list&role=&name=").forward(request, response);
	}

}