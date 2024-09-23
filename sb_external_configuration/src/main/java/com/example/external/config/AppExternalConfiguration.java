package com.example.external.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties("app")
public record AppExternalConfiguration (
  NotificationConfiguration notification,
  //system name -> auth mappings
  Map<String, AuthConfiguration> systemsAuth,
  List<ListItemConfiguration> listItemConfigurations
){
  public static final String SYSTEM_1_KEY = "system1";
}
