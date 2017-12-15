function toOrder(id, productNum, contact, address, remark) {

	var jsonParams = {
		"contacts" : contact,
		"details" : [ {
			"productId" : id,
			"productNum" : productNum
		} ],
		"orderRemark" : remark,
		"receivingAddress" : address
	};
	$.myAjax('front/order', 'POST', JSON.stringify(jsonParams), function(data) {
		if (data.code == 1) {
			alert("下单成功");
			window.location.href = "./getGoods.html";
		} else {
			alert(data.message);
		}
	});

}
function getOrder(id, num) {
	$.myAjax('front/order/my_orders?page=' + num + '&num=10&store_id=' + id,
			'GET', JSON.stringify(jsonParams), function(data) {
				alert(JSON.stringify(data.data));
			});
}
