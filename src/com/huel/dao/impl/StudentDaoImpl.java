package com.huel.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.huel.dao.StudentDao;
import com.huel.domain.Student;
import com.huel.utils.JDBCUtil02;
import com.huel.utils.TextUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xml.internal.utils.ListingErrorHandler;
/**
 * 这是StudentDao的实现，针对前面做出的规范，给出具体的实现
 * @author HJC
 *
 */
public class StudentDaoImpl implements StudentDao {
	
	/**
	 * 查询所有学生
	 * @throws SQLException 
	 */
	@Override
	public List<Student> findall() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtil02.getDataSource());
		
//		最原始写法
//		String sql = "select * from stu";
//		List<Student> list = queryRunner.query("select * from stu", new BeanListHandler<Student>(Student.class));
//		return list;	
		
//		简化写法
		return queryRunner.query("select * from stu", new BeanListHandler<Student>(Student.class));
	}
	
	/**
	 * 添加学生
	 */
	@Override
	public void insert(Student student) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		
		runner.update("insert into stu values(null , ?,?,?,?,?,?)" ,
				student.getSname(),
				student.getGender(),
				student.getPhone(),
				student.getBirthday(),
				student.getHobby(),
				student.getInfo()
				);
	}

	/**
	 * 根据学号删除学生
	 */
	@Override
	public void delete(int sid) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
		runner.update("delete from stu where sid=?" ,sid);
	}

	/**
	 * 根据学号查询学生
	 */
	@Override
	public Student findById(int id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtil02.getDataSource());
		return queryRunner.query("select * from stu where sid=?", new BeanHandler<Student>(Student.class),id);
	}

	
	
	/**
	 * 更新学生信息
	 */
	@Override
	public void update(Student student) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtil02.getDataSource());
		queryRunner.update("update stu set sname=? , gender=? , phone=? , birthday=? , hobby=? , info=? where sid=?", 
				student.getSname(),
				student.getGender(),
				student.getPhone(),
				student.getBirthday(),
				student.getHobby(),
				student.getInfo(),
				student.getSid()
				);
	}

	

	/**
	 * 模糊查询
	 */
	@Override
	public List<Student> serrchStudent(String sname, String sgender)
			throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtil02.getDataSource());
		
		String sql = "select * from stu where 1=1 ";
		List<String> list = new ArrayList<String>();

		//TextUtils.isEmpty() 是一个自己写的方法，作用是判定字符串是否为空
		if(!TextUtils.isEmpty(sname)){
			 sql = sql + " and sname like ?";
			 list.add("%"+sname+"%");
		}
		if(!TextUtils.isEmpty(sgender)){
			 sql = sql + " and gender = ?";
			 list.add(sgender);
		}
		//select * from stu where 1=1 and sname= like ? and gender = ?
		return queryRunner.query(sql, new BeanListHandler<Student>(Student.class),list.toArray());
		
		
//		String sql = "select * from stu where 1=1 ";
//		List<String> list = new ArrayList<String> ();
//				
//		if(!TextUtils.isEmpty(sname)){
//			sql = sql + "  and sname like ?";
//			list.add("%"+sname+"%");
//		}
//		
//		if(!TextUtils.isEmpty(sgender)){
//			sql = sql + " and gender = ?";
//			list.add(sgender);
//		}
//		
//		
//		return queryRunner.query(sql , new BeanListHandler<Student>(Student.class) ,list.toArray() );
	}
	
	/**
	 * 根据页码查询学生信息 
	 */
	@Override
	public List<Student> findStudentByPage(int currentPage) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtil02.getDataSource());
		//第一个?号：需要查询多少条数据        第二个?号：跳过之前多少条记录
		//第一页 --- 5 0  （currentPage-1）*5
		//第二页 --- 5 5  （currentPage-1）*5
		//第三页 --- 5 10 （currentPage-1）*5
		return queryRunner.query("select * from stu limit ? offset ?", new BeanListHandler<Student>(Student.class),PAGE_SIZE,(currentPage-1)*PAGE_SIZE);
	}

	/**
	 * 查询有多少条记录
	 */
	@Override
	public int findcount() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtil02.getDataSource());
		Long resust = (Long) queryRunner.query("select count(*) from stu", new ScalarHandler());
		return resust.intValue();
	}


}
