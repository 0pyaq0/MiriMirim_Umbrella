package Play;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class borrow extends Frame implements ActionListener{
	public static void main(String args[]) {
		
	}
	 borrow(){
		Connection conn = null; // DB ����
		Statement stmt = null; // ������ ����
		ResultSet rs = null;
	
		
		String sql = "SELECT * FROM login";
		
		// ȭ�� ���
		JFrame frm = new JFrame(); // ������ ����
		frm.setTitle("�α���"); // ������ ����
		frm.setSize(1250, 670); // ������ ������
		frm.setLocationRelativeTo(null); // �������� ȭ�� ����� ��ġ
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �������� �ݾ��� �� ���α׷��� ����ǵ��� ����
		
		// ���̾ƿ� ����
		frm.getContentPane().setLayout(null);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC ����̹� �ε�
			conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. �����ͺ��̽� ����
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String num = rs.getString("num");
				String name = rs.getString("name");
				String str_date = rs.getString("str_date");
				String end_date = rs.getString("end_date");
				
				rs.getString("num");
				rs.getString("name");
				rs.getString("str_date");
				rs.getString("end_date");
				
				System.out.println(num + " " + name);
				System.out.println(str_date);
				System.out.println(end_date);
				
				JLabel lbl = new JLabel(num + " " + name);
				lbl.setBounds(100, 100, 100, 30);
				lbl.setHorizontalAlignment(JLabel.CENTER);
				frm.getContentPane().add(lbl);
			}
		} catch (SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
		} catch (ClassNotFoundException e1) {
			System.err.println("[JDBC Connector Driver ���� : " + e1.getMessage() + "]");
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
		 		
			
		// �� ����
		JLabel lbl = new JLabel("�й�");
		lbl.setBounds(50, 35, 100, 30);
		lbl.setHorizontalAlignment(JLabel.CENTER);
		frm.getContentPane().add(lbl);
		
		
		frm.setVisible(true); // �������� ���̵��� ����
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
