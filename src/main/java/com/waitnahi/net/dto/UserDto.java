package com.waitnahi.net.dto;

import java.util.Date;
import java.util.List;

import com.waitnahi.net.model.UserAuthority;

/**
 * @author pramod
 * @since 15-Apr-2017
 */
public class UserDto {

	private Long userId;

	private String username;

	private String password;

	private String email;

	private Boolean enabled;

	private Date lastPasswordResetDate;

	private List<UserAuthority> userAuthority;
	

	/**
	 * Instantiates a new user dto.
	 *
	 * @param userId the user id
	 * @param username the username
	 * @param password the password
	 * @param email the email
	 * @param enabled the enabled
	 * @param lastPasswordResetDate the last password reset date
	 * @param userAuthority the user authority
	 */
	public UserDto(Long userId, String username, String password, String email, Boolean enabled,
			Date lastPasswordResetDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public Long getUserId() {
		return userId;
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

	public List<UserAuthority> getUserAuthority() {
		return userAuthority;
	}

	public void setUserAuthority(List<UserAuthority> userAuthority) {
		this.userAuthority = userAuthority;
	}

}
