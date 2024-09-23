package com.example.external.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.external")
@EnableConfigurationProperties(AppExternalConfiguration.class)
public class AppDiConfiguration {

}
