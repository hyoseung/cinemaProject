/**
 * 
 */
function reg_btn(){
	location.href="register.sku"; 
}

function loginWithKakao() {
	Kakao.init('7030fc67b3b2c71443851314f53085e2');
	
	Kakao.Auth.login({
		success: function(authObj){
			Kakao.API.request({
				url: '/v1/user/me',
				success: function(res){
					Kakao.Auth.logout(function(){
						location.href='register_kakao.sku?id='+res.id+'&name='+res.properties.nickname;
					});
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