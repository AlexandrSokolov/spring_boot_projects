package com.example.sb.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

  @Value(value = "${app.topic-name}")
  private String topicName;

  public record KafkaEvent(
    String message,
    String name) {}

  @Autowired
  private KafkaTemplate<String, KafkaEvent> kafkaTemplate;

  public void sendMessage(KafkaEvent event) {
    kafkaTemplate.send(topicName, event);
  }
}
