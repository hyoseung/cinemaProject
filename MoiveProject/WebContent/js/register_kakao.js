/**
 * 
 */
//핸드폰 번호 입력시 숫자만 가능하게 (backspace 포함)
function showKeyCodePhone(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if( ( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) || (keyID == 8)) return;
	else return false;
}

function result(){
	if(document.getElementsByName('phone')[0].value==""){
		alert("핸드폰번호를 입력해주세요");
		return false;
	}else if(document.getElementsByName('gender')[0].checked==false && document.getElementsByName('gender')[1].checked==false) {
		 alert("성별을 선택하세요");
		 return false;
	}
	return true;
}