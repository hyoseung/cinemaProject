package sk.movie.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	//접속
	public static Connection getConnection() throws Exception{
		Connection conn = null;
		//3. 드라이버 로딩 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//4.DB접속
		String url = "jdbc:oracle:thin:@117.17.143.67:1521:xe";
		conn = DriverManager.getConnection(url, "movie", "cs617");
		return conn;
	}
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(conn, ps);
	}
	
	//close
	public static void close(Connection conn, PreparedStatement ps ){
		//2. 선언한 객체를 닫는다. 
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
