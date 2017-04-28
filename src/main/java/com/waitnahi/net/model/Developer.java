package com.waitnahi.net.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
@Entity
@Table(name = "DEVELOPER")
public class Developer {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "FULLNAME")
	private String fullName;

	@Column(name = "PROFILE")
	private String profile;

	public Developer() {
	}

	public Developer(Long id, String fullName, String profile) {
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
