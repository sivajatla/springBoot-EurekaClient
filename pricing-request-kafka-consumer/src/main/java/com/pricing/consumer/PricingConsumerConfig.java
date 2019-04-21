package com.pricing.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


@EnableKafka
@Configuration
public class PricingConsumerConfig {
	
	public PricingConsumerConfig(){
		
	}
	
	public ConsumerFactory<String, String> consumerFactory() {
		System.out.println("inside consumerFactory");
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }
	
	
	public ConsumerFactory<String, PriceRequest> pricingRequestConsumerFactory() {
		System.out.println("inside pricingRequestConsumerFactory");
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "pricerequest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
   
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),new JsonDeserializer<>(PriceRequest.class));
    }
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, PriceRequest> priceRequestKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PriceRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(pricingRequestConsumerFactory());
        return factory;
    }

}
