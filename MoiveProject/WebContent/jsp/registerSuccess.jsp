<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sk.movie.member.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SeoKyeong Cinema</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/registerSuccess.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/registerSuccess.js"></script>
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
	MemberVO m = (MemberVO)request.getAttribute("newMember");
%>

<div class="main-content">
<center>
<a>가입성공</a>
	<table>
		<tr>
			<td class="text">이름</td>
			<td><%=m.getName() %></td>
		</tr>
		<tr>
			<td class="text">아이디</td>
			<td><%=m.getId() %></td>
		</tr>
		<tr>
			<td class="text">비밀번호</td>
			<td><%=m.getPw() %></td>
		</tr>
		<tr>
			<td class="text">핸드폰번호</td>
			<td><%=m.getPhone() %></td>
		</tr>
		<tr>
			<td class="text">성별</td>
			<td><%
				if(m.getGender().equals("M")){
					%>남자<%
				}else{
					%>여자<%
				}
			%></td>
		</tr>
	</table>
	<br>
	<input type="button" value="로그인하기" onclick="login_btn()">
</center>
</div>
</body>
</html>