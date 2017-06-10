package com.fallback;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
public class UserCreateController {
	
	private String userName;
	private String password;
	private RestTemplate restTemplate ;
	
//	public UserCreateController(RestTemplate rest){
//		restTemplate = rest;
//	}
	
	@RequestMapping (value = "/user/{id}/{pwd}")
	@ResponseBody
	public ResponseEntity login(@PathVariable ("id") String userName, @PathVariable ("pwd") String password){
		boolean result = false;
		
		try {
			result = callAvailablity();
		}catch (Exception ex){
			
		}
		return new ResponseEntity("user Created" + result, HttpStatus.CREATED);
	}
	
	@HystrixCommand(fallbackMethod = "reliable")
	public boolean callAvailablity(){
		this.restTemplate = new RestTemplate();
		URI uri = URI.create("http://localhost:8080/available");
		return this.restTemplate.getForObject(uri, boolean.class);
	}
	
	public String reliable(){
		return "Fall back called";
	}
	
//	public UserCreateController(String userName, String password) {
//		super();
//		this.userName = userName;
//		this.password = password;
//	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
