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
import com.wjysushe.bean.Dormitory;
import com.wjysushe.bean.User;
import com.wjysushe.service.ApartmentService;
import com.wjysushe.service.DormitoryService;
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
public class DormitoryServlet extends HttpServlet {

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
		String apartmentId = request.getParameter("apartmentId");
		List<Apartment> apartmentList = new ApartmentService().list(null);
		List<Dormitory> dormitoryList = new DormitoryService().list(apartmentId, name);
		request.setAttribute("list", dormitoryList);
		request.setAttribute("apartmentList", apartmentList);
		request.getRequestDispatcher("/page/dormitory/list.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		List<Apartment> apartmentList = new ApartmentService().list(null);
		List<User> studentList = new UserService().listByRole(3L);
		request.setAttribute("studentList", studentList);
		request.setAttribute("dormitory", new DormitoryService().get(id));
		request.setAttribute("apartmentList", apartmentList);
		request.getRequestDispatcher("/page/dormitory/edit.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (new DormitoryService().delete(id)){
			request.setAttribute("msg", ReplyUtil.alter("删除成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("删除失败"));
		}
		request.getRequestDispatcher("/dormitory_action?action=list").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String apartmentId = request.getParameter("apartmentId");
		String name = request.getParameter("name");
		String studentIds = request.getParameter("studentIds");
		if (new DormitoryService().update(id, apartmentId, name, studentIds)){
			request.setAttribute("msg", ReplyUtil.alter("修改成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("修改失败"));
		}
		request.getRequestDispatcher("/dormitory_action?action=list&apartmentId=&name=").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> studentList = new UserService().listByRole(3L);
		List<Apartment> apartmentList = new ApartmentService().list(null);
		request.setAttribute("studentList", studentList);
		request.setAttribute("apartmentList", apartmentList);
		request.getRequestDispatcher("/page/dormitory/add.jsp").forward(request, response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String apartmentId = request.getParameter("apartmentId");
		String name = request.getParameter("name");
		String studentIds = request.getParameter("studentIds");
		if (new DormitoryService().add(apartmentId, name, studentIds)){
			request.setAttribute("msg", ReplyUtil.alter("添加成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("添加失败"));
		}
		request.getRequestDispatcher("/dormitory_action?action=list&apartmentId=&name=").forward(request, response);
	}

}