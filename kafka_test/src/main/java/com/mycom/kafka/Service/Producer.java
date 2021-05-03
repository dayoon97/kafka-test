package com.mycom.kafka.Service;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Producer {

    //private static final String TOPIC = "${spring.kafka.template.default-topic}";
    
	private KafkaProducer<String, String> producer = null;
	
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
	@Value("${spring.kafka.producer.key-serializer}")
	private String keySerializer;
	
	@Value("${spring.kafka.producer.value-serializer}")
	private String valueSerializer;

    @Value(value = "${spring.kafka.template.default-topic}")
    private String topicName;
    

    public void build() {
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"localhost:9092, localhost:9093, localhost:9094");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);

		producer = new KafkaProducer<>(properties);
    }
    
    
    @PostMapping("/kafka")
    public void sendMessage(String message) {
        //System.out.println(String.format("Produce message : %s", message));
        
        if(message.equals("quit")) {
        	log.info("Stop");
        } else {
        	log.info("Produce msg => " + message);
        }
//    	String result = "SEND FAIL";
//    	ProducerRecord<String, String> prd = new ProducerRecord<String, String>(this.topicName, message);
//    	try {
//    		producer.send(prd, new Callback() {
//    			@Override
//    			public void onCompletion(RecordMetadata metadata, Exception exception) {
//    				if(exception != null) {
//    					log.info(exception.getMessage());
//    				}
//    			}
//    		});
//    		result = "SEND SUCCESS";
//    	}
//    	catch(Exception e) {
//    		log.info(e.getMessage());
//    		e.printStackTrace();
//    	}
//    	finally {
//    		log.info(result + " : " + message);
//    		producer.close();
//    	}
    }
}
