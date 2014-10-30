<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>欢迎来到同道中人</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=path %>/resource/js/users.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/common.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/jquery-1.4.2.min.js"></script>
</head>
<body>
	<form id="login">
		账号：<input type="text" name="userName"> 密码：<input type="text"
			name="password"> <input type="checkbox"> 自动登录
			<input type="button" value="登录" onclick="login()">
	</form>
</body>
<script type="text/javascript">
function login(){
	post('<%=path%>/users/login',"login","<%=path %>/users/index");
}
</script>
</html>
