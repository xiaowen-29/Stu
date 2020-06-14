<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

	function Delete(sid) {
		/* 逻辑：用户点击删除按钮，携带此条记录的id，并且触发JS函数。
		 	  JS函数弹出对话框，包括确认和取消两个按钮。
		 	  如果点击确认按钮，则携带Id的同时跳转到Servlet
		 	  如果点击了取消按钮，则无事发生 */
	    var flag = confirm("是否确认删除？");
		if(flag){
			//window.location.href="Delete";
			location.href="Delete?sid="+sid;
		}else{
			
		}
	}
		
</script>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询显示界面</title>
</head>
<body>
<form action="SearchStudent" method="post">
	<table border="1" width="700">
		
		<tr >
				<td colspan="8">
					按姓名查询:<input type="text" name="sname"/>
					&nbsp;
					按性别查询:<select name="sgender">
								<option value="">--请选择--
								<option value="男">男
								<option value="女">女
							  </select>
					&nbsp;&nbsp;&nbsp;
					<input type="submit" value="查询">
					&nbsp;&nbsp;&nbsp;
					<a href="add.jsp">添加</a>
				</td>
		</tr>
		<tr align="center">
			<td>编号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>电话</td>
			<td>生日</td>
			<td>爱好</td>
			<td>简介</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${list }" var="stu">
			<tr align="center">
				<td>${stu.sid }</td>
				<td>${stu.sname }</td>
				<td>${stu.gender }</td>
				<td>${stu.phone }</td>
				<td>${stu.birthday }</td>
				<td>${stu.hobby }</td>
				<td>${stu.info }</td>
				<td><a href="Edit?sid=${stu.sid}">更新</a>  <a href="#" onclick="Delete(${stu.sid})">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</form>
</body>
</html>