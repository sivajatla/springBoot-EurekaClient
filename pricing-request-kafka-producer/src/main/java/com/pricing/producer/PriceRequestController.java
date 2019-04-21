package com.pricing.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public class PriceRequestController {
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	private static String TOPIC = "test";
	private static String TOPIC2 = "test2";
	private static String TOPIC_PRICEREQ_JSON = "TOPIC_PRICEREQ_JSON";
	@GetMapping("/request/{reqPrice}")
	public String requestPrice(@PathVariable("reqPrice") String amount) {
		kafkaTemplate.send(TOPIC,amount);
		return "Requested price pushed to MQ";
	}
	
	@RequestMapping(value = "/priceReqDetails",method=RequestMethod.POST)
	public String requestPrice(@RequestBody PriceRequest priceReq) {
		System.out.println("reqpricejson::"+priceReq.toString());
		kafkaTemplate.send(TOPIC_PRICEREQ_JSON,priceReq.toString());
		return "Requested price pushed to the topic::"+TOPIC_PRICEREQ_JSON;
	}

}
