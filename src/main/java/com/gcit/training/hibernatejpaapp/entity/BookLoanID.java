package com.gcit.training.hibernatejpaapp.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;

@Embeddable
public class BookLoanID implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@ManyToOne
	@JoinColumn (name = "branchId")
	private LibraryBranch branchID;
	@NotNull
	@ManyToOne
	@JoinColumn (name = "bookId")
	private Book book;

	@NotNull
	@ManyToOne
	@JoinColumn (name = "cardNo")
	private Borrower borrower;
	
	
	public BookLoanID() {
		super();
	}
	public BookLoanID(@NotNull LibraryBranch branchID, @NotNull Book book, @NotNull Borrower borrower) {
		super();
		this.branchID = branchID;
		this.book = book;
		this.borrower = borrower;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LibraryBranch getBranchID() {
		return branchID;
	}
	public void setBranchID(LibraryBranch branchID) {
		this.branchID = branchID;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((borrower == null) ? 0 : borrower.hashCode());
		result = prime * result + ((branchID == null) ? 0 : branchID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookLoanID other = (BookLoanID) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (borrower == null) {
			if (other.borrower != null)
				return false;
		} else if (!borrower.equals(other.borrower))
			return false;
		if (branchID == null) {
			if (other.branchID != null)
				return false;
		} else if (!branchID.equals(other.branchID))
			return false;
		return true;
	}
	

}
