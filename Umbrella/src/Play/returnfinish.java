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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class returnfinish extends Frame implements ActionListener{
	public static void main(String args[]) {
		
	}
	 returnfinish(){
		Connection conn = null; // DB 연결
		Statement stmt = null; // 데이터 전송
		ResultSet rs = null;
	
		
		String sql = "SELECT * FROM login";
		
		// 화면 출력
		ImagePanel renturnfi = new ImagePanel(new ImageIcon(".//img//returnfinish.png").getImage()); // 프레임 생성
		renturnfi.setName("반납완료"); // 프레임 제목
		renturnfi.setSize(1920, 1080); // 프레임 사이즈
		renturnfi.setLocation(null); // 프레임을 화면 가운데에 배치
		System.exit(0);

		
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
				lbl.setBounds(100, 100, 100, 30); //이거 아마 수정해야할듯(이진)
				lbl.setHorizontalAlignment(JLabel.CENTER);
				renturnfi.add(lbl);
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
		lbl.setBounds(50, 35, 100, 30); //이것도 위치 수정해야할듯(이진)
		lbl.setHorizontalAlignment(JLabel.CENTER);
		renturnfi.add(lbl);
		
		
		renturnfi.setVisible(true); // 프레임이 보이도록 설정
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
