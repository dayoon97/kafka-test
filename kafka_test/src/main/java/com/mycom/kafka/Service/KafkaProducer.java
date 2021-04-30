package com.mycom.kafka.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class KafkaProducer {

    //private static final String TOPIC = "${spring.kafka.template.default-topic}";
    
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value(value = "${spring.kafka.template.default-topic}")
    private String topicName;
    
    
    @PostMapping("/kafka")
    public void sendMessage(String message) {
        System.out.println(String.format("Produce message : %s", message));
        this.kafkaTemplate.send(topicName, message);
    }
}
