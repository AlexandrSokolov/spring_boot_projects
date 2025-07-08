- [Run Kafka consumer](#run-kafka-consumer-via-async-test)
- [Kafka Consumer connection](#kafka-consumer-connection)
- [Multiple Kafka consumer connection URLs](#multiple-kafka-consumer-connection-urls)
- [Consumer identity](#consumer-identity)
- [Consumer key/value single deserializer via configuration](#consumer-keyvalue-single-deserializer)
- [Consumer key/value single deserializer programmatically](#consumer-keyvalue-single-deserializer-programmatically)
- [TODO Consumer key/value multiple deserializers](#consumer-keyvalue-multiple-deserializers)
- [TODO Consumer events filter](#consumer-events-filter)
- [Kafka event metadata](#kafka-event-metadata)
- [Seeking to a Specific Offset](https://docs.spring.io/spring-kafka/reference/kafka/seek.html)

### Run Kafka consumer via async test

Note: 
[`KafkaConsumerIT`](src/test/java/com/example/sb/kafka/consumer/service/KafkaConsumerIT.java) test runs in async mode
with `org.junit.jupiter.api.Timeout` to let you run easily the producer (and other tests). 

### Kafka Consumer connection

This is the key configuration for a consumer to be able to connect. 
Those attributes must be provided by ITO, responsible for producer configuration:

```yaml
app:
  topic-name: "dev-topic"
spring:
  kafka:
    bootstrap-servers: "localhost:9092"
```
[Spring Boot `KafkaConsumer`](src/main/java/com/example/sb/kafka/consumer/service/KafkaConsumer.java)
```java
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
```

### Multiple Kafka consumer connection URLs

```yaml
spring:
  kafka:
    bootstrap-servers: >
      server1.com:9092,
      server2.com:9092,
      server3.com:9092
```

### Consumer identity

```yaml
spring:
  application:
    name: "test-dev-consumer" # used to generate a consumer name
  kafka:
    consumer:
      group-id: "test-consumer-group-id"
      client-id: "test-consumer-client-id"
```

Log output:
```text
[test-dev-consumer] ... [Consumer clientId=test-consumer-client-id-0, groupId=test-consumer-group-id] Cluster ID: 5L6g3nShT-eMCtK--X86sw
```

### Consumer key/value single deserializer

If you don't have multiple deserializers for a key/value, then you could configure them. 
```yaml
spring:
  kafka:
    consumer:
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring:
          json:
            value:
              default:
                type: com.example.sb.kafka.consumer.service.KafkaConsumer$KafkaEvent
            use:
              type:
                headers: "false"
```
In the example above we configure:
- String key deserializer
- JSON value deserializer to convert string into object
- Object value type: `com.example.sb.kafka.consumer.service.KafkaConsumer$KafkaEvent` 
- Ignore producer value type hint with `spring.json.use.type.headers` - 
  producer could use its own types to serialize objects into string. 
  Our consumer is completely decoupled from producer and uses its own type to deserialize string into object.
- 

### Consumer key/value single deserializer programmatically

TODO: example is not fully tested. There is no value type property, no other required properties configured. 

```java
@Configuration
public class KafkaProducerConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private List<String> bootstrapAddresses;

  @Bean
  public ProducerFactory<String, KafkaEvent> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(
      ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
      bootstrapAddresses);
    configProps.put(
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      StringSerializer.class);
    configProps.put(
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, KafkaEvent> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
```

### Consumer key/value multiple deserializers

TODO

### Consumer events filter

TODO

### Kafka event metadata

You could use in the listener:
- `org.springframework.messaging.handler.annotation.Headers` to get all metadata that comes in event
- `org.springframework.messaging.handler.annotation.Header` to get specific metadata only

Example of usage `@Headers` in [`KafkaConsumer`](src/main/java/com/example/sb/kafka/consumer/service/KafkaConsumer.java):
```java
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
```

Example of getting an `offset` header:

```java
  @KafkaListener(topics = "${app.topic-name}")
  public void listen(
    @Payload KafkaEvent kafkaEvent,
    @Header(KafkaHeaders.OFFSET) long offset) {
    System.out.println("Message Message: " + kafkaEvent);
    System.out.println("Message offset: " + offset);
  }
```