package sk.movie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sk.movie.theater.TheaterDAO;
import sk.movie.theater.TheaterVO;

@Controller
public class CinemaController {
	@Autowired
	private TheaterDAO dao;
	
	@RequestMapping("/cinemaInfo.sku")
	public void cinemaInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		ArrayList<TheaterVO> list = new ArrayList<TheaterVO>();
		list = dao.cinemaInfo(request.getParameter("name"));
		String image = list.get(0).getImage();
		String address = list.get(0).getAddress();
		String phone = list.get(0).getPhone();
		String location = list.get(0).getLocation();
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(image+"?");
		out.print(address+"?");
		out.print(phone+"?");
		out.print(location);
		out.close();
		
	}
}
