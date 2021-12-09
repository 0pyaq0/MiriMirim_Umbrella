// 먹구름리스트
package Play;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class returnUmbrella extends Frame implements ActionListener{
	JButton btn = new JButton("메뉴"); // 메뉴로 돌아가는 버튼
	JTextArea txtResult = new JTextArea();
	
	JLabel lab = new JLabel();
	Connection conn = null; // DB 연결
	Statement stmt = null; // 데이터 전송
	ResultSet rs = null;
	
	ImagePanel rentalPanel;
	
	returnUmbrella(){
		setTitle("먹구름 리스트");
		rentalPanel = new ImagePanel(new ImageIcon(".//img//darkClouds.png").getImage());
		setLayout(new BorderLayout());
		
		laylnit(); // 레이아웃 메소드
		
		setBounds(400, 400, 700, 400);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼 누르면 종료
		
		String sql = "SELECT num, name, str_date, end_date FROM login WHERE end_date > today"; 
		// 반납일자가 오늘보다 작으면 => 연체된 것 : 그것들만 조회하자
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로딩
			conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. 데이터베이스 연결
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);
			int cnt = 1; // 번호
			txtResult.setText(" 번호\t 학번\t 이름\t 대여 날짜\t\t 반납 날짜\n");
			
			while(rs.next()) {
				Calendar today = Calendar.getInstance();
				sql = "UPDATE login set today = " + today;
				String num = rs.getString("num");
				String name = rs.getString("name");
				String str_date = rs.getString("str_date");
				String end_date = rs.getString("end_date");
								
				String str = " " + rs.getString("num") + "\t" + rs.getString("name") + "\t" + rs.getString("str_date") + "\t" + rs.getString("end_date") + "\n";
				txtResult.append("  " + cnt + "\t" + str);
				cnt++;
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
	 }

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}

	// 레이아웃
	private void laylnit() {
		JPanel panel = new JPanel();
		panel.add(btn); // 버튼
		txtResult.setEditable(false);
		JScrollPane pane = new JScrollPane(txtResult); // 스크롤
		
		add("Center", pane);
		add("North", panel);
		
		btn.addActionListener(this);
		
		
		rentalPanel.add(btn);
	}
	
	// DB 로딩
	/*private void accDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로딩
		} catch (Exception e) {
			System.out.println("오류 : " + e);
		}
	}*/
	
	// 버튼 실행
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) {
		new returnUmbrella();
	}
}
