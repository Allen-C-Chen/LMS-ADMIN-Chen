package com.gcit.training.hibernatejpaapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.gcit.training.hibernatejpaapp.dao.PublisherDao;
import com.gcit.training.hibernatejpaapp.entity.Publisher;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PublisherUnitTests {
//	  @Autowired
//	  private PublisherDao publisherDao;
//	  @Test
//	  @Sql("publisherSQL.sql")
//	  public void getAllMethodCheck() {
//		  Publisher publisher = publisherDao.getOne(1);
//		  assertEquals(publisher.toString(), "hi");
//	  }
}	
