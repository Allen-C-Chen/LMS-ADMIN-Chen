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
import com.gcit.training.hibernatejpaapp.entity.Author;

@RestController
@RequestMapping("/lms/admin")
public class AuthorController {
	@Autowired
	private AuthorDao authorDao;
	// Create a new Note
	@GetMapping("/author")
	public List<Author> getAllAuthors() {
		return authorDao.findAll();
	}
	
	@PostMapping("/author") //C
	public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {

		try {
			if(author.getAuthorName() == null) {
			    return new ResponseEntity<Author>(HttpStatus.BAD_REQUEST); //400
			}
			Author newAuthor =  authorDao.save(author);

			return new ResponseEntity<Author>(newAuthor, HttpStatus.CREATED); //204
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<Author>(HttpStatus.NOT_FOUND); //404
		}
	}
	
	@GetMapping("/author/{id}") //R
	public ResponseEntity<Author> getAuthorByID(@PathVariable Integer id) {
		Optional<Author> author = authorDao.findById(id);

		try {
			return new ResponseEntity<Author>(author.get(), HttpStatus.OK); 
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<Author>(HttpStatus.NOT_FOUND); //404
		}
	}
	@PutMapping("/author") //U
	@ResponseStatus(HttpStatus.CREATED)
	public void updateAuthor(@Valid @RequestBody Author author) {
		authorDao.save(author);
	}
	
	
	@DeleteMapping("/author/{id}") //D
	public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id) {
		try {
			authorDao.deleteById(id);
		    return new ResponseEntity<Author>(HttpStatus.NO_CONTENT); //204

		}
		catch(EmptyResultDataAccessException e){
			return new ResponseEntity<Author>(HttpStatus.BAD_REQUEST); //404
		}
		
	}
	
}
