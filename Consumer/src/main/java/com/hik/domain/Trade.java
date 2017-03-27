package com.hik.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Trade {
	@Id
	private Long id;
	@Column(nullable = false)
	private Type opt;
	@Column(nullable = false)
	private Long userid;
	@Column(nullable = false)
	private Long value;
	@Column(nullable = false)
	private boolean success = false;
	public Trade(){}
	public Trade(Long id, Type opt, Long userid, Long value) {
		this.id = id;
		this.opt = opt;
		this.userid = userid;
		this.value = value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Type getOpt() {
		return opt;
	}
	public void setOpt(Type opt) {
		this.opt = opt;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	static enum Type{
		ADD,
		REDUCE;
	}
}
