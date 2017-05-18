package sk.movie.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import sk.movie.DB.DBUtil;
import sk.movie.movieInfo.MovieInfoVO;
import sk.movie.ticketInfo.ReservTicketVO;

@Repository
public class MemberDAO {

	Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;
    
	public MemberDAO(){}
	
	public boolean idConfirm(String id){
		try {
			conn = DBUtil.getConnection();
			//출력
			sql = "Select * From member Where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("id 있음");
				return false;
			}
			else {
				System.out.println("id 없음");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	           DBUtil.close(conn, pstmt, rs);
	    }
		return true;
	}
	
	public boolean registerKakaoMember(MemberVO vo){
		sql = "insert into member(id, name, phone, gender, point) values(?,?,?,?,1)";
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getGender());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
           DBUtil.close(conn, pstmt);
        }
		return true;
	}
	
	public boolean registerMember(MemberVO vo){
		sql = "insert into member values(?,?,?,?,?,0)";
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getGender());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
           DBUtil.close(conn, pstmt);
        }
		return true;
	}
	
	public int login(String id, String pw){
		sql = "Select * From member where id=?";
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String dbid = rs.getString(1);
				String dbpw = rs.getString(2);
				
				if(!pw.equals(dbpw)) {
					System.out.println("비밀번호 오류");
					return 1;
				}
			}
			else {
				System.out.println("정보없음");
				return 2;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	           DBUtil.close(conn, pstmt, rs);
	    }
		System.out.println("로그인가능");
		return 0;
	}
	
	public int loginKakao(String id){
		sql = "Select * From member where id=?";
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(!rs.next()){
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	           DBUtil.close(conn, pstmt, rs);
	    }
		System.out.println("로그인가능");
		return 0;
	}
	
	public ArrayList<MemberVO> updateRegister(String id){
		MemberVO vo = null;
		ArrayList<MemberVO> list = new ArrayList<>();
		sql = "select name,gender from member where id=?";
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				vo = new MemberVO();
				vo.setName(rs.getString(1));
				vo.setGender(rs.getString(2));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
		return list;
	}
	public void upRegister(String id,String pw, String phone){
		sql ="update member set pw=?,phone=? where id=?";
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, phone);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt);
		}
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
	
	public ArrayList<ReservTicketVO> loadReserve(String id){
		ReservTicketVO vo = null;
		ArrayList<ReservTicketVO> list = new ArrayList<ReservTicketVO>();
		
		sql = "select * from reservationdetails where id=? order by num asc";
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				vo = new ReservTicketVO();
				vo.setReserveCode(rs.getString(1));
				vo.setViewingCode(rs.getString(3));
				vo.setTheater(rs.getString(4));
				vo.setMovieName(rs.getString(5));
				vo.setViewingDate(rs.getString(6));
				vo.setViewingTime(rs.getString(7));
				vo.setPerson(rs.getString(8));
				vo.setSeat(rs.getString(9));
				vo.setReserveDate(rs.getString(10));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public void deleteOrder(String myCode, String movieCode){
		
		String[] select_seat=null;
		String person = null;
		
		//좌석,인원 얻어오기
		sql = "select seat, person from reservationdetails where num=?";
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,myCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				select_seat = rs.getString(1).split(" ");
				person = rs.getString(2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
		
		//내역에서 삭제
		sql = "delete from reservationdetails where num=?";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myCode);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	           DBUtil.close(conn, pstmt);
	    }
		
		//인원 내리기
		sql = "update timetable set person=person-? where num=?";
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(person));  
			pstmt.setString(2, movieCode);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
           DBUtil.close(conn, pstmt);
        }
		
		//좌석 T로 바꾸기
		for(int i=0; i<select_seat.length; i++){
	    	sql = "update seat set search='T' where num=? and seatno=?";
			
			try {
				conn = DBUtil.getConnection();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, movieCode);
				pstmt.setString(2, select_seat[i]);
				
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
