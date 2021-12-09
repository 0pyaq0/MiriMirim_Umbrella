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

	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("�й� : ");
		lblLogin.setBounds(41, 52, 69, 35);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("�̸� : ");
		lblPassword.setBounds(41, 103, 69, 35);
		contentPane.add(lblPassword);
		
		num = new JTextField();
		num.setBounds(157, 52, 176, 35);
		contentPane.add(num);
		num.setColumns(10);

		rentalbtn = new JButton("��� �ݳ�");
		rentalbtn.setBounds(108, 154, 106, 29);
		contentPane.add(rentalbtn);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(157, 103, 176, 35);
		contentPane.add(name);
		
		setVisible(true);
		
		
		//��� �뿩
		rentalbtn.addActionListener(new ActionListener() {
			
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
	}
	
	
}