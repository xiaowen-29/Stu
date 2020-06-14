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
 * Servlet implementation class AddServlet
 */
public class AddServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
		
		
			try {
				//1.获取表单提交的数据
				String sname = request.getParameter("sname");
				String gender = request.getParameter("gender");
				String phone = request.getParameter("phone");
				String birthday = request.getParameter("birthday");
				//String hobby = request.getParameter("hobby");
				String info = request.getParameter("info");
				String [] h  = request.getParameterValues("hobby");
				String hobby = Arrays.toString(h);
				hobby = hobby.substring(1, hobby.length()-1);
				
				
				//2.添加到数据库
				//2.1 在接收表单数据时候，把日期作为String形式接收。但是在赋值给Bean的时候，Bean接收的类型是Data，这里需要把String类型的日期转为Bean中的类型日期
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
				Student student = new Student(sname, gender, phone, hobby, info, date);
				StudentService service = new StudentServiceImpl();
				service.insert(student);
				
				//3.跳转到显示页面（通过刷新List界面来显示数据库中新的信息来证明已经添加成功）
				//3.1这里重新显示页面的逻辑是 重新访问这个页面，有两种方式：一是直接访问List.jsp。二是通过StudentListServlet间接访问List.jsp
				//   对于方式一，直接重定向到Jsp页面，request内的东西是空的，不会赋值。而对于第二种方式，相当于重新走了一遍查询，显示的流程，则必然可以出结果
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
