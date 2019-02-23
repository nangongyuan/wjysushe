/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LoginFilter
 * Author:   Administrator
 * Date:     2019/2/23 0023 14:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wjysushe.filter;

import com.wjysushe.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wjysushe.service.UserService.SESSION_USER;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/23 0023
 * @since 1.0.0
 */
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		User user = (User) httpServletRequest.getSession().getAttribute(SESSION_USER);
		if (user==null && !httpServletRequest.getRequestURI().equals("/login.jsp") && httpServletRequest.getRequestURI().indexOf(".css")<0 &&
				!httpServletRequest.getRequestURI().equals("/login")){
			httpServletResponse.sendRedirect("/login.jsp");
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}