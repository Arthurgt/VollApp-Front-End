package com.babkiewicz.artur.BackEnd.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = -5691837983747283884L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
	@SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1, initialValue=1)
	private Long id;
	@Column(name="firstName")
	private String firstName;
	@Column(name="lastName")
	private String lastName;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="Role")
	private String Role;
	@Column(name="enabled")
	private boolean enabled;
	
	public User() {
	}	
	public User(String firstName, String lastName, String email, String password, String role, boolean enabled) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.Role = role;
		this.enabled = enabled;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String Role) {
		this.Role = Role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
