<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="sk.movie.ticketInfo.ReservTicketVO"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SeoKyeong Cinema</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/ticket_seat.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/ticket_seat.js"></script>
</head>

<body>
<%
	String id = (String)session.getAttribute("loginId");
%>
<div class="main-menu-box">
	<div class="main-small-menu">
		<ul>
		<% 
			if(id==null){
				%>
				<li id="login-btn" onclick="login_btn()">로그인</li>
				<li id="register-btn" onclick="register_btn()">회원가입</li>
				<% 
			}else{
				%>
				<li id="logout-btn" onclick="logout_btn()">로그아웃</li>
				<li id="mypage-btn" onclick="mypage_btn()">마이페이지</li>
				<% 
			}
		%>
		</ul>
	</div>
	<div class="main-menu">
		<div><a onclick="logo_btn()">SeoKyeong Cinema</a></div>
		<ul>
			<li id="movie-btn" onclick="movie_btn()">영화</li>
			<li id="cinema-btn" onclick="cinema_btn()">극장</li>
			<li id="ticket-btn" onclick="ticket_btn()">예매</li>
		</ul>
	</div>
</div>
<%
	ReservTicketVO vo = (ReservTicketVO) request.getAttribute("reservInfo");
%>
<div class="main-content">
<center>
    <table id="select_info_table">
    	<tr class="select_info_title">
    		<td id="one">영화이름</td>
    		<td id="two">극장</td>
    		<td id="three">일시</td>
    		<td id="four">시간</td>
    		<td id="five">좌석</td>
    	</tr>
    	<tr class="select_info_content">
    		<td><%=vo.getMovieName() %></td>
    		<td><%=vo.getTheater() %></td>
    		<td><%=vo.getViewingDate() %></td>
    		<td><%=vo.getViewingTime() %></td>
    		<td id="seat_result"></td>
    		</tr>
    </table>
    
<fieldset id="select_seat_info">
<legend>좌석선택</legend>
<div>
	<span><label>성인 : <input type="number" id="adult_num" value="0" min="0" max="4"/></label></span>
	<span><label>청소년 : <input type="number" id="child_num" value="0" min="0" max="4"/></label></span>
</div>
	<table id="selct_seat">		
		<tr><td id="screen" colspan="10">screen</td></tr>
		<tr><td width="45" height="45"></td></tr>
	<%
		for(int i=1; i<=5; i++){
			%>
			<tr>
			<%
				for(int j=1; j<=10; j++){
					String seatNum = Character.toString((char)(i+64));
					seatNum+=j;
					%>
					<td class="seat_td" id="td_<%=seatNum %>">
						<input type="checkbox" class="seat" name="checkbox_seat" id="<%=seatNum %>"><label for="<%=seatNum %>"><%=seatNum %></label>
					</td>
					<%
				}
			%>
			</tr>
			<%
		}
	%>
	</table>
</fieldset>	
<form action="ticket_success.sku" method="post" onsubmit="return check_btn();">
<input type="button" value="다시 선택하기" onclick="re_select()">
<input type="submit" value="결제하기" >
<input type="hidden" name="movieName" value="<%=vo.getMovieName() %>">
<input type="hidden" name="theater" value="<%=vo.getTheater() %>">
<input type="hidden" name="viewingDate" value="<%=vo.getViewingDate() %>">
<input type="hidden" name="viewingTime" value="<%=vo.getViewingTime() %>">
<input type="hidden" name="viewingCode" value="<%=vo.getViewingCode() %>">
<input type="hidden" name="seat" id="last_seat">
<input type="hidden" name="person" id="total_person">
</form>
</center>
</div>
</body>
</html>