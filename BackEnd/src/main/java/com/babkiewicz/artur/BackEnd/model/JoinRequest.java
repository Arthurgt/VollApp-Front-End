package com.babkiewicz.artur.BackEnd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="JoinRequest")
public class JoinRequest implements Serializable {
	private static final long serialVersionUID = -5773883199003397922L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOINREQUEST_SEQ")
	@SequenceGenerator(name = "JOINREQUEST_SEQ", sequenceName = "JOINREQUEST_SEQ", allocationSize = 1, initialValue=1)
	private Long id;
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="team_id")
	private Team team;
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	@JoinColumn(name="enabled")
	private Integer enabled;
	
	public JoinRequest() {
		
	}
	public JoinRequest(Team team, User user, Integer enabled) {
		super();
		this.team = team;
		this.user = user;
		this.enabled = enabled;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Team getTeam() {
		return this.team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}
