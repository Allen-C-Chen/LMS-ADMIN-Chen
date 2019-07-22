package com.gcit.training.hibernatejpaapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcit.training.hibernatejpaapp.entity.Book;


public interface BookDao extends JpaRepository<Book, Integer> {
	
}
