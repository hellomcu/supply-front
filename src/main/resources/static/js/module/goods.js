function getGoods(page) {

	$.myAjax('front/product/products?page=' + page + '&num=10', 'GET', null,
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
	var list = data.list;
	for (var i = 0; i < list.length; i++) {
		// alert(JSON.stringify(data[i].createTime));

		var x = document.getElementById('tb').insertRow(i);
		var y = x.insertCell(0);
		var z = x.insertCell(1);

		var a = x.insertCell(2);
		// var b = x.insertCell(3);
		var c = x.insertCell(3);
		y.innerHTML = list[i].productName;
		z.innerHTML = list[i].productPlace;

		a.innerHTML = list[i].productPrice + "/" + list[i].productUnit;
		// b.innerHTML = data[i].productUnit;

		// c.innerHTML = "<input type='button' value='立即购买'
		// onclick='createOrder(" + JSON.stringify(data[i]) + ");' />";
		c.innerHTML = "<button type='button' class='btn btn-success' onclick='createOrder("
				+ JSON.stringify(list[i]) + ");'>立即购买</button>";
	}
	
	
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
        	getGoods(page);
        }
    });
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