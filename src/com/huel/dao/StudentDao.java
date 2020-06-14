package com.huel.dao;

import java.sql.SQLException;
import java.util.List;

import com.huel.domain.Student;

/**
 * 这是针对数据库表的查询
 * @author HJC
 *
 */
public interface StudentDao {

	int PAGE_SIZE = 5;
	/**
	 * 根据页码查询学生信息
	 * @return 选定页学生信息
	 * @throws SQLException
	 */
	List<Student> findStudentByPage(int currentPage) throws SQLException;
	
	/**
	 * 查询所有学生信息
	 * @return List<Student>
	 */
	List<Student>  findall() throws SQLException;
	
	/**
	 * 根据id查学生信息
	 * @return List<Student>
	 */
	Student  findById(int id) throws SQLException;
	
	/**
	 * 模糊查询，通过 姓名|性别 最少一个进行筛选匹配
	 * @param sname
	 * @param sgender
	 * @return
	 * @throws SQLException
	 */
	List<Student>   serrchStudent(String sname,String sgender) throws SQLException;
	
	
	/**
	 * 需要添加到数据库的学生对象
	 * @param student
	 * @throws SQLException
	 */
	void insert(Student student) throws SQLException;
	
	
	/**
	 * 根据id删除学生
	 * @param sid
	 * @throws SQLException
	 */
	void delete(int sid) throws SQLException;
	
	
	/**
	 * 更改学生信息
	 * @param student
	 * @throws SQLException
	 */
	void update(Student student) throws SQLException;
	
	
	int findcount() throws SQLException;
}
