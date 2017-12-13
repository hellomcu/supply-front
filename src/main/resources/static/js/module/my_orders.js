function getMyOrders(num) {

	/*
	 * var jsonParams = { "page" : num, "num" : 10, };
	 */

	$.myAjax('front/order/my_orders?page=' + num + '&num=100000', 'GET', null,
			function(data) {
				alert(JSON.stringify(data.data));

				if (data.code != 1) {
					alert(data.message);
				} else {
					initData(data.data);
				}
			});

	// $.ajax({
	// url: 'front/order/my_orders?page=' + num + '&num=100000',
	// // contentType : "application/json; charset=utf-8",
	// // data : JSON.stringify(jsonParams),
	// withCredentials: true,
	// type: 'get',
	// cache: false,
	// dataType: 'json',
	// success: function (data) {
	// // alert(JSON.stringify(data));
	//
	// // alert(JSON.stringify(data.data));
	//
	// if (data.code != 1) {
	// alert(data.message);
	// } else {
	// initData(data.data);
	// }
	// },
	// error: function () {
	// alert("异常");
	// }
	// });

}

function initData(data) {
	for (var i = 0; i < data.length; i++) {
		// alert(JSON.stringify(data[i].createTime));

		var newRow = document.getElementById('tb').insertRow(i);
		var no = newRow.insertCell(0);
		var prod = newRow.insertCell(1);
		var num = newRow.insertCell(2);
		var total = newRow.insertCell(3);
		var statusCol = newRow.insertCell(4);
		var detail = newRow.insertCell(5);

		no.innerHTML = i + 1;

		var details = data[i].details;
		var nameStr = "";
		var len = details.length;
		for (var j = 0; j < len; j++) {
			var d = details[j];
			nameStr += d.productName + ", ";
		}
		var index = nameStr.lastIndexOf(",");
		if (index != -1) {
			nameStr = nameStr.substring(0, index);
		}
		prod.innerHTML = nameStr;
		num.innerHTML = data[i].productNum;
		total.innerHTML = data[i].totalPrice;
		
		var status = data[i].orderStatus;
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
		statusCol.innerHTML = statusStr;
		detail.innerHTML = "<button type='button' class='btn btn-success' >查看详情</button>";
	}
}