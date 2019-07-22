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
import com.gcit.training.hibernatejpaapp.entity.Book;

@RestController
@RequestMapping("/lms/admin")
public class BookAdminController {
	@Autowired
	private BookDao bookDao;
	@GetMapping("/book")
	public List<Book> getAllBooks() {
		return bookDao.findAll();
	}
	// Create a new Note
	@PostMapping("/book") //C
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
		if(book.getTitle() == null || book.getAuthor() == null || book.getPublisher() == null) {
		    return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST); //400
		}
		try {
			Book newBook = bookDao.save(book);
			return new ResponseEntity<Book>(newBook, HttpStatus.CREATED); //204
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<Book>(HttpStatus.NOT_FOUND); //404
		}
	    //return bookDao.save(book);
	}
	@GetMapping("/book/{id}") //R
	public ResponseEntity<Book> getBookByID(@PathVariable Integer id) {
		Optional<Book> book = bookDao.findById(id);

		try {
			return new ResponseEntity<Book>(book.get(), HttpStatus.OK); //200
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<Book>(HttpStatus.NOT_FOUND); //404
		}

		//return bookDao.findById(id);
	}
	@PutMapping("/book") //U
	@ResponseStatus(HttpStatus.CREATED)
	public void updateBook(@Valid @RequestBody Book book) {
		bookDao.save(book);
	}
	@DeleteMapping("/book/{id}") //D
	public ResponseEntity<Book> deleteBook(@PathVariable Integer id) {
		try {
			bookDao.deleteById(id);
		    return new ResponseEntity<Book>(HttpStatus.NO_CONTENT); //204

		}
		catch(EmptyResultDataAccessException e){
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST); //404
		}
	}

	
}
