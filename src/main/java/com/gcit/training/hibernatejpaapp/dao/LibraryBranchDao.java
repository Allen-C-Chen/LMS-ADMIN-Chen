package com.gcit.training.hibernatejpaapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcit.training.hibernatejpaapp.entity.LibraryBranch;

public interface LibraryBranchDao extends JpaRepository<LibraryBranch, Integer> {

}
