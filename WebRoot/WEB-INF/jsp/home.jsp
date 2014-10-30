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

<title>我的首页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript"
	src="<%=path%>/resource/js/jquery-1.4.2.min.js"></script>
</head>

<body>
	<div style="border:1px solid red;float: left;height: 800px;width: 1300px">
		<div style="border:1px solid red;height: 30;width: auto;"></div>
		<div style="float: left;border: 1px solid blue; height: 800px;width: 19%">
			
		</div>
		<div style="float: left;border: 1px solid blue; height: 800px;width: 60%;">
			<!-- 说说 -->
			<form id="addMessage">
				有什么新鲜事告诉大家。 <br />
				<textarea title="说说内容" id="content" name="content"></textarea>
				<br /> <a>图片</a><a>公开</a> <a href="javascript:void(0)" title="发布说说"
					onclick="postMessage()">发布</a>
			</form>
			<p>
				<a href="<%=path%>/users/fsearch">新消息<span style="color:red"
					id="unreadMessage">0</span>
				</a>
			</p>
		</div>
		<div style="float:right;border: 1px solid blue; height: 800px;width: 19%">
			<!-- 个人信息 -->
			<span id="attentionCount"></span>
			<span id="byAttentionCount"></span>
			<span id="messageCount"></span>
			<!-- 推荐关注好友 -->
			<div id="recommend"></div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
// 	var f=document.getElementById("xxx").getElementsByTagName("select");
// 	$(f[0]).remove();
// 	$(f[1]).remove();
// 	$("#xxx").append("<select name='select'></select>");
// 	list[0].remove(1);
	/**
	 * 公共的post请求
	 */
	function postMessage() {
// 		var a=$("#content").val();
// 		alert(a);
		$.ajax({
			cache : true,
			type : "POST",
			url : '<%=path%>/users/add',
			data : $("#addMessage").serialize(),// 你的formid
			async : false,
			dataType : 'html',
			error : function(request) {
				alert("Connection error");
			},
			success : function(data) {
				alert("添加说说"+data);
			}
		});
	}
	
	accessMessage();
	//刷新消息
	function accessMessage(){
		$.ajax({
			cache : true,
			type : "GET",
			url : '<%=path%>/users/refresh',
// 			data : $("#addMessage").serialize(),// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				alert("Connection error");
			},
			success : function(data) {
// 				alert("未读消息"+data);
				$("#unreadMessage").html(data);
			}
		});
// 		setTimeout("accessMessage()",5000); //指定1秒刷新一次
	}
	loadProfile();
	function loadProfile(){
		$.ajax({
			cache : true,
			type : "GET",
			url : '<%=path%>/users/Profile',
					async : false,
					dataType : 'json',
					error : function(request) {
						alert("Connection error");
					},
					success : function(data) {
// 						alert("个人信息"+data);
						var response = eval('(' + data + ')');
						$("#attentionCount").html("关注数" + response.attentionCount);
						$("#byAttentionCount").html("被关注数" + response.byAttentionCount);
						$("#messageCount").html("说说" + response.messageCount);
					}
				});
	}
	recommend();
	function recommend(){
		$.ajax({
			cache : true,
			type : "GET",
			url : '<%=path%>/users/recommendFriend',
					async : false,
					dataType : 'json',
					error : function(request) {
						alert("Connection error");
					},
					success : function(data) {
// 						alert("个人信息"+data);
						var response = eval('(' + data + ')');
// 						alert(response.length);
						for(var i=0;i < response.length;i++){
						//console.log(response[i].userId);//[i].userId
							var p = "<p style='border: 1px solid blue'>"+response[i].trueName+"<a href='<%= path%>/attention/add/"+response[i].userId+"'>关注</a></p>";
							$("#recommend").append(p);
						}
					}
				});
	}
</script>
