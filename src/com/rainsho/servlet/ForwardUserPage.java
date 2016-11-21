package com.rainsho.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardUserPage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String u = request.getRequestURI().replace("/TWCN/u/", "");
		System.out.println("servlet:" + u);
		request.getRequestDispatcher("/userpage.action?u=" + u).forward(
				request, response);

	}

}
