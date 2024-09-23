package com.example.external.config;

public record AuthConfiguration (
  String login,
  String password,
  String host
) {
}
