package com.example.external.rest.service;


import com.example.external.api.AppConfigRestApi;
import com.example.external.config.AppExternalConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppConfigRestService implements AppConfigRestApi {

  private static final Logger logger = LogManager.getLogger(AppConfigRestService.class.getName());

  @Autowired
  AppExternalConfiguration appExternalConfiguration;

  @Override
  public AppExternalConfiguration config() {
    logger.info("Getting AppExternalConfiguration");
    return appExternalConfiguration;
  }
}
