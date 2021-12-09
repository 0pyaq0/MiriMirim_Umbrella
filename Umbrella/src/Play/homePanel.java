package Play;

import java.awt.BorderLayout;
import Play.main;
import Play.rentalPanel;
import Play.returnPanel;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class homePanel extends Frame implements ActionListener{
	JFrame f = new JFrame();
	JButton rentalbtn = new JButton("");
	JButton returnbtn = new JButton("");
	JButton exitbtn = new JButton("");
	
	public homePanel(String str) {
		super(str);
		init();
		start();
		
		this.setLocation(0, 0); // 프레임 시작위치
		super.setVisible(true); // 프레임 화면 출력
		super.setSize(1920, 1080); //크기
		super.setResizable(false); // 사이즈 조절
	}
	
	public void init() {
		ImagePanel homePanel = new ImagePanel(new ImageIcon(".//img//home.png").getImage());
		setLayout(new BorderLayout());
		
		
		rentalbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new rentalPanel("대여");
			}
		});
		
		rentalbtn.setBounds(399, 607, 245, 215);
		rentalbtn.setIcon(new ImageIcon(".//img//rentalbtn.png"));
		rentalbtn.setBackground(Color.white);
		rentalbtn.setOpaque(false);
		rentalbtn.setBorderPainted(false);
		rentalbtn.setContentAreaFilled(false);
		rentalbtn.setFocusPainted(false);
		homePanel.add(rentalbtn);
		

		returnbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new returnPanel("반납");
			}
		});
		returnbtn.setBounds(832, 607, 259, 215);
		returnbtn.setIcon(new ImageIcon(".//img//returnbtn.png"));
		returnbtn.setBackground(Color.white);
		returnbtn.setOpaque(false);
		returnbtn.setBorderPainted(false);
		returnbtn.setContentAreaFilled(false);
		returnbtn.setFocusPainted(false);
		homePanel.add(returnbtn);
		
		exitbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new main("메인");
			}
		});
		exitbtn.setBounds(1262, 607, 259, 215);
		exitbtn.setIcon(new ImageIcon(".//img//exitbtn1.png"));
		exitbtn.setBackground(Color.white);
		exitbtn.setOpaque(false);
		exitbtn.setBorderPainted(false);
		exitbtn.setContentAreaFilled(false);
		exitbtn.setFocusPainted(false);
		homePanel.add(exitbtn);
		
		add("Center", homePanel);
	}
	
	public void start() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		homePanel home = new homePanel("");

	}
}
