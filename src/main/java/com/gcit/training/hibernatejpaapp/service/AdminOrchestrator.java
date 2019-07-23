package com.gcit.training.hibernatejpaapp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.gcit.training.hibernatejpaapp.entity.Publisher;

public class AdminOrchestrator {
    public static void main(String[] args) {
    	System.out.println("Starting testing");
    	System.out.println(
    	    	getPublishers("http://localhost:8080/lms/admin/publishers")
    			);
    	System.out.println(
    			getPublishersById("http://localhost:8080/lms/admin/publisher/{id}", "1")
    			);
    	
//    	System.out.println(
//    			createPublisher(
//    			"http://localhost:8080/lms/admin/publisher",
//    			"bob1", "addressnum", "phone9")
//    			);
		updatePublisher(
				"http://localhost:8080/lms/admin/publisher/{id}",
    			"2",
    			"bob12", "addressnum2", "phone92"
    			);
    	deletePublisher(
    			"http://localhost:8080/lms/admin/publisher/{id}"
    			,"15");
    	System.out.println("Ending");
    }
	private static String getPublishers(String uri){ //getAllPublishers
	    //final String uri = "http://localhost:8080/lms/admin/publishers";
	    RestTemplate  restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    return result;
	}
	private static Publisher getPublishersById(String uri, String param1)
	{
	    //final String uri = "http://localhost:8080/lms/admin/publisher/{id}";
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", param1);
	    RestTemplate restTemplate = new RestTemplate();
	    Publisher result = restTemplate.getForObject(uri, Publisher.class, params);
	     
	    //System.out.println(result);
	    return result;
	}
	private static Publisher createPublisher(String uri, String name, String address,String phone)
	{
	    //final String uri = "http://localhost:8080/lms/admin/publisher";
	    Publisher newPublisher = new Publisher(name, address, phone);
	    RestTemplate restTemplate = new RestTemplate();
	    Publisher result = restTemplate.postForObject( uri, newPublisher, Publisher.class);
	    //System.out.println(result);
	    return result;
	}
	private static void updatePublisher(String uri, String param1, String name, String address, String phone)
	{
	    //final String uri = "http://localhost:8080/lms/admin/publisher/{id}";
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", param1);
	    int idInt = Integer.parseInt(param1);
	    Publisher newPublisher = new Publisher(idInt, name, address, phone);    
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.put ( uri, newPublisher, params);
	    
	}
	private static void deletePublisher(String uri, String param1)
	{
	    //final String uri = "http://localhost:8080/lms/admin/publisher/{id}";
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", param1);
	     
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete ( uri,  params );
	}
}
