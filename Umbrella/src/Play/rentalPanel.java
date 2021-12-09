// �̸�, �й�, ��ȭ��ȣ �Է��ϰ� �뿩�ϴ� ��
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
	JTextField in_name; // �̸�
	JTextField in_num; // �й�
	JTextField in_phone; // ��ȭ��ȣ
	
	int umbrella_cnt = 40; // ��� ����
	
	JButton btn = new JButton(""); // ���뿩��ư
	
	public rentalPanel(String str) {
		super(str);
		init();
		start();
		
		this.setLocation(0, 0); // ������ ������ġ
		super.setVisible(true); // ������ ȭ�� ���
		super.setSize(1920, 1080); //ũ��
		super.setResizable(false); // ������ ����
	}
	
	public void init() {
		ImagePanel rentalPanel = new ImagePanel(new ImageIcon(".//img//rentalsrc.png").getImage());
		setLayout(new BorderLayout());
		Label str_date = new Label("�뿩��¥");
		
		btn.addActionListener(this); // ��ư �̺�Ʈ
		in_name = new JTextField(20);
		in_num = new JTextField(20);
		in_phone = new JTextField(20);
		
		
		// �̸� �Է�
		in_name.setBounds(734, 345, 452, 62);
		in_name.setFont(new Font("���� ���", Font.BOLD, 15));
		in_name.setColumns(10);
		rentalPanel.add(in_name);
		in_name.setBorder(null);
		
		// �й� �Է�
		in_num.setFont(new Font("���� ���", Font.BOLD, 15));
		in_num.setColumns(10);
		in_num.setBounds(734, 490, 452, 62);
		rentalPanel.add(in_num);
		in_num.setBorder(null);
		
		// ��ȭ��ȣ �Է�
		in_phone.setFont(new Font("���� ���", Font.BOLD, 15));
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
					
				Calendar strTime = Calendar.getInstance(); // �뿩 ��¥, �ð�
				String format_str = format.format(strTime.getTime());
				
				// ���� ��¥
				String format_today = format.format(strTime.getTime());
				
				
				Date now = new Date();
				strTime.add(Calendar.DATE, 7); //7�� ���ϱ�
				String format_end = format.format(strTime.getTime());
				
				if((JButton)obj == btn) {
					dto.setNum(in_num.getText()); // �Էµ� �й��� dto�� ����
					dto.setName(in_name.getText());
					dto.setPhone(in_phone.getText());
					dto.setstrDate(format_str);
					dto.setstrDate(format_today);
					dto.setendDate(format_end); //��Ʈ���� ����?
					
					try {
						if(umbrella_cnt != 0) {
							insertDAO.create(dto); // dto�� DAO�� �ѱ�
							umbrella_cnt--;
						} else {
							JOptionPane.showMessageDialog(null, "�뿩 ������ ����� �����ϴ�");
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
	public void itemStateChanged(ItemEvent e) { // üũ ���� Ȯ��
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { // �׼� �̺�Ʈ
		
		
	}
	public static void main(String args[]) {
		rentalPanel exam = new rentalPanel("�⺻ ������");
	}
}
