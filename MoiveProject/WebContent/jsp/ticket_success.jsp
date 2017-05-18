<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="sk.movie.ticketInfo.ReservTicketVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SeoKyeong Cinema</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/ticket_success.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/ticket_success.js"></script>
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
	String name = (String) request.getAttribute("name");
%>
<div class="main-content">
<center>
	<a id="title">예매가 완료 되었습니다.</a>
	<table>
		<tr>
			<td class="text">예매자</td>
			<td><%=name %></td>
		</tr>
		<tr>
			<td class="text">영화</td>
			<td><%=vo.getMovieName() %></td>
		</tr>
		<tr>
			<td class="text">영화관</td>
			<td><%=vo.getTheater() %></td>
		</tr>
		<tr>
			<td class="text">상영일</td>
			<td><%=vo.getViewingDate() %>&nbsp;<%=vo.getViewingTime() %></td>
		</tr>
		<tr>
			<td class="text">예매인원</td>
			<td><%=vo.getPerson() %></td>
		</tr>
		<tr>
			<td class="text">좌석</td>
			<td><%=vo.getSeat() %></td>
		</tr>
	</table>
	<div id="t">**예매 취소는 마이페이지에서 해주세요</div>
	<input type="button" value="메인화면으로 돌아가기" onclick="return_btn()">
</center>
</div>
</body>
</html>