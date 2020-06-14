package com.huel.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huel.domain.PageBean;
import com.huel.domain.Student;
import com.huel.service.StudentService;
import com.huel.service.impl.StudentServiceImpl;

/**
 * 这是用于分页显示学生信息的Servlet
 * @author HJC
 *
 */
public class StudentListPageServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取要显示的页码数
			int id = Integer.parseInt(request.getParameter("currentPage"));
			
			//2.根据指定的页数，取得响应的信息
			StudentService service = new StudentServiceImpl();
			PageBean<Student> pageBean = service.findStudentByPage(id);
			request.setAttribute("pageBean", pageBean);
			
			//3.携带信息，跳转页面显示
			request.getRequestDispatcher("list_page.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
