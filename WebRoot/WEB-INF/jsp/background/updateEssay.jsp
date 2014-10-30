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
<title>更新方案</title>
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
	${xxx}===>${requestScope.xxx }--->${sessionScope.xxx}--->${requestScope.solution }--->${sessionScope.solution }
	<form id="updateContent">
		<div style="margin: 50 50 50 50">
<!-- 			<input type="hidden" name="_method" value="put" /> -->
			<input type="hidden" id="sid" name="id" value="${solution.id }">
			标题： <input type="text" id="title" name="title" value="${solution.title }">
			<textarea style="width: 100%;">${solution.content }</textarea>
			<input type="button" value="提交" onclick="updateDate()">
		</div>
	</form>
	<span id="hou"></span>
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
	//这段js会缓存
	function updateDate() {
		var content = $('.nicEdit-main').html();
		$("#content").val(content);
		$.ajax({
				cache: true,
				type: "POST",
				url:'/exception/solution/putAnEssay',
				data:$("#updateContent").serialize(),// 你的formid
				async: false,
			    error: function(request) {
			        alert("Connection error");
			    },
			    success: function(data) {
		    		if(confirm("提示：修改"+data)){
		    			redirect();
		    		};
		    		setTimeout("redirect()", 3000);
			    }
			});
	}
	/* function submit() {
		var sid = $("#sid").val();
		var put = $("[name='_method']").val();
		var title = $("#title").val();
		var content = $('.nicEdit-main').html();
		
		pudate("/exception/solution/putAnEssay", {
			'_method' : put,
			'id' : sid,
			'title' : title,
			'content' : content
		});
	} */
</script>
