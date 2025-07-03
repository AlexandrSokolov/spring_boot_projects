package com.example.sb.kafka.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaConsumer {

  public record KafkaEvent(
    String message,
    String name) {}

  @KafkaListener(topics = "${app.topic-name}")
  public void listen(
    @Payload KafkaEvent kafkaEvent,
    @Headers Map<String, Object> headers) {
    System.out.println("Message Message: " + kafkaEvent);
    System.out.println("Message Headers: " + headers);
  }

}
