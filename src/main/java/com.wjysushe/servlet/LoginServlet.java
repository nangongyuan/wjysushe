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
import com.wjysushe.service.DormitoryService;
import com.wjysushe.service.UserService;
import com.wjysushe.utils.ReplyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wjysushe.service.UserService.SESSION_USER;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/21 0021
 * @since 1.0.0
 */
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sno = req.getParameter("sno");
		String password = req.getParameter("password");

		User user = new UserService().login(sno, password);

		if (user != null){
			if (user.getRole()==3){
				user.setDormitoryId(new DormitoryService().getDormitoryIdByUserId(user.getId()));
			}
			req.getSession().setAttribute(SESSION_USER, user);
			resp.sendRedirect("/index.jsp");
			return;
		}

		req.setAttribute("msg", ReplyUtil.alter("帐号或密码错误"));
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}