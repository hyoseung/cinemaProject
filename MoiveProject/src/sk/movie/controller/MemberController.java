package sk.movie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sk.movie.member.MemberDAO;
import sk.movie.member.MemberVO;
import sk.movie.theater.TheaterVO;
import sk.movie.ticketInfo.ReservTicketVO;

@Controller
public class MemberController {
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping("/idConfirm.sku")
	public void IdConfirm(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		String result;
		
		if(dao.idConfirm(id)) result = "true";
		else result = "false";
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
	}
	
	@RequestMapping("/registerPro.sku")
	public String RegisterPro(MemberVO vo, HttpServletRequest request){
		if(dao.registerMember(vo)){
			System.out.println("가입 성공");
			request.setAttribute("newMember", vo);
		}
		return "registerSuccess";
	}
	
	@RequestMapping("/registerKakaoPro.sku")
	public String RegisterKakaoPro(MemberVO vo, HttpServletRequest request){
		if(dao.registerKakaoMember(vo)){
			System.out.println("가입 성공");
			request.setAttribute("newMember", vo);
		}
		return "registerKakaoSuccess";
	}
	
	@RequestMapping("/loginPro.sku")
	public void LoginPro(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		int result = dao.login(id, pw);
		if(result==0){
			HttpSession session = request.getSession();
			session.setAttribute("loginId", id);
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(String.valueOf(result));
		out.close();
	}
	
	@RequestMapping("/loginKakaoPro.sku")
	public String LoginKakaoPro(HttpServletRequest request){
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int result = dao.loginKakao(id);
		if(result==0){
			HttpSession session = request.getSession();
			session.setAttribute("loginId", id);
			return "redirect:/main.sku";
		}
		else{
			return "redirect:/register_kakao.sku?id="+id+"&name="+name;
		}
	}
	
	@RequestMapping("/upRegister.sku")
	public String UpdateRegister(HttpServletRequest request){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginId");
		ArrayList<MemberVO> list = dao.updateRegister(id);
		
		request.setAttribute("list",list);
		return "upRegister";	
	}
	
	@RequestMapping("/updateStart.sku")
	public void UpdateStart(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		String id = request.getParameter("id"); 
		String pw = request.getParameter("pw");
		String phone = request.getParameter("phone");
		dao.upRegister(id, pw, phone);
		response.setContentType("text/html; charset=UTF-8");
		session.invalidate();
		response.sendRedirect("main.sku");	
	}
	
	@RequestMapping("/mypageOrder.sku")
	public String MypageOrder(HttpServletRequest request){
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginId");
		
		ArrayList<ReservTicketVO> list = new ArrayList<ReservTicketVO>();
		list = dao.loadReserve(id);
		
		request.setAttribute("reserveList",list);
		return "mypageOrder";	
	}
	
	@RequestMapping("/deleteOrder.sku")
	public String DeleteOrder(HttpServletRequest request){
		String myCode = request.getParameter("myCode");
		String movieCode = request.getParameter("movieCode");
		
		dao.deleteOrder(myCode,movieCode);
		return "redirect:/mypageOrder.sku";
	}
}
