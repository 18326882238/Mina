$(function(){
		$('.tab').click(function(){
			$('#token').html('');
			$('#notake').html('');
			$('#btn').hide();
			$(this).addClass('tabActive').siblings().removeClass('tabActive');
			var index =$(this).index();
			$('.centerMain').children().eq(index).show().siblings().hide();
			init($(this).index()+1);
		})
		init(1);
});
	function openLock(){
		if(confirm("确定远程开箱吗?")){
			var url = "doremote.do";
			var data = {'orderId':$("#order").html(),'boxId':$("#boxId").html()};
			$.post(url,data,function(result){
				alert(result.message);
				if(result.state == 0){
					$('#btn').hide();
				}
			})
		}
	}
	
	function getDetail(me){
		var url = "domore.do";
		var data = {'order':$(me).children().find('span').html(),'fi_boxId':$(me).find('.fi_boxId').html()};
		$.post(url,data,function(result){
			if(result.state == 0){
				$(me).parent().html('');
				var exp = result.data;
				var s = 0;
				var pickTime = '<li>取件时间：</li>';
				if(exp.status == 1){
					s = "等待取件";
				}
				if(exp.status == 2){
					s = "已经取件";
					pickTime = '<li>取件时间：'+new Date(exp.pickUpTime).toLocaleString()+'</li>';
				}
				var li = '<li>运单号: <span id="order">'+exp.trackNum+'</span></li>'
					+'<li>投递员所属快递公司: '+exp.express+'</li>'
					+'<li>投递员姓名: '+exp.deliverName+'</li>'
					+'<li>投递员电话: '+exp.deliverTel+'</li>'
					+'<li>收件员电话: '+exp.recTel+'</li>'
					+'<li>存件时间: '+new Date(exp.deliverTime).toLocaleString()+'</li>'
					+pickTime
					+'<li>取件密码: '+exp.pickPwd+'</li>'
					+'<li>E邮柜: '+exp.address+'</li>'
					+'<li>格口编号: <span id="boxId">'+exp.boxId+'</span></li>'
					+'<li>状态: '+s+'</li>';
				if(exp.status==1){
					$('#notake').append(li);
					$('#btn').show(200);
				}else{
					$('#token').append(li);
				}
			}else{
				alert("无该运单信息。");
			}
		})
	}
	
	function init(num){
		var url = "doload.do?status="+num;
		$.getJSON(url, function(result){
			if(result.state==0){
				var list = result.data;
				for(var i=0; i<list.length; i++){
					var st = "<li>状态: 等待取件</li>";
					if(list[i].status ==2){
						st = "<li>状态: 已经取件</li>";
					}
					var li = '<li class="post"><ul><li>运单号: <span>'+
					list[i].trackNum
					+'</span></li><li>收件人电话: '
					+list[i].recTel
					+'<a style="float:right;color:green;">快件详情</a></li>'
					+st
					+'<li>格口编号: <i class="fi_boxId">'
					+list[i].boxId
					+'</i></li>'
					+'</ul></li>';
					li = $(li).data('index', i);
					li.click(function(){
						getDetail(this);
					})
					if(list[i].status == 1){
						$('#notake').append(li);
					}else{
						$('#token').append(li);
					}
				}
			}else{
				alert(result.message); 
			}
		});
	}
