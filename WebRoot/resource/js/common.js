/**
 * 公共的post请求
 */
function post(URL,FORMID,href) {
	$.ajax({
		cache : true,
		type : "POST",
		url : URL,
		data : $("#"+FORMID).serialize(),// 你的formid
		async : false,
		dataType:'json',
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (confirm("提示：" + data)) {
				redirectLogin(href);
			};
			setTimeout("redirectLogin("+href+")", 3000);
		}
	});
}
function redirectLogin(href){
	window.location.href=href;
}