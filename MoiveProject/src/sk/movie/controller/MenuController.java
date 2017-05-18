package sk.movie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sk.movie.member.MemberDAO;
import sk.movie.movieInfo.*;
import sk.movie.theater.TheaterDAO;
import sk.movie.theater.TheaterVO;
import sk.movie.ticketInfo.ReservTicketDAO;
import sk.movie.ticketInfo.TotalCinemaInfoVO;

@Controller
public class MenuController {
	@Autowired
	private MovieInfoDAO dao;
	@Autowired
	private TheaterDAO tdao;
	@Autowired
	private ReservTicketDAO Rdao;
	@Autowired
	private MemberDAO mdao;
	
	@RequestMapping("/main.sku")
	public String Main(HttpServletRequest request){
		/*ArrayList<MovieInfoVO> list=null;
		MovieInfoVO vo = new MovieInfoVO();		
		list = dao.loadMainMovie("current");
		request.setAttribute("movieList",list);*/
		return "main";
	}
	
	@RequestMapping("/login.sku")
	public String Login(){
		return "login";
	}
	
	@RequestMapping("/registerBefore.sku")
	public String RegisterBefore(){
		return "register_before";
	}
	
	@RequestMapping("/register_kakao.sku")
	public String RegisterKakao(HttpServletRequest request){
		request.setAttribute("id",request.getParameter("id"));
		request.setAttribute("name",request.getParameter("name"));
		return "register_kakao";
	}
	
	@RequestMapping("/register.sku")
	public String Register(){
		return "register";
	}
	
	@RequestMapping("/movie.sku")
	public String Movie(HttpServletRequest request){
		ArrayList<MovieInfoVO> list=null;
		MovieInfoVO vo = new MovieInfoVO();		
		list = dao.loadMainMovie();
		request.setAttribute("movieList",list);
		return "movie";
	}
	
	@RequestMapping("/cinema.sku")
	public String Cinema(HttpServletRequest request){
		ArrayList<TheaterVO> locallist=null;
		locallist = tdao.local();
		request.setAttribute("localList", locallist);
		return "cinema";
	}
	
	@RequestMapping("/ticket.sku")
	public String Ticket(HttpServletRequest request){
		String code = request.getParameter("code");
		ArrayList<MovieInfoVO> mList = null;
		ArrayList<TotalCinemaInfoVO> cList = null;
		
		mList = Rdao.loadTicketMovie();
		cList = Rdao.loadTicketCinema();
		
		request.setAttribute("movieList",mList);
		request.setAttribute("cinemaList",cList);
		request.setAttribute("code", code);
		return "ticket";
	}
	
	@RequestMapping("/mypage.sku")
	public String Mypage(HttpServletRequest request){
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginId");
		
		String name = mdao.searchName(id);
		
		request.setAttribute("name",name);
		return "mypage";
	}
	
	@RequestMapping("/logout.sku")
	public String Logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/main.sku";
	}
	
	@RequestMapping("/loginState.sku")
	public void LoginState(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		String state = (String) session.getAttribute("loginId");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(state==null) out.print("false");
		else out.print("true");
		
		out.close();
	}
	
	
}
