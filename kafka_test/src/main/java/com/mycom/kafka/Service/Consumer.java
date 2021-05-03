package com.mycom.kafka.Service;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {
	
	private KafkaConsumer <String, String> consumer = null;
	
//	@Value("${spring.kafka.bootstrap-servers}")
//	private String bootstrapServer;
	
	@Value("${spring.kafka.consumer.key-deserializer}")
	private String keyDeSerializer;
	
	@Value("${spring.kafka.consumer.value-deserializer}")
	private String valueDeSerializer;
	
	@Value("${spring.kafka.template.default-topic}")
	private String topicName;
	
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	
	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String offsetReset;
	
	@Value("${spring.kafka.consumer.max-poll-records}")
	private String maxPollRecords;
	
	@Value("${spring.kafka.consumer.enable-auto-commit}")
	private String enableAutoCommit;
	
	@PostConstruct
	public void build() {
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"localhost:9092, localhost:9093, localhost:9094");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeSerializer);
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeSerializer);
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetReset);
		properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
		properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
		consumer = new KafkaConsumer<>(properties);
	}
	
	@KafkaListener(topics = "pooh", groupId = "xxx")
	public void consume2(@Headers MessageHeaders headers, @Payload String payload) throws IOException {
		log.info("CONSUME HEADERS : " + headers.toString());
		log.info("CONSUME PAYLOAD : " + payload);
	}
	
	@KafkaListener(topics = "exam1", groupId = "foo1")
	public void consume3(String message) throws IOException {
		System.out.println(String.format("Consumed message : %s", message));
	}
	
	@KafkaListener(topics = "exam2", groupId = "foo1")
	public void consume4(String message) throws IOException {
		System.out.println(String.format("Consumed message : %s", message));
	}
//	@KafkaListener(topicPattern = "pooh.*", groupId = "xxx")
//	public void consume(@Headers MessageHeaders headers, @Payload String payload, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws IOException {
//		log.info("CONSUME HEADERS : " + headers.toString());
//		log.info("CONSUME PAYLOAD : " + payload);
//	}
//	
	
//	String tp = "tttt";
//	
//	@Bean
//	public NewTopic myTopic() {
//		return TopicBuilder.name(tp)
//				.build();
//	}
	
//	@KafkaListener(topicPattern = "pooh.*", groupId = "xxx")
//	public void consume(String message) throws IOException {
//		System.out.println(String.format("Consumed message1 : %s", message));
//	}
	
//	@KafkaListener(topicPattern = "pooh.*", groupId = "xxx")
//	public void consume2(String message) throws IOException {
//		System.out.println(String.format("Consumed message2 : %s", message));
//	}
//	
	
}
