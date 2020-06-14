package com.huel.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.tools.jar.resources.jar;

import com.huel.domain.Student;
import com.huel.service.StudentService;
import com.huel.service.impl.StudentServiceImpl;

/**
 *  负责查询学生的信息，并且将数据返还给客户端
 */
public class StudentListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		try {
			//1.查询出来所有的学生
			StudentService studentServices = new StudentServiceImpl(); 
			List<Student> list = studentServices.findall();
			
			//2.将查询到的数据存储到requset的作用域中
			request.setAttribute("list", list);
			
			//3.跳转到显示的页面
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
