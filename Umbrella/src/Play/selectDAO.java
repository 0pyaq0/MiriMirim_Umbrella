package Play;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class selectDAO extends JFrame{
	JLabel lab = new JLabel();
	
	selectDAO(){
		super("�̸��̸� ��� �뿩");
		setLayout(new FlowLayout()); // ���̾ƿ�
		
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(600, 400);
		frm.setVisible(true);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. �����ͺ��̽� ����
			stmt = conn.createStatement(); 
			
			rs = stmt.executeQuery("SELECT * FROM login ORDER BY str_date DESC LIMIT 1" );
			
			// ResultSet�� ����� ������ ��� : ���� ���������� �� �� ��������
			if(rs.next()) { 
				String name = rs.getString("name"); 
				String num = rs.getString("num"); 
				String str_date = rs.getString("str_date");
				String end_date = rs.getString("end_date");				
				
				JTextField txt = new JTextField("\t" + name + " " + num + "\n �뿩�� : " + str_date + "\n �ݳ��� : " + end_date);
				frm.getContentPane().add(txt);
			}
		} catch (SQLException e) { 
			System.out.println("SQL Error : " + e.getMessage()); 
		} catch (ClassNotFoundException e1) { 
			System.out.println("[JDBC Connector Driver ���� : " + e1.getMessage() + "]"); 
		} finally { 
			//�������� �ݴ�� close �� 
			if (rs != null) { 
				try { 
					rs.close();
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} if (stmt != null) {
					try { 
						stmt.close(); 
					} catch (SQLException e) {
						e.printStackTrace(); 
					} 
				} if (conn != null) {
					try { 
						conn.close(); 
					} catch (SQLException e) {
						e.printStackTrace(); 
					} 
				} 
			}		
		}
	}
	public static void main(String args[]) {
		new selectDAO();
	}
}
