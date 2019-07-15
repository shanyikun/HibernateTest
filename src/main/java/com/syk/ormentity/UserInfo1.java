package com.syk.ormentity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users1")
public class UserInfo1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "ts")
	private LocalDateTime ts;
	
	public UserInfo1() {}
	public UserInfo1(String name, String password, LocalDateTime ts) {
		this.name = name;
		this.password = password;
		this.ts = ts;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	public void setTs(LocalDateTime ts) {
		this.ts = ts;
	}
	public LocalDateTime getTs() {
		return this.ts;
	}
}
