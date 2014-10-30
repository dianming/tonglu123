/*
 * 调用文本编辑器
 */
bkLib.onDomLoaded(function() {
	nicEditors.allTextAreas();
});

/*
 * js提交form表单
 */
//function post(URL, PARAMS) {
//	var formadd = document.createElement("form");
//	formadd.action = URL;
//	formadd.method = 'post';
//	formadd.style.display = 'none';
//	for ( var val in PARAMS) {
//		var opt = document.createElement("input");
//		opt.name = val;
//		opt.value = PARAMS[val];
//		// alert(opt.name)
//		formadd.appendChild(opt);
//	}
//	document.body.appendChild(formadd);
//	formadd.submit();
////	return formadd;
//}

function pudate(URL, PARAMS) {
	var formadd = document.createElement("form");
	formadd.action = URL;
	formadd.method = 'post';
	formadd.style.display = 'none';
	for ( var val in PARAMS) {
		var opt = document.createElement("input");
		opt.name = val;
		opt.value = PARAMS[val];
		// alert(opt.name)
		formadd.appendChild(opt);
	}
	document.body.appendChild(formadd);
	formadd.submit();
//	return formadd;
}
function redirect(){
	window.location.href="/exception/solution/showEssayList";
};