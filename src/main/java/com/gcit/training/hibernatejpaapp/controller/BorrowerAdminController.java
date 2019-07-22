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

import com.gcit.training.hibernatejpaapp.dao.BorrowerDao;
import com.gcit.training.hibernatejpaapp.entity.Borrower;
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;

@RestController
@RequestMapping("/lms/admin")

public class BorrowerAdminController {
	@Autowired
	private BorrowerDao borrowerDao;
	@GetMapping("/borrower")
	public List<Borrower> getAllBorrower() {
	    return borrowerDao.findAll();
	}
	
	// Create a new Note
	@PostMapping("/borrower") //C
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Borrower> createNote(@Valid @RequestBody Borrower borrower) {	
		try {
			if(borrower.getName() == null || borrower.getPhone() == null || borrower.getAddress() == null) {
			    return new ResponseEntity<Borrower>(HttpStatus.BAD_REQUEST); //400
			}
			Borrower newBorrower = borrowerDao.save(borrower);
			return new ResponseEntity<Borrower>(newBorrower, HttpStatus.CREATED); //204

		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<Borrower>(HttpStatus.NOT_FOUND); //404
		}
	}
	
	@GetMapping("/borrower/{id}") //R
	public ResponseEntity<Borrower> getBorrowerByID(@PathVariable Integer id) {
		Optional<Borrower> borrower = borrowerDao.findById(id);
		try {
		    return new ResponseEntity<Borrower>(borrower.get(), HttpStatus.OK); //200
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<Borrower>(HttpStatus.NOT_FOUND); //404
		}
	}
	@PutMapping("/borrower") //U
	@ResponseStatus(HttpStatus.CREATED)
	public @Valid Borrower  updateBorrower(@Valid @RequestBody Borrower borrower) {
		return borrowerDao.save(borrower);		
	}
	
	@DeleteMapping("/borrower/{id}") //D
	public ResponseEntity<Borrower> deletePost(@PathVariable Integer id){
		try {
			borrowerDao.deleteById(id);
		    return new ResponseEntity<Borrower>(HttpStatus.NO_CONTENT); //204

		}
		catch(EmptyResultDataAccessException e){
			return new ResponseEntity<Borrower>(HttpStatus.BAD_REQUEST); //404
		}
	}
}
