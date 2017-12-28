jQuery(document).ready(function(){
  function doStep(){
    // Next Button
    $('a.next-step').click(function(event){
    	
      reg=/^1[3|4|5|7|8][0-9]\d{4,8}$/i;
      if( ($(".phone").val()=="") ||
    		  (!reg.test($(".phone").val())) ||
    		  ($(".phone").val().length<11)){
    	alert("请输入正确的手机号!");
      }else{
    	  event.preventDefault();
          // Part 1
          if($('.alpha').hasClass("in")) {
            $('.alpha').removeClass("in");
          }
          $('.alpha').addClass("out");
          // Part 2
          if($('.beta').hasClass("out")) {
            $('.beta').removeClass("out");
          }
          $('.beta').addClass("in");
      }
    });
    
    // Previous Button
    $('a.prev-step').click(function(event){
      event.preventDefault(); 
      $('.alpha').removeClass("out").addClass("in");
      $('.beta').removeClass("in").addClass("out");
    });
    
    // Submit & Complete
    $('.submit').click(function(event){
      event.preventDefault();
      // Part 1
      if($('.beta').hasClass("in")) {
        $('.beta').removeClass("in");
      }
      $('.beta').addClass("out");
      // Part 2
      if($('.charlie').hasClass("out")) {
        $('.charlie').removeClass("out");
      }
      $('.charlie').addClass("in");
    });
  }
  // Active Functions
  doStep();
});