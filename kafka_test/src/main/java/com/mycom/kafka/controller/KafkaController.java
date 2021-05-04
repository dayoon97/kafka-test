package com.mycom.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.kafka.Service.Consumer;
import com.mycom.kafka.Service.Producer;
import com.mycom.model.Message;

@RestController
public class KafkaController {
	
	 @Autowired
	 private Producer producer;
	 
	 @Autowired
	 private Consumer consumer;

	 @Autowired
	 KafkaController(Producer producer) {
	     this.producer = producer;
	 }

	 @RequestMapping("/kafka")
	 public String sendMessage(@RequestParam String message, @RequestParam Integer age) {
		 producer.produce(new Message(message, age));
	     //this.producer.sendMessage(message);

	     return "here sendMessage = " + message;
	 }
}
