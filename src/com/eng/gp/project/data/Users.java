package com.eng.gp.project.data;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name= "users")
@Entity
public class Users {

	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_password")
	private String password;
	
	@Column(name="user_email")
	private String emailid;
	
	@Column(name="user_phone")
	private String mobile;
	
	@Column(name="user_days")
	private String days;
	
	
	
	
	
	
	
	
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	
	
}
