package com.babkiewicz.artur.BackEnd.model;

import java.io.Serializable;

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
@Table(name="PlayRequest")
public class PlayRequest implements Serializable {

	private static final long serialVersionUID = -7808287822329514476L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYREQUEST_SEQ")
	@SequenceGenerator(name = "PLAYREQUEST_SEQ", sequenceName = "PLAYREQUEST_SEQ", allocationSize = 1, initialValue=1)
	private Long id;
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="team_id")
	private Team team;
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="match_id")
	private Match match;
	@JoinColumn(name="enabled")
	private Integer enabled;
	
	public PlayRequest(){
	}

	public PlayRequest(Team team, Match match, Integer enabled) {
		super();
		this.team = team;
		this.match = match;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
}
