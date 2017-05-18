<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sk.movie.movieInfo.MovieInfoVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SeoKyeong Cinema</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/main.css" rel="stylesheet">
<link href="./css/button.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/main.js"></script>
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
	//ArrayList<MovieInfoVO> list = (ArrayList<MovieInfoVO>)request.getAttribute("movieList");
%>

<div class="main-content">
<center>
		<div class="main-video">
			<iframe width="1020" height="615" src="https://www.youtube.com/embed/XYova4AZLb8" frameborder="0" allowfullscreen ></iframe>
		</div>
		<div class="toggle-movie">
			<span id="current-movie" onclick="current_movie()">현재 상영작</span>
			<span id="next-movie" onclick="next_movie()">상영 예정작</span>
		</div>	

<%-- 	<div class="main-movieList">
	<table id="movieList_table">
		<%  
		for(int i=0; i<list.size();i++){ 
		if(i%3==0){
		%>
		<tr>		
		<%} %>
			<td>
			 <span class="main-viewlist">
				<div id="poster"><img src="<%=list.get(i).getMovieImage()%>"/></div> <!-- url을 파라미터받으면 될 듯  -->
				<div id="age_title">
					<span id="rate"><img src="<%=list.get(i).getRateIamge()%>"/></span>
					<span id="title"><%=list.get(i).getMovieName()%></span>
				</div>
				<div id="btn_title">
					<span class="detail-movie" id="<%=list.get(i).getCode()%>" onclick="detail_movie(this)">상세정보</span>
					<span class="reserve-movie" id="<%=list.get(i).getCode()%>" onclick="detail_reserve(this)">
					<input type="hidden" id="ttt" value="<%=list.get(i).getType() %>">
					예매하기
					</span>
				</div>
			</span> 
			</td>
		<%if(i%3==2){%>
		</tr>
		<%}
		} %>
		</table>
	</div> --%>
	<br>
	<div class="main-bottom">
		<span id="adress">서울특별시 성북구 서경로124 서경대학교 북악관 617호</span>
		<span id="telephone">| 전화번호: 02-940-7114</span><br>
		<span id="maker">만든이 찌질이들</span>
	</div>
</center>
</div>
</body>
</html>