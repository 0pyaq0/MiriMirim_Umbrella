package Play;

//�Էµ� ���� DB�� ����

import java.util.Calendar;

import javax.swing.JOptionPane;

public class DTO {
	String num; // �й�
	String name; // �̸�
	String phone; // ��ȭ��ȣ
	String str_date; // �뿩 ��¥
	String end_date; // �ݳ� ��¥

	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		if(num.length() > 4)  { 
			JOptionPane.showMessageDialog(null, "�й� 4�ڸ� ���� �ʰ��Ͽ����ϴ�");
     } else {
     	this.num = num;
     }
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		String chk = "/^[��-�R]+$/";
		/*if(name == chk) {
			JOptionPane.showMessageDialog(null, "�ѱ۷� �Է����ּ���");
     } else {*/
     	this.name = name;
     //}
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		if(phone.length() != 11) {
			JOptionPane.showMessageDialog(null, "�ڵ��� ��ȣ 11�ڸ� �Է����ּ���");
		} else {
			this.phone = phone;
		}
	}
	
	public String getstrDate() {
		return str_date;
	}
	public void setstrDate(String str_date) {
		this.str_date = str_date;
	}
	
	public String getendDate() {
		return end_date;
	}
	public void setendDate(String end_time) {
		this.end_date = end_time;
	}
	
}
