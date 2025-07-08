package com.example.sb.kafka.consumer.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaConsumer {

  private static final Logger logger = LogManager.getLogger();

  public record KafkaEvent(
    String message,
    String name) {}

  @KafkaListener(topics = "${app.topic-name}")
  public void listen(
    @Payload KafkaEvent kafkaEvent,
    @Headers Map<String, Object> headers) {
    logger.info(() -> "Message Message: " + kafkaEvent);
    logger.info(() -> "Message Headers: " + headers);
  }

}
