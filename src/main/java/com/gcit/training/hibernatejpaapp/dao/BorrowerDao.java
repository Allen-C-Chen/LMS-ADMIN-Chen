package com.gcit.training.hibernatejpaapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gcit.training.hibernatejpaapp.entity.Borrower;

public interface BorrowerDao extends JpaRepository<Borrower, Integer> {
	
}
