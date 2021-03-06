package Play;

//입력된 정보 DB에 보냄

import java.util.Calendar;

import javax.swing.JOptionPane;

public class DTO {
	String num; // 학번
	String name; // 이름
	String phone; // 전화번호
	String str_date; // 대여 날짜
	String end_date; // 반납 날짜

	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		if(num.length() > 4)  { 
			JOptionPane.showMessageDialog(null, "학번 4자리 수를 초과하였습니다");
     } else {
     	this.num = num;
     }
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		String chk = "/^[가-힣]+$/";
		/*if(name == chk) {
			JOptionPane.showMessageDialog(null, "한글로 입력해주세요");
     } else {*/
     	this.name = name;
     //}
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		if(phone.length() != 11) {
			JOptionPane.showMessageDialog(null, "핸드폰 번호 11자를 입력해주세요");
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
