package Play;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class selectDAO extends JFrame{
	JLabel lab = new JLabel();
	
	selectDAO(){
		super("미리미림 우산 대여");
		setLayout(new FlowLayout()); // 레이아웃
		
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(600, 400);
		frm.setVisible(true);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. 데이터베이스 연결
			stmt = conn.createStatement(); 
			
			rs = stmt.executeQuery("SELECT * FROM login ORDER BY str_date DESC LIMIT 1" );
			
			// ResultSet에 저장된 데이터 얻기 : 제일 마지막으로 들어간 값 가져오기
			if(rs.next()) { 
				String name = rs.getString("name"); 
				String num = rs.getString("num"); 
				String str_date = rs.getString("str_date");
				String end_date = rs.getString("end_date");				
				
				JTextField txt = new JTextField("\t" + name + " " + num + "\n 대여일 : " + str_date + "\n 반날일 : " + end_date);
				frm.getContentPane().add(txt);
			}
		} catch (SQLException e) { 
			System.out.println("SQL Error : " + e.getMessage()); 
		} catch (ClassNotFoundException e1) { 
			System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]"); 
		} finally { 
			//사용순서와 반대로 close 함 
			if (rs != null) { 
				try { 
					rs.close();
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} if (stmt != null) {
					try { 
						stmt.close(); 
					} catch (SQLException e) {
						e.printStackTrace(); 
					} 
				} if (conn != null) {
					try { 
						conn.close(); 
					} catch (SQLException e) {
						e.printStackTrace(); 
					} 
				} 
			}		
		}
	}
	public static void main(String args[]) {
		new selectDAO();
	}
}
