package com.gcit.training.hibernatejpaapp.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gcit.training.hibernatejpaapp.dao.BookDao;
import com.gcit.training.hibernatejpaapp.dao.BookLoanDao;
import com.gcit.training.hibernatejpaapp.dao.BorrowerDao;
import com.gcit.training.hibernatejpaapp.dao.LibraryBranchDao;
import com.gcit.training.hibernatejpaapp.entity.Book;
import com.gcit.training.hibernatejpaapp.entity.BookLoan;
import com.gcit.training.hibernatejpaapp.entity.BookLoanID;
import com.gcit.training.hibernatejpaapp.entity.Borrower;
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;
@Service
public class BookLoanService {
 //finish bookloanloan tomrorow
	@Autowired
	private BookLoanDao bookLoanDao;
	
	@Autowired
	private BookDao bookDao;
	@Autowired
	private BorrowerDao borrowerDao;
	@Autowired
	private LibraryBranchDao libraryBranchDao;
	
	@Transactional
	public ResponseEntity<List<BookLoan>> getAllBookLoans() {
		List<BookLoan> bookloans = bookLoanDao.findAll();
		return new ResponseEntity<List<BookLoan>>(bookloans, HttpStatus.OK); 
	}
	@Transactional
	public ResponseEntity<BookLoan> createNewBookLoan(BookLoan newBookLoan) {
		try {
			BookLoan bookloan =  bookLoanDao.save(newBookLoan);
			return new ResponseEntity<BookLoan>(bookloan, HttpStatus.CREATED); //201
		}
		catch(DataAccessException e){
		    return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST); //404
		}
	}
	@Transactional
	public ResponseEntity<BookLoan> getBookLoanById(int libId, int bookId, int cardNo) {
		try {
			BookLoan bookLoan = bookLoanDao.findById(
					new BookLoanID(
							libraryBranchDao.findById(libId).get(),
							bookDao.findById(bookId).get(),
							borrowerDao.findById(cardNo).get())).get();
			return new ResponseEntity<BookLoan>(bookLoan, HttpStatus.OK); 

		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<BookLoan>( HttpStatus.NOT_FOUND);
		}
	}
	@Transactional
	public ResponseEntity<BookLoan> overrideCheckOutDate(int libBranchId, int bookId, int cardNo,
			BookLoan bookLoanData) {
		try {
			Optional<BookLoan> bookLoan = bookLoanDao.findById(
					new BookLoanID(
					libraryBranchDao.findById(libBranchId).get(), 
					bookDao.findById(bookId).get(), 
					borrowerDao.findById(cardNo).get()));
			if(bookLoan == null) {
			    return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND); //404
			}
			bookLoan.get().setDateReturn(bookLoanData.getDateReturn());
			bookLoanDao.save(bookLoan.get());	
			return new ResponseEntity<BookLoan>(HttpStatus.CREATED); 
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<BookLoan>( HttpStatus.NOT_FOUND);
		}

	}
	@Transactional
	public ResponseEntity<BookLoan> deleteBookLoan(int libBranchId, int bookId, int cardNo) {
		try {
			bookLoanDao.deleteById(
					new BookLoanID(
					libraryBranchDao.findById(libBranchId).get(), 
					bookDao.findById(bookId).get(), 
					borrowerDao.findById(cardNo).get()));
		    return new ResponseEntity<BookLoan>(HttpStatus.NO_CONTENT); //204
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<BookLoan>( HttpStatus.NOT_FOUND);
		}
	}
	
}
