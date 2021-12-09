// �Ա�������Ʈ
package Play;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class returnUmbrella extends Frame implements ActionListener{
	JButton btn = new JButton("�޴�"); // �޴��� ���ư��� ��ư
	JTextArea txtResult = new JTextArea();
	
	JLabel lab = new JLabel();
	Connection conn = null; // DB ����
	Statement stmt = null; // ������ ����
	ResultSet rs = null;
	
	ImagePanel rentalPanel;
	
	returnUmbrella(){
		setTitle("�Ա��� ����Ʈ");
		rentalPanel = new ImagePanel(new ImageIcon(".//img//darkClouds.png").getImage());
		setLayout(new BorderLayout());
		
		laylnit(); // ���̾ƿ� �޼ҵ�
		
		setBounds(400, 400, 700, 400);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x��ư ������ ����
		
		String sql = "SELECT num, name, str_date, end_date FROM login WHERE end_date > today"; 
		// �ݳ����ڰ� ���ú��� ������ => ��ü�� �� : �װ͵鸸 ��ȸ����
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC ����̹� �ε�
			conn = DriverManager.getConnection("jdbc:mysql: //127.0.0.1:3306/greengrapeumbrella", "root", "0819");// 2. �����ͺ��̽� ����
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);
			int cnt = 1; // ��ȣ
			txtResult.setText(" ��ȣ\t �й�\t �̸�\t �뿩 ��¥\t\t �ݳ� ��¥\n");
			
			while(rs.next()) {
				Calendar today = Calendar.getInstance();
				sql = "UPDATE login set today = " + today;
				String num = rs.getString("num");
				String name = rs.getString("name");
				String str_date = rs.getString("str_date");
				String end_date = rs.getString("end_date");
								
				String str = " " + rs.getString("num") + "\t" + rs.getString("name") + "\t" + rs.getString("str_date") + "\t" + rs.getString("end_date") + "\n";
				txtResult.append("  " + cnt + "\t" + str);
				cnt++;
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
	 }

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}

	// ���̾ƿ�
	private void laylnit() {
		JPanel panel = new JPanel();
		panel.add(btn); // ��ư
		txtResult.setEditable(false);
		JScrollPane pane = new JScrollPane(txtResult); // ��ũ��
		
		add("Center", pane);
		add("North", panel);
		
		btn.addActionListener(this);
		
		
		rentalPanel.add(btn);
	}
	
	// DB �ε�
	/*private void accDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC ����̹� �ε�
		} catch (Exception e) {
			System.out.println("���� : " + e);
		}
	}*/
	
	// ��ư ����
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) {
		new returnUmbrella();
	}
}
