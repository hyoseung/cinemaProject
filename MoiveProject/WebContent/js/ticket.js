/**
 * 
 */
var init_cinema_loc = '서울';
var init_movie_id = null;
var init_cinema_id = null;
var list = null;
var time = null;

function seat_btn(){
	if($("#"+init_movie_id).text()=="") {
		alert("영화를 선택해 주세요");
		return false;
	}else if($("#"+init_cinema_id).text()==""){
		alert("영화관을 선택해 주세요");
		return false;
	}else if($('#calendar').val()=="날짜선택"){
		alert("날짜를 선택해 주세요");
		return false;
	}else if(time==null){
		alert("상영시간을 선택해 주세요");
		return false;
	}
	
	$("#movieName").val($("#"+init_movie_id).text());
	$("#theater").val($("#"+init_cinema_id).text());
	$("#viewingDate").val($('#calendar').val());
	$("#viewingTime").val($(':input:radio[name=time_sel]:checked').val());
	$("#viewingCode").val($(':input:radio[name=time_sel]:checked').attr('id'));
	
	return true;
}

//조회
function search_btn(){
	$('#time').empty();
	var r = "";
	
	if(init_movie_id==null){
		alert("영화를 선택해주세요");
	}else if(init_cinema_id==null){
		alert("영화관을 선택해주세요");
	}else if($('#calendar').val()=="날짜선택"){
		alert("날짜를 선택해주세요");
	}else {		
		$.ajax({
			url : "movieTimetable.sku",
			type : "post",
			data : {movieName:$("#"+init_movie_id).text(), theater:$("#"+init_cinema_id).text(),
					viewingDate:$('#calendar').val()
			},
			success : function(data){
				if(data=="null"){
					$('#time').append('<a>상영정보가 없습니다.</a>');
				}else{
					var today = new Date();
					
					list = JSON.parse(data);
					
					$.each(list.timeTable,function(key,value) {
						
						var hour = Number((value.time).split(":")[0]);
						var min = Number((value.time).split(":")[1]);
					    
						var s = true;
						
						var today_date = new Date(); //현재 시간
						var sel_date = new Date($('#calendar').val().split("-")[0],$('#calendar').val().split("-")[1]-1,$('#calendar').val().split("-")[2],hour,min); //선택한 시간
	
						if(today_date < sel_date){
							if(value.person=="50") s= false;
						}else{
							s = false;
						}
						
						//else if((sel_date.getTime() - today_date.getTime()) < 1200000) s = false; 예매 취소할때 사용
						
						if(s==false){
							r += '<div><input type="radio" class="radio_time" name="time_sel" id="'+value.num+'" value="'+value.time+'" disabled><label for="'+value.num+'">'+value.time+'</label></div>';
						}else if(s==true){
							r += '<div><a><input type="radio" class="radio_time" name="time_sel" id="'+value.num+'" value="'+value.time+'">';
							r += '<label for="'+value.num+'">'+value.time+'</label><label id="remain">'+value.person+'/50</label><a></div>';
						}
					});
					
					$('#time').append(r);
					
				}
			}
		});
	}
}


/**
 * 툴팁관련
 * 
r += '<div><a rel="tooltip" title="'+value.person+'/50"><input type="radio" class="radio_time" name="time_sel" id="'+value.num+'" value="'+value.time+'">';
r += '<label for="'+value.num+'">'+value.time+'</label><a></div>';

$(document).on("mouseover", "div[rel=tooltip]", function(){
	var tip = $(this).attr('title');
	alert(tip);
	$(this).attr('title', '');
	$(this).append('<div id="tooltip"><div class="tipBody">'+tip+'</div></div');
	
});

$(document).on("mouseout", "div[rel=tooltip]", function(){
	$(this).attr('title', $('.tipBody').html())
	$(this).children('div#tooltip').remove();
});  
 *  
 */

$(document).on("change", "input[name=time_sel]", function(){
	time="select";
});


//지역
$(document).on("click", ".c_name", function(){
	if(init_cinema_loc != $(this).text()){
		$('#'+init_cinema_loc).css('background-color', '#EFEBDB');
		$('.'+init_cinema_loc).css('visibility', 'hidden');
		
		init_cinema_loc = $(this).text();
		
		$('.'+init_cinema_loc).css('visibility', 'visible');
		
		$('#'+init_cinema_loc).css('background-color', '#F5DD7F');
		
	}
});

//영화관
$(document).on("click", ".cinema_sel", function(){
	if(init_cinema_id==null){
		init_cinema_id = $(this).attr('id');
		$(this).css('background-color', '#F5DD7F');
	}else{
		$('#'+init_cinema_id).css('background-color', '#EFEBDB');
		$(this).css('background-color', '#F5DD7F');
		init_cinema_id = $(this).attr('id');
	}
});



//영화
$(document).on("click", ".movie_sel", function(){
	if(init_movie_id == null){
		init_movie_id = $(this).attr('id');
		$(this).css('background-color', '#F5DD7F');
	}else{
		$('#'+init_movie_id).css('background-color', '#ffffff');
		$(this).css('background-color', '#F5DD7F');
		init_movie_id = $(this).attr('id');
	}
});

//달력
$(function() {
    $( "#calendar" ).datepicker({
    	/*changeMonth: true,*/
/*    	showOn: "both", 
        buttonImage: "./img/calendar.gif", 
        buttonImageOnly: true,*/
    	dateFormat: "yy-mm-dd",
        /*dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],*/
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        minDate: -0, 
        maxDate: "+7D"
    });
});



$(document).ready(function(){	
	$('#서울').css('background-color', '#F5DD7F');
	init_movie_id = $('#init_movie_code').val();
	
	if(init_movie_id=="null") init_movie_id = null;
	else init_check(); //영화 상세정보에서 바로 예매하기 눌렀을 경우
})

function init_check(){
	$('#'+init_movie_id).css('background-color', '#F5DD7F');
}
