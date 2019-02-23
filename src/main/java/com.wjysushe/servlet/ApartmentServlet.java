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

import com.wjysushe.bean.Apartment;
import com.wjysushe.bean.User;
import com.wjysushe.service.ApartmentService;
import com.wjysushe.service.UserService;
import com.wjysushe.utils.ReplyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/21 0021
 * @since 1.0.0
 */
public class ApartmentServlet extends HttpServlet {

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
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		List<Apartment> apartmentList = new ApartmentService().list(name);
		request.setAttribute("list", apartmentList);
		request.getRequestDispatcher("/page/apartment/list.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		List<User> managerList = new UserService().listByRole(2L);
		request.setAttribute("apartment", new ApartmentService().get(id));
		request.setAttribute("managerList", managerList);
		request.getRequestDispatcher("/page/apartment/edit.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (new ApartmentService().delete(id)){
			request.setAttribute("msg", ReplyUtil.alter("删除成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("删除失败"));
		}
		request.getRequestDispatcher("/apartment_action?action=list").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String managerId = request.getParameter("managerId");
		String name = request.getParameter("name");
		if (new ApartmentService().update(id, managerId, name)){
			request.setAttribute("msg", ReplyUtil.alter("修改成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("修改失败"));
		}
		request.getRequestDispatcher("/apartment_action?action=list&role=&name=").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> managerList = new UserService().listByRole(2L);
		request.setAttribute("managerList", managerList);
		request.getRequestDispatcher("/page/apartment/add.jsp").forward(request, response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String managerId = request.getParameter("managerId");
		String name = request.getParameter("name");
		if (new ApartmentService().add(managerId, name)){
			request.setAttribute("msg", ReplyUtil.alter("添加成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("添加失败"));
		}
		request.getRequestDispatcher("/apartment_action?action=list&role=&name=").forward(request, response);
	}

}