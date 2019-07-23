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

import com.gcit.training.hibernatejpaapp.dao.AuthorDao;
import com.gcit.training.hibernatejpaapp.entity.Author;
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;
import com.gcit.training.hibernatejpaapp.service.AuthorService;
import com.gcit.training.hibernatejpaapp.entity.Author;

@RestController
@RequestMapping(value = "/lms/admin")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	// Create a new Note
	@GetMapping(value = "/authors" ,
			produces = {"application/json", "application/xml"}) 
	public ResponseEntity<List<Author>> getAllAuthors() {
		return authorService.getAllAuthors();
	}
	
	@PostMapping(value = "/author",
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})//C
	public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {
				return authorService.createNewAuthor(author);
	}
	@GetMapping(path = "/author/{id}",  
			produces = {"application/json", "application/xml"})  //R
	public ResponseEntity<Author> getAuthorByID(@PathVariable Integer id) {
		return authorService.getAuthorById(id);
	}
	
	@PutMapping(value = "/author/{id}",
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})//C
	public ResponseEntity<Author> updateAuthor(
			@PathVariable Integer id,
			@Valid @RequestBody Author author) {
		return authorService.updateAuthor(id, author);
	}
	@DeleteMapping(value = "/author/{id}") //D
	public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id){
		return authorService.deleteAuthor(id);
	}




	
}
