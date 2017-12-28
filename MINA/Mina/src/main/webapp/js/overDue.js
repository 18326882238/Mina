/*
 * 快件取回页面执行js脚本
 */
$(function(){
	
	loadOrderMsg();
	
})

function loadOrderMsg(){
	var url = "loadDelivered.do?status="+1;
	$.getJSON(url, function(result){
		var list = result.data;
		paintList(list);
	});
}

function paintList(list){
	var ul = $("#orderList").empty();
	var li = "";
	for(var i=0;i<list.length;i++){
		li +=  '<li>订单号:<i id="orderId">'+list[i].trackNum+'</i>'+
			   '<span>投递时间:'+new Date(list[i].deliverTime).toLocaleString()+'</span>'+
			   '<i id="boxId" style="display:none">'+list[i].boxId+'</i>'+
			   '</li>';
	}
	ul.append(li);
	bindOpenBox();
}

function bindOpenBox(){
	$("li").click(function(){
		if(confirm("确认取回吗?")){
			var url = "doremote.do";
			var data = {'orderId':$("#orderId").html(),'boxId':$("#boxId").html(),'msg':"qh"};
			$.post(url, data, function(result){
				alert(result.message);
			});
		}
	});
}












