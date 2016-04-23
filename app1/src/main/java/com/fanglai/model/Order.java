package com.fanglai.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_order")
public class Order implements Serializable{
	private String id;
	private String name;
	
	private Test test;

	@Id
	@GeneratedValue(generator = "uuidGenerator")       
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Column(name="id", nullable=false, length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(nullable=false,length=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name="testId",nullable=false)
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
}
