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
import com.gcit.training.hibernatejpaapp.service.BookLoanService;

@RestController
@RequestMapping("/lms/admin")
public class BookLoanAdminController {
	@Autowired
	private BookLoanService bookLoanService;

	
	@GetMapping(value = "/loaned-books",
			produces = {"application/json", "application/xml"}) 
	public ResponseEntity<List<BookLoan>> getAllBookLoans() {
		return bookLoanService.getAllBookLoans();
	}
	//borrower/{cardNo}/book
	@GetMapping(
			value = "/loaned-book/librarybranch/{libBranchId}/book/{bookId}/borrower/{cardNo}",
			produces = {"application/json", "application/xml"})//R
	public ResponseEntity<BookLoan> getBookLoanByID(@PathVariable Integer libBranchId,
			@PathVariable Integer bookId,
			@PathVariable Integer cardNo) {
	    return bookLoanService.getBookLoanById(libBranchId, bookId, cardNo);
	}
	@PutMapping(
			value = "/loaned-book/librarybranch/{libBranchId}/book/{bookId}/borrower/{cardNo}/override/checkoutdate",
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})  //U
	public ResponseEntity<BookLoan> overrideCheckOutDate(
			@PathVariable Integer libBranchId,
			@PathVariable Integer bookId,
			@PathVariable Integer cardNo,
			@RequestBody BookLoan bookLoanData) {
			return bookLoanService.overrideCheckOutDate(
					libBranchId, bookId, cardNo, bookLoanData);
		}
	// Create a new Note
	@PostMapping(value = "/loaned-book",
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})//C
	public ResponseEntity<BookLoan> createBookLoan(@Valid @RequestBody BookLoan bookLoan) {
		return bookLoanService.createNewBookLoan(bookLoan);
	}
	@DeleteMapping(value = "/loaned-book/librarybranch/{libBranchId}/book/{bookId}/borrower/{cardNo}")
	public ResponseEntity<BookLoan> deleteBookLoan(@PathVariable Integer libBranchId,
			@PathVariable Integer bookId,
			@PathVariable Integer cardNo) {
		return bookLoanService.deleteBookLoan(libBranchId, bookId, cardNo);
	}

}
