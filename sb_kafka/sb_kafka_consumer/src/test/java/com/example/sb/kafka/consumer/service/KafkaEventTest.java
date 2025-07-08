package com.example.sb.kafka.consumer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KafkaEventTest {

  public static final String TOPIC = "test-topic";
  public static final String TEST_MESSAGE = "test message";
  public static final String TEST_NAME = "test name";

  @Test
  public void testKafkaEventDeserialization() throws IOException {
    try (var d = new JsonDeserializer<>(KafkaConsumer.KafkaEvent.class)) {
      var event = d.deserialize(
        TOPIC,
        Files.readString(Paths.get("src/test/resources/KafkaEvent.json"))
          .getBytes(StandardCharsets.UTF_8));
      Assertions.assertEquals(KafkaConsumer.KafkaEvent.class, event.getClass());
      Assertions.assertEquals(TEST_MESSAGE, event.message());
      Assertions.assertEquals(TEST_NAME, event.name());
    }
  }
}
