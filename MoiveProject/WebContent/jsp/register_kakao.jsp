<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SeoKyeong Cinema</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/register.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/register_kakao.js"></script>
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
	String kakao_id = (String) request.getAttribute("id");
	String kakao_name = (String) request.getAttribute("name");
%>
<div class="main-content">
<center>
<a>카카오 회원가입</a>
<form method="POST" action="registerKakaoPro.sku" onsubmit="return result();">
	<table>
		<tr>
			<td class="text">이름</td>
			<td><input type="text" name="name" value="<%=kakao_name %>" readonly></td>
		</tr>
		<tr>
			<td class="text">핸드폰번호</td>
			<td><input type="text" name="phone" onkeydown="return showKeyCodePhone(event)" maxLength="11">&nbsp;'-'없이 입력하세요</td>
		</tr>
		<tr>
			<td class="text">성별</td>
			<td><input type="radio" id="gender-check" name="gender" value="M"><label for="gender-check">남자</label>
				<input type="radio" id="gender-check" name="gender" value="F"><label for="gender-check">여자</label></td>
		</tr>
	</table>
	<input type="hidden" name="id" value="<%=kakao_id %>">
	<br>
	<input type="submit" value="가입하기">
</form>
</center>
</div>
</body>
</html>