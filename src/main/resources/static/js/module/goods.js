function getGoods(num) {

	/*
	 * var jsonParams = { "page" : num, "num" : 10,
	 *  };
	 */

	$.ajax({
		url: 'front/product/products?page=' + num + '&num=10',
		// contentType : "application/json; charset=utf-8",
		// data : JSON.stringify(jsonParams),
		withCredentials: true,  
		type: 'get',
		cache: false,
		dataType: 'json',
		success: function (data) {
			// alert(JSON.stringify(data));

			//			 alert(JSON.stringify(data.data));

			if (data.code != 1) {
				alert(data.message);
			} else {
				initData(data.data);
			}





		},
		error: function () {
			alert("异常");
		}
	});

}

function initData(data) {
	for (var i = 0; i < data.length; i++) {
		//alert(JSON.stringify(data[i].createTime));

		var x = document.getElementById('tb').insertRow(i);
		var y = x.insertCell(0);
		var z = x.insertCell(1);

		var a = x.insertCell(2);
		//var b = x.insertCell(3);
		var c = x.insertCell(3);
		y.innerHTML = data[i].productName;
		z.innerHTML = data[i].productPlace;

		a.innerHTML = data[i].productPrice + "/" + data[i].productUnit;
		//b.innerHTML = data[i].productUnit;

		//c.innerHTML = "<input type='button' value='立即购买' onclick='createOrder(" + JSON.stringify(data[i]) + ");' />";
		c.innerHTML = "<button type='button' class='btn btn-success' onclick='createOrder(" + JSON.stringify(data[i]) + ");'>立即购买</button>";
	}
}

function createOrder(obj) {

	window.location.href = "./create_order.html?product=" + encodeURI(encodeURI(JSON.stringify(obj))); 

	//	var c = document.getElementById("in"+data).value;
	//	if (c == null || c == ""){
	//		alert("不能输入空值");
	//	}else{
	//		alert(c);
	//	}

}