function toOrder(id, productName, productNum, unitPrice, productUnit, contact, address, remark) {

	var jsonParams = {
		"contacts" : contact,
		"orderDetails" : [ {
			"productId" : id,
			"productName" : productName,
			"productNum" : productNum,
			"productUnit" : productUnit,
			"unitPrice" : unitPrice
		} ],
		"orderRemark" : remark,
		"receivingAddress" : address
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

			if (data.code == 1) {
				alert("下单成功");
				window.location.href = "./getGoods.html";
			} else {
				alert(data.message);
			}

			

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
