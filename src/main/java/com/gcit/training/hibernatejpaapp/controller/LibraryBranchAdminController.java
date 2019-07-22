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

import com.gcit.training.hibernatejpaapp.dao.LibraryBranchDao;
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;
import com.gcit.training.hibernatejpaapp.entity.Publisher;
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;

@RestController
@RequestMapping("/lms/admin")
public class LibraryBranchAdminController {
	@Autowired
	private LibraryBranchDao libraryBranchDao;
	@GetMapping("/librarybranch")
	public List<LibraryBranch> getAllLibraryBranch() {
		return libraryBranchDao.findAll();
	}
	// Create a new Note
	@PostMapping("/librarybranch") //C
	public ResponseEntity<LibraryBranch> createLibraryBranch(@Valid @RequestBody LibraryBranch libraryBranch) {

		try {
			if(libraryBranch.getBranchAddress() == null || libraryBranch.getBranchName() == null) {
			    return new ResponseEntity<LibraryBranch>(HttpStatus.BAD_REQUEST); //400

			}
			LibraryBranch newLibraryBranch =  libraryBranchDao.save(libraryBranch);

			return new ResponseEntity<LibraryBranch>(newLibraryBranch, HttpStatus.CREATED); //204
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<LibraryBranch>(HttpStatus.NOT_FOUND); //404
		}
	}
	
	@GetMapping("/librarybranch/{id}") //R
	public ResponseEntity<LibraryBranch> getLibraryBranchByID(@PathVariable Integer id) {
		Optional<LibraryBranch> libraryBranch = libraryBranchDao.findById(id);

		try {
			return new ResponseEntity<LibraryBranch>(libraryBranch.get(), HttpStatus.OK); 
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<LibraryBranch>(HttpStatus.NOT_FOUND); //404
		}
	}
	@PutMapping("/librarybranch") //U
	@ResponseStatus(HttpStatus.CREATED)
	public void updateLibraryBranch(@Valid @RequestBody LibraryBranch libraryBranch) {
		libraryBranchDao.save(libraryBranch);
	}
	
	@DeleteMapping("/librarybranch/{id}") //D
	public ResponseEntity<LibraryBranch> deleteLibraryBranch(@PathVariable Integer id) {
		try {
			libraryBranchDao.deleteById(id);
		    return new ResponseEntity<LibraryBranch>(HttpStatus.NO_CONTENT); //204
		}
		catch(EmptyResultDataAccessException e){
			return new ResponseEntity<LibraryBranch>(HttpStatus.BAD_REQUEST); //404
		}
	}

}
