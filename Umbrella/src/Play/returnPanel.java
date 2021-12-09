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
	JTextField name; // �̸�
	JTextField num; // �й�
	
	JButton returnbtn = new JButton("");
	String dnum = null;
	
    String sql = null;
	
	public returnPanel(String str) {
		super(str);
		init();
		start();
		
		this.setLocation(0, 0); // ������ ������ġ
		super.setVisible(true); // ������ ȭ�� ���
		super.setSize(1920, 1080); //ũ��
		super.setResizable(false); // ������ ����
	}
	
	// db ����
	int deleteNumandName(String num) {
		int result = 0;
		this.dnum = num;
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. �����ͺ��̽� ����
			stmt = conn.createStatement();
			
			 sql = "delete from login where num='" + num + "'";
			 stmt.executeUpdate(sql);
			 
			 sql = "select * from login where num='" + num + "'";
	         rs = stmt.executeQuery(sql);
	         if (rs.next() == true) { // ��������
                result = 0; // ����
            } else {
                result = 1; // ����
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void init() {
		ImagePanel rentalPanel = new ImagePanel(new ImageIcon(".//img//return.png").getImage());
		setLayout(new BorderLayout());
		Label str_date = new Label("�뿩��¥");
		
		returnbtn.addActionListener(this); // ��ư �̺�Ʈ
		name = new JTextField(20);
		num = new JTextField(20);
		
		
		// �̸� �Է�
		name.setBounds(745, 417, 452, 61);
		name.setFont(new Font("���� ���", Font.BOLD, 15));
		name.setColumns(10);
		rentalPanel.add(name);
		name.setBorder(null);
		
		// �й� �Է�
		num.setFont(new Font("���� ���", Font.BOLD, 15));
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
					//�α��� ���� �޽���
					//JOptionPane.showMessageDialog(null, "��� �ݳ� �Ϸ�");
					
					// �ݳ� �Ϸ�� db ����
					int deletresult = deleteNumandName(txtnum);
					if(deletresult == 1) {
						JOptionPane.showMessageDialog(null, "��� �ݳ� �Ϸ�");
					}
					
					
					//���� ȭ�� ����
					dispose();
					//new main("�޴�");
					
				}else {
					JOptionPane.showMessageDialog(null, "��� �ݳ� ����");
				}
				
			}

			public int findByUsernumAndName(String num, String name) {
				Connection conn = null; //DB ���� ��ü
				PreparedStatement pstmt; //Query �ۼ� ��ü
				ResultSet rs; //Query ��� Ŀ��
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. �����ͺ��̽� ����
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					
					//2. Query �ۼ�
					pstmt = conn.prepareStatement("select num,name from login where num = ? and name = ?");
					
					//3. Query ? �ϼ� (index 1�� ���� ����)
					//setString, setInt, setDouble, setTimeStamp ���� ����.
					pstmt.setString(1, num);
					pstmt.setString(2, name);
					
					//4. Query ����
					//(1) executeQuery() = select = ResultSet ����
					//(2) executeUpdate() = insert, update, delete = ���� ����.
					rs = pstmt.executeQuery();
					
					//5. rs�� query�� ����� ù��° ��(���ڵ�) ������ �����
					//����� count(*) �׷��Լ��̱� ������ 1���� ���� ���ϵ�. while���� �ʿ� ����.
					if(rs.next()) { //next()�Լ��� Ŀ���� ��ĭ �����鼭 �ش� �࿡ �����Ͱ� ������ true, ������ false ��ȯ
						//����� �ִٴ� ���� �ش� ���̵�� ����� ��Ī�Ǵ� ���� �ִٴ� ��.
						return 1; //�α��� ����
					}		
				} catch (SQLException e) {
					e.printStackTrace();
				} 
				
				return -1; //�α��� ����
			}
		});
			
			/*@Override
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
				
				if((JButton)obj == returnbtn) {
					dto.setNum(in_num.getText()); // �Էµ� �й��� dto�� ����
					dto.setName(in_name.getText());
					dto.setstrDate(format_str);
					dto.setstrDate(format_today);
					dto.setendDate(format_end); //��Ʈ���� ����?
					
					try {
						insertDAO.create(dto); // dto�� DAO�� �ѱ�
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
	public void itemStateChanged(ItemEvent e) { // üũ ���� Ȯ��
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { // �׼� �̺�Ʈ
		
		
	}
	public static void main(String args[]) {
		returnPanel exam = new returnPanel("�⺻ ������");
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
