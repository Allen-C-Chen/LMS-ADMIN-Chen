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
import com.gcit.training.hibernatejpaapp.entity.Publisher;
import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;
import com.gcit.training.hibernatejpaapp.service.PublisherService;
import com.gcit.training.hibernatejpaapp.entity.Publisher;

@RestController
@RequestMapping(value = "/lms/admin")
public class PublisherAdminController {
	@Autowired
	private PublisherService publisherService;
	// Create a new Note
	@GetMapping(value = "/publishers" ,
			produces = {"application/json", "application/xml"}) 
	public ResponseEntity<List<Publisher>> getAllPublishers() {
		return publisherService.getAllPublishers();
	}
	
	@PostMapping(value = "/publisher",
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})//C
	public ResponseEntity<Publisher> createPublisher(@Valid @RequestBody Publisher publisher) {
				return publisherService.createNewPublisher(publisher);
	}
	@GetMapping(path = "/publisher/{id}",  
			produces = {"application/json", "application/xml"})  //R
	public ResponseEntity<Publisher> getPublisherByID(@PathVariable Integer id) {
		return publisherService.getPublisherById(id);
	}
	
	@PutMapping(value = "/publisher/{id}",
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})//C
	public ResponseEntity<Publisher> updatePublisher(
			@PathVariable Integer id,
			@Valid @RequestBody Publisher publisher) {
		return publisherService.updatePublisher(id, publisher);
	}
	@DeleteMapping(value = "/publisher/{id}") //D
	public ResponseEntity<Publisher> deletePublisher(@PathVariable Integer id){
		return publisherService.deletePublisher(id);
	}




	
}
