<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SeoKyeong Cinema</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/mypage.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/mypage.js"></script>
</head>

<body>
<%
	String id = (String)session.getAttribute("loginId");
	String name = (String)request.getAttribute("name");
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

<div class="main-content">
<center>
	<div class="mypage-first">
		<center>
			<img class="mypage-image"src="img/mypage.jpg">
			<span class="mypage-text">MY PAGE</span>
		</center>
	</div>
	<div class="mypage-second">
		<center><span id="id"><%=name%></span><span>님&nbsp;&nbsp;환영합니다.</span></center>
	</div>
	<div class="mypage-">
			<span class="mypage-second-confirm" onclick="order()"><img src="img/mypage_1.PNG"></span>
			<span class="mypage-second-update" id="<%=id %>" onclick="change(this)"><img src="img/mypage_2.PNG"></span>
	</div>
</center>
</div>
</body>
</html>