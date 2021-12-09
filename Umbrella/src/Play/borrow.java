package Play;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class borrow extends Frame implements ActionListener{
	public static void main(String args[]) {
		
	}
	 borrow(){
		Connection conn = null; // DB 연결
		Statement stmt = null; // 데이터 전송
		ResultSet rs = null;
	
		
		String sql = "SELECT * FROM login";
		
		// 화면 출력
		JFrame frm = new JFrame(); // 프레임 생성
		frm.setTitle("로그인"); // 프레임 제목
		frm.setSize(1250, 670); // 프레임 사이즈
		frm.setLocationRelativeTo(null); // 프레임을 화면 가운데에 배치
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임을 닫았을 때 프로그램이 종료되도록 설정
		
		// 레이아웃 설정
		frm.getContentPane().setLayout(null);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로딩
			conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. 데이터베이스 연결
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String num = rs.getString("num");
				String name = rs.getString("name");
				String str_date = rs.getString("str_date");
				String end_date = rs.getString("end_date");
				
				rs.getString("num");
				rs.getString("name");
				rs.getString("str_date");
				rs.getString("end_date");
				
				System.out.println(num + " " + name);
				System.out.println(str_date);
				System.out.println(end_date);
				
				JLabel lbl = new JLabel(num + " " + name);
				lbl.setBounds(100, 100, 100, 30);
				lbl.setHorizontalAlignment(JLabel.CENTER);
				frm.getContentPane().add(lbl);
			}
		} catch (SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
		} catch (ClassNotFoundException e1) {
			System.err.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		 		
			
		// 라벨 설정
		JLabel lbl = new JLabel("학번");
		lbl.setBounds(50, 35, 100, 30);
		lbl.setHorizontalAlignment(JLabel.CENTER);
		frm.getContentPane().add(lbl);
		
		
		frm.setVisible(true); // 프레임이 보이도록 설정
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
