<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>创建方案</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=path %>/resource/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/nicEdit/nicEdit.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/solutionAdd.js"></script>
</head>

<body>
	<div style="margin: 50 50 50 50">
		<form id="add">
			标题： <input type="text" id="title" name="title" value="">
			<textarea style="width: 100%;"></textarea>
			<input type="button" value="提交" onclick="add()">
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	window.onload=function(){
		var context = $('.nicEdit-main').html();
		var input = document.createElement("input");
		$(input).attr("type","hidden");
		$(input).attr("value",context);
		$(input).attr("id","content");
		$(input).attr("name","content");
		$(input).appendTo("form");
	};
	function add() {
		var content = $('.nicEdit-main').html();
		$("#content").val(content);
		$.ajax({
				cache: true,
				type: "POST",
				url:'<%= path%>/solution/add',
				data:$("#add").serialize(),// 你的formid
				async: false,
			    error: function(request) {
			        alert("Connection error");
			    },
			    success: function(data) {
		    		if(confirm("提示：添加"+data[0])){
		    			redirect();
		    		};
		    		setTimeout("redirect()", 3000);
			    }
			});
	}
</script>
