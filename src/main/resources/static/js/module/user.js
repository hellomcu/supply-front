

function userLogin(username, password, userType) {

	var jsonParams = {
		"password" : password,
		"type" : userType,
		"username" : username
	};

	$.ajax({
		url : 'user/user_login',
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(jsonParams),
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data.code === 1) {
				if (userType === 1) {
					window.location.href="./main.jsp"; 
				} else if (userType === 2) {
					window.location.href="./getGoods.html"; 
				} else {
					alert("用户身份错误");
				}
			} else {
				alert(data.message);
			}

		},
		error : function() {
			alert("异常");
		}
	});
}