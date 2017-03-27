package com.hik.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ApiModel
@Entity
public class User {
    @Id
    @GeneratedValue
    @ApiModelProperty(value="用户唯一标识",required=true)
    private Long id;
    @Column(nullable = false)
    @ApiModelProperty(value="这是用户名的描述",example="小明")
    private String name;
    @Column(nullable = false)
    @ApiModelProperty(value="这是年龄的描述",example="11")
    private Integer age;
    public User(){}
	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	/**
	 * @see #id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @see #id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @see #name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @see #name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @see #age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @see #age 
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
    
}
