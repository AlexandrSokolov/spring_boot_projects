package com.example.jakarta_resteasy.api;

import java.time.LocalDateTime;

public record Item(String name, LocalDateTime dateTime) {
  public static Item fromName(String name) {
    return new Item(name, LocalDateTime.now());
  }
}
