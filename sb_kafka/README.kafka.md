## Apache `Kafka` is a distributed and fault-tolerant stream processing system.

- [Kafka producer](sb_kafka_producer/README.kafka.producer.md)
- [Kafka consumer](sb_kafka_consumer/README.kafka.consumer.md)

### Communication with Kafka

To communicate with Kafka you need to know:
1. topic (we use `dev-topic` in the example)
2. 

### Spring's support of Apache `Kafka` 

`Spring` offers:
- a template programming model with a `KafkaTemplate` and 
- Message-driven POJOs via `@KafkaListener` annotation

### Spring's `Kafka` listener

- `org.springframework.kafka.listener.MessageListenerContainer` – An abstraction used to instantiate 
   Kafka message listener containers
- `org.springframework.kafka.listener.KafkaMessageListenerContainer` – An implementation class used to create 
   a single-threaded message listener container
- `org.springframework.kafka.listener.ConcurrentMessageListenerContainer` – An implementation class used to create 
   one or more KafkaMessageListenerContainers based on concurrency
- `org.springframework.kafka.config.KafkaListenerContainerFactory` – An abstract factory for MessageListenerContainers
- `org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory` – An implementation class used to create 
   a ConcurrentMessageListenerContainer


 
### `spring-kafka` maven dependency

```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

### Topic configuration

Wrong: we always use topic names
Topic names can be defined directly in the `@KafkaListener` annotation or in Spring configuration file.

```yaml
app:
  topic-name: "dev-topic"
spring:
  kafka:
    template:
      default-topic: ${app.topic-name}
```

Topic name is required in Spring `KafkaTemplate.send()` method, 
having a default topic does not help, because you must set it explicitly:
```java

```


### Topic creation 


Topic is created automatically, when you publish a new message. It is not necessary to have `KafkaTopicConfig` class:

```java
@Configuration
public class KafkaTopicConfig {

  @Value(value = "${app.topic-name}")
  private String topicName;

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private List<String> bootstrapAddresses;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddresses);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic newTopic() {
    return new NewTopic(topicName, 1, (short) 1);
  }
}
```