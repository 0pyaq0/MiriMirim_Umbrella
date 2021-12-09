package Play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class loginOk {
	private loginOk() {}
	private static loginOk instance=new loginOk();
	public static loginOk getInstance() {
		return instance;
	}
	
	private Connection conn; //DB ���� ��ü
	private PreparedStatement pstmt; //Query �ۼ� ��ü
	private ResultSet rs; //Query ��� Ŀ��
	
	//���� 1, ���� -1, ���� 0
	public int findByUsernameAndPassword(String num, String name) {
		//1. DB ����
		//conn = DBConnection.getConnection();
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
}
