function getGoods(num) {

	$.myAjax('front/product/products?page=' + num + '&num=10', 'GET', null,
			function(data) {
				// alert(JSON.stringify(data.data));

				if (data.code != 1) {
					alert(data.message);
				} else {
					initData(data.data);
				}
			});

}

function initData(data) {
	for (var i = 0; i < data.length; i++) {
		// alert(JSON.stringify(data[i].createTime));

		var x = document.getElementById('tb').insertRow(i);
		var y = x.insertCell(0);
		var z = x.insertCell(1);

		var a = x.insertCell(2);
		// var b = x.insertCell(3);
		var c = x.insertCell(3);
		y.innerHTML = data[i].productName;
		z.innerHTML = data[i].productPlace;

		a.innerHTML = data[i].productPrice + "/" + data[i].productUnit;
		// b.innerHTML = data[i].productUnit;

		// c.innerHTML = "<input type='button' value='立即购买'
		// onclick='createOrder(" + JSON.stringify(data[i]) + ");' />";
		c.innerHTML = "<button type='button' class='btn btn-success' onclick='createOrder("
				+ JSON.stringify(data[i]) + ");'>立即购买</button>";
	}
}

function createOrder(obj) {

	window.location.href = "./create_order.html?product="
			+ encodeURI(encodeURI(JSON.stringify(obj)));

	// var c = document.getElementById("in"+data).value;
	// if (c == null || c == ""){
	// alert("不能输入空值");
	// }else{
	// alert(c);
	// }

}