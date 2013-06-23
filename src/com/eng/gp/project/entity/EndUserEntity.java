package com.eng.gp.project.entity;

import static javax.persistence.TemporalType.DATE;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.eng.gp.project.util.date.Language;
import com.eng.gp.project.util.date.MeasurementSystem;

@Entity
@Table(name = "enduser", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class EndUserEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gpupSeq")
	@SequenceGenerator(name = "gpupSeq", sequenceName = "postgres_high_value", allocationSize = 5)
	@Column(name = "enduser_id")
	private Long ensUser;
	
	@OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
	
	  /** The username of the enduser. */
    @Column(name = "username", nullable = false, unique = true)
	private String username;
	
    /** The encoded password of the enduser. */
    @Column(name = "password", nullable = false)
    private String password;

    /** An enabled flag for the user. */
    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;
	
    /** The email address of the enduser. */
    @Column(name = "email", nullable = false)
    private String email;

    /** The email address of the enduser. */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /** The email address of the enduser. */
    @Column(name = "last_name", nullable = false)
    private String lastName;
	
	 /** A flag indicating whether the user is locked out or not */
    @Column(name = "not_locked_out", nullable = false)
    private boolean notLockedOut = true;

    /** A flag indicating if the user must reset his password */
    @Column(name = "reset_password", nullable = false)
    private boolean resetPassword = true;

    /** The numer of failed login attmepts since the last successful login */
    @Column(name = "num_failed_logins", nullable = false)
    private int numFailedLogins;
	
	@Column(name="last_login_date")
	private Date lastLoginDate;
	
	 /** The last time there was a failed login attempt */
    @Column(name = "last_failed_attempt_date")
    @Type(type = "com.eng.gp.project.ext.UtcTimestampType")
    @Temporal(DATE)
    private Date lastFailedAttemptDate;

	
	@Column(name="last_read_news_file_date")
	private Date lastReadNewsFileDate;
	
	@Column(name = "language", nullable = false)
	@Enumerated(EnumType.STRING)
	private Language language;
	
	@Column(name = "measurement_system", nullable = false)
    @Enumerated(EnumType.STRING)
    private MeasurementSystem measurementSystem;
	
	 /** Creation date for this record.  This is set in the db via a trigger. */
    @Column(name = "creation_date", insertable = false, updatable = false)
    @Type(type = "com.eng.gp.project.ext.UtcTimestampType")
    @Temporal(DATE)
    private Date creationDate;
	
	@Column(name="modification_date")
	private String modification_date;
	
	// setters and getters

	public Long getEnsUser() {
		return ensUser;
	}

	public void setEnsUser(Long ensUser) {
		this.ensUser = ensUser;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

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

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public MeasurementSystem getMeasurementSystem() {
		return measurementSystem;
	}

	public void setMeasurementSystem(MeasurementSystem measurementSystem) {
		this.measurementSystem = measurementSystem;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getModification_date() {
		return modification_date;
	}

	public void setModification_date(String modification_date) {
		this.modification_date = modification_date;
	}
	
}
