

### Set consumer name

Setting producer client id is not supported via properties:
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

### Set consumer key/value serializer/deserializer

If you use the default serializer/deserializer, you could set them via configuration properties:
```yaml
spring:
  kafka:
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
```

In this case you don't need to create `KafkaProducerConfig`:

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