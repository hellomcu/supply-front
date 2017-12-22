function addStore(storeName, storeAddress, callNumber, userName, passWord,
		beizhu) {

	var jsonParams = {
		"storeName" : storeName,
		"storePlace" : storeAddress,
		"contacts" : callNumber,
		"username" : userName,
		"password" : passWord,
		"description" : beizhu
	};

	$.ajax({
		url : 'admin/store',
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(jsonParams),
		type : 'put',
		cache : false,
		dataType : 'json',
		success : function(data) {
			window.location.href="./mendianManage.jsp"; 
			alert("添加成功！");

		},
		error : function() {
			alert("异常");
		}
	});
}

function getStore(page) {

	/*
	 * var jsonParams = { "page" : num, "num" : 10,
	 *  };
	 */

	$.ajax({
		url : 'admin/store/stores?page=' + page + '&num=10',
		// contentType : "application/json; charset=utf-8",
		// data : JSON.stringify(jsonParams),
		type : 'get',
		cache : false,
		dataType : 'json',
		success : function(data) {
			// alert(JSON.stringify(data));

			// alert(JSON.stringify(data.data));

			initData(data.data);

		},
		error : function() {
			alert("异常");
		}
	});

}

function initData(data) {
	var list = data.list;
	for (var i = 0; i < list.length; i++) {
//alert(JSON.stringify(data[i].createTime));
		
		 var x=document.getElementById('myTable').insertRow(i+1)
		  var y=x.insertCell(0)
		  var z=x.insertCell(1)
		  
		   var a=x.insertCell(2)
		  var b=x.insertCell(3)
		  var c=x.insertCell(4)
		  y.innerHTML=i+1
		  z.innerHTML='<a href="http://www.baidu.com">'+list[i].storeName+'</a>'
		  
		  a.innerHTML=list[i].storePlace
		  b.innerHTML=list[i].contacts
		  
		  c.innerHTML='<input type="button" value="删除" onclick="deleteStore('+list[i].id+')"/>'
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
        	getStore(page);
//            $('#alt-style-pagination-content').text('Page ' + page);
        }
    });
}
function deleteStore(id) {
	var r=confirm("要删除它吗？");
	if (r == true){
		

		$.ajax({
			url : 'admin/store?id='+id,
			// contentType : "application/json; charset=utf-8",
			// data : JSON.stringify(jsonParams),
			type : 'delete',
			cache : false,
			dataType : 'json',
			success : function(data) {
				// alert(JSON.stringify(data));

				// alert(JSON.stringify(data.data));

				window.location.href="./mendianManage.jsp"; 

			},
			error : function() {
				alert("异常");
			}
		});


		
		
		
		
	}else{
		
	}
}