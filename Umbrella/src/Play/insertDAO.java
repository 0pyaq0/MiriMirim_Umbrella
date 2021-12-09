package Play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class insertDAO {
	public static void main(String args[]) {
		
	}
	public static boolean create(DTO dto) throws Exception{
		boolean flag = false;
		Connection conn = null; // DB 연결
		Statement stmt = null; // 데이터 전송
		
		
		String num = dto.getNum();
		String name = dto.getName();
		String phone = dto.getPhone();
		
		// 날짜
		String str_date = dto.getstrDate();
		String end_date = dto.getendDate();
		
		// 오늘 날짜 추가 -> 매일 갱신해야 됨
		String today = dto.getendDate();
		
		
		
		String sql = "INSERT INTO login(num, name, phone, str_date, end_date, today) VALUES";
		
		try {
			sql += "('" + new String(num.getBytes(), "ISO-8859-1") + "','" 
				+ new String(name.getBytes(), "euc-kr") + "','"
				+ new String(phone.getBytes(), "ISO-8859-1")  + "','"
				+ new String(str_date.getBytes(), "ISO-8859-1") + "','"
				+ new String(end_date.getBytes(), "ISO-8859-1") + "','"
				+ new String(today.getBytes(), "ISO-8859-1") + "')";
				
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. 데이터베이스 연결
			stmt = (Statement) conn.createStatement();
			stmt.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		} finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return flag;
	}
}
