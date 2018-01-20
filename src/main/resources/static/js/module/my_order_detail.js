
function initData(order) {
	var details = order.details;
	var statusStr = order.status;
	var totalPrice = order.totalPrice;
	var status = order.orderStatus;
	var statusStr = '未知';

	if (status === 1) {
		statusStr = '已下单';
	} else if (status === 2) {
		statusStr = '出货中';
	} else if (status === 3) {
		statusStr = '配送中';
	} else if (status === 4) {
		statusStr = '已到达';
	} else if (status === 5) {
		statusStr = '已收货';
	}
	for (var i = 0; i < details.length; i++) {
		var newRow = document.getElementById('tb').insertRow(i);
		var no = newRow.insertCell(0);
		var prod = newRow.insertCell(1);
		var unit = newRow.insertCell(2);
		var num = newRow.insertCell(3);
		var total = newRow.insertCell(4);

		no.innerHTML = i + 1;

		var detail = details[i];

		prod.innerHTML = detail.productName;
		var price = detail.unitPrice;
		var productUnit = detail.productUnit;
		unit.innerHTML = price + ' 元/' + productUnit;
		var productNum = detail.productNum;
		var productNumStr = detail.productNum + ' ' + productUnit;
		num.innerHTML = productNumStr;
		total.innerHTML = price * productNum;
		
	}
	$('#status').html(statusStr);
	$('#total-price').html('总价: ' + totalPrice + '元');
	$('#addr').html("<strong>" + order.receiver + "<br/>" + order.contacts + "</strong></br>" + order.receivingAddress);
	$('#create-time').html("创建时间:&nbsp;" + order.createTime);
}
