package com.huel.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huel.domain.Student;
import com.huel.service.StudentService;
import com.huel.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class SearchStudentServlet
 */
public class SearchStudentServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			//1.接收模糊查询给的筛选条件
			String sname = request.getParameter("sname");
			String sgender = request.getParameter("sgender");
			
			
			//2.通过Service查询
			StudentService service = new StudentServiceImpl();
			List<Student> list = service.serrchStudent(sname, sgender);
			request.setAttribute("list", list);
			//3.跳转界面
			request.getRequestDispatcher("list.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
