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
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;
import com.gcit.training.hibernatejpaapp.service.BookService;
import com.gcit.training.hibernatejpaapp.entity.Book;

@RestController
@RequestMapping(value = "/lms/admin")
public class BookAdminController {
	@Autowired
	private BookService bookService;
	// Create a new Note
	@GetMapping(value = "/books" ,
			produces = {"application/json", "application/xml"}) 
	public ResponseEntity<List<Book>> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@PostMapping(value = "/book",
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})//C
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
				return bookService.createNewBook(book);
	}
	@GetMapping(path = "/book/{id}",  
			produces = {"application/json", "application/xml"})  //R
	public ResponseEntity<Book> getBookByID(@PathVariable Integer id) {
		return bookService.getBookById(id);
	}
	
	@PutMapping(value = "/book/{id}",
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})//C
	public ResponseEntity<Book> updateBook(
			@PathVariable Integer id,
			@Valid @RequestBody Book book) {
		return bookService.updateBook(id, book);
	}
	@DeleteMapping(value = "/book/{id}") //D
	public ResponseEntity<Book> deleteBook(@PathVariable Integer id){
		return bookService.deleteBook(id);
	}




	
}
