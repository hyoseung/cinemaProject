/**
 * 
 */
function cancel(s){
	var table = document.getElementById("table");
	var index = s.parentElement.parentElement.rowIndex;
	
	var today_date = new Date();
	var o = table.rows[index].cells[3].innerHTML;
	var o_date = o.split("&nbsp;")[0];
	var o_time = o.split("&nbsp;")[1];
	
	var sel_date = new Date(o_date.split("-")[0],o_date.split("-")[1]-1,o_date.split("-")[2],o_time.split(":")[0],o_time.split(":")[1]); //선택한 시간
	
	if((sel_date.getTime() - today_date.getTime()) < 1200000) alert("상영 20분 전에는 취소할 수 없습니다.");
	else{
		//삭제 가능
		var myCode = table.rows[index].cells[0].innerHTML;
		var movieCode = s.getAttribute('id');
		
		if (confirm("취소하시겠습니까?") == true)
			location.href="deleteOrder.sku?myCode="+myCode+"&movieCode="+movieCode; 
	}
}