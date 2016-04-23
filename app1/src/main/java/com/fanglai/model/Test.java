package com.fanglai.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_test",uniqueConstraints={@UniqueConstraint(columnNames={"sex","birthday"})})
public class Test implements Serializable {
	
	private String id;
	private String name;
	private byte sex;
	private Date birthday;
	
	private List<Order> orders;
	
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
	
	@Column(length=10,nullable=false,unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@OneToMany(mappedBy="test")
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
