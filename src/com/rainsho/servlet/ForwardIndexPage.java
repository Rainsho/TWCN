package com.rainsho.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rainsho.entity.Users;

public class ForwardIndexPage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Users user = (Users) request.getSession().getAttribute("LOGIN_USER");
		if (user == null) {
			// 验证cookie
			for (Cookie x : request.getCookies()) {
				if (x.getName().equals("TWCN_USER")) {
					String CODE = x.getValue();
					@SuppressWarnings("unchecked")
					Map<String, Integer> REMEMBERED = (Map<String, Integer>) request
							.getServletContext().getAttribute("REMEMBERED");
					if (REMEMBERED != null && REMEMBERED.containsKey(CODE)) {
						int uid = REMEMBERED.get(CODE);
						user = new Users();
						user.setUid(uid);
						request.getSession().setAttribute("LOGIN_USER", user);
					}
					break;
				}
			}
		}
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		request.getRequestDispatcher("/userindex.action").forward(request,
				response);

	}

}
