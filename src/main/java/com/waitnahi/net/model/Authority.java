package com.waitnahi.net.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AUTHORITY")
public class Authority {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", length = 50)
	@NotNull
	@Enumerated(EnumType.STRING)
	private AuthorityName name;

	@OneToMany(mappedBy = "authority", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserAuthority> userAuthorities;

	/*
	 * @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY) private
	 * List<User> users;
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}

	public List<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(List<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	/*
	 * public List<User> getUsers() { return users; }
	 * 
	 * public void setUsers(List<User> users) { this.users = users; }
	 */

}