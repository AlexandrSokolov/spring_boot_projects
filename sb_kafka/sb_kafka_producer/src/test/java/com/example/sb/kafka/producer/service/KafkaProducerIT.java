package com.example.sb.kafka.producer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KafkaProducerIT {

  @Autowired
  private KafkaProducer kafkaProducer;

  @Test
  public void testPublishing() {
    kafkaProducer.sendMessage(new KafkaProducer.KafkaEvent("Hello", "World"));
  }
}
