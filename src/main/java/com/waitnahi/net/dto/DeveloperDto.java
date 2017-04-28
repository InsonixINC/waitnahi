package com.waitnahi.net.dto;

/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
public class DeveloperDto {

	private Long id;

	private String fullName;

	private String profile;

	public DeveloperDto(Long id, String fullName, String profile) {
		this.id = id;
		this.fullName = fullName;
		this.profile = profile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
