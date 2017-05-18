/**
 * 
 */
function logo_btn(){
	location.href="main.sku"; 
}

function login_btn(){
	location.href="login.sku"; 
	
}

function register_btn(){
	//location.href="register.sku"; 
	location.href="registerBefore.sku"; 
}
function movie_btn(){
	location.href="movie.sku"; 
}

function cinema_btn(){
	location.href="cinema.sku"; 
}

function ticket_btn(){
	$.ajax({
		url : "loginState.sku",
		success : function(data){
			if(data=="true"){
				location.href="ticket.sku"; 
			}else{
				alert("로그인 후 이용가능합니다.");
				location.href="login.sku"; 
			}
		}
	});
}

function logout_btn(){
	Kakao.init('7030fc67b3b2c71443851314f53085e2');
	Kakao.Auth.logout(function(){
		location.href="logout.sku"; 
	});
}

function mypage_btn(){
	location.href="mypage.sku"; 
}