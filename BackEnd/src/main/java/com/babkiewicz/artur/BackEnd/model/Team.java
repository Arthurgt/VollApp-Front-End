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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="team")
public class Team implements Serializable {


	private static final long serialVersionUID = -8643521868436252674L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_SEQ")
	@SequenceGenerator(name = "TEAM_SEQ", sequenceName = "TEAM_SEQ", allocationSize = 1)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="info")
	private String info;
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "team")
    private List<User> players = new ArrayList<User>();
	
	public Team() {	
	}
	public Team(String name, String info, List<User> players) {
		super();
		this.name = name;
		this.info = info;
		this.players = players;
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<User> getPlayers() {
		return players;
	}
	public void setPlayers(List<User> players) {
		this.players = players;
	}
}
