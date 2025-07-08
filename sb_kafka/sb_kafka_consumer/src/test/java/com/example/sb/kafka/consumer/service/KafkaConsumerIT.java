package com.example.sb.kafka.consumer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class KafkaConsumerIT {

  public static final int SLEEP_TIME_SECONDS = 60;

  @Test
  @Timeout(value = SLEEP_TIME_SECONDS, unit = TimeUnit.SECONDS, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
  public void testConsuming() throws InterruptedException {
    TimeUnit.SECONDS.sleep(SLEEP_TIME_SECONDS);
  }
}
