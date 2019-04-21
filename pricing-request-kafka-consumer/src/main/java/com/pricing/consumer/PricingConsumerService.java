package com.pricing.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PricingConsumerService {
//	@KafkaListener(topics="test",groupId="group_id")
//	public void consume(String message) {
//		System.out.println("message is "+message);
//	}
	
	@KafkaListener(topics="TOPIC_PRICEREQ_JSON",groupId="pricerequest",
			containerFactory = "priceRequestKafkaListenerContainerFactory")
	public void consumeJson(PriceRequest priceReq) {
		System.out.println("pricereq is "+priceReq.getDesc());
	}

}
