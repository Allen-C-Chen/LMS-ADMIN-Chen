package com.gcit.training.hibernatejpaapp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.gcit.training.hibernatejpaapp.entity.Publisher;

public class AdminOrchestrator {
    public static void main(String[] args) {
    	getPublishers();
    	getPublishersById();
    	//createPublisher();
    	//updateEmployee();
    	deletePublisher();
    }
	private static void getPublishers(){ //getAllPublishers
	    final String uri = "http://localhost:8080/lms/admin/publishers";
	     
	    RestTemplate  restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	     
	    System.out.println(result);
	}
	private static void getPublishersById()
	{
	    final String uri = "http://localhost:8080/lms/admin/publisher/{id}";
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "1");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    Publisher result = restTemplate.getForObject(uri, Publisher.class, params);
	     
	    System.out.println(result);
	}
	private static void createPublisher()
	{
	    final String uri = "http://localhost:8080/lms/admin/publisher";
	 
	    Publisher newPublisher = new Publisher(10, "Adam", "addrr", "phone.com");
	 
	    RestTemplate restTemplate = new RestTemplate();
	    Publisher result = restTemplate.postForObject( uri, newPublisher, Publisher.class);
	 
	    System.out.println(result);
	}
	private static void updatePublisher()
	{
	    final String uri = "http://localhost:8080/lms/admin/publisher/{id}";
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "2");
	     
	    Publisher newPublisher = new Publisher(10, "Ada2", "ada2", "ada2.com");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.put ( uri, newPublisher, params);
	}
	private static void deletePublisher()
	{
	    final String uri = "http://localhost:8080/lms/admin/publisher/{id}";
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "7");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete ( uri,  params );
	}
}
