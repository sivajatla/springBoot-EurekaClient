package com.example.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.http.client.methods.HttpHead;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@RestController
public class EurekaCustomClient {

	@Autowired
    RestTemplate restTemplate;
	
	//@GetMapping
	@RequestMapping(value = "/getCollegeStaff", method = RequestMethod.GET)
	public ResponseEntity<String> getCollegeStaff() {
		System.out.println("inside getCollegeStaff");
		URI url;
		ResponseEntity<String> res = new ResponseEntity<String>(HttpStatus.ACCEPTED);
		try {
			url = new URI("http://SPRING-RS/spring-rs/vrsecstaffmgmt/user");
			res =restTemplate.getForEntity(url, String.class);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@RequestMapping(value = "/invokeCoreSpringRS", method = RequestMethod.GET)
	public ResponseEntity<Staff> invokeCoreSpringRS() {
		System.out.println("inside invokeCoreSpringRS");
		ResponseEntity<Staff> responseEntity = null;
		try {
			String endUrl = "http://SPRING-RS/spring-rs/vrsecstaffmgmt/saveProfileReqJson";
			Staff staff = new Staff("mic1","callign spring-rs");
			org.springframework.http.HttpHeaders reqhead = new org.springframework.http.HttpHeaders();
			reqhead.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
			
			HttpEntity<Staff> reqEntity = new HttpEntity<Staff>(staff,reqhead);
			
			
			responseEntity = restTemplate.exchange(
					endUrl,
	                HttpMethod.POST,
	                reqEntity,
	                Staff.class
	        );
			
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
	            System.out.println("response received");
	            System.out.println(responseEntity.getBody());
	        } else {
	            System.out.println("error occurred");
	            System.out.println(responseEntity.getStatusCode());
	        }
		}
			catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/invokeCoreSpringRSReqBodyJson", method = RequestMethod.GET)
	public ResponseEntity<String> invokeCoreSpringRSReqBodyJson() {
		System.out.println("inside invokeCoreSpringRS");
		ResponseEntity<String> responseEntity = null;
		try {
			String endUrl = "http://SPRING-RS/spring-rs/vrsecstaffmgmt/saveProfileReqJson";
			
			org.springframework.http.HttpHeaders reqhead = new org.springframework.http.HttpHeaders();
			reqhead.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
			
			
			
			
			JSONObject reqJson = new JSONObject();
			reqJson.put("id", new String("eurekhacline"));
			reqJson.put("name", new String("calling spring-rs core"));
			HttpEntity<String> reqEntity = new HttpEntity<>(reqJson.toString(),reqhead);
			
			
			responseEntity = restTemplate.exchange(
					endUrl,
	                HttpMethod.POST,
	                reqEntity,
	                String.class
	        );
			
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
	            System.out.println("response received");
	            System.out.println(responseEntity.getBody());
	            //HashMap<String,String> resMap = (HashMap) responseEntity.getBody();
	            Gson gson = new Gson();
	            Staff resStaff = gson.fromJson(responseEntity.getBody(), Staff.class);
	            //System.out.println("resJson::"+resMap.get("id"));
	            //System.out.println("resentity::"+responseEntity.toString());
	            System.out.println("resStaff.id::"+resStaff.getId());
	            System.out.println("resStaff.name::"+resStaff.getName());
	            
	            
	        } else {
	            System.out.println("error occurred");
	            System.out.println(responseEntity.getStatusCode());
	        }
		}
			catch (JSONException e) {
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/invokeSpringSecurityApp",method = RequestMethod.GET)
	public String invokeSpringSecurityApp() {
		System.out.println("inside invokeSpringSecurityApp");
		ResponseEntity<String> responseEntity = null;
		try {
			String endUrl = "http://spring-security:2222/getWebSiteInfo";
			org.springframework.http.HttpHeaders reqhead = new org.springframework.http.HttpHeaders();
			//reqhead.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
			reqhead.setBasicAuth("spider","web");
			HttpEntity<String> reqEntity = new HttpEntity<>(reqhead);
			responseEntity = restTemplate.exchange(
					endUrl,
	                HttpMethod.GET,
	                reqEntity,
	                String.class
	        );
			System.out.println(responseEntity.getBody());
			} catch (Exception e) {
				e.printStackTrace();
			}
		return responseEntity.getBody();
		}
		
	
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
