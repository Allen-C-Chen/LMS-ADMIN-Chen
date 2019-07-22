package com.gcit.training.hibernatejpaapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcit.training.hibernatejpaapp.entity.BookLoan;
import com.gcit.training.hibernatejpaapp.entity.BookLoanID;

public interface BookLoanDao extends JpaRepository<BookLoan, BookLoanID>{

}
