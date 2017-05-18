package sk.movie.ticketInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import sk.movie.DB.DBUtil;
import sk.movie.movieInfo.MovieInfoVO;

@Repository
public class ReservTicketDAO {
	
	Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;

    public ReservTicketDAO() {}
    
    public String loadMovieTime(ReservTicketVO vo){
    	String result = null;
    	
		sql = "select num, viewingtime, person from timetable where theater=? and moviename=? and viewingdate=? order by viewingtime asc";
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTheater());
			pstmt.setString(2, vo.getMovieName());
			pstmt.setString(3, vo.getViewingDate());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = "{\"timeTable\":["
				        + "{\"num\":\""+rs.getString(1)+"\","
				        + "\"time\":\""+rs.getString(2)+"\","
				        + "\"person\":\""+rs.getString(3)+"\""
				        + "}";
				
				while(rs.next()){
					result += ",{\"num\":\""+rs.getString(1)+"\","
					        + "\"time\":\""+rs.getString(2)+"\","
					       	+ "\"person\":\""+rs.getString(3)+"\""
					        + "}";
				}
				result+= "]}";
				
				System.out.println(vo.getMovieName()+" "+vo.getTheater()+" "+vo.getViewingDate() + "상영시간 : ");
				System.out.println(result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
           DBUtil.close(conn, pstmt, rs);
        }
		return result;
	}
    
    public ArrayList<MovieInfoVO> loadTicketMovie() { 
		ArrayList<MovieInfoVO> list = new ArrayList<MovieInfoVO>();
		MovieInfoVO vo = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select moviename,rateimage,code from movieinfo where type=? order by opendate desc");
			pstmt.setString(1, "current");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new MovieInfoVO();
				vo.setMovieName(rs.getString(1));
				vo.setRateIamge(rs.getString(2));
				vo.setCode(rs.getInt(3));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);

		}
		return list;
	}
    
    public ArrayList<TotalCinemaInfoVO> loadTicketCinema() { 
		ArrayList<TotalCinemaInfoVO> list = new ArrayList<>();
		TotalCinemaInfoVO vo = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("Select theater, local From cinema");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new TotalCinemaInfoVO();
				vo.setcName(rs.getString(1));
				vo.setcLoc(rs.getString(2));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);

		}
		return list;
	}
    
    public String seatSearch(String num) { 
    	String result = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("Select seatno From seat Where num=? and search='F'");
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = "{\"seat\":["
				        + "{\"num\":\""+rs.getString(1)+"\"}";
				while(rs.next()){
					result += ",{\"num\":\""+rs.getString(1)+"\"}";    
				}
				result+= "]}";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);

		}
		return result;
	}
    
    public String personChek(String num) { 
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("Select person From timetable Where num=?");
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()){
				if(rs.getString(1).equals("50")) return "false";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);

		}
		return "true";
	}
    
	public String searchName(String id){
		String result = null;
		
		sql = "select name from member where id=?";
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
    public void insertReserve(ReservTicketVO vo){
    	//예매내역 등록
    	sql = "insert into reservationdetails values(reservation_seq.nextval,?,?,?,?,?,?,?,?,sysdate)";
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getViewingCode());
			pstmt.setString(3, vo.getTheater());
			pstmt.setString(4, vo.getMovieName());
			pstmt.setString(5, vo.getViewingDate());
			pstmt.setString(6, vo.getViewingTime());
			pstmt.setString(7, vo.getPerson());
			pstmt.setString(8, vo.getSeat());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
           DBUtil.close(conn, pstmt);
        }
		
		//인원 추가
    	sql = "update timetable set person=person+? where num=?";
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(vo.getPerson()));  
			pstmt.setString(2, vo.getViewingCode());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
           DBUtil.close(conn, pstmt);
        }
		
		//좌석
		String[] array = vo.getSeat().split(" ");
	    for(int i=0; i<array.length; i++){
	    	sql = "update seat set search='F' where num=? and seatno=?";
			
			try {
				conn = DBUtil.getConnection();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getViewingCode());
				pstmt.setString(2, array[i]);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
	           DBUtil.close(conn, pstmt);
	        }
	    }
		
    }
}
