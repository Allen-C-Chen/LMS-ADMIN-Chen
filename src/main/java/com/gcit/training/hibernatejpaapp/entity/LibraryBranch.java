package com.gcit.training.hibernatejpaapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_library_branch")
public class LibraryBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto
	private Integer branchID;
    @Column(name = "branchName")
	private String branchName;
    @Column(name = "branchAddress")
	private String branchAddress;
    
    
	public LibraryBranch() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LibraryBranch(Integer branchID) {
		super();
		this.branchID = branchID;
	}

	public LibraryBranch(Integer branchID, String branchName, String branchAddress) {
		super();
		this.branchID = branchID;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}

	public LibraryBranch(String branchName, String branchAddress) {
		super();
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}

	public Integer getBranchID() {
		return branchID;
	}
	public void setBranchID(Integer branchID) {
		this.branchID = branchID;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

    
}
