function userLogin(username, password, userType) {

	var jsonParams = {
		"password" : password,
		"type" : userType,
		"username" : username
	};

	$.myAjax('../user/user_login', 'POST', JSON.stringify(jsonParams), function(
			data) {
		// alert(JSON.stringify(data.data));

		if (data.code === 1) {
			if (userType === 2) {
				window.location.href = "./home.html";
			} else {
				alert("用户身份错误");
			}
		} else {
			alert(data.message);
		}
	});

}