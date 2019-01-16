package com.babkiewicz.artur.BackEnd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="match")
public class Match implements Serializable {
	private static final long serialVersionUID = 4295864315759767663L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATCH_SEQ")
	@SequenceGenerator(name = "MATCH_SEQ", sequenceName = "MATCH_SEQ", allocationSize = 1)
	private Long id;
	@Temporal(TemporalType.DATE)
	@Column(name="match_date")
	private Date match_date;
	@Column(name="hour")
	private String hour;
	@Column(name="minute")
	private String minute;
	@Column(name="status")
	private String status;
	@Column(name="city")
	private String city;
	@Column(name="court")
	private String court;
	@Column(name="street")
	private String street;
	@Column(name="nr")
	private String nr;
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="team1id")
	private Team team1;
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="team2id")
	private Team team2;
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "match")
    private List<PlayRequest> playRequests = new ArrayList<PlayRequest>();
	
	public Match(){		
	}
	public Match(Date match_date, String status, Team team1, Team team2, String court, String city, String street, String nr, String hour, String minute, List<PlayRequest> playRequests) {
		super();
		this.match_date = match_date;
		this.status = status;
		this.team1 = team1;
		this.team2 = team2;
		this.court = court;
		this.city = city;
		this.street = street;
		this.nr = nr;
		this.hour = hour;
		this.minute = minute;
		this.playRequests = playRequests;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return match_date;
	}
	public void setDate(Date match_date) {
		this.match_date = match_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Team getTeam1() {
		return team1;
	}
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}
	public Team getTeam2() {
		return team2;
	}
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	public Date getMatch_date() {
		return match_date;
	}
	public void setMatch_date(Date match_date) {
		this.match_date = match_date;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public List<PlayRequest> getPlayRequests() {
		return playRequests;
	}
	public void setPlayRequests(List<PlayRequest> playRequests) {
		this.playRequests = playRequests;
	}
}