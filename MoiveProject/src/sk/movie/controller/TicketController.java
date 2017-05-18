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

import sk.movie.movieInfo.MovieInfoDAO;
import sk.movie.movieInfo.MovieInfoVO;
import sk.movie.ticketInfo.ReservTicketDAO;
import sk.movie.ticketInfo.ReservTicketVO;

@Controller
public class TicketController {
	@Autowired
	private MovieInfoDAO Mdao;
	
	@Autowired
	private ReservTicketDAO Rdao;
	
	@RequestMapping("/movieTimetable.sku")
	public void MovieTimetable(ReservTicketVO vo, HttpServletResponse response) throws IOException{
		//String list = dao.loadCinema();
		String list=null;
		list = Rdao.loadMovieTime(vo);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(list);
		out.close();
	}
	
	@RequestMapping("/ticket_seat.sku")
	public String Ticket_seat(ReservTicketVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String state = (String) session.getAttribute("loginId");
		if(state==null) return "redirect:/login.sku"; 
		
		request.setAttribute("reservInfo",vo);
		return "ticket_seat";
	}
	
	@RequestMapping("/ticket_success.sku")
	public String Ticket_successt(ReservTicketVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String state = (String) session.getAttribute("loginId");
		if(state==null) return "redirect:/login.sku"; 
		
		vo.setId(state);
		
		Rdao.insertReserve(vo);
		
		String name = Rdao.searchName(vo.getId());
		
		request.setAttribute("name",name);
		request.setAttribute("reservInfo",vo);
		
		return "ticket_success";
	}
	
	@RequestMapping("/seatSearch.sku")
	public void SeatSearch(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//String list = dao.loadCinema();
		String list=null;
		list = Rdao.seatSearch((String)request.getParameter("num"));
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(list);
		out.close();
	}
	
	@RequestMapping("/personCheck.sku")
	public void PersonCheck(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String result="true";
		result = Rdao.personChek((String)request.getParameter("num"));
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
	}
}
