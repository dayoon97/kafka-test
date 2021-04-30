package com.mycom.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.kafka.Service.KafkaProducer;

@RestController
public class KafkaController {
	
	 private final KafkaProducer producer;

	 @Autowired
	 KafkaController(KafkaProducer producer) {
	     this.producer = producer;
	 }

	 @RequestMapping("/kafka")
	 public String sendMessage(@RequestParam("message") String message) {
	     this.producer.sendMessage(message);

	     return "here sendMessage = " + message;
	 }
}
