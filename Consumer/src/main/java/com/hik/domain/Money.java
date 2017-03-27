package com.hik.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Money {

	@Id
	private Long userid;
	@Column(nullable = false)
	private Long value;
	public Money(){}
	public Money(Long value, Long userid) {
		this.value = value;
		this.userid = userid;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
}
