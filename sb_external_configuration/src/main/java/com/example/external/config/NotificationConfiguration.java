package com.example.external.config;

import java.util.List;

public record NotificationConfiguration (
  Sender sender,
  List<String> recipients
) {
  public record Sender (
    String name,
    String email
  ) {
  }
}
