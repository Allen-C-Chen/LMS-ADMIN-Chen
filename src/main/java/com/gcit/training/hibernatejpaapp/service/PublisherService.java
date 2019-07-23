package com.gcit.training.hibernatejpaapp.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.training.hibernatejpaapp.dao.PublisherDao;
import com.gcit.training.hibernatejpaapp.entity.Publisher;
@Service
public class PublisherService {
	@Autowired
	private PublisherDao publisherDao; //return 404 if connection fails
	@Transactional
	public ResponseEntity<List<Publisher>> getAllPublishers() {
		List<Publisher> publishers = publisherDao.findAll();
		return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK); 
	}
	@Transactional
	public ResponseEntity<Publisher> createNewPublisher(Publisher newPublisher) {
		try {
			Publisher publisher =  publisherDao.save(newPublisher);
			return new ResponseEntity<Publisher>(publisher, HttpStatus.CREATED); //201
		}
		catch(DataAccessException e){
		    return new ResponseEntity<Publisher>(HttpStatus.BAD_REQUEST); //404
		}
	}
	@Transactional
	public ResponseEntity<Publisher> getPublisherById(int id) {
		try {
			Publisher publisher = publisherDao.findById(id).get();
			return new ResponseEntity<Publisher>(publisher, HttpStatus.OK); 

		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Publisher>( HttpStatus.NOT_FOUND);
		}
	}
	@Transactional
	public ResponseEntity<Publisher> updatePublisher(int id, Publisher newPublisher) {
		try {
			Publisher publisher = publisherDao.findById(id).get();
			publisherDao.save(newPublisher);
			return new ResponseEntity<Publisher>(newPublisher, HttpStatus.OK); 

		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Publisher>( HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Publisher> deletePublisher(int id) {
		try {
			Publisher publisher = publisherDao.findById(id).get();
			publisherDao.delete(publisher);
			return new ResponseEntity<Publisher>(HttpStatus.OK); 
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Publisher>( HttpStatus.NOT_FOUND);
		}
	}
}
