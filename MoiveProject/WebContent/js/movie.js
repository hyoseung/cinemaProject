/**
 * 
 */

function detail_movie(num){
	var code = document.getElementById(num.getAttribute('id')).getAttribute('id');	
	location.href="detailMovie.sku?code="+code;
}

function detail_reserve(num){
	var type = document.getElementById(num.getAttribute('id')).getAttribute('value');

	$.ajax({
	      url : "loginState.sku",
	      success : function(data){
	         if(data=="true"){
	            var code = document.getElementById(num.getAttribute('id')).getAttribute('id');
	            if(type =="current")
	            	location.href="ticket.sku?code="+code;
	            else
	            	alert("현재 상영작이 아닙니다.");
	         }else{
	            alert("로그인 후 이용가능합니다.");
	            location.href="login.sku"; 
	         }
	      }
	   });
	}
