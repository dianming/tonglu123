<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>解决方案列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<table border="1">
		<tr>
			<td colspan="3">
				<form action="" method="get">
					搜索 <input type="text" value="">
					<input type="submit" value="提交">
				</form>
			</td>
		</tr>
		<tr>
			<td>标题</td>
			<td>内容</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${solutionList }" var="sl">
			<tr>
				<td>${sl.title}</td>
				<td>
					<%-- ${sl.context} --%>
				</td>
				<td><a href="solution/showAnEssay/${sl.id }">编辑</a> <a href="">冻结</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
