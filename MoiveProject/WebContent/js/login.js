/**
 * 
 */
function result(){
	if(document.getElementsByName('id')[0].value==""){
		alert("id를 입력해주세요");
		return false;
	}else if(document.getElementsByName('pw')[0].value==""){
		alert("비밀번호를 입력해주세요");
		return false;
	}
	return true;
}

function reg_btn(){
	location.href="register.sku"; 
}

function login_btn(){
	if(result()){
		$.ajax({
			url : "loginPro.sku",
			type : "post",
			data : {id:document.getElementsByName('id')[0].value, pw:document.getElementsByName('pw')[0].value},
			success : function(data){
				if(data=="0"){
					location.href="main.sku"; 
				}else if(data=="1"){
					alert("비밀번호가 맞지 않습니다.");
				}else if(data=="2"){
					alert("정보가 없습니다.");
				}
			}
		});
	}
}

function loginWithKakao() {
	Kakao.init('7030fc67b3b2c71443851314f53085e2');
	
	Kakao.Auth.login({
		success: function(authObj){
			Kakao.API.request({
				url: '/v1/user/me',
				success: function(res){
					location.href='loginKakaoPro.sku?id='+res.id+'&name='+res.properties.nickname;;
				},
				fail: function(error){
					alert(JSON.stringify(error));
				}
			});
		},
		fail: function(err){
			alert(JSON.stringify(err));
		}
    });
};