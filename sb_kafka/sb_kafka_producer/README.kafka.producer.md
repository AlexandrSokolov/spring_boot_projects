- [Run Kafka producer via `KafkaProducerIT` test](src/test/java/com/example/sb/kafka/producer/service/KafkaProducerIT.java)
- [Kafka Producer connection](#kafka-producer-connection)
- [Kafka Producer identity](#producer-identity)
- [Producer key/value single serializer via configuration](#producer-keyvalue-single-serializer)
- [Producer key/value single serializer programmatically](#producer-keyvalue-single-serializer-programmatically)

### Kafka Producer connection

The key configuration for a producer:

```text
app:
  topic-name: "dev-topic"
spring:
  kafka:
    bootstrap-servers: localhost:9092
```
[`KafkaProducer`](src/main/java/com/example/sb/kafka/producer/service/KafkaProducer.java):

```java
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
```

### Producer identity

Setting producer client id via properties does not function:
```yaml
spring:
  application:
    name: "test-dev-producer" # used to generate a producer name
  kafka:
    producer:
      #not working:
      client-id: "someId"
      properties:
        #not working:
        client-id: "someId"
```

The only way to assign an identifier to a producer set `spring.application.name` - `test-dev-producer` in our example.

In the log we'll get:
```text
... [Producer clientId=test-dev-producer-producer-1]
```

### Producer key/value single serializer

If you use the default serializer/deserializer, you could set them via configuration properties:
```yaml
spring:
  kafka:
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
```

### Producer key/value single serializer programmatically

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