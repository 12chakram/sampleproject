package com.eng.gp.project.domain;

import java.util.Date;

import com.eng.gp.project.util.date.MeasurementSystem;

public class EndUser {
	

	private Long endUser;
    private Long role;
	private String username;
    private String password;
    private boolean enabled = true;
    private String email;
    private String firstName;
    private String lastName;
    private boolean notLockedOut = true;
    private boolean resetPassword = true;
    private int numFailedLogins;
	private Date lastLoginDate;
    private Date lastFailedAttemptDate;
	private Date lastReadNewsFileDate;
	private String language;
    private MeasurementSystem measurementSystem;
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isNotLockedOut() {
		return notLockedOut;
	}
	public void setNotLockedOut(boolean notLockedOut) {
		this.notLockedOut = notLockedOut;
	}
	public boolean isResetPassword() {
		return resetPassword;
	}
	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}
	public int getNumFailedLogins() {
		return numFailedLogins;
	}
	public void setNumFailedLogins(int numFailedLogins) {
		this.numFailedLogins = numFailedLogins;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getLastFailedAttemptDate() {
		return lastFailedAttemptDate;
	}
	public void setLastFailedAttemptDate(Date lastFailedAttemptDate) {
		this.lastFailedAttemptDate = lastFailedAttemptDate;
	}
	public Date getLastReadNewsFileDate() {
		return lastReadNewsFileDate;
	}
	public void setLastReadNewsFileDate(Date lastReadNewsFileDate) {
		this.lastReadNewsFileDate = lastReadNewsFileDate;
	}
	
	public MeasurementSystem getMeasurementSystem() {
		return measurementSystem;
	}
	public void setMeasurementSystem(MeasurementSystem measurementSystem) {
		this.measurementSystem = measurementSystem;
	}
	public Long getRole() {
		return role;
	}
	public void setRole(Long role) {
		this.role = role;
	}
	public Long getEndUser() {
		return endUser;
	}
	public void setEndUser(Long endUser) {
		this.endUser = endUser;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	
}
