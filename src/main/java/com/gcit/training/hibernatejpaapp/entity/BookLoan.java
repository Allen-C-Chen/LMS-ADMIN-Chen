package com.gcit.training.hibernatejpaapp.entity;



import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_book_loans")
public class BookLoan {
	@EmbeddedId
	private BookLoanID bookLoanID;	
	
	@Temporal(TemporalType.TIMESTAMP)
	Date dateOut;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date dateReturn;

	
	public BookLoanID getBookLoanID() {
		return bookLoanID;
	}

	public void setBookLoanID(BookLoanID bookLoanID) {
		this.bookLoanID = bookLoanID;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}
	
}
