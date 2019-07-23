package com.gcit.training.hibernatejpaapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.training.hibernatejpaapp.dao.BookDao;
import com.gcit.training.hibernatejpaapp.dao.BookLoanDao;
import com.gcit.training.hibernatejpaapp.dao.BorrowerDao;
import com.gcit.training.hibernatejpaapp.dao.LibraryBranchDao;
import com.gcit.training.hibernatejpaapp.entity.Author;
import com.gcit.training.hibernatejpaapp.entity.BookLoan;
import com.gcit.training.hibernatejpaapp.entity.BookLoanID;
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;

@RestController
@RequestMapping("/lms/admin")
public class BookLoanAdminController {
	@Autowired
	private BookLoanDao bookLoanDao;
	
	@Autowired
	private BookDao bookDao;
	@Autowired
	private BorrowerDao borrowerDao;
	@Autowired
	private LibraryBranchDao libraryBranchDao;
	
	@GetMapping("/book/checkout")//fix name
	public List<BookLoan> getAllBookLoans() {
		return bookLoanDao.findAll();
	}
	//borrower/{cardNo}/book
	@GetMapping("/bookloan/librarybranch/{libBranchId}/book/{bookId}/borrower/{cardNo}")
	public ResponseEntity<BookLoan> getBookLoanByID(@PathVariable Integer libBranchId,
			@PathVariable Integer bookId,
			@PathVariable Integer cardNo) {
		
		if(libBranchId == null || bookId == null || cardNo == null) {
			return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST);
		}

		try {
			Optional<BookLoan> bookLoan = bookLoanDao.findById(
					new BookLoanID(
					libraryBranchDao.findById(libBranchId).get(), 
					bookDao.findById(bookId).get(), 
					borrowerDao.findById(cardNo).get()));
			
			return new ResponseEntity<BookLoan>(bookLoan.get(), HttpStatus.OK); 
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND); //404
		}
	}
	@PutMapping("/bookloan/librarybranch/{libBranchId}/book/{bookId}/borrower/{cardNo}/override/checkoutdate")
	public ResponseEntity<BookLoan> overrideCheckOutDate(
			@PathVariable Integer libBranchId,
			@PathVariable Integer bookId,
			@PathVariable Integer cardNo,
			@RequestBody BookLoan bookLoanData) {
		if(libBranchId == null || bookId == null || cardNo == null || bookLoanData == null) {
			return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST);
		}
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
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND); //404
		}
	}
	// Create a new Note
	@PostMapping("/bookloan") //C
	public ResponseEntity<BookLoan> createBookLoan(@Valid @RequestBody BookLoan bookLoan) {

		try {
			if(bookLoan.getBookLoanID() == null || bookLoan.getDateOut() == null 
					|| bookLoan.getDateReturn() == null) {
			    return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST); //400

			}
			BookLoan newBookLoan =  bookLoanDao.save(bookLoan);
			return new ResponseEntity<BookLoan>(newBookLoan, HttpStatus.CREATED); //201
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND); //404
		}
	}
	@DeleteMapping("/bookloan/librarybranch/{libBranchId}/book/{bookId}/borrower/{cardNo}")
	public ResponseEntity<BookLoan> deleteBookLoan(@PathVariable Integer libBranchId,
			@PathVariable Integer bookId,
			@PathVariable Integer cardNo) {
		if(libBranchId == null || bookId == null || cardNo == null) {
			return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST);
		}
		try {
			bookLoanDao.deleteById(
					new BookLoanID(
					libraryBranchDao.findById(libBranchId).get(), 
					bookDao.findById(bookId).get(), 
					borrowerDao.findById(cardNo).get()));
		    return new ResponseEntity<BookLoan>(HttpStatus.NO_CONTENT); //204
		}
		catch(EmptyResultDataAccessException e){
			return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST); //404 for deleting something deleting
		}
	}

}
