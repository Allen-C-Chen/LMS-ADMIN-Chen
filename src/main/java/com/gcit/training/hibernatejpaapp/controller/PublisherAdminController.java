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

import com.gcit.training.hibernatejpaapp.dao.PublisherDao;
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;
import com.gcit.training.hibernatejpaapp.entity.Publisher;

@RestController
@RequestMapping("/lms/admin")
public class PublisherAdminController {

	@Autowired
	private PublisherDao publisherDao;
	
	@GetMapping("/publisher")
	public List<Publisher> getAllPublisher() {
	    return publisherDao.findAll();
	}
	
	// Create a new Note
	@PostMapping("/publisher") //C
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity <Publisher> createPubisher (@Valid @RequestBody Publisher publisher) {
		try {
			if(publisher.getPublisherName() == null || publisher.getPublisherAddress() == null
					|| publisher.getPublisherPhone() == null) {
			    return new ResponseEntity<Publisher>(HttpStatus.BAD_REQUEST); //400
			}
			Publisher newPublisher =  publisherDao.save(publisher);
			return new ResponseEntity<Publisher>(newPublisher, HttpStatus.CREATED); //204
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND); //404
		}
	}
	
	@GetMapping("/publisher/{id}") //R
	public ResponseEntity<Publisher> getPublisherByID(@PathVariable Integer id) {
		Optional<Publisher> publisher = publisherDao.findById(id);
		try {
		    return new ResponseEntity<Publisher>(publisher.get(), HttpStatus.OK); //200
		}
		catch(EmptyResultDataAccessException e){
		    return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND); //404
		}
	}
	@PutMapping("/publisher") //U
	@ResponseStatus(HttpStatus.CREATED)
	public @Valid Publisher  updatePublisher(@Valid @RequestBody Publisher publisher) {
		return publisherDao.save(publisher);		
	}
	
	@DeleteMapping("/publisher/{id}") //D
	public ResponseEntity<Publisher> deletePost(@PathVariable Integer id){
		try {
			publisherDao.deleteById(id);
		    return new ResponseEntity<Publisher>(HttpStatus.NO_CONTENT); //204

		}
		catch(EmptyResultDataAccessException e){
			return new ResponseEntity<Publisher>(HttpStatus.BAD_REQUEST); //404
		}
	}
}
