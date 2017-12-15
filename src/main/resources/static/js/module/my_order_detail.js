
function initData(order, totalPrice, statusStr, details) {

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
		unit.innerHTML = price + '/' + detail.productUnit;
		var productNum = detail.productNum;
		num.innerHTML = productNum;
		total.innerHTML = price * productNum;
		$('#addr').html("<strong>" + order.contacts + "</strong></br>" + order.receivingAddress);
	}
}
