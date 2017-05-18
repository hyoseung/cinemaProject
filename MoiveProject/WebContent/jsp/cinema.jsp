<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="sk.movie.theater.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SeoKyeong Cinema</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/cinema.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/cinema.js"></script>
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
	TheaterDAO dao = new TheaterDAO();
	ArrayList<TheaterVO> localList = (ArrayList<TheaterVO>)request.getAttribute("localList");
	
	
%>
<div class="main-content">
	<nav class="topMenu">
		<ul>
		<%for(int i=0; i<localList.size(); i++){ %>
			<li class="topMenuLi">
				<a class="menuLink"><%=localList.get(i).getLocal() %></a>
				<ul class="submenu">
					<%
						ArrayList<TheaterVO> theaterList = dao.theaterName(localList.get(i).getLocal());
						for(int j=0 ; j<theaterList.size() ; j++ ){ 
					%>
					<li><a class="submenuLink longLink" id="<%=theaterList.get(j).getTheater()%>" onclick="select_cinema(this)"><%=theaterList.get(j).getTheater() %></a></li>
				<%} %>
				</ul>
			</li>
			<%} %>
		</ul>
	</nav><br>
	<div>
		<h1 class="cinema-theater">==================THEATER==================</h1>
	</div>
	<div id="mainimg"><center><img src="img/cinema/seongshin.jpg"/></center></div>
	<div class="cinema-info">
		<center>
			주소: <span id="address">서울특별시 성북구 동선동 1가 1-2 10~12층 </span>
			/ 전화번호: <span id="phone">1544-1122</span>
		</center>
	</div>
	<div><center><h1 class="map">찾아오시는 길</h1></center></div>
	<div id="imageLocation">
		<center><img  class="cinema-image" src="img/cinema/seongshinLocation.jpg"/></center>
	</div>
</div>
</body>
</html>