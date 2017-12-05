function toOrder(id, productName, unitPrice, productUnit) {

	var jsonParams = {
		"contacts" : "13120719587",
		"orderDetails" : [ {
			"productId" : id,
			"productName" : productName,
			"productNum" : 10,
			"productUnit" : productUnit,
			"unitPrice" : 5.5
		} ],
		"orderRemark" : "加急",
		"receivingAddress" : "濮阳濮城",
		"storeId" : 20
	};

	$.ajax({
		url : 'front/order',
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(jsonParams),
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			// alert(JSON.stringify(data));

			alert(data.code);

			if (data.code == 1) {
				alert("下单成功");
			} else {
				alert(data.message);
			}

			window.location.href = "./getGoods.html";

		},
		error : function() {
			alert("异常");
		}
	});

}
function getOrder(id, num) {

	$.ajax({
		url : 'front/order/my_orders?page=' + num + '&num=10&store_id=' + id,
		// contentType : "application/json; charset=utf-8",
		// data : JSON.stringify(jsonParams),
		type : 'get',
		cache : false,
		dataType : 'json',
		success : function(data) {
			// alert(JSON.stringify(data));

			alert(JSON.stringify(data.data));

		},
		error : function() {
			alert("异常");
		}
	});
}
