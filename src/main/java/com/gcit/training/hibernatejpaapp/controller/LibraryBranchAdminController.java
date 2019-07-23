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
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;
import com.gcit.training.hibernatejpaapp.service.LibraryBranchService;
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;

@RestController
@RequestMapping(value = "/lms/admin")
public class LibraryBranchAdminController {
	@Autowired
	private LibraryBranchService libraryBranchService;
	// Create a new Note
	@GetMapping(value = "/librarybranchs" ,
			produces = {"application/json", "application/xml"}) 
	public ResponseEntity<List<LibraryBranch>> getAllLibraryBranchs() {
		return libraryBranchService.getAllLibraryBranchs();
	}
	
	@PostMapping(value = "/librarybranch",
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})//C
	public ResponseEntity<LibraryBranch> createLibraryBranch(@Valid @RequestBody LibraryBranch libraryBranch) {
				return libraryBranchService.createNewLibraryBranch(libraryBranch);
	}
	@GetMapping(path = "/librarybranch/{id}",  
			produces = {"application/json", "application/xml"})  //R
	public ResponseEntity<LibraryBranch> getLibraryBranchByID(@PathVariable Integer id) {
		return libraryBranchService.getLibraryBranchById(id);
	}
	
	@PutMapping(value = "/librarybranch/{id}",
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})//U
	public ResponseEntity<LibraryBranch> updateLibraryBranch(
			@PathVariable Integer id,
			@Valid @RequestBody LibraryBranch libraryBranch) {
		return libraryBranchService.updateLibraryBranch(id, libraryBranch);
	}
	@DeleteMapping(value = "/librarybranch/{id}") //D
	public ResponseEntity<LibraryBranch> deleteLibraryBranch(@PathVariable Integer id){
		return libraryBranchService.deleteLibraryBranch(id);
	}




	
}
