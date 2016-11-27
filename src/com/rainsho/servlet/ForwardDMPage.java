package com.rainsho.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rainsho.entity.Users;

public class ForwardDMPage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Users user = (Users) request.getSession().getAttribute("LOGIN_USER");
		if (user == null) {
			response.sendRedirect("../login.jsp");
			return;
		}
		request.getRequestDispatcher("/dmfindmap.action").forward(request,
				response);

	}

}
