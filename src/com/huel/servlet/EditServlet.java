package com.huel.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huel.domain.Student;
import com.huel.service.StudentService;
import com.huel.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class EditServlet
 */
public class EditServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.接收传递过来的id
			int sid = Integer.parseInt(request.getParameter("sid"));
			
			//2.通过id查询到学生的信息
			StudentService service = new StudentServiceImpl();
			Student stu = service.findById(sid);
			
			//3.将查询到的数据返还到jsp页面
			//3.1 将数据存储到requset中
			request.setAttribute("stu", stu);
			//3.2 跳转到edit.jsp页面
			request.getRequestDispatcher("edit.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
