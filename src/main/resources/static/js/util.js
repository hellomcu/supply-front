function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

$.extend($, {
	/*
	 * ajax调用封装，返回json url 服务路径 data一般为js对象 callback 回调函数
	 */
	myAjax : function(url, method, body, callback) {
		$.ajax({
			url : url,
			data : body,
			dataType : 'json',
			method : method,
			cache : false,
			beforeSend : function() {
				$("body").mLoading("show");
				//showLoading();// 打开加载层
			},
			complete : function() {
				$("body").mLoading("hide");
				//closeLoading();// 关闭加载层
			},
			success : function(data) {
				if (typeof callback != 'undefined')
					callback.call(this, data);
			},
			error: function () {
				alert("异常");
			}
		});
	}
});