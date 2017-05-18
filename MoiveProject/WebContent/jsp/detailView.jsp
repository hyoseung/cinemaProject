<%@page import="sk.movie.movieInfo.MovieInfoVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sk.movie.content.ContentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화상세보기</title>
<link href="./css/detailview.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/button.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/detailView.js"></script>

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
	ArrayList<MovieInfoVO> list = (ArrayList<MovieInfoVO>)request.getAttribute("detailMovie");
%>
<div class="main-content">
	<table class="detail-content">
	<tr>
		<td><span class="detail-movieimgae"><img src="<%=list.get(0).getMovieImage()%>"></span></td>
		<td class="second-td"><span class="detail-span">
			<div class="detail-rt">
				<span><img class="detail-rate" src="<%=list.get(0).getRateIamge()%>" /></span>
				<span class="detail-title"> <%=list.get(0).getMovieName() %></span>
			</div>
			<div class="detail-opendate">개봉일: <%=list.get(0).getOpenDate()%></div>
			<div class="detail-genre"> 장르: <%=list.get(0).getGenre()%></div>
			<div class="detail-runtime"> 상영시간: <%=list.get(0).getRuntime()%>분</div>
			<div class="detail-director"> 감독: <%=list.get(0).getDirector()%></div>
			<div class="detail-actor"> 주연: <%=list.get(0).getActor()%></div>
			<div class="detail-story">
				<div class="">줄거리</div>
				<div class="detail-story-second"><%=list.get(0).getStory() %></div>
			</div>
			<div class="detail-reserve">
				<div class="reserve-movie" id="<%= list.get(0).getCode()%>" onclick="reserve(this)">예매하기</div>
				<input type="hidden" id="ttt" value="<%=list.get(0).getType() %>">
			</div>
		</span></td>
	</tr>
	</table>
</div>
<% ArrayList<ContentVO> clist = (ArrayList<ContentVO>)request.getAttribute("clist"); %>
<div  class="line">
	<span id="oneline">한줄평</span>
	<span></span>
</div>
<center>
	<table>
	<tr>
		<td><textarea name="textarea" rows=5 cols="100" placeholder="로그인 후 입력가능합니다."></textarea></td>
		<td><span class="Enrollment" id="<%= list.get(0).getCode()%>" onclick="enrollment(this)" >등록</span></td>
	</tr>
	</table >

	<div class="l">	
	<table id="table">
	<% if(clist.size()!=0){
		for(int i=0; i<clist.size();i++){
			String date =clist.get(i).getDate();
			String[] dateValue = date.split(" "); 
	%>
	<tr>
		<td class="user-f"><%= clist.get(i).getId() %></td>
		<td class="user-s"><%=dateValue[0] %></td>
		<td class="comment"><%=clist.get(i).getContent() %></td>
		<%
		if(clist.get(i).getId().equals(id)) {%>
		<td class="del" id="<%=clist.get(i).getCount()%>" onclick="del(this)">삭제</td>
		<%} %>
	</tr>	
		<%}} else%>
		<h3>등록된 댓글이 없습니다.</h3>
	</table>
	</div> 
</center>
</body>
</html>