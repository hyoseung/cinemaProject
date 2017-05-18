<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sk.movie.movieInfo.MovieInfoVO"%>
<%@page import="sk.movie.ticketInfo.TotalCinemaInfoVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SeoKyeong Cinema</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/ticket.css" rel="stylesheet">
<link href="./css/radio_button.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/ticket.js"></script>

<!-- jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  
<!-- http://www.nextree.co.kr/p9887/ 참고사이트 -->

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
	ArrayList<MovieInfoVO> mlist = (ArrayList<MovieInfoVO>)request.getAttribute("movieList");
	ArrayList<TotalCinemaInfoVO> clist = (ArrayList<TotalCinemaInfoVO>)request.getAttribute("cinemaList");
	String init_movie_code = (String)request.getAttribute("code");
%>

<div class="main-content">
<center>
<input type="hidden" id="init_movie_code" value="<%=init_movie_code %>">
<table id="fieldset_table">
	<tr>
	<td>
		<fieldset id="movie_field">
    	<legend>영화</legend>
    	<div id="movie_list">
    		<ul>
    		<% 
    			for(int i=0; i<mlist.size(); i++){
    				%>
    					<li class="movie_sel" id="<%=mlist.get(i).getCode() %>"><%=mlist.get(i).getMovieName() %></li>
    				<%
    			}
    		%>
    		</ul>
    	</div>
		</fieldset>
	</td>
	<td>
		<fieldset id="cinema_field">
    	<legend>영화관</legend>
    	<table id="cinema_list"> 
    		<tr>
    			<td class="c_name" id="서울">서울</td>
    			<td id="cinema_name" rowspan="8">
    			<ul class="서울">
    				<%
    					for(int i=0; i<clist.size(); i++){
    						if(clist.get(i).getcLoc().equals("서울")){
    						%>
    						<li class="cinema_sel" id="<%=clist.get(i).getcName() %>"><%=clist.get(i).getcName() %></li>
    						<%
    						}
    					}
    				%>
    			</ul>
    			
    			<ul class="경기">
    				<%
    					for(int i=0; i<clist.size(); i++){
    						if(clist.get(i).getcLoc().equals("경기")){
    						%>
    						<li class="cinema_sel" id="<%=clist.get(i).getcName() %>"><%=clist.get(i).getcName() %></li>
    						<%
    						}
    					}
    				%>
    			</ul>
    			
    			<ul class="인천">
    				<%
    					for(int i=0; i<clist.size(); i++){
    						if(clist.get(i).getcLoc().equals("인천")){
    						%>
    						<li class="cinema_sel" id="<%=clist.get(i).getcName() %>"><%=clist.get(i).getcName() %></li>
    						<%
    						}
    					}
    				%>
    			</ul>
    			
    			</td>
    		</tr>
    		<tr>
    			<td class="c_name" id="경기">경기</td>
    		</tr>
    		<tr>
    			<td class="c_name" id="인천">인천</td>
    		</tr>
    		<tr><td class="blank"></td></tr>
    		<tr><td class="blank"></td></tr>
    		<tr><td class="blank"></td></tr>
    		<tr><td class="blank"></td></tr>
    		<tr><td class="blank"></td></tr>
    	</table>
		</fieldset>
	</td>
	<td>
		<fieldset id="date_field">
    	<legend>날짜</legend>
    	<p>
    		<!-- <label>월 : <input type="number" id="month" value="4" min="1" max="12"/></label> -->
    		<input type="text" id="calendar" value="날짜선택">
    	    <input type="button" id="search" value="조회" onclick="search_btn()">
    	</p>
    	<div id="time">
    	</div>
		</fieldset>
	</td>
	</tr>
</table>

<form action="ticket_seat.sku" method="post" onsubmit="return seat_btn();">
	<input type="submit" value="좌석선택하기"> <!-- onclick="seat_btn()" -->
	<input type="hidden" id="movieName" name="movieName">
	<input type="hidden" id="theater" name="theater">
	<input type="hidden" id="viewingDate" name="viewingDate">
	<input type="hidden" id="viewingTime" name="viewingTime">
	<input type="hidden" id="viewingCode" name="viewingCode">
</form>
</center>

</div>
</body>
</html>