/**
 * 
 */
var adult = 0;
var child = 0;
var total = 0;
var sel_num = 0;

var can = true;
$(document).on("click", "input:checkbox", function(){
	var id = $(this).attr('id');
	
	if(total == "0"){
		alert("인원을 먼저 선택해주세요");
		$(this).attr('checked',false);
	}else {
		if ( $(this).prop('checked') ) { //체크함
			if(total == sel_num){
				$(this).attr('checked',false);
			}else{
				sel_num++;
				$('#td_'+id).css('background-color', '#F5DD7F');
			}
		}else { 
			sel_num--;
			$('#td_'+id).css('background-color', '#EFEBDB');
		}
		
		check_num();
	}

});


function check_num(){
	$('#seat_result').empty();
	var result = "<a>";
	var last = "";
	$('input[name="checkbox_seat"]:checkbox').each(function() {
		if($(this).is(":checked")){
			result += $(this).attr('id') + " ";
			last += $(this).attr('id') + " ";
		}
	});
	result += "</a>";
	$('#seat_result').append(result);
	$('#last_seat').val(last);
}

$(document).on("change", "#adult_num", function(){
	adult = $("#adult_num").val();
	child = $("#child_num").val();
	total = Number(adult) + Number(child);
});

$(document).on("change", "#child_num", function(){
	adult = $("#adult_num").val();
	child = $("#child_num").val();
	total = Number(adult) + Number(child);
});


function re_select(){
	location.href="ticket.sku"; 
}

function check_btn(){
	if(total == 0){
		alert("인원을 선택해 주세요");
		return false;
	}
	if(total!=sel_num){
		alert("좌석을 선택해 주세요");
		return false;
	}
	if($('#last_seat').val()=="") return false;
	
	
	if(can==true){
		$('#total_person').val(total);
		var price = Number(adult)*10000 + Number(child)*8000;
		var str = "========== 결제창 ==========\n";
		str += "어 른 : " + Number(adult) + "명 * 10,000원 = " + (Number(adult)*10000) + "원\n";
		str += "청소년 : " + Number(child) + "명 *  8,000원 = " + (Number(child)*10000) + "원\n";
		str += "총 " + price + "원 결제 하시겠습니까?";
		
		if (confirm(str) == true) return true;
		else return false;
	}
	else {
		alert("결제 할 수 없습니다.\n다시 예매해주세요.")
		setTimeout(function() {
			location.href="ticket.sku"; 
		}, 1000);
	}
	
	return can;
}


$(document).ready(function(){	
	//예약하는 중에 이미 50명이 되었을 경우 판단 결제 못하게 막아둠
	//좌석 아직 해결 못함
	setInterval(function() {
		
		$.ajax({
			url : "seatSearch.sku",
			type : "post",
			data : {num:$("input[name=viewingCode]").attr("value")},
			success : function(data){
				if(data!="null"){
					list = JSON.parse(data);
					$.each(list.seat,function(key,value) { 
						if ( $('#'+value.num).prop('checked') ){
							$('#'+value.num).attr('checked',false);
							sel_num--;
						}
						$('#'+value.num).attr("disabled",true);
						$('#td_'+value.num).css('background-color', '#DDDDDD');
					});
				}
			}
		});
		
		$.ajax({
			url : "personCheck.sku",
			type : "post",
			async: false,
			data : {num : $('input[name="viewingCode"]').val()},
			success : function(data){
				if(data=="true"){
					can = true;
				}
				else if(data=="false"){
					can = false;	
				}
			}
		});
	}, 500);
})

