package Play;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class returnPanel extends Frame implements ActionListener, ItemListener, Runnable{
	DTO dto = new DTO();
	JTextField name; // 이름
	JTextField num; // 학번
	
	JButton returnbtn = new JButton("");
	String dnum = null;
	
    String sql = null;
	
	public returnPanel(String str) {
		super(str);
		init();
		start();
		
		this.setLocation(0, 0); // 프레임 시작위치
		super.setVisible(true); // 프레임 화면 출력
		super.setSize(1920, 1080); //크기
		super.setResizable(false); // 사이즈 조절
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
	
	public void init() {
		ImagePanel rentalPanel = new ImagePanel(new ImageIcon(".//img//return.png").getImage());
		setLayout(new BorderLayout());
		Label str_date = new Label("대여날짜");
		
		returnbtn.addActionListener(this); // 버튼 이벤트
		name = new JTextField(20);
		num = new JTextField(20);
		
		
		// 이름 입력
		name.setBounds(745, 417, 452, 61);
		name.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		name.setColumns(10);
		rentalPanel.add(name);
		name.setBorder(null);
		
		// 학번 입력
		num.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		num.setColumns(10);
		num.setBounds(745, 565, 452, 61);
		rentalPanel.add(num);
		num.setBorder(null);
		
		
		String dnum = null;
		
		returnbtn.addActionListener(new ActionListener() {
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
					//new main("메뉴");
					
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
			
			/*@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object obj = e.getSource();
				SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
					
				Calendar strTime = Calendar.getInstance(); // 대여 날짜, 시간
				String format_str = format.format(strTime.getTime());
				
				// 오늘 날짜
				String format_today = format.format(strTime.getTime());
				
				
				Date now = new Date();
				strTime.add(Calendar.DATE, 7); //7일 더하기
				String format_end = format.format(strTime.getTime());
				
				if((JButton)obj == returnbtn) {
					dto.setNum(in_num.getText()); // 입력된 학번을 dto에 저장
					dto.setName(in_name.getText());
					dto.setstrDate(format_str);
					dto.setstrDate(format_today);
					dto.setendDate(format_end); //인트형이 문제?
					
					try {
						insertDAO.create(dto); // dto를 DAO에 넘김
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						new returnfinish();
					}
				}
			}
		});*/
		returnbtn.setBounds(841, 791, 234, 207);
		returnbtn.setIcon(new ImageIcon(".//img//returnbtn1.png"));
		returnbtn.setBackground(Color.white);
		returnbtn.setOpaque(false);
		returnbtn.setBorderPainted(false);
		returnbtn.setContentAreaFilled(false);
		returnbtn.setFocusPainted(false);
		rentalPanel.add(returnbtn);
		
		add("Center", rentalPanel);
	}
	
	public void start() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
	
	public void run() {
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) { // 체크 상태 확인
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { // 액션 이벤트
		
		
	}
	public static void main(String args[]) {
		returnPanel exam = new returnPanel("기본 프레임");
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}
}
