package com.example.sb.kafka.consumer.configs;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.List;

//todo @EnableKafka
@Configuration
public class KafkaConsumerConfig {

//  @Value(value = "${spring.kafka.bootstrap-servers}")
//  private List<String> bootstrapAddresses;
//
//  @Bean
//  public ConsumerFactory<String, KafkaEvent> consumerFactory() {
//    var configProps = new HashMap<String, Object>();
//    configProps.put(
//      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
//      bootstrapAddresses);
//    configProps.put(
//      ConsumerConfig.GROUP_ID_CONFIG,
//      "${spring.kafka.consumer.group-id}");
//
//    return new DefaultKafkaConsumerFactory<>(
//      configProps,
//      new StringDeserializer(),
//      jsonDeserializer());
//  }
//
//  @Bean
//  public ConcurrentKafkaListenerContainerFactory<String, KafkaEvent> kafkaListenerContainerFactory() {
//    var factory = new ConcurrentKafkaListenerContainerFactory<String, KafkaEvent>();
//    factory.setConsumerFactory(consumerFactory());
//    factory.setRecordFilterStrategy(recordFilterStrategy());
//    return factory;
//  }
//
//  @Bean
//  public RecordFilterStrategy<String, KafkaEvent> recordFilterStrategy() {
//    return record -> record.value().name().startsWith("qq");
//  }
//
//  @Bean
//  public JsonDeserializer<KafkaEvent> jsonDeserializer() {
//    return new JsonDeserializer<>(KafkaEvent.class);
//  }
}
