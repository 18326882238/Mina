/*
 * 投递期刊执行js
 */

$(function(){
	
	getOwnerMessage();
	
})

function getOwnerMessage(){
	var url = "getOwnerMessageByopenId.do";
	$.getJSON(url, function(result){
		var list = result.data;
		paintOwnerMsg(list);
	});	
}

function paintOwnerMsg(list){
	var div = $("#ownerMsg").empty();
	var html = "";
	for(var i=0;i<list.length;i++){
		html = 
				'<div class="item">'+
					'<h2><a">'+list[i].name+'</a></h2>'+
					'<p class="num">'+list[i].buildingNum+'-'+list[i].unitNum+'-'+list[i].doorNum+'</p>'+
					'<p style="display:none" class="ownerId">'+list[i].ownerId+'</p>'+
				'</div>';
		//绑定 序号
		html = $(html).data("index", i);
		//监听单击事件
		html.click(function(){
//			alert($(this).find('.ownerId').html());
			openHireBox(this);
		});
		div.append(html);
	}
//	bindfuntion();
}

//function bindfuntion(){
//	$('.item').bind('click', function(){
//		alert($(".ownerId").html());
//		var url = "openHiredBox.do";
//		var data = {"ownerId":$(".ownerId").html()};
//		$.post(url, data, function(result){
//			alert(result.message);
//		});
//	});
//}

function openHireBox(obj){
//	alert($(obj).find('.ownerId').html());
	var url = "openHiredBox.do";
	var data = {"ownerId":$(obj).find('.ownerId').html()};
	$.post(url, data, function(result){
		alert(result.message);
	});
}

function startOpen(){
//	alert($("#domain").val());
	var url = "openHireBoxByNumStr.do";
	var data = {"numStr":$("#domain").val()};
	$.post(url, data, function(result){
		alert(result.message);
	});
}








