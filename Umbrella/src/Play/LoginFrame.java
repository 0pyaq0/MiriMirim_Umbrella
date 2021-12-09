package Play;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField num, name;
	private JButton rentalbtn, inbtn;

	String dnum = null;
	
    String sql = null;
    String sql2 = null;
   
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// db 삭제
	int deleteNumandName(String num) {
		int result = 0;
		this.dnum = num;
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. 데이터베이스 연결
			stmt = conn.createStatement();
			
			 sql = "delete from login where num='" + num + "'";
			 stmt.executeUpdate(sql);
			 
			 sql = "select * from login where num='" + num + "'";
	         rs = stmt.executeQuery(sql);
	         if (rs.next() == true) { // 다음값의
                result = 0; // 실패
            } else {
                result = 1; // 성공
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("학번 : ");
		lblLogin.setBounds(41, 52, 69, 35);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("이름 : ");
		lblPassword.setBounds(41, 103, 69, 35);
		contentPane.add(lblPassword);
		
		num = new JTextField();
		num.setBounds(157, 52, 176, 35);
		contentPane.add(num);
		num.setColumns(10);

		rentalbtn = new JButton("우산 반납");
		rentalbtn.setBounds(108, 154, 106, 29);
		contentPane.add(rentalbtn);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(157, 103, 176, 35);
		contentPane.add(name);
		
		setVisible(true);
		
		
		//우산 대여
		rentalbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String txtnum = num.getText();
				String txtname = name.getText();
				
				int result = findByUsernumAndName(txtnum, txtname);
				if(result == 1) {
					//로그인 성공 메시지
					//JOptionPane.showMessageDialog(null, "우산 반납 완료");
					
					// 반납 완료시 db 삭제
					int deletresult = deleteNumandName(txtnum);
					if(deletresult == 1) {
						JOptionPane.showMessageDialog(null, "우산 반납 완료");
					}
					
					
					//현재 화면 종료
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "우산 반납 실패");
				}
				
			}

			public int findByUsernumAndName(String num, String name) {
				Connection conn = null; //DB 연결 객체
				PreparedStatement pstmt; //Query 작성 객체
				ResultSet rs; //Query 결과 커서
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. 데이터베이스 연결
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					
					//2. Query 작성
					pstmt = conn.prepareStatement("select num,name from login where num = ? and name = ?");
					
					//3. Query ? 완성 (index 1번 부터 시작)
					//setString, setInt, setDouble, setTimeStamp 등이 있음.
					pstmt.setString(1, num);
					pstmt.setString(2, name);
					
					//4. Query 실행
					//(1) executeQuery() = select = ResultSet 리턴
					//(2) executeUpdate() = insert, update, delete = 리턴 없음.
					rs = pstmt.executeQuery();
					
					//5. rs는 query한 결과의 첫번째 행(레코드) 직전에 대기중
					//결과가 count(*) 그룹함수이기 때문에 1개의 행이 리턴됨. while문이 필요 없음.
					if(rs.next()) { //next()함수는 커서를 한칸 내리면서 해당 행에 데이터가 있으면 true, 없으면 false 반환
						//결과가 있다는 것은 해당 아이디와 비번에 매칭되는 값이 있다는 뜻.
						return 1; //로그인 성공
					}		
				} catch (SQLException e) {
					e.printStackTrace();
				} 
				
				return -1; //로그인 실패
			}
		});
	}
	
	
}