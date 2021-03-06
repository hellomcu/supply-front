function getMyOrders(page, createTime) {

	/*
	 * var jsonParams = { "page" : num, "num" : 10, };
	 */

	$.myAjax('./front/order/my_orders?page=' + page + '&num=10&createTime=' + createTime, 'GET', null,
			function(data) {
//				alert(JSON.stringify(data.data));

				if (data.code != 1) {
					alert(data.message);
				} else {
					initData(data.data);
				}
			});

}

function initData(data) {
	var list = data.list;
	var tbody = document.getElementById('tb');
	$(tbody).empty();
	for (var i = 0; i < list.length; i++) {
		
		
		var newRow = tbody.insertRow(i);
		
		var newRow = document.getElementById('tb').insertRow(i);
		var no = newRow.insertCell(0);
		var prod = newRow.insertCell(1);
//		var num = newRow.insertCell(2);
		var total = newRow.insertCell(2);
//		var statusCol = newRow.insertCell(4);
		var time = newRow.insertCell(3);
		var detail = newRow.insertCell(4);

		no.innerHTML = i + 1;
		var order = list[i];
		var details = order.details;
		
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
		var totalPrice = order.totalPrice;
//		num.innerHTML = order.productNum;
		total.innerHTML = totalPrice;

//		var status = order.orderStatus;
//		var statusStr = '未知';
//		console.log(status);
//		if (status === 1) {
//			statusStr = '已下单';
//		} else if (status === 2) {
//			statusStr = '出货中';
//		} else if (status === 3) {
//			statusStr = '配送中';
//		} else if (status === 4) {
//			statusStr = '已到达';
//		} else if (status === 5) {
//			statusStr = '已收货';
//		}
//		statusCol.innerHTML = statusStr;
		time.innerHTML = order.createTime;
		
		var params = 'order=' + encodeURI(encodeURI(JSON.stringify(order)));

		detail.innerHTML = "<button type='button' class='btn btn-success btn-flat' onclick='javascript:window.location.href=\"my_order_detail.html?" + params + "\"'>查看详情</button>";
	}
	
	var sumPrice = data.totalPrice;
	if (sumPrice == null) {
		sumPrice = 0;
	}
	
	$('#total-span').html("共有 <span class='text-primary'>" + data.totalNum +  "</span> 个订单,总价格:<span class='text-success'> " + sumPrice + "</span> 元");
	
	var totalPage = data.totalPage;
	$('#pagination').pagination({
        items: data.totalPage,
        itemOnPage: data.itemNum,
        currentPage: data.currentPage,
        cssStyle: '',
        prevText: '上一页',
        nextText: '下一页',
        onInit: function () {
            // fire first page loading
        },
        onPageClick: function (page, evt) {
        	var date = $("#datetimepicker").datepicker("getDate");
        	if (date === null || date === undefined) {
        		date = 0;
        	} else {
        		date = date.valueOf();
        	}
        	getMyOrders(page, date);
        }
    });
}

function getMyOrderDetail(order, products) {
	for (var i = 0; i < products.length; i++) {
		// alert(JSON.stringify(data[i].createTime));

		var newRow = document.getElementById('tb').insertRow(i);
		var no = newRow.insertCell(0);
		var prod = newRow.insertCell(1);
		var unit = newRow.insertCell(2);
		var num = newRow.insertCell(3);
		var total = newRow.insertCell(4);
		
		no.innerHTML = i + 1;
		prod.innerHTML = products[i].productName;
		var unitPrice = products[i].unitPrice;
		unit.innerHTML = unitPrice;
		var productNum = products[i].productNum;
		num.innerHTML = productNum;
		total.innerHTML = unitPrice * productNum;
	}

}
