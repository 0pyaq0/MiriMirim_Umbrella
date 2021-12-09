package Play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class main extends Frame implements ActionListener{
	JButton mainbtn = new JButton("");
	JButton listbtn = new JButton("");
	
	public main(String str) {
		super(str);
		init();
		start();
		
		this.setLocation(0, 0); // 프레임 시작위치
		super.setVisible(true); // 프레임 화면 출력
		super.setSize(1920, 1080); //크기
		super.setResizable(false); // 사이즈 조절
	}

	public void init() {
		homePanel h= new homePanel("");
		h.setVisible(false);
		ImagePanel mainPanel = new ImagePanel(new ImageIcon(".//img//mainPanel.png").getImage());
		setLayout(new BorderLayout());
		
		
		mainbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				h.setVisible(true);
			}
		});
		mainbtn.setBounds(1578, 717, 143, 200);
		mainbtn.setIcon(new ImageIcon(".//img//mainbtn.png"));
		mainbtn.setBackground(Color.white);
		mainbtn.setOpaque(false);
		mainbtn.setBorderPainted(false);
		mainbtn.setContentAreaFilled(false);
		mainbtn.setFocusPainted(false);
		mainPanel.add(mainbtn);
		

		listbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new returnUmbrella();
			}
		});
		listbtn.setBounds(168, 705, 143, 200);
		listbtn.setIcon(new ImageIcon(".//img//listbtn.png"));
		listbtn.setBackground(Color.white);
		listbtn.setOpaque(false);
		listbtn.setBorderPainted(false);
		listbtn.setContentAreaFilled(false);
		listbtn.setFocusPainted(false);
		mainPanel.add(listbtn);
		
		add("Center", mainPanel);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void start() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
	public static void main(String[] args) {
		main m = new main("메인 화면");
	}

}
