/*
 * 快递员主页面执行js脚本
 */

$(function(){
	
	$('.themeD').hide();
	
	checkMenu();
	
	
});

function checkMenu(){
	var url = "checkMenu.do";
	$.getJSON(url, function(result){
		if(result.message == "isAdmin")
			$('.themeD').show();
	});
}

function DeliveryPackage(){
	window.location.href='goDeliver.do';
}

function DeliveryJournal(){
	window.location.href='goDeliveryJournal.do';
}

function Expired(){
	window.location.href='goExpired.do';
}
































