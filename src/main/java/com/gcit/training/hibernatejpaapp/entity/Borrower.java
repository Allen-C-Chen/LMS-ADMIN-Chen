package com.gcit.training.hibernatejpaapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_borrower")
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto
	private int cardNo; //Primary Key, Not Null
    @Column(name = "name")
	private String name;
    @Column(name = "address")
	private String address;
    @Column(name = "phone")
	private String phone;
	public int getCardNo() {
		return cardNo;
	}
	
	public Borrower() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Borrower(int cardNo) {
		super();
		this.cardNo = cardNo;
	}

	public Borrower(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public Borrower(int cardNo, String name, String address, String phone) {
		super();
		this.cardNo = cardNo;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
	
	
}
