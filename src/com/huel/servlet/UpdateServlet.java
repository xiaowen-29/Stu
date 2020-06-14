package com.huel.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huel.domain.Student;
import com.huel.service.StudentService;
import com.huel.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			request.setCharacterEncoding("UTF-8");
			
			//1.获取表单提交的数据
			int sid = Integer.parseInt(request.getParameter("sid"));
			String sname = request.getParameter("sname");
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String birthday = request.getParameter("birthday");
			//String hobby = request.getParameter("hobby");
			String info = request.getParameter("info");
			String [] h  = request.getParameterValues("hobby");
			String hobby = Arrays.toString(h);
			hobby = hobby.substring(1, hobby.length()-1);
			//2.生成student对象
			//2.1 在接收表单数据时候，把日期作为String形式接收。但是在赋值给Bean的时候，Bean接收的类型是Data，这里需要把String类型的日期转为Bean中的类型日期
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			Student student = new Student(sid,sname, gender, phone, hobby, info, date);
			
			//3.更新数据库
			StudentService service = new StudentServiceImpl();
			service.update(student);
			
			//4.跳转页面
			request.getRequestDispatcher("StudentList").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
