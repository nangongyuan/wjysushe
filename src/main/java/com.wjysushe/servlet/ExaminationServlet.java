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

import com.wjysushe.bean.*;
import com.wjysushe.service.ApartmentService;
import com.wjysushe.service.DormitoryService;
import com.wjysushe.service.ExaminationService;
import com.wjysushe.service.RepairsService;
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
public class ExaminationServlet extends HttpServlet {

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
		String apartmentId = request.getParameter("apartmentId");
		List<Apartment> apartmentList = new ApartmentService().list(null);
		request.setAttribute("apartmentList", apartmentList);

		User user = (User) request.getSession().getAttribute(SESSION_USER);

		List<Examination> examinationList = new ExaminationService().list(apartmentId, user.getDormitoryId());
		request.setAttribute("list", examinationList);
		request.getRequestDispatcher("/page/examination/list.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		request.setAttribute("examination", new ExaminationService().get(id));

		List<Dormitory> dormitoryList = new DormitoryService().list(null, null);
		request.setAttribute("dormitoryList", dormitoryList);

		request.getRequestDispatcher("/page/examination/edit.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (new ExaminationService().delete(id)){
			request.setAttribute("msg", ReplyUtil.alter("删除成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("删除失败"));
		}
		request.getRequestDispatcher("/examination_action?action=list").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String examinationDate = request.getParameter("examinationDate");
		String dormitoryId = request.getParameter("dormitoryId");
		String peopleNumber = request.getParameter("peopleNumber");
		String realNumber = request.getParameter("realNumber");
		String studentNames = request.getParameter("studentNames");
		if (new ExaminationService().update(id, examinationDate, dormitoryId, peopleNumber, realNumber, studentNames)){
			request.setAttribute("msg", ReplyUtil.alter("修改成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("修改失败"));
		}
		request.getRequestDispatcher("/examination_action?action=list&dormitoryName=&apartmentId=").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Dormitory> dormitoryList = new DormitoryService().list(null, null);
		request.setAttribute("dormitoryList", dormitoryList);
		request.getRequestDispatcher("/page/examination/add.jsp").forward(request, response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String examinationDate = request.getParameter("examinationDate");
		String dormitoryId = request.getParameter("dormitoryId");
		String peopleNumber = request.getParameter("peopleNumber");
		String realNumber = request.getParameter("realNumber");
		String studentNames = request.getParameter("studentNames");
		if (new ExaminationService().add(examinationDate, dormitoryId, peopleNumber, realNumber, studentNames)){
			request.setAttribute("msg", ReplyUtil.alter("添加成功"));
		}else{
			request.setAttribute("msg", ReplyUtil.alter("添加失败"));
		}
		request.getRequestDispatcher("/examination_action?action=list&dormitoryName=&apartmentId=").forward(request, response);
	}

}