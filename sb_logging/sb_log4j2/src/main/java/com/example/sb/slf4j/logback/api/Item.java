package com.example.sb.slf4j.logback.api;

import java.time.LocalDateTime;

public record Item(String name, LocalDateTime dateTime) {
  public static Item fromName(String name) {
    return new Item(name, LocalDateTime.now());
  }
}
