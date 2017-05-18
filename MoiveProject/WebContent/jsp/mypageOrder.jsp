<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sk.movie.ticketInfo.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SeoKyeong Cinema</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/mypageOrder.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/mypageOrder.js"></script>
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
	ArrayList<ReservTicketVO> list = (ArrayList<ReservTicketVO>) request.getAttribute("reserveList");

	Date today = new Date(); 
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	transFormat.format(today);
%>
<div class="main-content">
<center>
	<a id="title">예매 내역</a>
	<table class="listTable" id="table">
		<tr class="list_title">
			<td id="list1">예매번호</td><td id="list2">영화</td><td id="list3">영화관</td><td id="list4">상영일</td><td id="list5">예매인원</td><td id="list6">좌석</td><td id="list7">예매일</td><td id="list8">취소</td>
		</tr>
		<%
			for(int i=0; i<list.size(); i++){
				%>
					<tr class="list_content">
					<td><%=list.get(i).getReserveCode() %></td>
					<td><%=list.get(i).getMovieName() %></td>
					<td><%=list.get(i).getTheater() %></td>
					<td><%=list.get(i).getViewingDate() %>&nbsp;<%=list.get(i).getViewingTime() %></td>
					<td><%=list.get(i).getPerson() %>명</td>
					<td><%=list.get(i).getSeat() %></td>
					<td><%=list.get(i).getReserveDate().split(" ")[0] %></td>
					<td>
					<%
						String selectDate_str = list.get(i).getViewingDate()+" "+list.get(i).getViewingTime()+":00";
						Date selectDate = transFormat.parse(selectDate_str);
						
						int compare = today.compareTo(selectDate);
						if(compare < 0){
							%>
							<input type="button" id="<%=list.get(i).getViewingCode() %>" value="취소" onclick="cancel(this)">
							<%
						}
					%>
					</td>
					</tr>
				<%
			}
		%>
	</table>
</center>
</div>
</body>
</html>