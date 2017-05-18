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

import sk.movie.content.ContentDAO;
import sk.movie.content.ContentVO;
import sk.movie.movieInfo.MovieInfoDAO;
import sk.movie.movieInfo.MovieInfoVO;

@Controller
public class MovieController {
	@Autowired
	private MovieInfoDAO dao;
	@Autowired
	private ContentDAO cdao;
	
	@RequestMapping("/loadMainMovie.sku")
	public String LoadMainMovie(HttpServletRequest request){
		ArrayList<MovieInfoVO> list=null;
		MovieInfoVO vo = new MovieInfoVO();
		String type=request.getParameter("type");
		list = dao.loadMainMovie(type);
		request.setAttribute("movieList",list);
		//넘겨온 값을 받아온다.
		return "main";
	}
	@RequestMapping("/detailMovie.sku")
	public String DetailMovie(HttpServletRequest request){
		ArrayList<MovieInfoVO> list = null;
		MovieInfoVO vo = new MovieInfoVO();
		String codeValue = request.getParameter("code");
		int code = Integer.parseInt(codeValue);
		list = dao.detailMovie(code);
		request.setAttribute("detailMovie", list);
	
		ArrayList<ContentVO> clist = new ArrayList<ContentVO>();
		clist = cdao.oneline(code);
		
		request.setAttribute("clist", clist);
		return "detailView";
	}
		@RequestMapping("/contentline.sku")
	public void loadContent(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ContentVO vo = null;
		ArrayList<ContentVO> clist = new ArrayList<>();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginId");
		int code = Integer.parseInt(request.getParameter("code"));
		String text = request.getParameter("text");
		cdao.insertLine(id, code, text);
		PrintWriter out = response.getWriter();
		out.print("true");
		out.close();
		
		
	}
	@RequestMapping("/delcontent")
	public void delContent(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int num = Integer.parseInt(request.getParameter("vv"));
		cdao.deleteLine(num);
		String rr="true";
		PrintWriter out = response.getWriter();
		out.print(rr);
		out.close();
	}
	
}
