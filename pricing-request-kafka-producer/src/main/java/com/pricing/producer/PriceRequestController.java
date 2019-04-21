package com.pricing.producer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/price")
public class PriceRequestController {
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	private static String TOPIC_STRING_DATA = "TOPIC_STRING_DATA";
	private static String TOPIC_PRICEREQ_JSON = "TOPIC_PRICEREQ_JSON";
	
	@GetMapping("/request/{reqPrice}")
	public String requestPrice(@PathVariable("reqPrice") String amount) {
		kafkaTemplate.send(TOPIC_STRING_DATA,amount);
		return "Requested price pushed to MQ "+TOPIC_STRING_DATA;
	}
	
	@RequestMapping(value = "/priceReqDetails",method=RequestMethod.POST)
	public String requestPrice(@RequestBody PriceRequest priceReq) {
		System.out.println("reqpricejson::"+priceReq.toString());
		// Creating Object of ObjectMapper define in Jakson Api 
        ObjectMapper Obj = new ObjectMapper(); 
        try { 
            // get Oraganisation object as a json string 
            String jsonStr = Obj.writeValueAsString(priceReq); 
            // Displaying JSON String 
            System.out.println(jsonStr);
            kafkaTemplate.send(TOPIC_PRICEREQ_JSON,jsonStr);
        } 
  
        catch (IOException e) { 
            e.printStackTrace(); 
        } 
			
		return "Requested price pushed to the topic::"+TOPIC_PRICEREQ_JSON;
	}
}
