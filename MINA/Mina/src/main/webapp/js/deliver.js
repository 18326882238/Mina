/*
 * 投递页面执行js脚本
 */

$(function(){
//	alert(location.href.split('#')[0]);		//当前请求路径
	
//	checkSign();							//扫描接口验证
	
	checkBoxNum();							//箱柜详情检测
	
});

//$(".phone").blur(function(){
//	reg=/^1[3|4|5|7|8][0-9]\d{4,8}$/i;		//验证手机正则(输入前7位至11位)
//	if( $(".phone").val()=="")
//	{
//		alert("请输入手机号!");
//	}
//	else if($(".phone").val().length<11)
 //   {
 //       alert("手机号长度有误!");
//    }
//	else if(!reg.test($(".phone").val()))
 //   {
  //      alert("输入手机号有误!");
 //   }
//});

function checkSign(){
	var url = "checkSign.do";
	$.get(url, function(result){
		var signMap = result.data;
		wx.config({
            debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId : 'wx2fc256375c739c19', // 必填，公众号的唯一标识
            timestamp : signMap.timestamp, // 必填，生成签名的时间戳
            nonceStr : signMap.nonceStr, // 必填，生成签名的随机串
            signature : signMap.signature,// 必填，签名，见附录1
            jsApiList : [ 'scanQRCode' ]
        // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
	});
}

$("#scanQRCode").click(function() {
    wx.scanQRCode({
        // 默认为0，扫描结果由微信处理，1则直接返回扫描结果
        needResult : 1,
        desc : 'scanQRCode desc',
        success : function(res) {
            //扫码后获取结果参数赋值给Input
            var url = res.resultStr;
            //商品条形码，取","后面的
            if(url.indexOf(",")>=0){
                var tempArray = url.split(',');
                var tempNum = tempArray[1];
                $("#id_securityCode_input").val(tempNum);
            }else{
                $("#id_securityCode_input").val(url);
            }
        }
    });
});

function checkBoxNum(){
	var url = "simpleSizeBoxSum.do";
	$.getJSON(url, function(result){
		var numList = result.data;
		paintInfo(numList);
	});
}

function paintInfo(numList){
	var b = 0;
	var m = 0;
	var s = 0;
	for(var i=0;i<numList.length;i++){
		if(numList[i].boxSize == 1)
			b = numList[i].boxSum;
		if(numList[i].boxSize == 2)
			m = numList[i].boxSum;
		if(numList[i].boxSize == 3)
			s = numList[i].boxSum;
	}
	var fieldset = $("#stepSec").empty();
	var html = 
				'<legend><b>2.</b>选择格口</legend>'+
				'<div class="frow">'+
					'<img onclick="openBox(1);" src="img/dgk.png" />'+
					'<span>剩余大格口: <b style="color:#ff0000">'+b+' </b>个</span>'+
				'</div>'+
				'<div class="frow">'+
					'<img onclick="openBox(2);" src="img/zgk.png" />'+
					'<span>剩余中格口: <b style="color:#ff0000">'+m+'</b> 个</span>'+
				'</div>'+
				'<div class="frow">'+
					'<img onclick="openBox(3);" src="img/xgk.png" />'+
					'<span>剩余小格口: <b style="color:#ff0000">'+s+' </b>个</span>'+
				'</div>'+
				'<div class="frow"><a class="prev-step" href="#">上一步</a></div>';
	fieldset.append(html);
	prevStep();
}

function prevStep(){
	$('a.prev-step').click(function(event){
	    event.preventDefault(); 
	    $('.alpha').removeClass("out").addClass("in");
	    $('.beta').removeClass("in").addClass("out");
	  });
}

function openBox(boxSize){
//	alert(boxSize);
	var url = "doRandomOpenBox.do";
	var data = {"boxSize":boxSize};
	$.post(url,data,function(result){
		if(result.message == "开箱成功"){
//			var p = $("#openBox").append(result.message);
			if($('.charlie').hasClass("out")) {
	        $('.charlie').removeClass("out");
			}
			$('.charlie').addClass("in");
		}else{
			alert(result.message);
		}
	});
}

$('a.complete').click(function(){
	var data = {"ordeId":$("#id_securityCode_input").val(),"phone":$("#tell").val()};
	var url = "saveOrder.do";
	$.post(url,data,function(result){
		alert(result.message);
		$("#tell").val('');
		$('.alpha').removeClass("out").addClass("in");
	    $('.beta').removeClass("in").addClass("out");
	    $('.charlie').removeClass("in").addClass("out");
	});
});

$('a.reelect').click(function(event){
	var url = "reelectOpenBox.do";
	$.get(url,function(result){
		alert(reuslt.message);
	});
	event.preventDefault();
	//part3
	if($('.charlie').hasClass("in")) {
        $('.charlie').removeClass("in");
	}
	$('.charlie').addClass("out");
	//part2
	if($('.beta').hasClass("out")) {
        $('.beta').removeClass("out");
      }
      $('.beta').addClass("in");
});





















