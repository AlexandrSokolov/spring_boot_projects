package com.example.scopes;

import com.example.scopes.api.ServiceApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.scopes")
public class AppConfig {

  @Bean //not request scope
  public ServiceApi serviceApi() {

    return new ServiceApi() {
      @Override
      public String serviceApiMethod() {
        return "ServiceApi from AppConfig injected";
      }
    };
  }
}
