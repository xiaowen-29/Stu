package com.huel.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huel.service.StudentService;
import com.huel.service.impl.StudentServiceImpl;

/**
 * 用于处理删除学生
 * @author HJC
 *
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		try {
			//1.接收jsp页面传递的id
			int sid = Integer.parseInt(request.getParameter("sid"));
			//2. Servlet调用service，service调用dao，最终实现删除操作
			StudentService service = new StudentServiceImpl();
			service.delete(sid);
			//3.重新更新显示list页面
			request.getRequestDispatcher("StudentList").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
