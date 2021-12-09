// 이름, 학번, 전화번호 입력하고 대여하는 곳
package Play;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class rentalPanel extends Frame implements ActionListener, ItemListener, Runnable{
	DTO dto = new DTO();
	JTextField in_name; // 이름
	JTextField in_num; // 학번
	JTextField in_phone; // 전화번호
	
	int umbrella_cnt = 40; // 우산 개수
	
	JButton btn = new JButton(""); // 우산대여버튼
	
	public rentalPanel(String str) {
		super(str);
		init();
		start();
		
		this.setLocation(0, 0); // 프레임 시작위치
		super.setVisible(true); // 프레임 화면 출력
		super.setSize(1920, 1080); //크기
		super.setResizable(false); // 사이즈 조절
	}
	
	public void init() {
		ImagePanel rentalPanel = new ImagePanel(new ImageIcon(".//img//rentalsrc.png").getImage());
		setLayout(new BorderLayout());
		Label str_date = new Label("대여날짜");
		
		btn.addActionListener(this); // 버튼 이벤트
		in_name = new JTextField(20);
		in_num = new JTextField(20);
		in_phone = new JTextField(20);
		
		
		// 이름 입력
		in_name.setBounds(734, 345, 452, 62);
		in_name.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		in_name.setColumns(10);
		rentalPanel.add(in_name);
		in_name.setBorder(null);
		
		// 학번 입력
		in_num.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		in_num.setColumns(10);
		in_num.setBounds(734, 490, 452, 62);
		rentalPanel.add(in_num);
		in_num.setBorder(null);
		
		// 전화번호 입력
		in_phone.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		in_phone.setColumns(10);
		in_phone.setBounds(734, 638, 452, 62);
		in_phone.setBorder(null);
		rentalPanel.add(in_phone);
		
		
		btn.addActionListener(new ActionListener() {
			
			@Override
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
				
				if((JButton)obj == btn) {
					dto.setNum(in_num.getText()); // 입력된 학번을 dto에 저장
					dto.setName(in_name.getText());
					dto.setPhone(in_phone.getText());
					dto.setstrDate(format_str);
					dto.setstrDate(format_today);
					dto.setendDate(format_end); //인트형이 문제?
					
					try {
						if(umbrella_cnt != 0) {
							insertDAO.create(dto); // dto를 DAO에 넘김
							umbrella_cnt--;
						} else {
							JOptionPane.showMessageDialog(null, "대여 가능한 우산이 없습니다");
						}
						new rentalfinish();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btn.setBounds(841, 791, 234, 207);
		btn.setIcon(new ImageIcon(".//img//go_rentalbtn.png"));
		btn.setBackground(Color.white);
		btn.setOpaque(false);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		rentalPanel.add(btn);
		
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
		rentalPanel exam = new rentalPanel("기본 프레임");
	}
}
