package com.hik.domain.secondary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String content;
    public Message(){}
    public Message(String name, String content) {
        this.name = name;
        this.content = content;
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
	 * @see #content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @see #content 
	 */
	public void setContent(String content) {
		this.content = content;
	}
    
}
