package com.waitnahi.net.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author pramod
 * @since 15-Apr-2017
 */
@Entity(name = "USER")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1383246568133920964L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@Column(name = "ENABLED")
	@NotNull
	private Boolean enabled;

	@Column(name = "LASTPASSWORDRESETDATE")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date lastPasswordResetDate;

	//@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserAuthority> userAuthority;

	/*
	 * @ManyToMany(fetch = FetchType.LAZY)
	 * 
	 * @JoinTable(name = "USER_AUTHORITY", joinColumns = {
	 * 
	 * @JoinColumn(name = "USER_ID", referencedColumnName = "ID") },
	 * inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID") })
	 * private List<Authority> authorities;
	 */
	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * @param userId
	 * @param username
	 * @param password
	 * @param email
	 */
	
	public Long getUserId() {
		return userId;
	}

	public User(Long userId, String username, String password, String email, Boolean enabled,
			Date lastPasswordResetDate, List<UserAuthority> userAuthority) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.userAuthority = userAuthority;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	/*
	 * public List<Authority> getAuthorities() { return authorities; }
	 * 
	 * public void setAuthorities(List<Authority> authorities) {
	 * this.authorities = authorities; }
	 */

	public List<UserAuthority> getUserAuthority() {
		return userAuthority;
	}

	public void setUserAuthority(List<UserAuthority> userAuthority) {
		this.userAuthority = userAuthority;
	}

}
