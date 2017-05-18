package sk.movie.movieInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import sk.movie.DB.DBUtil;

@Repository
public class MovieInfoDAO {
	DBUtil dbUtil;
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	String sql = null;

	public ArrayList<MovieInfoVO> loadMainMovie(String type) { //메인페이지 영화출력
		ArrayList<MovieInfoVO> list = new ArrayList<>();
		MovieInfoVO vo = null;
		try {
			conn = dbUtil.getConnection();
			pst = conn.prepareStatement("select moviename,movieimage,rateimage,code,type from movieinfo where type=? order by opendate desc");
			pst.setString(1, type);
			rs = pst.executeQuery();

			while (rs.next()) {
				vo = new MovieInfoVO();
				vo.setMovieName(rs.getString(1));
				vo.setMovieImage(rs.getString(2));
				vo.setRateIamge(rs.getString(3));
				vo.setCode(rs.getInt(4));
				vo.setType(rs.getString(5));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pst, rs);

		}
		return list;
	}
	public ArrayList<MovieInfoVO> loadMainMovie() { //영화 메뉴에서 클릭시
		ArrayList<MovieInfoVO> list = new ArrayList<>();
		MovieInfoVO vo = null;
		try {
			conn = dbUtil.getConnection();
			pst = conn.prepareStatement("select moviename,movieimage,rateimage,code,type from movieinfo order by opendate desc");
			rs = pst.executeQuery();

			while (rs.next()) {
				vo = new MovieInfoVO();
				vo.setMovieName(rs.getString(1));
				vo.setMovieImage(rs.getString(2));
				vo.setRateIamge(rs.getString(3));
				vo.setCode(rs.getInt(4));
				vo.setType(rs.getString(5));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pst, rs);

		}
		return list;
	}
	
	public ArrayList<MovieInfoVO> detailMovie(int code){
		ArrayList<MovieInfoVO> list = new ArrayList<>();
		MovieInfoVO vo = null;
		try{
			conn = dbUtil.getConnection();
			pst = conn.prepareStatement("select movieimage,moviename,rateimage,opendate,director,actor,genre,runtime,story,code,type from movieinfo where code=? ");
			pst.setInt(1, code);
			rs = pst.executeQuery();
			
			while(rs.next()){
				vo = new MovieInfoVO();
				vo.setMovieImage(rs.getString(1));
				vo.setMovieName(rs.getString(2));
				vo.setRateIamge(rs.getString(3));
				vo.setOpenDate(rs.getString(4));
				vo.setDirector(rs.getString(5));
				vo.setActor(rs.getString(6));
				vo.setGenre(rs.getString(7));
				vo.setRuntime(rs.getString(8));
				vo.setStory(rs.getString(9));
				vo.setCode(rs.getInt(10));
				vo.setType(rs.getString(11));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbUtil.close(conn, pst, rs);

		}
		return list;
	}
	public static void main(String[] args) {
		MovieInfoDAO dao = new MovieInfoDAO();
		System.out.println(dao.loadMainMovie("current").size());
	}
	
	/*public String loadCinema(){
		String result=null;
		try {
			conn = DBUtil.getConnection();
			//출력
			sql = "Select theater, local From cinema";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()){
				result = "{\"cinema\":["
				        + "{\"c_name\":\""+rs.getString(1)+"\","
				        + "\"c_loc\":\""+rs.getString(2)+"\""
				        + "}";
				
				while(rs.next()){
					result += ",{\"c_name\":\""+rs.getString(1)+"\","
					        + "\"c_loc\":\""+rs.getString(2)+"\""
					        + "}";
				}
				result+= "]}";
			}
	        
			System.out.println("영화관 : " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	           DBUtil.close(conn, pst, rs);
	    }
		
		return result;
	}*/
}
