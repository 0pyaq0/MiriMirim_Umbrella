package Play;

//ÀÔ·ÂµÈ Á¤º¸ DB¿¡ º¸³¿

import java.util.Calendar;

import javax.swing.JOptionPane;

public class DTO {
	String num; // ÇĞ¹ø
	String name; // ÀÌ¸§
	String phone; // ÀüÈ­¹øÈ£
	String str_date; // ´ë¿© ³¯Â¥
	String end_date; // ¹İ³³ ³¯Â¥

	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		if(num.length() > 4)  { 
			JOptionPane.showMessageDialog(null, "ÇĞ¹ø 4ÀÚ¸® ¼ö¸¦ ÃÊ°úÇÏ¿´½À´Ï´Ù");
     } else {
     	this.num = num;
     }
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		String chk = "/^[°¡-ÆR]+$/";
		/*if(name == chk) {
			JOptionPane.showMessageDialog(null, "ÇÑ±Û·Î ÀÔ·ÂÇØÁÖ¼¼¿ä");
     } else {*/
     	this.name = name;
     //}
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		if(phone.length() != 11) {
			JOptionPane.showMessageDialog(null, "ÇÚµåÆù ¹øÈ£ 11ÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä");
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
