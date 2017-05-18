/**
 * 
 */
var r = false;

function id_confirm(){
	var input_id = document.getElementById("input_id").value;
	if(input_id!=""){
		$.ajax({
			url : "idConfirm.sku",
			type : "post",
			data : {id:input_id},
			success : function(data){
				if(data=="true"){
					r = true;
					document.getElementById("id_result").innerHTML = "사용가능합니다.";
				}
				else{
					r = false;
					document.getElementById("id_result").innerHTML = "사용불가능합니다.";
				}
			}
		});
	}
}

//핸드폰 번호 입력시 숫자만 가능하게 (backspace 포함)
function showKeyCodePhone(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if( ( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) || (keyID == 8)) return;
	else return false;
}

//id,pw입력 시 space는 입력못하게 하는 기능
function showKeyCode(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if(keyID==32) return false;
	else return;
}

function result(){
	if(document.getElementsByName('name')[0].value==""){
		alert("이름을 입력해주세요");
		return false;
		
	}else if(document.getElementsByName('id')[0].value==""){
		alert("id를 입력해주세요");
		return false;
	}else if(document.getElementsByName('pw')[0].value==""){
		alert("비밀번호를 입력해주세요");
		return false;
	}else if(document.getElementsByName('phone')[0].value==""){
		alert("핸드폰번호를 입력해주세요");
		return false;
	}else if(document.getElementsByName('gender')[0].checked==false && document.getElementsByName('gender')[1].checked==false) {
		 alert("성별을 선택하세요");
		 return false;
	}else if(r==false){
		alert("id중복검사를 해주세요");
		return false;
	}
	return true;
}
function updateResult(){
	
	if(document.getElementsByName('pw')[0].value==""){
		alert("비밀번호를 입력해주세요");
		return false;
	}else if(document.getElementsByName('phone')[0].value==""){
		alert("핸드폰번호를 입력해주세요");
		return false;
	}
	
		return true;
}
