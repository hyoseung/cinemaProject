/**
 * 
 */
function reserve(num) {
	var type = document.getElementById("ttt").value;
	$.ajax({
		url : "loginState.sku",
		success : function(data) {
			if (data == "true") {
				var code = document.getElementById(num.getAttribute('id'))
						.getAttribute('id');
				if (type == "current")
					location.href = "ticket.sku?code=" + code;
				else
					alert("현재 상영작이 아닙니다.");
			} else {
				alert("로그인 후 이용가능합니다.");
				location.href = "login.sku";
			}
		}
	});
}

function enrollment(num) {
	var code = document.getElementById(num.getAttribute('id')).getAttribute(
			'id');
	var text = document.getElementsByName('textarea')[0].value
	$.ajax({
		url : "loginState.sku",
		success : function(data) {
			if(data=="true"){
			if (text == "") {
				alert("한줄평을 입력해주세요.");
				return false;
			} 
			else {
				$.ajax({
					url : "contentline.sku",
					data : {
						code : code,
						text : text
					},
					success : function(content) {
							alert("등록되었습니다.");
							location.reload(true);
							return true;
						}
					})
				}
			}
			else{
				alert("로그인 후 이용가능합니다.");
				location.href = "login.sku";
			}
		}
	})
}
function del(num) {
	var table = document.getElementById("table");
	var index = num.parentElement.parentElement.rowIndex;
	var value = document.getElementById(num.getAttribute('id')).getAttribute(
			'id');
	$.ajax({
		url : "delcontent.sku",
		data : {
			vv : value
		},
		success : function(rr) {
			alert("삭제되었습니다.");
			table.deleteRow(index);
		}
	});

}