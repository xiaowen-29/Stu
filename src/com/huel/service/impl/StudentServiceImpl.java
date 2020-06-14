package com.huel.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.huel.dao.StudentDao;
import com.huel.dao.impl.StudentDaoImpl;
import com.huel.domain.PageBean;
import com.huel.domain.Student;
import com.huel.service.StudentService;
/**
 * 这是学生查询的业务实现
 * @author HJC
 *
 */
public class StudentServiceImpl implements StudentService {

	@Override
	public List<Student> findall() throws SQLException {
		
		StudentDao studao = new StudentDaoImpl();
		
		return studao.findall();
	}
	
	@Override
	public Student findById(int id) throws SQLException {
		
		StudentDao studao = new StudentDaoImpl();
		return studao.findById(id);
	}


	@Override
	public void insert(Student student) throws SQLException {
		
		StudentDao studao = new StudentDaoImpl();
		studao.insert(student);
	}

	@Override
	public void delete(int sid) throws SQLException {
		StudentDao studao = new StudentDaoImpl();
		studao.delete(sid);
		
	}

	@Override
	public void update(Student student) throws SQLException {
		StudentDao studao = new StudentDaoImpl();
		studao.update(student);
	}

	@Override
	public List<Student>  serrchStudent(String sname, String sgender)
			throws SQLException {
		StudentDao studao = new StudentDaoImpl();
		
		return studao.serrchStudent(sname, sgender);
	}
	
	/**
	 * 通过给定页查询学生信息
	 */
	@Override
	public PageBean findStudentByPage(int currentPage) throws SQLException {
		
		//生成分页显示的bean类型
		PageBean<Student> pageBean = new PageBean<Student>();
		pageBean.setCurrentPage(currentPage);					//设置当前页
		pageBean.setPageSize(StudentDao.PAGE_SIZE);//设置每一页显示的数量
		List<Student> list = new StudentDaoImpl().findStudentByPage(currentPage);
		pageBean.setList(list); //设置当前页面的学生数据
		StudentDao studentDao = new StudentDaoImpl();
		int count = studentDao.findcount();
		pageBean.setTotalSize(count);			//设置总记录数
		pageBean.setTotalPage(count % StudentDao.PAGE_SIZE ==0 ? count / StudentDao.PAGE_SIZE : ( count / StudentDao.PAGE_SIZE ) + 1); //设置总页数
		return pageBean;
	}


}
