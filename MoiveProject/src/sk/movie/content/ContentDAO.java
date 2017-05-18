package sk.movie.content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import sk.movie.DB.DBUtil;

@Repository
public class ContentDAO {
	
	public ArrayList<ContentVO> oneline(int code){
		ContentVO vo = null; 
		ArrayList<ContentVO> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select id,count,content,opendate from line where code=?";
		try{
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, code);
			rs = pst.executeQuery();
			while(rs.next()){
				vo = new ContentVO();
				vo.setId(rs.getString(1));
				vo.setCount(rs.getInt(2));
				vo.setContent(rs.getString(3));
				vo.setDate(rs.getString(4));
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, pst, rs);
		}

		return list;
	}
	
	public void insertLine(String id,int code,String text){
		Connection conn=null;
		PreparedStatement pst = null;
		String sql="insert into line(id,code,count,content,opendate) values(?,?,count_seq.nextval,?,sysdate)";
		
		try{
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setInt(2, code);
			pst.setString(3, text);
			pst.executeUpdate();
			System.out.println("한줄평 삽입 성공!!!!!!");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, pst);
		}
	}
	public void deleteLine(int num){
		Connection conn=null;
		PreparedStatement pst = null;
		String sql="delete from line where count=?";
		try{
			conn = DBUtil.getConnection();
			pst =conn.prepareStatement(sql);
			pst.setInt(1, num);
			pst.executeUpdate();
			System.out.println("한줄평 삭제 성공");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pst);
		}
	}
}
