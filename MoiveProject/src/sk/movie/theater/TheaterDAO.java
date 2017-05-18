package sk.movie.theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import sk.movie.DB.DBUtil;

@Repository
public class TheaterDAO {
	DBUtil dbUtil;
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	public ArrayList<TheaterVO> local(){
		ArrayList<TheaterVO> list = new ArrayList<>();
		TheaterVO vo = null;
		
		try{
			conn = dbUtil.getConnection();
			pst = conn.prepareStatement("select distinct local from cinema");
			rs = pst.executeQuery();
			while(rs.next()){
				vo = new TheaterVO();
				vo.setLocal(rs.getString(1));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbUtil.close(conn, pst, rs);
		}
		return list;
	}
	
	public ArrayList<TheaterVO> theaterName(String local){
		ArrayList<TheaterVO> list = new ArrayList<>();
		TheaterVO vo = null;
		
		try{
			conn = dbUtil.getConnection();
			pst = conn.prepareStatement("select theater from cinema where local=?");
			pst.setString(1, local);
			rs = pst.executeQuery();
			while(rs.next()){
				vo = new TheaterVO();
				vo.setTheater(rs.getString(1));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbUtil.close(conn, pst, rs);
		}
		return list;
	}
	
	public ArrayList<TheaterVO> cinemaInfo(String theater){
		ArrayList<TheaterVO> list = new ArrayList<>();
		TheaterVO vo = null;
		
		try{
			conn = dbUtil.getConnection();
			pst = conn.prepareStatement("select image,address,phone,location from cinema where theater=?");
			pst.setString(1, theater);
			rs = pst.executeQuery();
			while(rs.next()){
				vo = new TheaterVO();
				vo.setImage(rs.getString(1));
				vo.setAddress(rs.getString(2));
				vo.setPhone(rs.getString(3));
				vo.setLocation(rs.getString(4));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbUtil.close(conn, pst, rs);
		}
		return list;
	}
}
