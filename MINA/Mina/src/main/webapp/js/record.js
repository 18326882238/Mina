/*
 * 投递记录执行js脚本
 */

var model = {
		currentOrderList:[]		//当前投递记录
		
}

$(function(){
	
//	ListOrderMsgByCardId();
	
	loadMore();
	
//	loadFinished();
});

function loadFinished(){
	$(window).scroll(function(){
		
		var distanceTop = $('#last').offset().top - $(window).height();
	
		if($(window).scrollTop() > distanceTop){
			$('#slidebox').animate({'marginLeft':'0px'},300);
		}else{ 
			$('#slidebox').stop(true).animate({'marginLeft':'-430px'},100);
		}
				
	});
	
	$('#slidebox .close').bind('click',function(){
		$(this).parent("#slidebox").stop(true).animate({'marginLeft':'-430px'},100);
	});
}

function loadMore(){
	  /*初始化*/
	  var counter = 0; /*计数器*/
	  var pageStart = 0; /*offset*/
	  var pageSize = 20; /*size*/
	  var isEnd = false;/*结束标志*/
	  $('.foot').hide();
	  /*首次加载*/
	  getData(pageStart, pageSize);
	  
	  /*监听加载更多*/ 
	  $(window).scroll(function(){
	    if(isEnd == true){
	      $('.foot').show();
	    }
	    // 当滚动到最底部以上100像素时， 加载新内容
	    // 核心代码
	    if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
	      counter ++;
	      pageStart = pageStart + pageSize;
	      
	      getData(pageStart, pageSize);
	    }
	  });
	
}

function getData(pageStart, pageSize){
	var url = "list.do";
	var data = {"pageStart":pageStart,"pageSize":pageSize};
	$.post(url, data, function(result){
		model.currentOrderList = result.data;
		paintOrderList();
	});
}


function ListOrderMsgByCardId(){
	var url = "list.do";
	$.getJSON(url, function(result){
		if(result.state == 0){
			model.currentOrderList = result.data;
			paintOrderList();
		}
	});
}

function changeDatetoString(stamp){  
    var date=new Date(stamp);  
    var str=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes();  
   
   return str;  
}

function paintOrderList(){
	var list = model.currentOrderList;
	var ul = $('#orderList');
	var li = "";
	var str = "";
	for(var i=0;i<list.length;i++){
		if(list[i].status == 1){
			str = "<p>订单状态: 未取件</p>";
		}
		if(list[i].status == 2){
			str = "<p>订单状态: 取件成功</p>";
		}
		if(list[i].status == 3){
			str = "<p>订单状态: 投递员取回</p>";
		}
		if(list[i].status == 4){
			str = "<p>订单状态: 异常订单</p>";
		}
		li += 
				'<li>'+
					'<time class="cbp_tmtime" datetime="'+list[i].deliverTime+'">'+
					'<span>'+changeDatetoString(list[i].deliverTime).substring(0,9)+'</span>'+
					'<span>'+changeDatetoString(list[i].deliverTime).substring(9,14)+'</span></time>'+
//						'<span>'+new Date(list[i].deliverTime).toLocaleDateString().substring(0,9)+'</span>'+
//						'<span>'+new Date(list[i].deliverTime).toLocaleDateString().substring(9,15)+'</span></time>'+
					'<div class="cbp_tmicon cbp_tmicon-phone"></div>'+
					'<div class="cbp_tmlabel">'+
						'<h2>订单号:'+list[i].trackNum+'</h2>'+
						'<p>取件手机:'+list[i].recTel+'</p>'+str+
					'</div>'+
				'</li>';
	}
	ul.append(li);
}








