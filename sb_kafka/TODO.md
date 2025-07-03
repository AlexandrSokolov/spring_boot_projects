### 

https://docs.spring.io/spring-kafka/reference/kafka.html

### steps to run kafka, run consumer in background, run consumer

### filter

https://www.baeldung.com/spring-kafka#4-adding-message-filter-for-listeners

### multiple consumers with different filters

### Custom Message Converters

https://www.baeldung.com/spring-kafka#custom-message-converters

### run test in background, explain why

https://www.baeldung.com/java-junit-5-timeout-annotation


### check example applicaiton from spring

https://spring.io/projects/spring-kafka

### @KafkaListener Annotation, opitons

Note set offset at runtime
https://docs.spring.io/spring-kafka/reference/kafka/receiving-messages/listener-annotation.html

At build or config time, see `initialOffset`:
```java
@KafkaListener(
  topicPartitions = @TopicPartition(topic = "topicName",
  partitionOffsets = {
    @PartitionOffset(partition = "0", initialOffset = "0"), 
    @PartitionOffset(partition = "3", initialOffset = "0")}),
  containerFactory = "partitionsKafkaListenerContainerFactory")
public void listenToPartition(
  @Payload String message, 
  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
    System.out.println(
      "Received Message: " + message"
      + "from partition: " + partition);
}
```

### event header information

https://www.baeldung.com/spring-kafka#2-consuming-messages

### consume from specific partition only

https://www.baeldung.com/spring-kafka#3-consuming-messages-from-a-specific-partition

### testing kafka with testcontainers

https://www.baeldung.com/spring-boot-kafka-testing
https://docs.spring.io/spring-kafka/reference/testing.html

### quickstart

https://kafka.apache.org/quickstart

### get event offset to catch messages you skipped while was not active

### native Kafka Java client APIs.